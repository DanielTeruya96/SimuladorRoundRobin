/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.rr.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Daniel Leite
 */
public class Global {
    
    public static boolean terminou = false;
    public static Memoria memoria;
    public static Disco disco;
    public static Integer tq;

    public static void criarMemoria(int tmp, Lock m, Condition P) {
        memoria = new Memoria(tmp, m, P);
    }

    public static void criarDisco() {
       disco = new Disco();
    }
    
    public static void addProcessoDisco(Processo p){
        disco.addProcesso(p);
    }
    
    public static Processo getProcessoDisco(){
        return disco.getProcesso();
    }
    public static Processo getProcessovyIdDisco(int id){
        return disco.removePorID(id);
    }

    public static void setTq(Integer tqa) {
       tq = tqa;
    }
    
    
    
    
}
