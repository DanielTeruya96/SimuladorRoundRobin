/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import simulador.de.rr.model.Memoria;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
public class EscalonadorLongoPrazo implements Runnable{
    BlockingQueue<Processo> filaEntrada;
    BlockingQueue<Processo> pronto;
    Memoria memoria;

  

    public EscalonadorLongoPrazo(BlockingQueue<Processo> filaEntrada, BlockingQueue<Processo> pronto, Memoria memoria) {
        this.filaEntrada = filaEntrada;
        this.pronto = pronto;
        this.memoria = memoria;
    }
    
    
    
    
    @Override
    public void run() {
        while(true){
            if(!filaEntrada.isEmpty()){
                if(filaEntrada.peek().getTp() > memoria.menorTamanho()){
                    System.out.println("Escalonador FCFS de longo prazo não retirou o processo id da fila de entrada porque não há espaço na memória");
                }else{
                    Processo p = filaEntrada.poll();
                    System.out.println("Escalonador FCFS de longo prazo escolheu o processo ID: "+p.getId());
                    pronto.add(p);
                    System.out.println("Escalonador FCFS de longo prazo retirou o processo ID: "+p.getId()+" da fila de entrada, colocando-o na fila de prontos.");
                    memoria.alocarProcesso(p);
                }
            }
        }
    }
    
}
