package org.d11.boot.cli.command;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * File filter that accepts directories or files ending with .htm/.html.
 */
public class HtmlFileFilter extends FileFilter {

    @Override
    public String getDescription() {
        return "HTML File";
    }

    @Override
    public boolean accept(final File file) {
        return file.isDirectory()
                || file.getName().endsWith(".html")
                || file.getName().endsWith(".htm");
    }

}
