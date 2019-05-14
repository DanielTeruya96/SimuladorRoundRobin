/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.rr.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Daniel Leite
 */
class Disco {
    
    static Queue<Processo> filaEntrada;
    public static List<Processo> backingStore;

    public Disco() {
        this.filaEntrada = new LinkedList<Processo>();
        backingStore = new ArrayList<>();
    }
    /**
     * Adiciona o processo p
     * @param p 
     */
    public synchronized  void addProcesso(Processo p){
        filaEntrada.add(p);
    }
    /**
     * Pega o primeiro processo da Fila
     * @return 
     */
    public synchronized  Processo getProcesso(){
        return filaEntrada.poll();
    }
    /**
     * Remove o processo com o mesmo id
     * @param id
     * @return 
     */
    public synchronized  Processo removePorID(int id){
        Processo aux = null;
        for(Processo p:backingStore){
            if(p.getId() == id){
                backingStore.remove(p);
                return p;
            }
        }
        
        
        for(Processo p: filaEntrada){
            if(p.getId() == id){
                
                filaEntrada.remove(p);
                return p;
                
            }
        }
        return aux;
    }
    
    public synchronized boolean vazio(){
        return filaEntrada.isEmpty();
    }

    @Override
    public String toString() {
        String s = "";
        for(Processo p: filaEntrada){
            s += p;
        }
        return "Disco{" +s+ '}';
    }

    public int getIDProcesso() {
        return filaEntrada.peek().getId();
    }

    public int getProcessoTamanhoDisco() {
       return filaEntrada.peek().getTp();
    }
    public void setProcessoBackingSotre(Processo p){
        backingStore.add(p);
    }
    
}
