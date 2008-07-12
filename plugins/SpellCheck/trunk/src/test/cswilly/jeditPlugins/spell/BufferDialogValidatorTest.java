/*
* $Revision$
* $Date$
* $Author$
*
* Copyright (C) 2008 Eric Le Lay
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

package cswilly.jeditPlugins.spell;


//{{{ Imports

import java.io.*;
import java.util.concurrent.atomic.*;
import javax.swing.*;
import java.util.*;

//{{{ 	jEdit
import org.gjt.sp.jedit.*;

//}}}

//{{{	junit
import org.junit.*;
import static org.junit.Assert.*;
//}}}

//{{{	FEST...
import org.fest.swing.fixture.*;
import org.fest.swing.core.*;
import org.fest.swing.finder.WindowFinder;
//}}}

///}}}

import cswilly.spell.ValidationDialog;
import cswilly.spell.SpellException;
import cswilly.spell.Result;
import cswilly.spell.WordListValidator;

import static cswilly.jeditPlugins.spell.TestUtils.*;


/**
 * Test the functions of BufferDialogValidator
 *	- test the validation dialog UI and results
 *	- test with multiple buffers in the same view (test not working yet)
 */
public class BufferDialogValidatorTest{
	private static String testsDir;
	private static String exePath;
	
	@BeforeClass
	public static void setUpjEdit(){
		testsDir = System.getProperty(ENV_TESTS_DIR);
		assertTrue("Forgot to set env. variable '"+ENV_TESTS_DIR+"'",testsDir!=null);
		exePath = System.getProperty(ENV_ASPELL_EXE);
		assertTrue("Forgot to set env. variable '"+ENV_ASPELL_EXE+"'",exePath!=null);

		TestUtils.setUpjEdit();
	}

	@AfterClass
	public static  void tearDownjEdit(){
		TestUtils.tearDownjEdit();
	}

	@Before
	public void beforeTest(){
		jEdit.getPlugin(SpellCheckPlugin.class.getName()).getPluginJAR().activatePluginIfNecessary();
	}

	@After
	public void afterTest(){
		jEdit.getPlugin(SpellCheckPlugin.class.getName()).getPluginJAR().deactivatePlugin(false);
	}

	
	@Test
	public void testDialog(){
		final BufferDialogValidator valid = new BufferDialogValidator();

		
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		try{
		SwingUtilities.invokeAndWait(
			new Runnable(){
				public void run(){
					jEdit.newFile(view);
				}
			});
		}catch(Exception e){
			System.err.println(e);
		}
	
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		
		final Buffer buffer = view.getBuffer();
		buffer.insert(0,"The qwick brown foxe");
		final String oldText = buffer.getText(0,buffer.getLength());
		
		valid.start();
		valid.setTextArea(view.getTextArea());
		final List<Result> res = new LinkedList<Result>();
		res.add(new Result(1,Result.OK,null,"The"));
		res.add(new Result(5,Result.SUGGESTION,Arrays.asList(new String[]{"wick","quick","Vick"}),"qwick"));
		res.add(new Result(11,Result.OK,null,"brown"));
		res.add(new Result(17,Result.SUGGESTION,Arrays.asList(new String[]{"Fox","fox","Foxes"}),"foxe"));
		
		List<Result> oldRes =  new ArrayList<Result>(res);
		final AtomicReference<Boolean> cancel = new AtomicReference<Boolean>(Boolean.FALSE);
		Thread spellThread = new Thread(){
			public void run(){
				cancel.set(!valid.validate(0,oldText,res));
			}
		};
		spellThread.start();
		
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(10000).using(TestUtils.robot());
		
		spellDialog.textBox("originalWord").requireText("foxe");
		spellDialog.textBox("changeTo").requireText("Fox");
		
		spellDialog.list().selectItem("fox");
		spellDialog.textBox("changeTo").requireText("fox");
		spellDialog.list().selectItem("Fox");
		spellDialog.textBox("changeTo").requireText("Fox");

		spellDialog.button("Change").click();
		try{Thread.sleep(2000);}catch(InterruptedException ie){}
		spellDialog.list().selectItem("quick");
		spellDialog.button("Change").click();
		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
		
		assertTrue(!cancel.get());
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());

		assertEquals(2,res.size());
		for(int i=0;i<res.size();i++){
			assertEquals(Result.SUGGESTION,res.get(i).getType());
			assertEquals(1,res.get(i).getSuggestions().size());
		}
		valid.done();

