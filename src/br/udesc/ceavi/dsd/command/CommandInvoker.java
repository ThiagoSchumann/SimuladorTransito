package br.udesc.ceavi.dsd.command;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public class CommandInvoker {

    public CommandInvoker() {
        super();
    }

    public void execute(Command command) {
        command.executar();
    }
}
