/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.rr.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.IIOException;


/**
 *
 * @author Daniel Leite
 */
public class Memoria {
    private int tamanho;
    private int[] memoria;
    private List<Secao> processos;
    private List<Secao> livres;

    public Memoria(int tamanho) {
        this.tamanho = tamanho;
        memoria = new int[tamanho];
        for(int i =0;i<tamanho;i++){
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
     * @throws IOException Memoria cheia
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
        if(s.getTamanho() != p.getTp()){
            s.setFim(s.getBase()+p.getTp());
            
        }
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
        boolean contando = false;
        for (int i = 0; i < tamanho; i++) {
            if(memoria[i] == -1 && !contando){
                contando = true;
                s.setBase(i);
            }else if(contando){
                s.setFim(i);
                contando = false;
                livres.add(s);
                s = new Secao();
            }
            if(contando && i == tamanho-1){
                s.setFim(i);
                livres.add(s);
            }
        }
        
    }
    
   /**
    * Remover o Processo da memoria
    * 
    * @param id ID do processo
    * @return Retorna o processo removido da memoria
    */
    public Processo removerProcesso(int id){
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
    public Processo removerPrimeiroProcesso() throws IOException{
        if(!processos.isEmpty()){
            return removerProcesso(processos.get(0).getProcesso().getId());
        }
        throw new IOException();
    }
    /**
     * Verifica se existe o processo na memoria
     * @param id ID do processo
     * @return true se existir false se não existir
     */
    public boolean existeProcesso(int id){
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
        int menor = tamanho;
        for(Secao s: livres){
            if(menor< s.getTamanho()){
                menor = s.getTamanho();
            }
        }
        return menor;
    }
    
}
