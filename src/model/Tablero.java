package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
	
	public static final int _casillero_inicial = 0;
	public static final int _casillero_relleno = 1;
	public static final int _casillero_incorrecto = 2;
	private int _fila;
	private int _columna;
	private int[][] _tablero;
	public int[][] _solucion;
	public List<List<Integer>> _pistasEnFilas;
	public List<List<Integer>> _pistasEnColumnas;
	
	public Tablero(int fila, int columna) {
		_fila = fila;
		_columna = columna;
		_tablero = new int[fila][columna];
		_pistasEnFilas = new ArrayList<>();
		_pistasEnColumnas = new ArrayList<>();
		for(int i = 0; i < fila; i++) _pistasEnFilas.add(new ArrayList<>());
	    for(int j = 0; j < columna; j++) _pistasEnColumnas.add(new ArrayList<>());
	}
	
	//Cambia el valor del casillero: (blanco) -> 0 (negro) -> 1 (X) -> 2
	public void cambiarEstadoCasillero(int fila, int columna) {
		_tablero[fila][columna] = (_tablero[fila][columna] + 1) % 3;
	}
	
	public int obtenerEstadoCasillero(int fila, int columna) {
	    return _tablero[fila][columna];
	}

	public List<Integer> obtenerPistasColumna(int columna) {
		return _pistasEnColumnas.get(columna);
	}
	
	public List<Integer> obtenerPistasFila(int fila) {
		return _pistasEnFilas.get(fila);
	}
	
	public int obtenerFilas() {
		return _fila;
	}
	
	public int obtenerColumnas() {
		return _columna;
	}
	
	public void colocarPistasFila(int fila, List<Integer> pistas) {
	    _pistasEnFilas.set(fila, pistas);
	}

	public void colocarPistasColumna(int columna, List<Integer> pistas) {
	    _pistasEnColumnas.set(columna, pistas);
	}
	
	public void generarSolucionRandom() {
	    Random random = new Random();
	    _solucion = new int[_fila][_columna];
	    for(int i = 0; i < _fila; i++) {
	        for(int j = 0; j < _columna; j++) {
	            _tablero[i][j] = 0;
	            _solucion[i][j] = random.nextBoolean() ? _casillero_relleno : _casillero_inicial;
	        }
	    }
	}
	
	public void calcularPistas() {
	    // Calculamos pistas para las filas y columnas usando _solucion
	    for(int i = 0; i < _fila; i++) {
	        List<Integer> pistasFila = new ArrayList<>();
	        int contador = 0;
	        for(int j = 0; j < _columna; j++) {
	            if(_solucion[i][j] == _casillero_relleno) contador++;
	            else {
	                if(contador > 0) {
	                    pistasFila.add(contador);
	                    contador = 0;
	                }
	            }
	        }
	        if(contador > 0) pistasFila.add(contador);
	        if(pistasFila.isEmpty()) pistasFila.add(0);
	        _pistasEnFilas.set(i, pistasFila);
	    }

	    for(int j = 0; j < _columna; j++) {
	        List<Integer> pistasColumna = new ArrayList<>();
	        int contador = 0;
	        for(int i = 0; i < _fila; i++) {
	            if(_solucion[i][j] == _casillero_relleno) contador++;
	            else {
	                if(contador > 0) {
	                    pistasColumna.add(contador);
	                    contador = 0;
	                }
	            }
	        }
	        if(contador > 0) pistasColumna.add(contador);
	        if(pistasColumna.isEmpty()) pistasColumna.add(0);
	        _pistasEnColumnas.set(j, pistasColumna);
	    }
	}
	
	
	public boolean validarCasillero(int fila, int columna) {
	    if(_tablero[fila][columna] != _casillero_relleno) return true; //solo validamos rellenos
	    boolean filaValida = validarLinea(_tablero[fila], columna, _pistasEnFilas.get(fila));

	    int[] columnaActual = new int[_fila];
	    for(int i = 0; i < _fila; i++)
	        columnaActual[i] = _tablero[i][columna];

	    boolean columnaValida = validarLinea(columnaActual, fila, _pistasEnColumnas.get(columna));
	    return filaValida && columnaValida;
	}
	
	
	private boolean validarLinea(int[] linea, int pos, List<Integer> pistas) {
	    int bloque = 0;
	    int contador = 0;

	    for(int i = 0; i <= pos; i++) {
	        if(linea[i] == _casillero_relleno) contador++;
	        else {
	            if(contador > 0) {
	                if(bloque >= pistas.size() || contador > pistas.get(bloque)) return false;
	                bloque++;
	                contador = 0;
	            }
	        }
	    }
	    //si el casillero actual está dentro de un bloque permitido
	    return (contador > 0 && bloque < pistas.size() && contador <= pistas.get(bloque));
	}
	
	public boolean[][] comprobarTablero() {
	    boolean[][] resultado = new boolean[_fila][_columna];

	    for(int i = 0; i < _fila; i++) {
	        for(int j = 0; j < _columna; j++) {
	            int jugador = _tablero[i][j];
	            int solucion = _solucion[i][j];

	            if(solucion == _casillero_relleno) resultado[i][j] = (jugador == _casillero_relleno);
	            else
	            	resultado[i][j] = (jugador == _casillero_inicial || jugador == _casillero_incorrecto);
	        }
	    }
	    return resultado;
	}
	
	// Crea una copia del tablero actual
	public int[][] tableroActual(){
		int[][] copia = new int[_fila][_columna];
		
		for(int i = 0; i < _fila; i++) {
			for(int j = 0; j < _columna; j++) {
				copia[i][j] = _tablero[i][j];
			}
		}
		
		return copia;
	}
	
	public int[][] obtenerTableroSolucion(){
		return _solucion;
	}

}


