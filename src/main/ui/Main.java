package ui;

import asg.cliche.ShellFactory;
import ui.cli.ClicheCLI;
import ui.gui.JfxApp;

import java.io.IOException;

/**
 * Entry point for CLI app
 * @author Jason Hsu
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell("xypher~", "Xypher - Encipher Text", new ClicheCLI())
                .commandLoop();
        //JfxApp.run(args);
    }
}
