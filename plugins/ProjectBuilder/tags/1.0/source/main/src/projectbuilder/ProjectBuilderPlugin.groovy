/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectbuilder
// imports {{{
import projectbuilder.build.BuildCommand
import projectbuilder.run.RunCommand

import java.util.zip.*
import javax.script.ScriptContext
import javax.swing.*

import org.gjt.sp.jedit.*
import org.gjt.sp.jedit.msg.*
import org.gjt.sp.util.Log
import org.gjt.sp.jedit.jEdit as JEDIT

import org.apache.tools.ant.Project
import org.apache.tools.ant.ProjectHelper

import projectbuilder.utils.ZipUtils
import projectviewer.vpt.VPTProject
import com.townsfolkdesigns.jedit.plugins.scripting.*
import console.Shell
import antfarm.AntFarmPlugin
// }}} imports
/**
 *
 * @author elberry
 */
public class ProjectBuilderPlugin extends EditPlugin {

   private Project building = null; // The currently-building project
   public static final String userTemplateDir = JEDIT.getSettingsDirectory()+"/project-templates"
   
   @Override
   public void start() {
      Log.log(Log.DEBUG, ProjectBuilderPlugin.class, "Start called")
      File pluginHome = this.pluginHome
      if (!pluginHome.exists()) {
         pluginHome.mkdirs()
      }
      File templatesDir = new File(pluginHome, "templates")
      if (!templatesDir.exists()) {
         templatesDir.mkdirs()
      }
      File templatesZipFile = new File(pluginHome, "templates.zip")
      if(!templatesZipFile.exists()) {

         PluginJAR pluginJar = getPluginJAR()
         ZipFile pluginZip = null
         try {
            pluginZip = pluginJar.getZipFile()
            ZipEntry templatesEntry = pluginZip.getEntry("templates.zip")
            if (templatesEntry) {
               Log.log(Log.DEBUG, ProjectBuilderPlugin.class, "Attempting to extract templates.zip from PluginJar to: ${templatesZipFile.path}")
               ZipUtils.copyStream(pluginZip.getInputStream(templatesEntry), templatesZipFile.newOutputStream())
            }
         } catch (Exception e) {
            Log.log(Log.ERROR, ProjectBuilderPlugin.class, "Error getting plugin zip from plugin jar", e)
         }


         File templatesFile = EditPlugin.getResourcePath(this, "templates.zip")
         Log.log(Log.DEBUG, ProjectBuilderPlugin.class, "Attempting to extract templates.zip - null: ${(templatesFile == null)} | exists: ${templatesFile.exists()} | path: ${templatesFile.path}")
         if (templatesFile?.exists()) {
            try {
               ZipUtils.extract(templatesFile, templatesDir)
            } catch (Exception e) {
               Log.log(Log.ERROR, ProjectBuilderPlugin.class, "Error extracting ${templatesFile.name} to: ${templatesDir.path}", e)
            }
         }
      }
   }

   @Override
   public void stop() {
   	  
   }

   public void createNewProject(View view, String projectType) {
      Log.log(Log.DEBUG, ProjectBuilderPlugin.class, "Create new project: ${ScriptEnginePlugin.class.getCanonicalName()}")
      ScriptEnginePlugin plugin = JEDIT.getPlugin(ScriptEnginePlugin.class.getCanonicalName())
      ScriptExecutionDelegate delegate = plugin.getScriptExecutionDelegate()

      ScriptContext scriptContext = ScriptEngineUtilities.getDefaultScriptContext(view)
      scriptContext.setAttribute("projectType", projectType, ScriptContext.ENGINE_SCOPE)
      
      File baseTemplateFile = EditPlugin.getResourcePath(this, "templates/Base.groovy")
      if(baseTemplateFile) {
         delegate.evaluateString(baseTemplateFile.getText(), "groovy", scriptContext)
      }
   }
   
   public void createNewProject(View view) {
   	   createNewProject(view, "")
   }
   
   public void buildProject(View view) {
   	   buildProject(view, projectviewer.ProjectViewer.getActiveProject(view))
   }
   
   public void buildProject(View view, VPTProject proj) {
   	   if (building != null) return
	   if (proj == null) {
	   	   GUIUtilities.error(view, "projectBuilder.msg.no-project", null)
	    } else {
	    	JEDIT.getAction("error-list-clear").invoke(view)
	    	JEDIT.saveSettings()
	    	BuildCommand.run(view, proj)
	   }
   }
   
   public void runProject(View view) {
   	   runProject(view, projectviewer.ProjectViewer.getActiveProject(view))
   }
   
   public void runProject(View view, VPTProject proj) {
   	   if (building != null) return
	   if (proj == null) {
	   	   GUIUtilities.error(view, "projectBuilder.msg.no-project", null)
	    } else {
	    	JEDIT.getAction("error-list-clear").invoke(view)
	    	RunCommand.run(view, proj)
	   }
   }
   
   public void editBuildSettings(View view) {
   	   VPTProject proj = projectviewer.ProjectViewer.getActiveProject(view)
   	   if (proj == null) {
   	   	   GUIUtilities.error(view, "projectBuilder.msg.no-project", null)
   	   	   return
   	   }
   	   BuildCommand.editCommands(view, proj)
   }
   
   public void editRunSettings(View view) {
   	   VPTProject proj = projectviewer.ProjectViewer.getActiveProject(view)
   	   if (proj == null) {
   	   	   GUIUtilities.error(view, "projectBuilder.msg.no-project", null)
   	   	   return
   	   }
   	   RunCommand.editCommands(view, proj)
   }
   
}

