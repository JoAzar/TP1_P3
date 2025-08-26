package model;
import java.util.HashSet;
import java.util.Set;

public class Tablero {
	
	public static final int _casillero_inicial = 0;
	public static final int _casillero_relleno = 1;
	public static final int _casillero_incorrecto = 2;
	
	private int _fila;
	private int _columna;
	private int[][] _tablero;
	private int[][] _pistasEnFilas;
	private int[][] _pistasEnColumnas;
	
	//La cantidad de vertices esta predeterminada desde el constructor
	public Tablero(int fila, int columna) {
		_fila = fila;
		_columna = columna;
		_tablero = new int[fila][columna];
		_pistasEnFilas = new int[columna][];
		_pistasEnColumnas = new int[fila][];
		
	}
	
	// Cambia el valor del casillero: 0 (blanco) -> 1 (negro) -> 2 (X) --> 0 ...
	public void cambiarEstadoCasillero(int fila, int columna) {
		_tablero[fila][columna] = (_tablero[fila][columna] + 1) % 3;
	}
	
	public boolean casilleroValido(int fila, int columna, int casilleroSeleccionado) {
		return _tablero[fila][columna] == casilleroSeleccionado;
	}

	//tamanio de la matriz
	public int tamano() {
		return _tablero.length;
	}
	
	//MARCA EL CASILLERO COMO CORRECTO/NEGRO
	public void marcarCasilleroRelleno(int fila, int columna) {
		_tablero[fila][columna] = _casillero_relleno;
	}
	
	//MARCA EL CASILLERO COMO INCORRECTO/CRUZ
	public void marcarCasilleroIncorrecto(int fila, int columna) {
		_tablero[fila][columna] = _casillero_incorrecto;
	}
	
	//PONE EL CASILLERO EN INICIAL/BLANCO
	public void limpiarCasillero(int fila, int columna) {
		_tablero[fila][columna] = _casillero_inicial;
	}

}


