package dev.swt.gui;

import java.util.Arrays;
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
        Locale[] supportedLocales = { new Locale("en", "US"), new Locale("de", "DE") };

        if (args.length == 2) {
            Locale currentLocale = new Locale(args[0], args[1]);
            if (Arrays.asList(supportedLocales).contains(currentLocale)) {
                new Editor(ResourceBundle.getBundle("dev.swt.gui.MessageBundle", currentLocale)).run();
            } else {
                System.out.println("Supported locales:");
                for (Locale locale : supportedLocales) {
                    System.out.println(locale);
                }
            }
        } else {
            System.out.println("Missing Arguements: language region");
        }
    }
}
