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
   
  
    Lock m;
    Condition P;

 

    public EscalonadorLongoPrazo(BlockingQueue<Processo> filaEntrada, BlockingQueue<Processo> filaPronto, Lock m, Condition P) {
       this.filaEntrada = filaEntrada;
        this.pronto = pronto;
        
        this.m = m;
        this.P = P;
    }

  

    
    
    
    
    @Override
    public synchronized void run() {
        while(true){
            if(!filaEntrada.isEmpty()){
                
                 
                    
                    if(filaEntrada.peek().getTp() > Global.memoria.menorTamanho()){
                    System.out.println("Escalonador FCFS de longo prazo não retirou o processo id da fila de entrada porque não há espaço na memória");
                       m.lock();
                        try {
                            P.await();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(EscalonadorLongoPrazo.class.getName()).log(Level.SEVERE, null, ex);
                        }finally{
                             m.unlock();
                        }
                      
                    }
                    Processo p = filaEntrada.poll();
                    System.out.println("Escalonador FCFS de longo prazo escolheu o processo ID: "+p.getId());
                    pronto.add(p);
                    System.out.println("Escalonador FCFS de longo prazo retirou o processo ID: "+p.getId()+" da fila de entrada, colocando-o na fila de prontos.");
                    Global.memoria.alocarProcesso(p);
                    
                    
                
            }
        }
    }

    private synchronized void alocaProcesso(Processo p) throws InterruptedException {
        Global.memoria.alocarProcesso(p);
    }
    
}
