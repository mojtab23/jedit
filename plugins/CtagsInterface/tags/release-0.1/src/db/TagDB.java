package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.gjt.sp.jedit.jEdit;

import ctags.Tag;

public class TagDB {
	private Connection conn;
	private Set<String> columns;
	// Column types
	public static final String IDENTITY_TYPE = "IDENTITY";
	public static final String VARCHAR_TYPE = "VARCHAR";
	public static final String INTEGER_TYPE = "INTEGER";
	// Tags table
	public static final String TAGS_TABLE = "TAGS";
	public static final String TAGS_NAME = "NAME";
	public static final String TAGS_FILE_ID = "FILE_ID";
	public static final String TAGS_PATTERN = "PATTERN";
	public static final String TAGS_EXTENSION_PREFIX = "A_";
	public static final String TAGS_LINE = "A_LINE";
	// Files table
	public static final String FILES_TABLE = "FILES";
	public static final String FILES_ID = "ID";
	public static final String FILES_NAME = "FILE";
	// Origins table
	public static final String ORIGINS_TABLE = "ORIGINS";
	public static final String ORIGINS_ID = "ID";
	public static final String ORIGINS_NAME = "NAME";
	public static final String ORIGINS_TYPE = "TYPE";
	// Files/Origin map table
	public static final String MAP_TABLE = "MAP";
	public static final String MAP_FILE_ID = "FILE_ID";
	public static final String MAP_ORIGIN_ID = "ORIGIN_ID";
	// Origin types
	public static final int TEMP_ORIGIN_INDEX = 0;
	public static final String TEMP_ORIGIN_NAME = "Temp";
	public static final String TEMP_ORIGIN = "Temp";
	public static final String PROJECT_ORIGIN = "Project";
	public static final String DIR_ORIGIN = "Dir";

