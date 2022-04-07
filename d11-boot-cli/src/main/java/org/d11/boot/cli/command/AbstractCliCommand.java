package org.d11.boot.cli.command;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.prefs.Preferences;

/**
 * Base class for command line commands.
 */
public abstract class AbstractCliCommand implements Callable<Integer> {

    /**
     * Preference name for remembering the directory we last opened a file from.
     */
    private static final String LAST_DIRECTORY = ".LAST_DIRECTORY";
    /**
     * Preferences containing the UPLOAD_COMMAND_DIRECTORY preference.
     */
    private final Preferences preferences;

    /**
     * Creates a new command line command.
     */
    protected AbstractCliCommand() {
        this.preferences = Preferences.userNodeForPackage(getClass());
    }

    /**
     * Opens a file chooser with the provided file filter.
     *
     * @param fileFilter File filter for the file chooser.
     * @return The selected files.
     */
    protected File[] getFiles(final FileFilter fileFilter) {
        System.setProperty("java.awt.headless", "false");
        final String directory = this.preferences.get(getClass().getName() + LAST_DIRECTORY, ".");
        final JFileChooser fileChooser = new JFileChooser(directory);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(fileFilter);

        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            final File[] files = fileChooser.getSelectedFiles();
            if(files.length > 0) {
                this.preferences.put(getClass().getName() + LAST_DIRECTORY, files[0].getParent());
            }
            return files;
        }
        return new File[0];
    }

}
