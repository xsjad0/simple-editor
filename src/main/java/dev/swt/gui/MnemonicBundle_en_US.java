package dev.swt.gui;

import java.util.ListResourceBundle;

public class MnemonicBundle_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = { { "editorFile", 'D' }, { "editorEdit", 'E' }, { "editorHelp", 'H' },
            { "editorNew", 'N' }, { "editorOpen", 'O' }, { "editorSave", 'S' }, { "editorQuit", 'B' },
            { "editorColor", 'F' }, { "editorVersion", 'V' } };
}