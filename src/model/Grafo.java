package model;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
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