		TestUtils.close(view,buffer);
	}

	@Test
	public void testNullDictIgnoreAll(){
		final BufferDialogValidator valid = new BufferDialogValidator();

		
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		try{
		SwingUtilities.invokeAndWait(
			new Runnable(){
				public void run(){
					jEdit.newFile(view);
				}
			});
		}catch(Exception e){
			System.err.println(e);
		}
	
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		
		final Buffer buffer = view.getBuffer();
		buffer.insert(0,"The qwick qwick foxe");
		final String oldText = buffer.getText(0,buffer.getLength());
		
		valid.setUserDictionary(null);
		valid.setIgnoreAll(null);
		valid.start();
		valid.setTextArea(view.getTextArea());
		final List<Result> res = new LinkedList<Result>();
		res.add(new Result(1,Result.OK,null,"The"));
		res.add(new Result(5,Result.SUGGESTION,Arrays.asList(new String[]{"wick","quick","Vick"}),"qwick"));
		res.add(new Result(11,Result.SUGGESTION,Arrays.asList(new String[]{"wick","quick","Vick"}),"qwick"));
		res.add(new Result(17,Result.SUGGESTION,Arrays.asList(new String[]{"Fox","fox","Foxes"}),"foxe"));
		
		List<Result> oldRes =  new ArrayList<Result>(res);
		final AtomicReference<Boolean> cancel = new AtomicReference<Boolean>(Boolean.FALSE);
		Thread spellThread = new Thread(){
			public void run(){
				cancel.set(!valid.validate(0,oldText,res));
			}
		};
		spellThread.start();
		
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(10000).using(TestUtils.robot());

		spellDialog.textBox("originalWord").requireText("foxe");
		spellDialog.button("Change").click();
		try{Thread.sleep(2000);}catch(InterruptedException ie){}

		spellDialog.textBox("originalWord").requireText("qwick");
		spellDialog.button("Add").click();
		try{Thread.sleep(2000);}catch(InterruptedException ie){}

		spellDialog.textBox("originalWord").requireText("qwick");
		spellDialog.button("Ignore All").click();
		try{Thread.sleep(2000);}catch(InterruptedException ie){}

		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
		
		assertTrue(!cancel.get());
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());

		assertEquals(1,res.size());
		valid.done();
		SwingUtilities.invokeLater(
		new Runnable(){
			public void run(){
			jEdit.closeBuffer(view,buffer);
			}
		});
		try{Thread.sleep(2000);}catch(InterruptedException ie){}
		TestUtils.findDialogByTitle("File Not Saved").button(AbstractButtonTextMatcher.withText(JButton.class,"Non")).click();
	}
	
	@Test
	public void testUserDict(){
		final BufferDialogValidator valid = new BufferDialogValidator();

		
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		try{
		SwingUtilities.invokeAndWait(
			new Runnable(){
				public void run(){
					jEdit.newFile(view);
				}
			});
		}catch(Exception e){
			System.err.println(e);
		}
	
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		
		final Buffer buffer = view.getBuffer();
		buffer.insert(0,"The qwick qwick foxe");
		final String oldText = buffer.getText(0,buffer.getLength());
		WordListValidator wl = new WordListValidator();
		wl.addWord("qwick");
		valid.setUserDictionary(wl);
		valid.setIgnoreAll(null);
		valid.start();
		valid.setTextArea(view.getTextArea());
		final List<Result> res = new LinkedList<Result>();
		res.add(new Result(1,Result.OK,null,"The"));
		res.add(new Result(5,Result.SUGGESTION,Arrays.asList(new String[]{"wick","quick","Vick"}),"qwick"));
		res.add(new Result(11,Result.SUGGESTION,Arrays.asList(new String[]{"wick","quick","Vick"}),"qwick"));
		res.add(new Result(17,Result.SUGGESTION,Arrays.asList(new String[]{"Fox","fox","Foxes"}),"foxe"));
		
		List<Result> oldRes =  new ArrayList<Result>(res);
		final AtomicReference<Boolean> cancel = new AtomicReference<Boolean>(Boolean.FALSE);
		Thread spellThread = new Thread(){
			public void run(){
				cancel.set(!valid.validate(0,oldText,res));
			}
		};
		spellThread.start();
		
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(10000).using(TestUtils.robot());

		spellDialog.textBox("originalWord").requireText("foxe");
		spellDialog.button("Add").click();
		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
		
		assertTrue(!cancel.get());
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());

		assertEquals(0,res.size());
		valid.done();
		assertEquals(2,wl.getAllWords().size());
		
		TestUtils.close(view,buffer);
	}
}
