package br.udesc.ceavi.dsd.command;

import java.util.List;

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
        command.execute();
        command.setCarro(null);
    }
}
