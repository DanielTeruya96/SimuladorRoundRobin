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
public class Secao implements Comparable<Secao>{
    
    private Processo p;
    private int base;
    private int fim;

    public Secao( int base, int fim) {
       
        this.base = base;
        this.fim = fim;
    }

    Secao() {
       
    }
    /**
     * Tamanho da Seção 
     * 
     * @return fim da seção - base
     */
    public int getTamanho(){
        return fim-base;
    }
    /**
     * Tamanho da Base
     * 
     * @return retorna a base da seção
     */
    public int getBase() {
        return base;
    }
    /**
     * Setar o tamanho da base
     * @param base 
     */
    public void setBase(int base) {
        this.base = base;
    }
    /**
     *  Retorna o Fim
     * @return 
     */
    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }
    /**
     * Setar o Processo
     * 
     * @param p 
     */
    public void setP(Processo p) {
        this.p = p;
    }
    
    @Override
    public int compareTo(Secao s) {
       return this.base - s.getBase();
    }

    public Processo getProcesso() {
        return this.p;
    }
    
}
