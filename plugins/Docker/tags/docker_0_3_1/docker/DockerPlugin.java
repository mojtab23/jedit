/*
 * DockerPlugin.java
 * :tabSize=3:indentSize=3:noTabs=true:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright (C) 2001, 2002 Calvin Yu
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

package docker;

import common.gui.ListItem;
import common.gui.PopupList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.lang.reflect.Method;
import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.DockableWindowManager;
import org.gjt.sp.jedit.gui.OptionsDialog;
import org.gjt.sp.jedit.gui.PanelWindowContainer;
import org.gjt.sp.jedit.msg.EditPaneUpdate;
import org.gjt.sp.jedit.msg.PropertiesChanged;
import org.gjt.sp.jedit.msg.ViewUpdate;

import org.gjt.sp.util.Log;

/**
 * The docker plugin class.
 */
public class DockerPlugin extends EBPlugin
{

   static private Icon dockIcon;

   private Map handlers;
   private DockerConfig config;

   /**
    * Create a new <code>DockerPlugin</code>
    */
   public DockerPlugin() {
      handlers = new HashMap(2);
      config = new DockerConfig();
   }

   public DockerConfig getConfig() {
      return config;
   }

   public ViewHandler getViewHandler(View view) {
      return (ViewHandler) handlers.get(view);
   }

   /**
    * Handle an EditBus message.
    */
   public void handleMessage(EBMessage message)
   {
      if (message instanceof ViewUpdate) {
         ViewUpdate viewUpdate = (ViewUpdate) message;
         if (viewUpdate.getWhat() == ViewUpdate.CREATED) {
            attach(viewUpdate.getView());
         } else if (viewUpdate.getWhat() == ViewUpdate.CLOSED) {
            detach(viewUpdate.getView());
         }
      } else if (message instanceof PropertiesChanged) {
         for (Iterator i = handlers.values().iterator(); i.hasNext();) {
            ((ViewHandler) i.next()).init();
         }
      } else if (message instanceof EditPaneUpdate) {
         EditPaneUpdate editPaneUpdate = (EditPaneUpdate) message;
         if (editPaneUpdate.getWhat().equals(EditPaneUpdate.CREATED) ||
             editPaneUpdate.getWhat().equals(EditPaneUpdate.DESTROYED)) {
            View view = editPaneUpdate.getEditPane().getView();
            ViewHandler viewHandler = getViewHandler(view);
            if (viewHandler != null) {
               if (editPaneUpdate.getWhat().equals(EditPaneUpdate.CREATED)) {
                  viewHandler.editPaneCreated(editPaneUpdate.getEditPane());
               } else {
                  viewHandler.editPaneDestroyed(editPaneUpdate.getEditPane());
               }
            }
         }
      }
   }

   /**
    * Shows a popup for the top dock.
    */
   public void showTopDockPopup(View view)
   {
      showDockPopup(view,
                    view.getDockableWindowManager().getTopDockingArea(),
                    "docker.popup.top-dock");
   }

   /**
    * Shows a popup for the left dock.
    */
   public void showLeftDockPopup(View view)
   {
      showDockPopup(view,
                    view.getDockableWindowManager().getLeftDockingArea(),
                    "docker.popup.left-dock");
   }

   /**
    * Shows a popup for the bottom dock.
    */
   public void showBottomDockPopup(View view)
   {
      showDockPopup(view,
                    view.getDockableWindowManager().getBottomDockingArea(),
                    "docker.popup.bottom-dock");
   }

   /**
    * Shows a popup for the right dock.
    */
   public void showRightDockPopup(View view)
   {
      showDockPopup(view,
                    view.getDockableWindowManager().getRightDockingArea(),
                    "docker.popup.right-dock");
   }

   public void start() {
      EditBus.addToBus(new DockFocusManager());
   }

   /**
    * Gets the Plugin attribute of the DockerPlugin class
    */
   static public DockerPlugin getPlugin()
   {
      return (DockerPlugin) jEdit.getPlugin(DockerPlugin.class.getName());
   }

   /**
    * Attach a docking handler to the given view.
    */
   private void attach(View view)
   {
      handlers.put(view, new ViewHandler(view));
   }

   /**
    * Detach a docking handler from the given view.
    */
   private void detach(View view)
   {
      ((ViewHandler) handlers.remove(view)).detach();
   }

   /**
    * Show a popup for a particular dock.
    */
   private void showDockPopup(final View view,
                              final PanelWindowContainer dock,
                              String actionName)
   {
      String[] names = dock.getDockables();
      List items = new ArrayList(names.length + 1);
      items.add(new DockListItem(view.getDockableWindowManager(), null));
      for (int i=0; i<names.length; i++) {
         items.add(new DockListItem(view.getDockableWindowManager(), names[i]));
      }
      PopupList popup = PopupList.show(view, items,
                                       getCurrentDockable(view, dock),
                                       new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            PopupList popup = (PopupList) evt.getSource();
            final String name = (String) popup.getSelectedActualItem();
            if (name == null) {
               dock.show(null);
               view.addWindowFocusListener(new java.awt.event.WindowAdapter() {
                  public void windowGainedFocus(WindowEvent evt) {
                     view.getEditPane().getTextArea().requestFocus();
                     view.removeWindowFocusListener(this);
                     super.windowGainedFocus(evt);
                  }
               });
               popup.cancel();
               /*Log.log(Log.DEBUG, this, "Sending focus to text area");
               view.getEditPane().getTextArea().requestFocus();*/
            } else {
               Log.log(Log.DEBUG, this, "Sending focus to dockable: " + name);
               view.addWindowFocusListener(new java.awt.event.WindowAdapter() {
                  public void windowGainedFocus(WindowEvent evt) {
                     view.getDockableWindowManager().getDockable(name).requestDefaultFocus();
                     view.removeWindowFocusListener(this);
                     super.windowGainedFocus(evt);
                  }
               });
               view.getDockableWindowManager().showDockableWindow(name);
            }
         }
      });
      popup.enableKeyStrokeCycling(actionName);
   }

   /**
    * Finds the current visible dockable at the given dock.
    */
   static private String getCurrentDockable(View view, PanelWindowContainer dock)
   {
      String[] names = dock.getDockables();
      for (int i=0; i<names.length; i++) {
         if (view.getDockableWindowManager().isDockableWindowVisible(names[i]))
            return names[i];
      }
      return null;
   }

   /**
    * A list item that represents a dockable component.
    */
   private class DockListItem implements ListItem
   {
      private DockableWindowManager dockableWindowManager;
      private String dockableName;

      /**
       * Create a new <code>DockListItem</code>.
       */
      public DockListItem(DockableWindowManager aDockableWindowManager,
                          String aDockableName)
      {
         dockableWindowManager = aDockableWindowManager;
         dockableName = aDockableName;
      }

      /**
       * Returns the label for the item.
       */
      public String getLabel()
      {
         if (dockableName == null) {
            return config.getProperty("no-dockable.label");
         } else {
            return dockableWindowManager.getDockableTitle(dockableName);
         }
      }

      /**
       * Returns the icon for the item.
       */
      public Icon getIcon()
      {
         if (dockableName != null) {
            if (dockIcon == null) {
               dockIcon = new ImageIcon(getClass().getResource("dock.gif"));
            }
            return dockIcon;
         }
         return null;
      }

      /**
       * Returns the actual item.
       */
      public Object getActualItem()
      {
         return dockableName;
      }
   }

}
