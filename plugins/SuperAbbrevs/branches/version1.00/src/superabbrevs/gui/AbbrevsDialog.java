/*
 * AbbrevsDialog.java
 *
 * Created on 10. juni 2007, 19:34
 */
package superabbrevs.gui;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.EnhancedDialog;
import superabbrevs.AbbrevsOptionPaneController;
import superabbrevs.SuperAbbrevsPlugin;

/**
 *
 * @author  Sune Simonsen
 */
public class AbbrevsDialog extends EnhancedDialog {

    private AbbrevsEditorPane abbrevsOptionPane;

    /** Creates new form AbbrevsDialog */
    public AbbrevsDialog(View view, boolean modal, AbbrevsOptionPaneController controller) {
        super(view, "Abbreviations", modal);

        initComponents();

        Properties p = loadWindowState();
        
        if (p != null) {
            int height = new Integer(p.getProperty("AbbrevsDialog.height")).intValue();
            int width = new Integer(p.getProperty("AbbrevsDialog.width")).intValue();
            int x = new Integer(p.getProperty("AbbrevsDialog.x")).intValue();
            int y = new Integer(p.getProperty("AbbrevsDialog.y")).intValue();
            
            this.setBounds(x, y, width, height); 
        } else {
            this.setLocationRelativeTo(view);
        }

        setEnterEnabled(false);
        abbrevsOptionPane = new AbbrevsEditorPane(controller);
        abbrevsOptionPane.setVisible(true);
        setContentPane(abbrevsOptionPane);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Abbreviation editor");
        setName("abbrevsDialog"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 822, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 548, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void ok() {
    }

    public void cancel() {
        try {
            abbrevsOptionPane.save();
            saveWindowState();
            dispose();
        } catch (ValidationException ex) {
        }
    }

    private Properties loadWindowState() {
        Properties p = new Properties();
        InputStream in = SuperAbbrevsPlugin.getResourceAsStream(
                SuperAbbrevsPlugin.class, "AbbrevsDialog.properties");
        try {    
            p.load(in);
            return p;
        } catch (Exception ex) {
            return null;
        } finally {
            try { in.close(); } catch (Exception ex) {}
        }
    }

    private void saveWindowState() {
        Properties p = new Properties();
        p.setProperty("AbbrevsDialog.height", "" + this.getHeight());
        p.setProperty("AbbrevsDialog.width", "" + this.getWidth());
        p.setProperty("AbbrevsDialog.x", "" + this.getX());
        p.setProperty("AbbrevsDialog.y", "" + this.getY());
        //p.setProperty("AbbrevsDialog.splitter", "" + abbrevsOptionPane.);

        OutputStream out = SuperAbbrevsPlugin.getResourceAsOutputStream(
                SuperAbbrevsPlugin.class, "AbbrevsDialog.properties");

        try {
            p.store(out, "Saving the window state");
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(this, ex.getMessage(), "Exception",
                    JOptionPane.ERROR);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }
}
