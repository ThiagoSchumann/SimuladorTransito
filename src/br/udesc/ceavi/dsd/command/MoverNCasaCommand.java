package br.udesc.ceavi.dsd.command;

import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * @since 29/08/2019
 */
public class MoverNCasaCommand implements Command {

    //Começo de Tudo
    private final ICasa origem;
    //Fim de Tudo
    private final ICasa destino;

    //Armazena todos o caminho INCLUSIVEL O destino
    private final List<ICasa> caminho;

    public MoverNCasaCommand(ICasa origem, ICasa destino, List<ICasa> caminho) {
        this.origem = origem;
        this.destino = destino;
        this.caminho = Collections.unmodifiableList(caminho);
    }

    @Override
    public void executar() {
        ICarro carro = origem.getCarro();
        boolean liberado;
        do {
            liberado = true;
            //Vai tentar pegar o recurso de todas!!
            for (int i = 0; i < caminho.size(); i++) {
                ICasa casa = caminho.get(i);
                if (!casa.reservarCasa()) {
                    liberado = liberarRecursos(i);
                    break;
                }
            }
            
            if (!liberado) {
                carro.sleep(100 + carro.getRandom().nextInt(200));
            }

        } while (!liberado);

        int velocidade = carro.getVelocidade();

        origem.removerCarro();
        origem.repintar();

        ICasa primeiracasa = caminho.get(0);
        primeiracasa.setCarro(carro);
        primeiracasa.repintar();
        carro.setCasa(primeiracasa);

        origem.liberarRecurso();
        carro.sleep(velocidade);

        for (int i = 0; i < caminho.size() - 1; i++) {
            //saindo da casa
            ICasa casaAtual = caminho.get(i);
            casaAtual.removerCarro();
            casaAtual.repintar();

            //entrando na casa
            ICasa novaCasa = caminho.get(i + 1);
            novaCasa.setCarro(carro);
            carro.setCasa(novaCasa);
            novaCasa.repintar();

            casaAtual.liberarRecurso();

            carro.sleep(velocidade);
        }
    }

    private boolean liberarRecursos(int i) {
        for (int j = (i - 1); j >= 0; j--) {
            caminho.get(j).liberarRecurso();
        }
        return false;
    }

}
