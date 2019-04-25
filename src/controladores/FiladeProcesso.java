/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import simulador.de.rr.model.Processo;

/**
 *
 * @author Daniel Leite
 */
public class FiladeProcesso {
    
   private  Queue<Processo> disco;

    public FiladeProcesso() {
        disco = new LinkedList<Processo>();
        
    }
    
    public synchronized void add(Processo p){
        disco.add(p);
    }
    
    public synchronized Processo get(){
        return disco.poll();
    }
    
    public synchronized Processo get(int id) throws IOException{
        for(Processo p: disco){
            if(p.getId() == id){
                disco.remove(p);
                return p;
            }
        }
        throw new IOException("id nao encontrado");
    }
    
  
    
}
