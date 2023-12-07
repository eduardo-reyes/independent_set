package classes.src;

import java.util.*;
import java.util.Scanner;
import java.io.*;

/*
 * @author: Eduardo Alfonso Reyes López.
 */

/* 
 * Clase Arista: representa una arista de
 * una gráfica dirigida. Tiene dirección,
 * así que va de un vértice de origen a
 * uno de destino.
 */
class Arista {
    int origen, destino;

    /*Constructor Arista */
    Arista(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }

    /*
     * Método de imprime en consola una representación de una arista.
     */
    public void imprimirArista() {
        System.out.println("Arista. Origen: " + origen + ". Destino: " + destino);
    }
}

/*
 * Clase Gráfica: Gráfica dirigida.
 */
class Grafica {

    /*
     * Clase Vértice:
     * Vértice de una gráfica dirigida.
     */
    static class Vertice {
        int valor;

        /* Constructor Vertice */
        Vertice(int valor) {
            this.valor = valor; // El valor de un vértice es su etiqueta.
        }
    };

    List<List<Vertice>> adyacencias = new ArrayList<>();

    /* Constructor Grafica */
    public Grafica(List<Integer> vertices, List<Arista> aristas) {
        for(int i = 0; i <= vertices.size(); i++) {
            adyacencias.add(i, new ArrayList<>());
        }
        for(Arista a : aristas) {
            adyacencias.get(a.origen).add(new Vertice(a.destino));
        }
    }

    /*
     * Método que imprime en consola la representación de una gráfica.
     * @param Grafica graf una gráfica dirigida.
     */
    public static void imprimirGrafica(Grafica graf) {
        int v_origen = 0;
        int longitud = graf.adyacencias.size();

        System.out.print("Vértices: ");
        for(int i = 1; i <= longitud; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        while(v_origen < longitud) {
            if(!graf.adyacencias.get(v_origen).isEmpty())
                System.out.println("Adyacencias vértice: " + v_origen);
            for(Vertice v : graf.adyacencias.get(v_origen)) {
                System.out.println("Arista: " + (v_origen) + "->" + (v.valor) + "\t");
            }
            v_origen++;
        }
        System.out.println();
    }

    /* 
     * Método que encuentra uno de los posibles conjuntos independientes de
     * vértices dentro de una gráfica dirigida tal que cualquier vértice de
     * la gráfica es alcanzable por una traycetoria de longitud a lo más dos.
     */
    public void conjuntoIndependiente() {
        System.out.println("ALGORITMO CONJUNTO INDEPENDIENTE");

        List<List<Vertice>> copia = new ArrayList<>(adyacencias);
        List<Integer> independiente = new ArrayList<>();
        int[] numVecinos = null;
        int maximo = 0;
        int indVertice = 0;
        List<Vertice> vecindad = null;
        int contador = 0;
        int paso = 1;

        while(true) {

            contador = 0;
            for(int i = 0; i < copia.size(); i++) {
                if(copia.get(i) == null) {
                    contador++;
                }
            }
            if(contador == copia.size() - 1) break;

            System.out.println("PASO " + paso);
            paso++;

            numVecinos = numVecinos(copia);
            maximo = max(numVecinos);
            indVertice = encuentraValor(numVecinos, maximo);
            vecindad = copia.get(indVertice);
            independiente.add(indVertice);
            copia = quitaVecindad(indVertice, vecindad, copia);

            System.out.print("Vértices subgráfica: ");
            for(int k = 1; k < copia.size(); k++) {
                if(copia.get(k) != null) {
                    System.out.print(k + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Conjunto independiente: " + Arrays.toString(independiente.toArray()));
    }

    /* 
     * Cuenta el número de vecinos de los vértices de una gráfica.
     * @param List<List<Vertice>> adyacencias lista de las adyacencias de los vértices.
     * @return int[] arreglo con el número de vecinos por vértice.
     */
    private int[] numVecinos(List<List<Vertice>> adyacencias) {
        int longitud = adyacencias.size();
        int[] vecinos = new int[longitud];
        for(int i = 0; i < longitud; i++) {
            if(adyacencias.get(i) != null) {
                List<Vertice> v = adyacencias.get(i);
                vecinos[i] = v.size();
            }
            else {
                vecinos[i] = 0;
            }
        }
        return vecinos;
    }

    /*
     * Encuentra el elemnto máximo en un arreglo de enteros.
     * @param int[] a un arreglo de enteros.
     * @return int el elemento máximo.
     */
    private int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
        }
        return max;
    }

    /*
     * Encuentra el valor pasado como parámetro en un arreglo.
     * @param int[] a el arreglo de enteros.
     * @param int valor el valor a encontrar.
     * @return el índice dentro del arreglo con el valor.
     */
    private int encuentraValor(int[] a, int valor) {
        int indice = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == valor) {
                indice = i;
            }
        }
        System.out.println("Vértice seleccionado : " + indice);
        return indice;
    }

    /*
     * Elimina los vértices alrededor de un cierto
     * vértice dentro de la lista de adyacencias.
     * @param int vertice índice del vértice.
     * @param List<Vertice> vecindad es la vecindad del vértice.
     * @param List<List<Vertice>> adyacencias es la lista de adyacencias.
     * @return List<List<Vertice>> la lista de adyacencias sin los vértices.
     */
    private List<List<Vertice>> quitaVecindad(int vertice, List<Vertice> vecindad, List<List<Vertice>> adyacencias) {
        System.out.print("Vecindad: ");
        for(Vertice v : vecindad) {
            adyacencias.set(v.valor, null);
            System.out.print(v.valor + " ");       
        }
        System.out.println();
        System.out.print("Otras adyacencias: ");
        adyacencias.set(vertice, null);
        for(int i = 0; i < adyacencias.size(); i++) {
            if(adyacencias.get(i) == null) {
                continue;
            }
            List<Vertice> lista = adyacencias.get(i);
            for(Vertice v : lista) {
                if(v.valor == vertice) {
                    adyacencias.set(i, null);
                    System.out.print(i + " ");
                }
            }
        }
        System.out.println();
        return adyacencias;
    }

    /*
     * Método que imprime una lista de vértices con sus adyacencias.
     * @param List<List<Vertice>> lista es la lista de vértices
     */
    public static void imprimirLista(List<List<Vertice>> lista) {
        int i = 0;
        int longitud = lista.size();
        System.out.println("Tamaño: " + longitud);
        while(i < longitud) {
            if(lista.get(i) == null) {
                System.out.println(i + " vacío");
                i++;
                continue;
            }
            if(!lista.get(i).isEmpty())
                System.out.println("Adyacencias vértice: " + i);
            for(Vertice v : lista.get(i)) {
                System.out.println("Arista: " + (i) + "->" + (v.valor) + "\t");
            }
            i++;
        }
    }
}

class Prac1 {

