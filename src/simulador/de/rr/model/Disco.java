/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.rr.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Daniel Leite
 */
class Disco {
    
    static Queue<Processo> filaEntrada;

    public Disco() {
        this.filaEntrada = new LinkedList<Processo>();
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
        for(Processo p: filaEntrada){
            if(p.getId() == id){
                aux = p;
                filaEntrada.remove(p);
                
            }
        }
        return aux;
    }
    
}
