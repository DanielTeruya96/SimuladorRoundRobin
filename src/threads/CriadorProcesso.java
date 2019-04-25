/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
public class CriadorProcesso implements Runnable{
    
    private List<Processo> processos;
    private BlockingQueue<Processo> filaProcesso;

    public CriadorProcesso(List<Processo> processos,  BlockingQueue filaProcesso) {
         this.processos = processos;
        this.filaProcesso = filaProcesso;
      
    }
    
    
    
    

    @Override
    public void run() {
        int tempo = 0,auxTempo = 0;
       
        for(Processo p: processos){
            
            tempo = p.getTc();
            auxTempo = tempo - auxTempo;
            
            try {
                
                Thread.sleep(auxTempo*1000);
                auxTempo = tempo;
                System.out.println("Criador de Processo criou  o processo de ID "+p.getId()+" e o colocou na fila de Entrada");
              
                filaProcesso.add(p);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(CriadorProcesso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
