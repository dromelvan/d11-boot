package org.d11.boot.cli.command;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * File filter that accepts directories or files ending with .json.
 */
public class JsonFileFilter extends FileFilter {

    @Override
    public String getDescription() {
        return "JSON File";
    }

    @Override
    public boolean accept(final File file) {
        return file.isDirectory()
                || file.getName().endsWith(".json");
    }

}
