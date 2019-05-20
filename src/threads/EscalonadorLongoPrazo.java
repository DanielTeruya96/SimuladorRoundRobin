
package threads;


import java.util.concurrent.BlockingQueue;

import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.de.rr.model.Global;
import simulador.de.rr.model.Processo;


/**
 *
 * @author Daniel Leite
 */
public class EscalonadorLongoPrazo implements Runnable{
   
   
    
    private  BlockingQueue<Integer> idProcesso;

 

   


    public EscalonadorLongoPrazo(BlockingQueue<Integer> idProntos) {
       this.idProcesso = idProntos;
       
    }

  

    
    
    
    
    @Override
    public synchronized void run() {
        
        while(!Global.finaliza()){
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
                    
                }    
                Swapper sw = new Swapper(id,Global.ESCALONADORFCFS);
                Thread tr = new Thread(sw);
                Global.imprimir("Escalonador FCFS solicitou que o Swapper traga id="+id+" a memória");
                tr.start();
                synchronized(tr){
                    try {
                        tr.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EscalonadorLongoPrazo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Global.imprimir("Escalonador é avisado pelo Swapper");
                }
                
                idProcesso.add(id);
                Global.imprimir("Escalonador FCFS colocou id="+id+"na fila de prontos");
                
                    
                
                
                
               
            
            }
        }
    }

    private synchronized void alocaProcesso(Processo p) throws InterruptedException {
        Global.memoria.alocarProcesso(p);
    }
    
}
