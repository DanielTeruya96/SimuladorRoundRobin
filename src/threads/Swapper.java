/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.de.rr.model.Global;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
public class Swapper implements Runnable{
    Integer i;
    String chamado;
  

    Swapper(int id, String chamado) {
        this.i = id;
        this.chamado = chamado;
    }

    @Override
    public synchronized void run() {
        
       Processo p = Global.getProcessovyIdDisco(i);
      
       while(Global.memoria.menorTamanho() < p.getTp()){
           Global.imprimir("Swapper percebe que não há espaço ao precesso "+i+" na memória");
           
          
               Processo aux = Global.memoria.removerPrimeiroProcesso();
               Global.addBackingSotre(aux);
               
               Global.imprimir("Swapper retirou o processo "+aux.getId()+" para liberar espaço na memória, e o enviou ao disco");
               
               
           
       }
        Global.imprimir("Swapper percebe que há espaço no processo" + p.getId()+" na memória");
        Global.imprimir("Swapper tras o processo "+p.getId()+" dp disco e coloca na memoria");
        
       Global.memoria.alocarProcesso(p);
       Global.m.lock();
        try {
            Global.P.signalAll();
        } finally {
            Global.m.unlock();
        }
       
       Global.avisoSwapper.lock();
        try {
            if(chamado.equals(Global.DESPACHANTE)){
                Global.imprimir("Swapper avisa ao despachante que o processo "+p.getId()+" esta na memória");
            }else{
                this.notify();
                Global.imprimir("Swapper avisa o Escalonador de FCFS que o processo"+p.getId()+"esta na memória");
            }
            
            Global.as.signal();
        } finally {
            
            Global.avisoSwapper.unlock();
        }
        
       
    }
    
}
