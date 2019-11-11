package dev.swt.gui;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

public class ListenerClose implements Listener {

    private ResourceBundle msg;

    private CTabFolder tabFolder;

    public ListenerClose(ResourceBundle msg, CTabFolder tabFolder) {
        this.msg = msg;
        this.tabFolder = tabFolder;
    }

    @Override
    public void handleEvent(Event event) {
        CTabItem[] tabs = tabFolder.getItems();
        boolean isAnyTabModified = false;

        for (CTabItem tab : tabs) {
            MyText text = (MyText) tab.getControl();
            if (text.isModified()) {
                isAnyTabModified = true;
                break;
            }
        }

        if (isAnyTabModified) {
            MessageBox messageBox = new MessageBox(tabFolder.getShell(), SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
            messageBox.setText("Information");
            messageBox.setMessage(msg.getString("stopClosing"));
            event.doit = messageBox.open() == SWT.YES;
        }
    }

}