package model;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
	public static final int _blanco = 0;
	public static final int _negro = 1;
	public static final int _equis = 2;
	
	private int[][] _matriz;
	private int _vertices;
	
	//La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices) {
		_vertices = vertices;
		_matriz = new int[vertices][vertices];
	}
	
	//obtengo el valor de la matriz
	public int obtenerValorDeMatriz(int fila, int columna) {
		return _matriz[fila][columna];
	}
	
	// Cambia el valor del casillero: 0 (blanco) -> 1 (negro) -> 2 (X) --> 0 ...
	public void cambiarEstadoCasillero(int fila, int columna) {
		_matriz[fila][columna] = (_matriz[fila][columna] + 1) % 3;
	}
	
	public boolean casilleroValido(int fila, int columna, int casilleroSeleccionado) {
		return _matriz[fila][columna] == casilleroSeleccionado;
	}

	//tamanio de la matriz
	public int tamano() {
		return _matriz.length;
	}
	
	//si los casilleros son simétricos entonces estoy ante una casilla válida sino es una X
	protected boolean casillerosSimetricos(int valorDefila, int valorDecolumna) {
		return _matriz[valorDefila] == _matriz[valorDecolumna];
	}

}


