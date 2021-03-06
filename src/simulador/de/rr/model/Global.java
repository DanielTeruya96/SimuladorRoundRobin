/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.rr.model;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Leite
 */
public class Global {
    
    public final static String ESCALONADORFCFS = "EscalonadorFcfs";
    public final static String DESPACHANTE = "Despachante";
    
    public static boolean terminou = false;
    public static Memoria memoria;
    public static Disco disco;
    public static Integer tq;
    public static Lock avisoTimer;
    public static Condition at;
    public static Lock avisoSwapper;
    public static Condition as;
    public static Lock m = new ReentrantLock () ;
    public static Condition P = m.newCondition () ;
    public static boolean flag[];
    
    public static void inicializaFlag(int n){
        flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            flag[i] = false;
        }
    }
    
    public synchronized static boolean discoVazio(){
        return disco.vazio();
    }
    
    public synchronized static void inicializaMSwapper(){
        avisoSwapper = new ReentrantLock();
        as = avisoSwapper.newCondition();
    }
    
    public synchronized static void inicializarMutex(){
        avisoTimer = new ReentrantLock();
        at = avisoTimer.newCondition();
    }

    public static void criarMemoria(int tmp) {
        memoria = new Memoria(tmp);
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

    public static int getIDProcessoDisco() {
        return disco.getIDProcesso();
    }

    

    public static int getIDProcessoTamahoDisco() {
         return disco.getProcessoTamanhoDisco();
    }
    
    public synchronized static void imprimir(String s){
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");
        String dataFormatada = formatador.format(data);
        System.out.println(dataFormatada+" : "+s);
    }
    
    public static void addBackingSotre(Processo p){
        disco.setProcessoBackingSotre(p);
    }
    
    public synchronized  static boolean finaliza(){
         boolean termino = true;
            for(boolean a: flag){
                if(!a){
                    termino = false;
                }
            }
            return termino;
    }
    
    
    
    
}
