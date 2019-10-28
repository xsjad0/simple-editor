package dev.swt.gui;

import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;

public class TabEventListener implements CTabFolder2Listener {

    @Override
    public void close(CTabFolderEvent event) {
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