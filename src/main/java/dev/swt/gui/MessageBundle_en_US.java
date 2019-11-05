package dev.swt.gui;

import java.util.ListResourceBundle;

public class MessageBundle_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = { { "editorTitle", "Text-Editor" }, { "editorTabText", "unbenannt" },
            { "editorFile", "&Datei" }, { "editorEdit", "&Editieren" }, { "editorHelp", "&Hilfe" },
            { "editorNew", "&Neu" }, { "editorOpen", "&Öffnen" }, { "editorSave", "&Speichern..." },
            { "editorQuit", "&Beenden" }, { "editorColor", "Text &Farbe" }, { "colorTitle", "Farb-Auswahl" },
            { "colorInfo", "Bitte wählen Sie eine Textfarbe aus" }, { "colorGroup", "Textfarbe" },
            { "colorRed", "rot" }, { "colorGreen", "grün" }, { "colorBlue", "blau" }, { "colorOkay", "Ok" },
            { "colorCancel", "Abbruch" } };
}