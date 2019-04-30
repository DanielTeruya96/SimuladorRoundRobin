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
    public void run() {
        long aux = 0;
        if(processo.getTb() > Global.tq){
            aux = Global.tq * 1000;
            
        }else{
            aux = processo.getTb();
            Global.terminou = true;
        }
        try {
            Thread.sleep(aux);
        } catch (InterruptedException ex) {
            Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Acordar o escalonador RR
        
        
    }
    
}
