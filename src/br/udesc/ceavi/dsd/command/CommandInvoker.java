package br.udesc.ceavi.dsd.command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public class CommandInvoker {

    private List<Command> commands;

    public CommandInvoker() {
        super();
        this.commands = new ArrayList<>();
    }

    public void execute(Command command) {
        command.executar();
        commands.add(command);
    }
}
