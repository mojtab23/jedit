package com.addictedtor.orchestra;

import java.awt.Dialog;
import java.awt.Window;

import javax.swing.SwingUtilities;

import org.af.commons.threading.ProgressDialog;

@SuppressWarnings("serial")
public class ProgressDialogInstaller extends ProgressDialog<Void, String> {

    private ProgressDialogInstaller(Dialog parent, Installer i) {
        super(parent, "Orchestra Installation", i, true, true);
    }

    @Override
    protected void onFinish() {
        dispose();
        Installer i = (Installer) getTask();
        FinishedDialog fd = new FinishedDialog(i.getShortcutDir(), i.getStartScript());
        fd.setVisible(true);
    }

    public static ProgressDialogInstaller make(OrchestraOptionPane oop, Installer i) {
        Window p = SwingUtilities.getWindowAncestor(oop);
        return new ProgressDialogInstaller( (Dialog) p, i); 
    }
}
