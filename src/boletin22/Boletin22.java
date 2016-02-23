
package boletin22;

import javax.swing.JOptionPane;

public class Boletin22 {

    public static void main(String[] args) {
        Libro.cargarLibros();
        do{
        int option = Integer.parseInt(JOptionPane.showInputDialog("MENU\n1)Engadir Libro\n2)Consultar prezo\n3)Visualizar libros\n4)Borrar libros con 0 unidades"
                + "\n5)Modificar precio\n6)Ordenar por titulo\n7)Buscar libros por autor\n8)Salir"));
        switch(option){
            case 1:
                Libro.engadir();
                break;
            case 2:
                Libro.consultar(JOptionPane.showInputDialog("Titulo del libro a consultar"));
                break;
            case 3:
                Libro.visualizar();
                break;
            case 4:
                Libro.borrar();
                break;
            case 5:
                Libro.modificarPrecio(JOptionPane.showInputDialog("Nombre del libro:"));
                break;
            case 6:
                Libro.ordearPorTitulo();
                JOptionPane.showMessageDialog(null, "Libros ordenador por titulo");
                break;
            case 7:
                Libro.buscarLibros(JOptionPane.showInputDialog("Autor:"));
                break;
            case 8:
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null,"Opción no válida");
        }
        }while(true);
    }
    
}
