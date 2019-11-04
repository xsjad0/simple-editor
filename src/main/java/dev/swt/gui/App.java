package dev.swt.gui;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Simple-Editor entry point
 */
public final class App {
    private App() {
    }

    /**
     * Start simple-editor
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            new Editor(ResourceBundle.getBundle("dev.swt.gui.i18n.MessageBundle", new Locale(args[0], args[1]))).run();
        } else if (args.length == 1) {
            new Editor(ResourceBundle.getBundle("dev.swt.gui.i18n.MessageBundle", new Locale(args[0]))).run();
        } else {
            new Editor(ResourceBundle.getBundle("dev.swt.gui.i18n.MessageBundle")).run();
        }

    }
}
