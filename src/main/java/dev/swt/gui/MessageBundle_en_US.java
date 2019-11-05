package dev.swt.gui;

import java.util.ListResourceBundle;

public class MessageBundle_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = { { "editorTitle", "Simple-Editor" }, { "editorTabText", "untitled" },
            { "editorFile", "&File" }, { "editorEdit", "&Edit" }, { "editorHelp", "&Help" }, { "editorNew", "&New" },
            { "editorOpen", "&Open" }, { "editorSave", "&Save..." }, { "editorQuit", "&Quit" },
            { "editorColor", "Text &Color" }, { "editorVersion", "&Version" }, { "colorTitle", "Color-Picker" },
            { "colorInfo", "Please select text foreground color" }, { "colorGroup", "Text Color" },
            { "colorRed", "red" }, { "colorGreen", "green" }, { "colorBlue", "blue" }, { "colorOkay", "Ok" },
            { "colorCancel", "Cancel" }, { "version", "1.1" }, { "owner", "Marius Schenzle" },
            { "stopClosing", "Modified tabs found.\nClose application anyway?" } };
}