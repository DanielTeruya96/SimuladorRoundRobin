
//1000 3 2 1 500 5 15 0 400 1 5 2 600 2 10 
package simulador.de.rr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import simulador.de.rr.model.Global;
import simulador.de.rr.model.Processo;
import threads.CriadorProcesso;
import threads.EscalonadorLongoPrazo;
import threads.EscalonadorRR;

/**
 *
 * @author Daniel Teruya
 */
public class SimuladorDeRR {
    
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada[] = sc.nextLine().split(" ");
        Global.imprimir("Inicio da Observação");
        
       
       List<Processo> processos = new ArrayList<Processo>();
  
       BlockingQueue<Integer> idProntos = new LinkedBlockingDeque<>();
     
       Global.inicializarMutex();
       Global.inicializaMSwapper();
       
       
        int tmp,n,tq;
        tmp = Integer.parseInt(entrada[0]);
        n = Integer.parseInt(entrada[1]);
        tq = Integer.parseInt(entrada[2]);
        Global.criarMemoria(tmp);
        Global.criarDisco();
        Global.setTq(tq);
        Global.inicializaFlag(n); 
        for (int i = 3; i < (n*4)+3; i+=4) {
            int id,tp,tc,tb;
            id = Integer.parseInt(entrada[i]);
            tp = Integer.parseInt(entrada[i+1]);
            tc = Integer.parseInt(entrada[i+2]);
            tb = Integer.parseInt(entrada[i+3]);
            Processo p = new Processo(id,tp,tc,tb);
            processos.add(p);
        }
        Collections.sort(processos);
        CriadorProcesso cp = new CriadorProcesso(processos);
        Thread threadCriadorProcesso = new Thread(cp);
        threadCriadorProcesso.start();
         
        EscalonadorLongoPrazo ep = new EscalonadorLongoPrazo(idProntos);
        Thread threadEscalonadorLongoPrazo = new Thread(ep);
        threadEscalonadorLongoPrazo.start();
       
        
        EscalonadorRR er = new EscalonadorRR(idProntos);
        Thread threadEscalonadorRR = new Thread(er);
        threadEscalonadorRR.start(); 
        
        while(!Global.finaliza()){
            
        }
        Global.imprimir("Fim da observação");
        
    
        
        
        
    }
    
}
