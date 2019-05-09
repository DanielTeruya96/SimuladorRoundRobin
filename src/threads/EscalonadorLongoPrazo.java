/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.de.rr.model.Global;
import simulador.de.rr.model.Memoria;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
public class EscalonadorLongoPrazo implements Runnable{
    BlockingQueue<Processo> filaEntrada;
    BlockingQueue<Processo> pronto;
   
  


 

   

    public EscalonadorLongoPrazo(BlockingQueue<Processo> filaEntrada, BlockingQueue<Processo> filaPronto) {
        this.filaEntrada = filaEntrada;
        this.pronto = pronto;
    }

  

    
    
    
    
    @Override
    public synchronized void run() {
        while(true){
            if(!Global.discoVazio()){
                int id = Global.getIDProcessoDisco();
                int tamanho = Global.getIDProcessoTamahoDisco();
                Global.imprimir("Escalonador FCFS de longo prazo escolheu o processo ID: "+id);
                
                
                
                if(tamanho > Global.memoria.menorTamanho()){
                    Global.imprimir("Escalonador FCFS de longo prazo não retirou o processo id"+id+" da fila de entrada porque não há espaço na memória");
                    
                    Global.m.lock();
                    try {
                        Global.P.await();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EscalonadorLongoPrazo.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                        Global.m.unlock();
                    }
                    
                }else{
                    Processo p = Global.getProcessoDisco();
                    Global.imprimir("Escalonador FCFS de longo prazo retirou o processo ID: "+p.getId()+" da fila de entrada, colocando-o na fila de prontos.");
                    
                
                    Global.memoria.alocarProcesso(p);
                }
                
                
               
            } else {
            }
        }
    }

    private synchronized void alocaProcesso(Processo p) throws InterruptedException {
        Global.memoria.alocarProcesso(p);
    }
    
}
