package dev.swt.gui;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

public class ModifyListenerText implements ModifyListener {

    @Override
    public void modifyText(ModifyEvent e) {
        MyText text = (MyText) e.getSource();
        text.modify();
    }
}