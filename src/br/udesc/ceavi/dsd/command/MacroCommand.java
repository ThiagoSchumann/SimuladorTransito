package br.udesc.ceavi.dsd.Command;

import br.udesc.ceavi.dsd.model.carro.Carro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public class MacroCommand implements Command {

    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    @Override
    public void execute() {
        commands.forEach(c -> c.execute());
    }

    @Override
    public void setCarro(Carro carro) {
        commands.forEach(c -> c.setCarro(carro));
    }

}
