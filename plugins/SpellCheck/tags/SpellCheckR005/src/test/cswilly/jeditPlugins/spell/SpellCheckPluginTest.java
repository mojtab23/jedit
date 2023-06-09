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

//{{{ 	Java Classpath
import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.concurrent.atomic.AtomicReference;

//}}}

//{{{ 	jEdit
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.options.PluginOptions;
import org.gjt.sp.jedit.gui.EnhancedDialog;
import org.gjt.sp.jedit.textarea.Selection;

//}}}

//{{{	junit
//annotations
import org.junit.*;
//usual classes
    import static org.junit.Assert.*;
//}}}

//{{{	FEST...
import org.fest.swing.fixture.*;
import org.fest.swing.core.*;
import org.fest.swing.finder.WindowFinder;
//}}}

import cswilly.spell.ValidationDialog;
import cswilly.spell.SpellException;


//{{{	FEST...
import errorlist.ErrorSource;
import errorlist.ErrorSourceUpdate;
//}}}
///}}}

import static cswilly.jeditPlugins.spell.TestUtils.*;

/**
 * test high-level functions of the plugin:
 *	- Option Pane
 *  - Spell-check on save
 *	- interactive spell-checking
 *  - stop()
 *  - spellCheck with lang...
 *  - set buffer's language
 */
public class SpellCheckPluginTest
{	

	@BeforeClass
	public  static void setUpjEdit(){
		TestUtils.setUpjEdit();
	}

	@AfterClass
	public static void tearDownjEdit(){
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
	public void testOptions(){
		
		TestUtils.jeditFrame().menuItemWithPath("Plugins","Plugin Options...").select();
		
		DialogFixture optionsDialog = WindowFinder.findDialog(PluginOptions.class).withTimeout(5000).using(TestUtils.robot());
			
		optionsDialog.tree().selectPath(new TreePath(new String[]{"Plugins","Spell Check"}));
		
		JPanelFixture pane = optionsDialog.panel(new GenericTypeMatcher<SpellCheckOptionPane>(){
			@Override protected boolean isMatching(SpellCheckOptionPane ignored) {
				return true;
			}
		});
		
		optionsDialog.close();
		
		//don't do that, as it quits the VM
		//TestUtils.jeditFrame().menuItem(AbstractButtonTextMatcher.withText(JMenuItem.class,"Exit")).select();
		// try{
		// 	runJeditThread.join(10000);
		// }catch(InterruptedException ie){
		// 	fail("don't interrupt me !");
		// }
		// assertTrue(!runJeditThread.isAlive());
	}

	@Test
	public void testInteractiveSpellCheck(){
		String exePath = System.getProperty(ENV_ASPELL_EXE);
		assertTrue("Forgot to set env. variable '"+ENV_ASPELL_EXE+"'",exePath!=null);
		jEdit.setProperty(SpellCheckPlugin.ASPELL_EXE_PROP,exePath);
		jEdit.setProperty(SpellCheckPlugin.ASPELL_LANG_PROP,"en");
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		jEdit.newFile(view);
		final Buffer buff = view.getBuffer();
		buff.insert(0,"The quick brown foxe");
		Thread spellThread = new Thread(){
			public void run(){
				SpellCheckPlugin.checkBuffer(view,buff);
			}
		};
		spellThread.start();
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(5000).using(TestUtils.robot());
		spellDialog.list().selectItem("fox");
		spellDialog.button("Change").click();
		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());
		assertEquals("The quick brown fox",buff.getText(0,buff.getLength()));
	}
	
