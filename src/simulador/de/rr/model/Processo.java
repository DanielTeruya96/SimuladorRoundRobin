/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.rr.model;

/**
 *
 * @author Daniel Leite
 */
public class Processo implements Comparable<Processo> {

    
    
    private int id;
    private int tp;// tamanho do processo
    private int tc;// tempo de chegada na fila de pronto
    private int tb;// tempo de CPU Burst
    
    public Processo(int id, int tp, int tc, int tb) {
        this.id = id;
        this.tp = tp;
        this.tc = tc;
        this.tb = tb;
    }
    
    public int getId() {
        return id;
    }
    /**
     * Devolve o tamanho do processo
     *
     * @return inteiro do tamanho do processo
     */
    public int getTp() {
        return tp;
    }
    /**
     * 
     * @return Retrona o tempo de chegada
     */
    public int getTc() {
        return tc;
    }
    /**
     * 
     * @return  Retorna o tempo de CPU Burst
     */
    public int getTb() {
        return tb;
    }

    @Override
    public int compareTo(Processo t) {
        return this.tc-t.getTc();
    }
    
    @Override
    public String toString(){
        
        return "ID: "+id+" tp: "+tp+"tc: "+tc+"tb: "+tb;
    }
    
    

    
  
    
    
    
}
