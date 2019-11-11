package dev.swt.gui;

import java.util.ListResourceBundle;

public class MessageBundle_de_DE extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = { { "editorTitle", "Text-Editor" }, { "editorTabText", "unbenannt" },
            { "editorFile", "&Datei" }, { "editorEdit", "&Editieren" }, { "editorHelp", "&Hilfe" },
            { "editorNew", "&Neu" }, { "editorOpen", "&Öffnen" }, { "editorSave", "&Speichern..." },
            { "editorQuit", "&Beenden" }, { "editorColor", "Text &Farbe" }, { "editorVersion", "&Version" },
            { "colorTitle", "Farb-Auswahl" }, { "colorInfo", "Bitte wählen Sie eine Textfarbe aus" },
            { "colorGroup", "Textfarbe" }, { "colorRed", "rot" }, { "colorGreen", "grün" }, { "colorBlue", "blau" },
            { "colorOkay", "Ok" }, { "colorCancel", "Abbruch" }, { "version", "Version 1.3" },
            { "owner", "Marius Schenzle" },
            { "stopClosing", "Modifizierte Tabs entdeckt.\nWollen Sie trotzdem schließen?" } };
}