/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//1000 3 2 1 500 5 15 0 700 1 5 2 600 2 10
package simulador.de.rr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import simulador.de.rr.model.Global;
import simulador.de.rr.model.Memoria;
import simulador.de.rr.model.Processo;
import threads.CriadorProcesso;
import threads.EscalonadorLongoPrazo;
import threads.EscalonadorRR;

/**
 *
 * @author Daniel Leite
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
       BlockingQueue<Processo> filaEntrada = new LinkedBlockingQueue<>();
       BlockingQueue<Processo> filaPronto = new LinkedBlockingQueue<>();
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
        CriadorProcesso cp = new CriadorProcesso(processos, idProntos);
        Thread threadCriadorProcesso = new Thread(cp);
        threadCriadorProcesso.start();
        
        while(true){
            boolean terminou = true;
            for(boolean a: Global.flag){
                if(!a){
                    terminou = false;
                }
            }
            if(terminou){
                break;
            }
        }
        Global.imprimir("Fim da observação");
        
    
        
        
        
    }
    
}
