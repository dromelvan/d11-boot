package org.d11.boot.cli.command;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import javax.swing.JFileChooser;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.prefs.Preferences;

/**
 * Command for opening a WhoScored match file, parsing it and saving the json output to a destination file.
 */
@Component
@Command(name = "parse", mixinStandardHelpOptions = true)
public class ParseCommand implements Callable<Integer> {

    /**
     * Preference name for remembering the directory we last opened a file from.
     */
    private static final String PARSE_COMMAND_DIRECTORY = ".PARSE_COMMAND_DIRECTORY";
    /**
     * Preferences containing the PARSE_COMMAND_DIRECTORY preference.
     */
    private final Preferences preferences;
    /**
     * Producer template for sending a file to the parse route.
     */
    @Produce("{{app.route.parse}}")
    private ProducerTemplate producerTemplate;

    /**
     * Creates a new parse command.
     */
    public ParseCommand() {
        this.preferences = Preferences.userNodeForPackage(getClass());
    }

    @Override
    public Integer call() {
        System.setProperty("java.awt.headless", "false");
        final String directory = this.preferences.get(getClass().getName() + PARSE_COMMAND_DIRECTORY, ".");
        final JFileChooser fileChooser = new JFileChooser(directory);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new HtmlFileFilter());

        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            final File[] files = fileChooser.getSelectedFiles();
            if(files.length > 0) {
                this.preferences.put(getClass().getName() + PARSE_COMMAND_DIRECTORY, files[0].getParent());

                for(final File file : files) {
                    this.producerTemplate.sendBody(file);
                }
            }
        }
        return 0;
    }
}
