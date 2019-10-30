package dev.swt.gui;

import java.io.File;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TabEventListener implements CTabFolder2Listener {

    @Override
    public void close(CTabFolderEvent event) {
        CTabFolder tabFolder = (CTabFolder) event.getSource();
        MyText text = (MyText) tabFolder.getSelection().getControl();

        if (text.isModified()) {
            Shell shell = (Shell) text.getShell();
            FileDialog ask = new FileDialog(shell, SWT.SAVE);
            String path = ask.open();

            if (path != null) {
                FileIO.write(path, text.getText());
            }
        }
        event.item.dispose();
    }

    @Override
    public void minimize(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void maximize(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void restore(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showList(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

}