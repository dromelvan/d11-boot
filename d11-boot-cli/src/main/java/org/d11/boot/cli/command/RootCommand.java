package org.d11.boot.cli.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * CLI root command.
 */
@Component
@Command(name = "d11", version = "4.0.0", mixinStandardHelpOptions = true, subcommands = {
        ParseCommand.class,
        FixturesCommand.class,
        UploadCommand.class,
        SquadsCommand.class,
        PhotosCommand.class,
        TransferWindowCommand.class
})
public class RootCommand implements Callable<Integer> {

    @Override
    public Integer call() {
        return 0;
    }
}
