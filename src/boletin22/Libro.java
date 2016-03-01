
package boletin22;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Libro implements Comparable<Libro>{
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
    
    public static ArrayList<Libro> copiarFichero(){
        ArrayList<Libro> lista = new ArrayList();
        String[] cadena;
        try(Scanner lector=new Scanner(new File("../src/boletin22/libros"))){
            while(lector.hasNextLine()){
                cadena=lector.nextLine().split(",");
                lista.add(new Libro(cadena[0],cadena[1],Float.parseFloat(cadena[2]),Integer.parseInt(cadena[3])));
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista;
    }
    
    public static Libro crearLibro(){
        String nome=JOptionPane.showInputDialog("Nome libro:");
        String autor=JOptionPane.showInputDialog("Autor libro:");
        float prezo=Float.parseFloat(JOptionPane.showInputDialog("Prezo libro:"));
        int unidades = Integer.parseInt(JOptionPane.showInputDialog("Unidades libro:"));
        return new Libro(nome,autor,prezo,unidades);
    }
    
    public static void engadir(){
        Libro l = crearLibro();
        PrintWriter escritor=null;
        FileWriter archivo = null;
        try{
            archivo=new FileWriter("../src/boletin22/libros",true);
            escritor=new PrintWriter(archivo);
            escritor.append("\n" + l.nome + "," + l.autor + "," + l.prezo + "," + l.unidades);   
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            escritor.close();
        }
    } 
    
    public static void consultar(String titulo){
        ArrayList<Libro> lista = copiarFichero();
        boolean notFound = true;
        for(Libro l:lista){
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
        Scanner sc = null;
        File f=null;
        try{
            f = new File("../src/boletin22/libros");
            sc=new Scanner(f);
            while(sc.hasNextLine()){
                conjunto+="\n"+sc.nextLine();
            }
            JOptionPane.showMessageDialog(null, conjunto);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            sc.close();
        }
        
    }
    
    public static void borrar(){
        ArrayList<Libro> lista = copiarFichero();
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).unidades==0){
                lista.remove(i);
            }
        }

        try(PrintWriter escritor = new PrintWriter(new FileWriter("../src/boletin22/libros",false))){
            for(Libro l:lista){
                escritor.println(l.nome + "," + l.autor + "," + l.prezo + "," + l.unidades);
            }
            JOptionPane.showMessageDialog(null, "Libros con 0 unidades eliminados satisfactoriamente");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    public static void modificarPrecio(String titulo,Float prezo){
        ArrayList<Libro> lista = copiarFichero();
        boolean notFound=true;
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).nome.equalsIgnoreCase(titulo)){
                lista.get(i).prezo=prezo;
                notFound=false;
                try(PrintWriter escritor=new PrintWriter(new File("../src/boletin22/libros"))){
                    for(Libro l:lista){
                        escritor.println(l.nome + "," + l.autor + "," + l.prezo + "," + l.unidades);
                    }
                }catch(IOException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                break;
            }
        }
        if(notFound){
            JOptionPane.showMessageDialog(null, "Libro no encontrado");
        }
    }
    
    public static void ordearPorTitulo(){
        ArrayList<Libro> lista = copiarFichero();
        Collections.sort(lista);
        try(PrintWriter escritor=new PrintWriter(new File("../src/boletin22/libros"))){
            for(Libro l:lista){
                escritor.println(l.nome + "," + l.autor + "," + l.prezo + "," + l.unidades);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void buscarLibros(String autor){
        ArrayList<Libro> lista = copiarFichero();
        String conjunto="";
        for(Libro l:lista){
            if(l.autor.equalsIgnoreCase(autor)){
                conjunto+=l.nome;
            }
        }
        if(!conjunto.isEmpty())
            JOptionPane.showMessageDialog(null, conjunto);
        else
            JOptionPane.showMessageDialog(null, "Libros no encontrados");
    }
    
    
}
