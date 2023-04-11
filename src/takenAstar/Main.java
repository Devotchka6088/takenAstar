package takenAstar;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[] vector = crearVectorAleatorio();
		int[][] tablero = mezclarTablero(vector);
		mostrarTablero(tablero);
		jugarTakenAstar(tablero);
	}
	
	public static void jugarTakenAstar(int[][] tablero) {
		LinkedList<Nodo> colaPrioridad = new LinkedList<>();
		HashSet<String> visitados = new HashSet<>();
		Nodo nuevoNodo = new Nodo(tablero, null, 0, distanciaGlobal(tablero), 0);
	    colaPrioridad.add(nuevoNodo);
	    boolean noSolucion = true;

	    while (!colaPrioridad.isEmpty()) {
	    	Nodo nodoActual = colaPrioridad.pop();
	        int[][] tableroActual = nodoActual.getTablero();
	        int noRegreso = nodoActual.getNoRegreso();
	        if (verificarSolucion(tableroActual)) {
	            System.out.println("\nSolucion ENCONTRADA");
	            System.out.println("Nodos visitados > " + (visitados.size() + 1));
	            noSolucion = false;
	            break;
	        }
	        
	        visitados.add(Arrays.deepToString(tableroActual));
	        int[][] taTemp1 = new int [tablero.length][tablero.length];
			int[][] taTemp2 = new int [tablero.length][tablero.length];
			int[][] taTemp3 = new int [tablero.length][tablero.length];
			int[][] taTemp4 = new int [tablero.length][tablero.length];
			
			for(int i=0;i<tablero.length;i++) {
		        for (int j=0;j<tablero.length;j++) {
		        	taTemp1[i][j] = tableroActual[i][j];
		        	taTemp2[i][j] = tableroActual[i][j];
		        	taTemp3[i][j] = tableroActual[i][j];
		        	taTemp4[i][j] = tableroActual[i][j];
		        }
		    }
			
	        for(int i=0;i<tablero.length;i++) {
	        	for(int j=0;j<tablero.length;j++) {
	        		if(tableroActual[i][j]==(tablero.length*tablero.length)) {
	        			if(j-1>=0&&noRegreso!=1) {
	        				int temp = taTemp1[i][j-1];
	        				taTemp1[i][j-1] = taTemp1[i][j];
	        				taTemp1[i][j] = temp;
	        				if (!visitados.contains(Arrays.deepToString(taTemp1))) {
	        					Nodo nodoTemp = new Nodo(taTemp1, nodoActual, nodoActual.getDistancia()+1, distanciaGlobal(taTemp1), 1);
	        					colaPrioridad.add(nodoTemp);
	    	                }
	        			}
	        			if(i-1>=0&&noRegreso!=2) {
	        				int temp = taTemp2[i-1][j];
	        				taTemp2[i-1][j] = taTemp2[i][j];
	        				taTemp2[i][j] = temp;
	        				if (!visitados.contains(Arrays.deepToString(taTemp2))) {
	        					Nodo nodoTemp = new Nodo(taTemp2, nodoActual, nodoActual.getDistancia()+1, distanciaGlobal(taTemp2), 2);
	        					colaPrioridad.add(nodoTemp);
	        				}
	        			}
	        			if(j+1<tablero.length&&noRegreso!=3) {
	        				int temp = taTemp3[i][j+1];
	        				taTemp3[i][j+1] = taTemp3[i][j];
	        				taTemp3[i][j] = temp;
	        				if (!visitados.contains(Arrays.deepToString(taTemp3))) {
	        					Nodo nodoTemp = new Nodo(taTemp3, nodoActual, nodoActual.getDistancia()+1, distanciaGlobal(taTemp3), 3);
	        					colaPrioridad.add(nodoTemp);
	        				}
	        			}
	        			if(i+1<tablero.length&&noRegreso!=4) {
	        				int temp = taTemp4[i+1][j];
	        				taTemp4[i+1][j] = taTemp4[i][j];
	        				taTemp4[i][j] = temp;
	        				if (!visitados.contains(Arrays.deepToString(taTemp4))) {
	        					Nodo nodoTemp = new Nodo(taTemp4, nodoActual, nodoActual.getDistancia()+1, distanciaGlobal(taTemp4), 4);
	        					colaPrioridad.add(nodoTemp);
	        				}
	        			}
	        			i = tablero.length;
	        			j = tablero.length;
	        		}
	        	}
	        }
	        Collections.sort(colaPrioridad);
	    }
	    if(noSolucion) {
	    	System.out.println("\nSolucion NO encontrada");
	    	System.out.println("Nodos visitados > " + (visitados.size() + 1));
	    }
	}
	
	public static int distanciaGlobal(int[][] vector) {
		int disGlo = 0;
		for(int i=0;i<vector.length;i++) {
			for(int j=0;j<vector.length;j++) {
				disGlo += Math.abs((vector[i][j]-1)/vector.length-i)+
						 Math.abs((vector[i][j]-1)%vector.length-j);
			}
		}
		return disGlo;
	}

	public static int[] crearVectorAleatorio() {
		Scanner entrada = new Scanner(System.in);
		System.out.print(" > Ingrese el tamano del tablero : ");
		int lado = entrada.nextInt();
		entrada.close();
		int[] vector = new int[lado*lado];
		for(int i=0;i<(lado*lado);i++) {
			vector[i]=(i+1);
		}
		Random rnd = new Random();
        for (int i=vector.length-1;i>0;i--) {
            int j = rnd.nextInt(i+1);
            int temp = vector[i];
            vector[i] = vector[j];
            vector[j] = temp;
        }
		return vector;
	}
	
	public static int[][] mezclarTablero(int[] vector) {
		int lado = (int)Math.sqrt(vector.length);
		int[][] tablero = new int[lado][lado];
		int k = 0;
		for (int i=0;i<lado;i++) {
            for (int j=0;j<lado;j++) {
                tablero[i][j] = vector[k++];
            }
        }
		return tablero;
	}
	
	public static boolean verificarSolucion(int[][] tablero) {
		int count = 0;
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero.length;j++) {
				count++;
				if(tablero[i][j]!=count) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void mostrarTablero(int[][] tablero) {
		System.out.println();
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero.length;j++) {
				System.out.printf("[%d]",tablero[i][j]);
			}
			System.out.println();
		}
	}
}
