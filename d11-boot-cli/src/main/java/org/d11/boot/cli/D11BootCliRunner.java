package org.d11.boot.cli;

import org.d11.boot.cli.command.RootCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

/**
 * Command line runner that starts a Picocli root command.
 */
@Component
public class D11BootCliRunner implements CommandLineRunner, ExitCodeGenerator {

    /**
     * The CLI root command.
     */
    private final RootCommand rootCommand;
    /**
     * Picocli IFactory.
     */
    private final IFactory iFactory;
    /**
     * CLI command exit code.
     */
    private int exitCode;

    /**
     * Creates a new command line runner.
     *
     * @param rootCommand CLI root command.
     * @param iFactory Picocli IFactory.
     */
    @Autowired
    public D11BootCliRunner(final RootCommand rootCommand, final IFactory iFactory) {
        this.rootCommand = rootCommand;
        this.iFactory = iFactory;
    }


    @Override
    public void run(final String... args) {
        this.exitCode = new CommandLine(this.rootCommand, this.iFactory).execute(args);
    }

    @Override
    public int getExitCode() {
        return this.exitCode;
    }

}