    private static String path = "src/Grafica.txt";

    /*
     * Método que lee el archivo de entrada y lo transforma en una
     * lista de enteros que contendrá a los vértices y a las aristas.
     * @return List<List<Integer>> la lista con los vértices y aristas.
     */
    private static List<List<Integer>> readFile(String file) {
        String ruta = null;
        if(file == "" || file == null) {
            ruta = path;
            System.out.println("Ejecutando demo.");
        }
        else {
            ruta = "src/" + file;
        }
        BufferedReader lector = null;
        String[] datos = null;
        List<List<Integer>> datosLimpios = new ArrayList<>();

        try {
            lector = new BufferedReader(new FileReader(ruta));
            String renglon = "";

            while((renglon = lector.readLine()) != null) {
                List<Integer> listaRenglon = new ArrayList<>();
                datos = renglon.split(",");
                for(String valor : datos) {
                    listaRenglon.add(Integer.parseInt(valor));
                }
                datosLimpios.add(listaRenglon);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingrese un archivo válido. Leer README.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lector != null) {
            try {
                lector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return datosLimpios;
    }
    public static void main(String[] args) {
        String archivo = null;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el nombre del archivo: ");
            archivo = sc.nextLine();
            if (sc != null) {
                try {
                    sc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(archivo.isEmpty()) {
                System.out.println("No se ingresó ningún archivo.");
                archivo = "";
            }
        }catch(Exception e) {
            e.getMessage();
        }

        List<List<Integer>> listaGraf = readFile(archivo); 
        List<Integer> vertices = listaGraf.get(0);
        List<Arista> aristas = new ArrayList<>();
        for(int i = 1; i < listaGraf.size(); i++) {
            List<Integer> arista = listaGraf.get(i);
            Arista aristaNueva = new Arista(arista.get(0),arista.get(1));
            aristas.add(aristaNueva); 
        }
        Grafica graf = new Grafica(vertices, aristas);
        Grafica.imprimirGrafica(graf);
        graf.conjuntoIndependiente();
    }
}