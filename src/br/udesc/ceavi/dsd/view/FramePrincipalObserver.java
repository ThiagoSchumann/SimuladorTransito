package br.udesc.ceavi.dsd.view;

/**
 *
 * @author Gustavo C. Santos 27/08/2019
 */
public interface FramePrincipalObserver {

    public void notificarNumeroDeCarro(int numCarro);

    public void notificarSimulacaoFinalizada();

    public void notificarRepawnEnd();
}
