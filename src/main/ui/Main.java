package ui;

import asg.cliche.ShellFactory;
import ui.cli.ClicheCLI;

import java.io.IOException;

/**
 * Main class - Represents the entry point to the application
 * @author Jason Hsu
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell("xypher~", "Xypher - Encipher Text", new ClicheCLI())
                .commandLoop();
    }
}
