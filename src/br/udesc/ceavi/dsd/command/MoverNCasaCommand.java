package br.udesc.ceavi.dsd.command;

import br.udesc.ceavi.dsd.controller.SystemController;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo C. Santos
 * @since 29/08/2019
 */
public class MoverNCasaCommand implements Command {

    //Começo de Tudo
    private ICasa origem;
    //Fim de Tudo
    private ICasa destino;

    //Armazena todos o caminho INCLUSIVEL O destino
    private List<ICasa> caminho;

    //Armazena as casa cujo o recurso esta sobre nossa posse
    private List<ICasa> casaLivres;

    public MoverNCasaCommand(ICasa origem, ICasa destino, List<ICasa> caminho) {
        this.origem = origem;
        this.destino = destino;
        this.caminho = caminho;
        this.casaLivres = new ArrayList<>();
    }

    @Override
    public void executar() {
        boolean liberado = false;
        do {
            //Vai tentar pegar o recurso de todas!!
            for (ICasa casa : caminho) {
                if (casa.reservarCasa()) { //Tentando
                    casaLivres.add(casa);//Sucesso

                    liberado = caminhoInteiramenteLivre();

                } else {//Insucesso ao pegar o recurso de uma casa
                    casaLivres.forEach(casaLivre -> casaLivre.liberarRecurso()); //Liberando as que eu peguei
                    break;
                }
            }
            try {
                ((Thread) origem.getCarro()).sleep(SystemController.getInstance().getRandom().nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(MoverNCasaCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!liberado);


        ICarro carro = origem.removerCarro();
        origem.repintar();
        for (int i = 0; i < casaLivres.size() - 1; i++) {
            //saindo da casa
            ICasa casaAtual = casaLivres.get(i);
            carro = casaAtual.removerCarro();
            casaAtual.repintar();
            casaAtual.liberarRecurso();

            //entrando na casa
            ICasa novaCasa = casaLivres.get(i + 1);
            novaCasa.setCarro(carro);
            novaCasa.repintar();

            if (!(i < casaLivres.size() - 1)) {
                //Esperando um pouco
                try {
                    ((Thread) carro).sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MoverNCasaCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        carro.setCasa(destino);
        destino.setCarro(carro);
        casaLivres.clear();
        origem.liberarRecurso();
    }

    /**
     * Retorna se todas se o caminho todos esta livre
     *
     * @param liberado
     * @return
     */
    private boolean caminhoInteiramenteLivre() {
        return casaLivres.size() == caminho.size();
    }

}
