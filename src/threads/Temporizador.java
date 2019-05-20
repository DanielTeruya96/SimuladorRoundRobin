/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.de.rr.model.Global;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
class Temporizador implements Runnable{
    Processo processo;
    Temporizador(Processo p) {
        processo = p;
    }

    @Override
    public synchronized void run() {
        long aux = 0;
       
        if(processo.getTb() > Global.tq){
            aux = Global.tq * 1000;
            processo.setTb(processo.getTb()-Global.tq);
        }else{
            aux = processo.getTb();
            Global.m.lock();
            try {
                Global.P.signal();
            } finally {
                Global.m.unlock();
            }
            Global.terminou = true;
            
            
            
        }
        try {
            Thread.sleep(aux);
            if(Global.terminou){
                
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            Global.avisoTimer.lock();
        try {
           Global.imprimir("Timer informa ao Escalonador Round-Robin de CPU que o processo "+processo.getId()+" atualmente em execução precisa ser retirado da CPU"); 
           if(Global.terminou){
               Global.imprimir("Processo "+processo.getId()+" terminou sua execução");
           }
            Global.at.signalAll();
        } finally {
            Global.avisoTimer.unlock();
        }
            
            
        
        
        
        
    }
    
}
