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
import simulador.de.rr.model.Memoria;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
public class Despachante implements Runnable{
    Integer i;
   

   

 

    Despachante(Integer i) {
         this.i = i;
    }
    

    @Override
    public synchronized void run() {
        
        if(!Global.memoria.existeProcesso(i)){
            Global.imprimir("Despachante percebe que o processo id "+i+" está no disco e solicita que o swapper traga id="+i+" á memoria");
            
            Swapper s = new Swapper(i,Global.DESPACHANTE);
            Thread t = new Thread(s);
            t.start();
            Global.avisoSwapper.lock();
            try {
                Global.as.await();
                Global.imprimir("despachante é avisado pelo Swapper que o processo "+i+" está na memória");
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Despachante.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                Global.avisoSwapper.unlock();
            }
            
            
        }
        Global.imprimir("Despachante percebe que o processo "+i+" esta na memória");
        
        Processo p = Global.memoria.getProcesso(i);
        Temporizador tp = new Temporizador(p);
        Thread t = new Thread(tp);
        Global.imprimir("Despachante Reiniciou o Timer com tq="+Global.tq+" e liberou a CPU ao processo ID: "+i);
        t.start();

        
            
        }
       
}
    