	public TagDB() {
		removeStaleLock();
        try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:file:" +
					getDBFilePath(), "sa", "");
        } catch (final Exception e) {
			e.printStackTrace();
		}
		createTables();
		columns = getColumns();
	}

	// Check if a source file is in the DB
	public boolean hasSourceFile(String file) {
		return tableColumnContainsValue(FILES_TABLE, FILES_NAME, file);
	}
	
	// Returns the ID of a source file, or (-1) if source file not in DB
	public int getSourceFileID(String file) {
		return queryInteger(FILES_ID, "SELECT " + FILES_ID + " FROM " + FILES_TABLE +
			" WHERE " + FILES_NAME + "=" + quote(file), -1);
	}
	
	// Inserts a source file to the DB
	public void insertSourceFile(String file) {
		try {
			update("INSERT INTO " + FILES_TABLE + " (" + FILES_NAME +
				") VALUES (" + quote(file) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Inserts a source file -> origin mapping to the DB
	public void insertSourceFileOrigin(int fileId, int originId) {
		try {
			Query q = new Query("*", MAP_TABLE, MAP_FILE_ID + "=" + quote(fileId));
			q.addCondition(MAP_ORIGIN_ID + "=" + quote(originId));
			ResultSet rs = query(q);
			if (rs.next())
				return;
			update("INSERT INTO " + MAP_TABLE + " (" + MAP_FILE_ID + "," + MAP_ORIGIN_ID +
				") VALUES (" + quote(fileId) + "," + quote(originId) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Delete tags from source file
	public void deleteTagsFromSourceFile(int fileId) {
		if (fileId < 0)
			return;
		deleteRowsWithValue(TAGS_TABLE, TAGS_FILE_ID, Integer.valueOf(fileId));
	}
	
	/*
	 * Insert a tag into the DB with a given file ID.
	 * If file ID is negative, the ID is looked up in the FILES table. If the file
	 * is not in the table, it is inserted.
	 */
	public void insertTag(Tag t, int fileId) {
		if (fileId < 0) {
			System.err.println("insertTag called with fileId=-1");
			return;
		}
		// Find missing columns and build the inserted value string
		StringBuffer valueStr = new StringBuffer();
		StringBuffer columnStr = new StringBuffer();
		Iterator<String> it = t.getExtensions().iterator();
		while (it.hasNext()) {
			String extension = it.next();
			String col = extension2column(extension.toUpperCase());
			String val = t.getExtension(extension);
			if (! columns.contains(col)) {
				try {
					update("ALTER TABLE " + TAGS_TABLE + " ADD " + col + " " +
						VARCHAR_TYPE + ";");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				columns.add(col);
			}
			if (columnStr.length() > 0)
				columnStr.append(",");
			columnStr.append(col);
			if (valueStr.length() > 0)
				valueStr.append(",");
			valueStr.append(quote(val));
		}
		// Insert the record
		try {
			update("INSERT INTO " + TAGS_TABLE + " (" + TAGS_NAME + "," +
				TAGS_PATTERN + "," + TAGS_FILE_ID + "," + 
				columnStr.toString() + ") VALUES (" +
				quote(t.getName()) + "," +
				quote(t.getPattern()) + "," +
				fileId + "," + valueStr.toString() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Returns a basic tag query, to fill in conditions
	public Query getBasicTagQuery() {
		Query q = new Query();
		q.addColumn(field(TAGS_TABLE, "*"));
		q.addColumn(field(FILES_TABLE, FILES_NAME));
		q.addTable(TAGS_TABLE);
		q.addTable(FILES_TABLE);
		q.addCondition(field(TAGS_TABLE, TAGS_FILE_ID) + "=" + field(FILES_TABLE, FILES_ID));
		return q;
	}
	
	// Returns a query for a tag name
	public Query getTagNameQuery(String tag) {
		Query q = getBasicTagQuery();
		q.addCondition(field(TAGS_TABLE, TAGS_NAME) + "=" + quote(tag));
		return q;
	}
	// Returns a query for a tag name in a specified project
	private Query getTagInProjectQuery(String tag, String project) {
		Query projectQuery = new Query(ORIGINS_ID, ORIGINS_TABLE, ORIGINS_NAME + "=" +
			quote(project));
		projectQuery.addCondition(ORIGINS_TYPE + "=" + quote(PROJECT_ORIGIN));
		
		Query projectFilesQuery = new Query();
		projectFilesQuery.setColumn(MAP_FILE_ID);
		projectFilesQuery.setTable(MAP_TABLE);
		projectFilesQuery.addCondition(field(
			MAP_TABLE, MAP_FILE_ID) + "=" + field(FILES_TABLE, FILES_ID));
		projectFilesQuery.addCondition(field(
			MAP_TABLE, MAP_ORIGIN_ID) + "=(" + projectQuery.toString() + ")");
		
		Query q = new Query();
		q.addColumn("*");
		q.addTable(TAGS_TABLE);
		q.addTable(FILES_TABLE);
		q.addCondition(field(TAGS_TABLE, TAGS_NAME) + "=" + quote(tag));
		q.addCondition(field(TAGS_TABLE, TAGS_FILE_ID) + "=" + field(FILES_TABLE, FILES_ID));
		q.addCondition("EXISTS (" + projectFilesQuery.toString() + ")");
		return q;
	}
	// Runs a query for the specified tag name
	public ResultSet queryTag(String tag) throws SQLException {
		return query(getTagNameQuery(tag));
	}
	// Runs a query for the specified tag name in the specified project
	public ResultSet queryTagInProject(String tag, String project) throws SQLException {
		Query q = getTagInProjectQuery(tag, project);
		return query(q);
	}
	// Returns the ID of an origin
	public int getOriginID(String type, String name) {
		return queryInteger(ORIGINS_ID, "SELECT " + ORIGINS_ID + " FROM " + ORIGINS_TABLE +
			" WHERE " + ORIGINS_TYPE + "=" + quote(type) + " AND " + ORIGINS_NAME + "=" +
			quote(name), -1);
	}
	
	// Inserts a new origin to the DB
	public void insertOrigin(String type, String name) throws SQLException {
		update("INSERT INTO " + ORIGINS_TABLE + " (" +
			ORIGINS_TYPE + "," + ORIGINS_NAME + ") VALUES (" +
			quote(type) + "," + quote(name) + ")");
	}

	// Delete all data associated with the specified origin
	public void deleteOriginAssociatedData(String type, String name) throws SQLException {
		int originId = getOriginID(type, name); 
		if (originId < 0)
			return;
		// Remove all mappings to the origin
		query("DELETE FROM " + MAP_TABLE + " WHERE " + MAP_ORIGIN_ID + "=" +
			quote(originId));
		// Remove all orphaned (with no origin) files
		query("DELETE FROM " + FILES_TABLE + " WHERE NOT EXISTS " +
			"(SELECT " + MAP_FILE_ID + " FROM " + MAP_TABLE + " WHERE " +
			MAP_FILE_ID + "=" + FILES_ID + ")");
		// Remove all orphaned (with no file) tags
		query("DELETE FROM " + TAGS_TABLE + " WHERE NOT EXISTS " +
			"(SELECT " + FILES_ID + " FROM " + FILES_TABLE + " WHERE " +
			FILES_ID + "=" + TAGS_FILE_ID + ")");
	}
	
	// Deletes an origin and all its associated data from the DB
	public void deleteOrigin(String type, String name) throws SQLException {
		deleteOriginAssociatedData(type, name); 
		query("DELETE FROM " + ORIGINS_TABLE + " WHERE " +
			ORIGINS_TYPE + "=" + quote(type) + " AND " +
			ORIGINS_NAME + "=" + quote(name));
	}
	
	private String field(String table, String column) {
		return table + "." + column;
	}

	/*
	 * Check if the table contains a row with the specified value in the specified column.
	 * Used for checking if a buffer is in the DB. 
	 */
	private boolean tableColumnContainsValue(String table, String column, Object value) {
		try {
			Query q = new Query("TOP 1 " + column, table, column + "=" + quote(value));
			ResultSet rs = query(q);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Runs a query and returns an integer value from the specified column of the first
	 * row in the query result, or defaultValue if the query result is empty.
	 */
	public int queryInteger(String column, String query, int defaultValue) {
		try {
			ResultSet rs = query(query);
			if (! rs.next())
				return defaultValue;
			return rs.getInt(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	/*
	 * Converts query results to tags.
	 */
	public Vector<Tag> getResultSetTags(ResultSet rs) {
		Vector<Tag> tags = new Vector<Tag>();
		try {
			ResultSetMetaData meta;
			meta = rs.getMetaData();
			String [] cols = new String[meta.getColumnCount()];
			int [] types = new int[meta.getColumnCount()];
			for (int i = 0; i < cols.length; i++) {
				cols[i] = meta.getColumnName(i + 1);
				types[i] = meta.getColumnType(i + 1);
			}
			while (rs.next()) {
				Tag t = new Tag(rs.getString(TAGS_NAME),
					rs.getString(FILES_NAME), rs.getString(TAGS_PATTERN));
				Hashtable<String, String> extensions = new Hashtable<String, String>();
				Hashtable<String, String> attachments = new Hashtable<String, String>();
				for (int i = 0; i < cols.length; i++) {
					if (types[i] != Types.VARCHAR)
						continue;
					String value = rs.getString(i + 1); 
					if (value != null) {
						if (isExtensionColumn(cols[i]))
							extensions.put(column2extension(cols[i]), value);
						else
							attachments.put(cols[i], value);
					}
				}
				t.setExtensions(extensions);
				t.setAttachments(attachments);
				tags.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tags;
	}
	
	public Vector<String> getOrigins(String type) {
		return queryStringList(ORIGINS_NAME,
			"SELECT * FROM " + ORIGINS_TABLE + " WHERE " +
			ORIGINS_TYPE + "=" + quote(type));
	}
	
	public synchronized void update(String expression) throws SQLException {
		Statement st = conn.createStatement();
		try {
			if (st.executeUpdate(expression) == -1)
	            System.err.println("db error : " + expression);
		} catch (SQLException e) {
			System.err.println("SQL update: " + expression);
			throw e;
		}
        st.close();
    }
	
	public synchronized ResultSet query(Query query) throws SQLException {
		return query(query.toString());
	}
	public synchronized ResultSet query(String expression) throws SQLException {
		try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(expression);
	        st.close();
	        return rs;
		}
		catch (SQLException e) {
			System.err.println("Failed query: " + expression);
			throw e;
		}
    }
	
	public Vector<String> queryStringList(String column, String query) {
		Vector<String> values = new Vector<String>();
		try {
			ResultSet rs = query(query);
			while (rs.next())
				values.add(rs.getString(column));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return values;
	}
	
	public void shutdown()
	{
		try {
			Statement st = conn.createStatement();
	        st.execute("SHUTDOWN");
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String quote(Object value) {
		if (value instanceof String)
			return "'" + ((String)value).replaceAll("'", "''") + "'";
		return value.toString();
	}
	
	public void deleteRowsWithValue(String table, String column, Object value) {
		try {
			query("DELETE FROM " + table + " WHERE " + column + "=" + quote(value));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static public String extension2column(String extensionName) {
		return TAGS_EXTENSION_PREFIX + extensionName.toUpperCase();
	}
	static public String column2extension(String columnName) {
		return columnName.substring(TAGS_EXTENSION_PREFIX.length()).toLowerCase();
	}
	static public boolean isExtensionColumn(String columnName) {
		return columnName.startsWith(TAGS_EXTENSION_PREFIX);
	}
	
	public HashSet<String> getColumns() {
		HashSet<String> columnNames = new HashSet<String>();
		try {
			Query q = new Query("*", TAGS_TABLE, TAGS_NAME + "=''");
			ResultSet rs = query(q);
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			for (int i = 0; i < cols; i++)
				columnNames.add(meta.getColumnName(i + 1));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return columnNames;
	}
	
	private String getDBFilePath() {
		return jEdit.getSettingsDirectory() + "/CtagsInterface/tagdb";
	}

	private void removeStaleLock() {
		File lock = new File(getDBFilePath() + ".lck");
		if (lock.exists())
			lock.delete();
	}

	public void createIndex(String index, String table, String column)
	throws SQLException {
		update("CREATE INDEX " + index + " ON " + table + "(" + column + ")");
	}
	
	private void createTable(String table, String [] columns)
	throws SQLException {
		StringBuffer st = new StringBuffer("CREATE CACHED TABLE ");
		st.append(table);
		st.append("(");
		for (int i = 0; i < columns.length; i += 2) {
			if (i > 0)
				st.append(", ");
			st.append(columns[i]);
			st.append(" ");
			st.append(columns[i + 1]);
		}
		st.append(")");
		update(st.toString());
	}

	private void createTables() {
		try {
			// Create Tags table
			createTable(TAGS_TABLE, new String [] {
				TAGS_NAME, VARCHAR_TYPE,
				TAGS_FILE_ID, INTEGER_TYPE,
				TAGS_PATTERN, VARCHAR_TYPE
			});
			createIndex("TAGS_NAME", TAGS_TABLE, TAGS_NAME);
			createIndex("TAGS_FILE", TAGS_TABLE, TAGS_FILE_ID);
			// Create Files table
			createTable(FILES_TABLE, new String [] {
				FILES_ID, IDENTITY_TYPE,
				FILES_NAME, VARCHAR_TYPE
			});
			createIndex("FILES_NAME", FILES_TABLE, FILES_NAME);
			// Create Origins table
			createTable(ORIGINS_TABLE, new String [] {
				ORIGINS_ID, IDENTITY_TYPE,
				ORIGINS_NAME, VARCHAR_TYPE,
				ORIGINS_TYPE, VARCHAR_TYPE
			});
			update("INSERT INTO " + ORIGINS_TABLE + " (" + ORIGINS_ID + "," +
				ORIGINS_NAME + "," + ORIGINS_TYPE + ") VALUES (" + TEMP_ORIGIN_INDEX +
				"," + quote(TEMP_ORIGIN_NAME) + ", " + quote(TEMP_ORIGIN) + ")");
			// Create Map table
			createTable(MAP_TABLE, new String [] {
				MAP_FILE_ID, INTEGER_TYPE,
				MAP_ORIGIN_ID, INTEGER_TYPE
			});
			createIndex("MAP_FILE_ID", MAP_TABLE, MAP_FILE_ID);
			createIndex("MAP_ORIGIN_ID", MAP_TABLE, MAP_ORIGIN_ID);
		} catch (SQLException e) {
			// Table already exists
		}
	}
	
}
