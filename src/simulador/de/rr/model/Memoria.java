package simulador.de.rr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.imageio.IIOException;
import simulador.de.rr.model.Processo;
import simulador.de.rr.model.Secao;


/**
 *
 * @author Daniel Leite
 */
public class Memoria {
    private int tamanho;
    private int[] memoria;
    public List<Secao> processos;
    public List<Secao> livres;
 

    public Memoria(int tamanho) {
        this.tamanho = tamanho+1;
        
        memoria = new int[this.tamanho];
        for(int i =0;i<this.tamanho;i++){
            memoria[i] = -1;
        }
        processos = new ArrayList<>();
        livres = new ArrayList<>();
        Secao  sec = new Secao(0, tamanho);
        livres.add(sec);
    }
    /**
     * 
     * @param p Processo para colocar na memoria
     * 
     */
    public synchronized void alocarProcesso(Processo p) {
       
       
        
        for(Secao s: livres){
            if(s.getTamanho() >= p.getTp()){
                setarProcesso(s,p);
                
                return;
            }
        }
        
        
    }
    
    private void setarProcesso(Secao s, Processo p) {
        
        s.setFim(s.getBase()+p.getTp());
        s.setP(p);
        for(int i = s.getBase(); i<s.getFim();i++){
            memoria[i] = p.getId();
        }
        processos.add(s);
        Collections.sort(processos);
        atualizarListas();
        
    }

    private void atualizarListas() {
        
        livres = new ArrayList();
        Secao s = new Secao();
        int count = 0;
        boolean contando = false;
        for (int i = 0; i < tamanho; i++) {
            if(memoria[i] == -1 && !contando){
                contando = true;
                s.setBase(i);
            }else if(contando){
                count++;
                
            }
            if(contando && memoria[i] != -1){
                s.setFim(i);
                livres.add(s);
                s = new Secao();
                contando = false;
            }
            if(contando && i == tamanho-1){
                s.setFim(i);
                livres.add(s);
                s = new Secao();
                contando = false;
            }
        }
        
    }
    
   /**
    * Remover o Processo da memoria
    * Tem que notificar que removeu o processo
    * @param id ID do processo
    * @return Retorna o processo removido da memoria
    */
    public synchronized Processo removerProcesso(int id){
        Secao aux = new Secao();
        for(Secao s: processos){
            if(s.getProcesso().getId() == id){
                aux = s;
                break;
            }
        }
        desalocarProcesso(aux);
       
        return aux.getProcesso();
    }

    private void desalocarProcesso(Secao aux) {
        processos.remove(aux);
        for(int i = aux.getBase();i<aux.getFim();i++){
            memoria[i] = -1;
        }
        atualizarListas();
        
        
    }
    /**
     * Retira o primeiro processo da memoria
     * @return o processo que foi removido
     * @throws IOException 
     */
    public Processo removerPrimeiroProcesso(){
        if(!processos.isEmpty()){
            return removerProcesso(processos.get(0).getProcesso().getId());
        }
        return null;
    }
    /**
     * Verifica se existe o processo na memoria
     * @param id ID do processo
     * @return true se existir false se não existir
     */
    public synchronized boolean existeProcesso(int id){
        
        for(Secao s:processos){
            
            if(s.getProcesso().getId() == id){
               
                return true;
            }
        }
        return false;
    }
    /**
     * Menor tamnho disponivel para alocação
     * @return o numero do menor tamanho possivel para alocação
     */
    public int menorTamanho(){
        int menor = 0;
        for(Secao s: livres){
            
            if(menor < s.getTamanho()){
                menor = s.getTamanho();
            }
        }
        return menor;
    }

    public Processo getProcesso(Integer i) {
        for(Secao s: processos){
            if(s.getProcesso().getId() == i){
                return s.getProcesso();
            }
        }
        return null;
    }
    
}
