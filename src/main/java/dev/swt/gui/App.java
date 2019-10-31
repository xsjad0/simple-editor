package dev.swt.gui;

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
        switch (args[0]) {
        case "editor":
            new Editor().run();
            break;
        default:
            new Editor().run();
            break;
        }
    }
}
