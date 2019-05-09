/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.de.rr.model.Global;
import simulador.de.rr.model.Memoria;

/**
 *
 * @author Daniel Leite
 */
public class EscalonadorRR implements Runnable{
    private BlockingQueue<Integer> idProcesso;
    /**
     * 
     * @param idProcesso  Fila de processos para o Escalonador de RR escolher
     */
    public EscalonadorRR(BlockingQueue<Integer> idProcesso) {
        this.idProcesso = idProcesso;
    }
    
    

    /**
     * Pega o primeiro da fila de processos e coloca para executar se não terminou volta para a fila
     */
    @Override
    public synchronized void run() {
        while(true){
            if(!idProcesso.isEmpty()){
                Integer i = idProcesso.poll();
                Despachante d = new Despachante(i);
                Global.imprimir("Escalonador Round-Robin de CPU escolheu o processoid: "+i+", retirou-o da fila de prontos e o encaminhou ao Despachante");
              
                Thread t = new Thread(d);
                t.start();
               
                    Global.avisoTimer.lock();
                try {
                    Global.at.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(EscalonadorRR.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                     Global.avisoTimer.unlock();
                }
                    
                    
                    if(Global.terminou){
                       Global.memoria.removerProcesso(i);
                       Global.imprimir("Processo "+i+" terminou sua execução");
                        
                    }else{
                        idProcesso.add(i);
                    }
                 
                
                   
                
            }
        }
    }
    
}
