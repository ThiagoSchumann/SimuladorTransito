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
                carro.sleep(carro.getRandom().nextInt(1500));
            }

//            for (ICasa casa : caminho) {
//                if (casa.reservarCasa()) { //Tentando
//                    casaLivres.add(casa);//Sucesso
//
//                    liberado = caminhoInteiramenteLivre();
//
//                } else {//Insucesso ao pegar o recurso de uma casa
//                    casaLivres.forEach(casaLivre -> casaLivre.liberarRecurso()); //Liberando as que eu peguei
//                    casaLivres.clear();
//                    break;
//                }
//            }
        } while (!liberado);

        int velocidade = carro.getVelocidade();
        
        origem.removerCarro();
        origem.repintar();

        ICasa primeiracasa = caminho.get(0);
        primeiracasa.setCarro(carro);
        primeiracasa.repintar();
        carro.setCasa(caminho.get(0));

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

            carro.sleep(carro.getVelocidade());
        }
//
//        origem.removerCarro();
//        origem.repintar();
//        casaLivres.get(0).setCarro(carro);
//        casaLivres.get(0).repintar();
//        carro.sleep(carro.getVelocidade());
//        for (int i = 0; i < casaLivres.size() - 1; i++) {
//            //saindo da casa
//            ICasa casaAtual = casaLivres.get(i);
//            casaAtual.removerCarro();
//            casaAtual.repintar();
//            casaAtual.liberarRecurso();
//
//            //entrando na casa
//            ICasa novaCasa = casaLivres.get(i + 1);
//            novaCasa.setCarro(carro);
//            novaCasa.repintar();
//
//            carro.sleep(carro.getVelocidade());
//        }
//
//        carro.setCasa(destino);
//        destino.setCarro(carro);
//        casaLivres.clear();
//        origem.liberarRecurso();
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

    private boolean liberarRecursos(int i) {
        for (int j = (i - 1); j >= 0; j--) {
            caminho.get(j).liberarRecurso();
        }
        return false;
    }

}
