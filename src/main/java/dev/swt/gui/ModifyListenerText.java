package dev.swt.gui;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

public class ModifyListenerText implements ModifyListener {

    private CTabFolder tabFolder;

    public ModifyListenerText(CTabFolder tabFolder) {
        this.tabFolder = tabFolder;
    }

    @Override
    public void modifyText(ModifyEvent e) {
        MyText text = (MyText) e.getSource();
        if (!text.isModified()) {
            CTabItem selectedTab = tabFolder.getSelection();
            selectedTab.setText(selectedTab.getText() + "*");
            text.modify();
        }
    }
}