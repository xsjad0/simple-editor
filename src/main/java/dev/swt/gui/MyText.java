package dev.swt.gui;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MyText extends Text {

    private boolean modified = false;

    public MyText(Composite parent, int style) {
        super(parent, style);
    }

    public void modify() {
        modified = true;
    }

    public boolean isModified() {
        return modified;
    }

    @Override
    protected void checkSubclass() {
        // allow subclassing
    }

}