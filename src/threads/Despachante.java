/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

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
    public void run() {
        if(Global.memoria.existeProcesso(i)){
            Processo p = Global.memoria.getProcesso(i);
            Temporizador tempo = new Temporizador(p);
            
            
        }else{
            
        }
       
    }
    
}