	@Test
	public void testShowCustomLangSpellDialog(){
		String exePath = System.getProperty(ENV_ASPELL_EXE);
		assertTrue("Forgot to set env. variable '"+ENV_ASPELL_EXE+"'",exePath!=null);
		jEdit.setProperty(SpellCheckPlugin.ASPELL_EXE_PROP,exePath);
		jEdit.setProperty(SpellCheckPlugin.ASPELL_LANG_PROP,"en-w_accents");
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		jEdit.newFile(view);
		try{Thread.sleep(1000);}catch(InterruptedException ie){}//let new file be created
		final Buffer buff = view.getBuffer();
		buff.setProperty(SpellCheckPlugin.BUFFER_LANGUAGE_PROP,"en");
		buff.insert(0,"Les licences de la plupart des logiciels\nsont concues pour vous enlever toute libertée");
		
		final AtomicReference<SpellException> except = new AtomicReference<SpellException>(null);
		Thread spellThread = new Thread(){
			public void run(){
				try{
				SpellCheckPlugin.showCustomLangSpellDialog(view,buff);
				}catch(SpellException spe){
					except.set(spe);
					spe.printStackTrace(System.err);
				}
			}
		};
		spellThread.start();
		DialogFixture langDialog = WindowFinder.findDialog(EnhancedDialog.class).withTimeout(5000).using(TestUtils.robot());
		try{Thread.sleep(1000);}catch(InterruptedException ie){}//let dictionaries be loaded

		langDialog.comboBox().selectItem("fr");
		langDialog.button(AbstractButtonTextMatcher.withText(JButton.class,"OK")).click();
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(10000).using(TestUtils.robot());

		try{Thread.sleep(1000);}catch(InterruptedException ie){}//let dictionaries be loaded
		assertEquals("fr",buff.getProperty(SpellCheckPlugin.BUFFER_LANGUAGE_PROP));
		spellDialog.list().selectItem("liberté");
		spellDialog.button("Change").click();
		try{Thread.sleep(1000);}catch(InterruptedException ie){}//let dictionaries be loaded
		spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(5000).using(TestUtils.robot());
		spellDialog.list().selectItem("conçues");
		spellDialog.button("Change").click();
		try{spellThread.join(5000);}catch(InterruptedException ie){}
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());
		assertEquals(null,except.get());
		assertEquals("Les licences de la plupart des logiciels\nsont conçues pour vous enlever toute liberté",buff.getText(0,buff.getLength()));
		assertEquals("en",buff.getProperty(SpellCheckPlugin.BUFFER_LANGUAGE_PROP));
	}

	@Test
	public void testSpellCheckOnSave(){
		String testsDir = System.getProperty(ENV_TESTS_DIR);
		assertTrue("Forgot to set env. variable '"+ENV_TESTS_DIR+"'",testsDir!=null);

		String exePath = System.getProperty(ENV_ASPELL_EXE);
		assertTrue("Forgot to set env. variable '"+ENV_ASPELL_EXE+"'",exePath!=null);
		
		String path = testsDir+"/spellTest.txt";
		
		jEdit.setProperty(SpellCheckPlugin.ASPELL_EXE_PROP,exePath);
		jEdit.setProperty(SpellCheckPlugin.ASPELL_LANG_PROP,"en");
		jEdit.setBooleanProperty(SpellCheckPlugin.SPELLCHECK_ON_SAVE_PROP,true);
		
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		jEdit.openFile(view,path);
		
		TestUtils.jeditFrame().menuItemWithPath("File","Save").select();
		
		final AtomicReference<Boolean> atr = new AtomicReference(Boolean.FALSE);
		EBComponent eb = new EBComponent(){
			public void handleMessage(EBMessage ebm){
				if(ebm instanceof ErrorSourceUpdate){
					System.err.println("got error update : "+ebm);
					if(ErrorSourceUpdate.ERROR_SOURCE_ADDED.equals(((ErrorSourceUpdate)ebm).getWhat()))
						atr.set(Boolean.TRUE);
				}
			}
		};
		
		EditBus.addToBus(eb);
		
		try{Thread.sleep(10000);}catch(InterruptedException ie){}
		
		assertEquals(Boolean.TRUE,atr.get());
		
		ErrorSource[] sources = ErrorSource.getErrorSources();
		ErrorSource spellError = null;
		for(int i=0;i<sources.length;i++){
			if(sources[i] instanceof ErrorListValidator){
				spellError = sources[i];
			}
		}
		assertNotNull(spellError);
		
		assertTrue(6==spellError.getFileErrorCount(path));
		EditBus.removeFromBus(eb);
	}
	
	
	@Test
	public void testStop(){
		
		final PluginJAR jar = jEdit.getPlugin(SpellCheckPlugin.class.getName()).getPluginJAR();
		try{
		SwingUtilities.invokeAndWait(
			new Runnable(){
				public void run(){
					jar.deactivatePlugin(false);
				}
			});
		}catch(Exception ie){}
		ErrorSource[] sources = ErrorSource.getErrorSources();
		ErrorSource spellError = null;
		for(int i=0;i<sources.length;i++){
			if(sources[i] instanceof ErrorListValidator){
				spellError = sources[i];
			}
		}
		
		assertNull(spellError);
		
	}

	@Test
	public void testMultipleBuffers(){
		
		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		
		jEdit.newFile(view);
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		jEdit.newFile(view);

		
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		
		final Buffer buffer1 = view.getBuffer();
		buffer1.insert(0,"The wiek comes to an end");
		final String oldText1 = buffer1.getText(0,buffer1.getLength());

		view.splitHorizontally();
		final EditPane pane2 = view.getEditPanes()[1];
		
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		final Buffer buffer2 = buffer1.getPrev();
		pane2.setBuffer(buffer2);
		buffer2.insert(0,"The qwick brown foxe");


		//select the second pane and spell-check in the first
		pane2.getTextArea().setSelection(new Selection.Range(0,10));
		
		final AtomicReference<Boolean> except = new AtomicReference<Boolean>(Boolean.FALSE);
		Thread spellThread = new Thread(){
			public void run(){
				try{
					SpellCheckPlugin.checkBuffer(view,buffer1);
				}catch(IllegalArgumentException iae){
					except.set(Boolean.TRUE);
					try{
					SpellCheckPlugin.checkBuffer(view,buffer2);
					}catch(Exception e){
						e.printStackTrace(System.err);
					}
				}
			}
		};
		spellThread.start();
		
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(5000).using(TestUtils.robot());
		
		assertEquals("SpellCheckPlugin didn't throw an exception on view/buffer mismatch",Boolean.TRUE,except.get());
		
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		spellDialog.list().selectItem("quick");
		spellDialog.button("Change").click();
		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());
		assertEquals("The quick brown foxe",buffer2.getText(0,buffer2.getLength()));

		assertEquals("The wiek comes to an end",buffer1.getText(0,buffer1.getLength()));
	}
	
	@Test
	public void testMarkupModes(){
		String exePath = System.getProperty(ENV_ASPELL_EXE);
		assertTrue("Forgot to set env. variable '"+ENV_ASPELL_EXE+"'",exePath!=null);

		String testDir = System.getProperty(ENV_TESTS_DIR);
		assertTrue("Forgot to set env. variable '"+ENV_TESTS_DIR+"'",testDir!=null);

		jEdit.setProperty(SpellCheckPlugin.ASPELL_EXE_PROP,exePath);
		jEdit.setProperty(SpellCheckPlugin.ASPELL_LANG_PROP,"en");

		final View view = TestUtils.jeditFrame().targetCastedTo(View.class);
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		view.unsplit();
		jEdit.openFile(view,testDir+"/latex-file.tex");
		final Buffer buff = view.getBuffer();

		try{Thread.sleep(1000);}catch(InterruptedException ie){}

		//with "none" filter
		jEdit.setProperty(SpellCheckPlugin.ASPELL_MARKUP_MODE_PROP,
			SpellCheckPlugin.AspellMarkupMode.NO_MARKUP_MODE.toString());
		
		Thread spellThread = new Thread(){
			public void run(){
				SpellCheckPlugin.checkBuffer(view,buff);
			}
		};
		spellThread.start();
		DialogFixture spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(10000).using(TestUtils.robot());
		spellDialog.textBox("originalWord").requireText("twoside");
		spellDialog.button("Cancel").click();
		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
		assertTrue("spell-checking didn't finish", !spellThread.isAlive());
		
		
		//with manual markup mode
		jEdit.setProperty(SpellCheckPlugin.ASPELL_MARKUP_MODE_PROP,
			SpellCheckPlugin.AspellMarkupMode.MANUAL_MARKUP_MODE.toString());
		assertEquals("tex",
					jEdit.getProperty(SpellCheckPlugin.FILTERS_PROP+".latex"));

		spellThread = new Thread(){
			public void run(){
				SpellCheckPlugin.checkBuffer(view,buff);
			}
		};
		spellThread.start();
		spellDialog = WindowFinder.findDialog(ValidationDialog.class).withTimeout(5000).using(TestUtils.robot());
		spellDialog.textBox("originalWord").requireText("mispelled");
		spellDialog.button("Cancel").click();
		try{
			spellThread.join(5000);
		}catch(InterruptedException ie){}
	}
}
