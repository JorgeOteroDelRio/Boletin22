
package boletin22;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Libro implements Comparable<Libro>{
    private static ArrayList<Libro> libreria = new ArrayList();
    private String nome;
    private String autor;
    private float prezo;
    private int unidades;
    
    public Libro(String nome,String autor,float prezo, int unidades){
        this.nome=nome;
        this.autor=autor;
        this.prezo=prezo;
        this.unidades=unidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPrezo() {
        return prezo;
    }

    public void setPrezo(float prezo) {
        this.prezo = prezo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public ArrayList<Libro> getLibreria() {
        return libreria;
    }

    public void setLibreria(ArrayList<Libro> libreria) {
        Libro.libreria = libreria;
    }

    @Override
    public String toString() {
        return "Libro{" + "nome=" + nome + ", autor=" + autor + ", prezo=" + prezo + "€" + ", unidades=" + unidades + '}';
    }
    
    @Override
    public int compareTo(Libro t) {
        if(this.nome.compareTo(t.nome)>0){
            return 1;
        }else if(this.nome.compareTo(t.nome)==0){
            return 0;
        }else{
            return -1;
        }
    }
    
    public static void cargarLibros(){
        String[] registro;
        Scanner lector = null;
        try{
            lector=new Scanner(new File("src/boletin22/libros.txt"));
            while(lector.hasNextLine()){
                registro=lector.nextLine().split(",");
                libreria.add(new Libro(registro[0],registro[1],Float.parseFloat(registro[2]),Integer.parseInt(registro[3])));
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally{
            lector.close();
        }
    }
    
    public static Libro crearLibro(){
        String nome=JOptionPane.showInputDialog("Nome libro:");
        String autor=JOptionPane.showInputDialog("Autor libro:");
        Float prezo=Float.parseFloat(JOptionPane.showInputDialog("Prezo libro:"));
        int unidades = Integer.parseInt(JOptionPane.showInputDialog("Unidades libro:"));
        return new Libro(nome,autor,prezo,unidades);
    }
    
    public static void escribirFichero(){
        PrintWriter escritor=null;
        try{
            escritor=new PrintWriter(new File("src/boletin22/libros.txt"));
            for(Libro libro : libreria){
                escritor.println(libro.nome + "," + libro.autor + "," + libro.prezo + "," + libro.unidades);
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            escritor.close();
        }
    }
    
    public static void engadir(){
        Libro l = crearLibro();
        libreria.add(l);
        escribirFichero();
    } 
    
    public static void consultar(String titulo){
        boolean notFound = true;
        for(Libro l:libreria){
            if(l.nome.equalsIgnoreCase(titulo)){
                JOptionPane.showMessageDialog(null,"El precio de ese libro es de " + l.prezo + "€");
                notFound=false;
                break;
            }
        }
        
        if(notFound){
            JOptionPane.showMessageDialog(null, "Libro no encontrado");
        }
        
    }
    
    public static void visualizar(){
        String conjunto="";
        for(Libro l:libreria){
            conjunto+="\n"+l;
        }
        JOptionPane.showMessageDialog(null, conjunto);
    }
    
    public static void borrar(){ 
        for(int i=0;i<libreria.size();i++){
            if(libreria.get(i).unidades==0){
                libreria.remove(libreria.get(i));
            }
        }
        escribirFichero();
        JOptionPane.showMessageDialog(null,"Libros con 0 unidades eliminados satisfactoriamente");
    }
    
    public static void modificarPrecio(String titulo){
        boolean notFound = true;
        for(int i=0;i<libreria.size();i++){
            if(libreria.get(i).nome.equalsIgnoreCase(titulo)){
                libreria.get(i).prezo=Float.parseFloat(JOptionPane.showInputDialog("Novo prezo: "));
                notFound=false;
            }
        }
        
        if(notFound){
            JOptionPane.showMessageDialog(null, "No se encontró ningún libro con ese nomnbre");
        }
    }
    
    public static void ordearPorTitulo(){
        Collections.sort(libreria);
    }

    public static void buscarLibros(String autor){
        String conjunto="";
        for(Libro l:libreria){
            if(l.autor.equalsIgnoreCase(autor)){
                conjunto+=l.nome;
            }
        }
        JOptionPane.showMessageDialog(null, conjunto);
    }
    
    
}
