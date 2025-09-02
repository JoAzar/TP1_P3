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
	
	//Cambia el valor del casillero: 0 (blanco) -> 1 (negro) -> 2 (X) -> 0
	public void cambiarEstadoCasillero(int fila, int columna) {
		if(_tablero[fila][columna] == _casillero_inicial) _tablero[fila][columna] = _casillero_relleno;
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
        for(int i = 0; i < _fila; i++) {
            for(int j = 0; j < _columna; j++) 
                _tablero[i][j] = random.nextBoolean() ? 1 : 0;
        }
    }
	
	public void calcularPistas() {
        for(int i = 0; i < _fila; i++) {
            List<Integer> pistasFila = new ArrayList<>();
            int contador = 0;
            for(int j = 0; j < _columna; j++) {
                if(_tablero[i][j] == 1) contador++;
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
                if(_tablero[i][j] == 1) contador++;
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
	
	//=========================================== METODOS A MEJORAR ==================================================================
	
	//validarLinea y ValidarCasilleros están mal, hay que revisarlos
	
	private boolean validarLinea(int[] linea, int pos, List<Integer> pistas) {
	    int bloqueIndex = 0;
	    int contador = 0;

	    for(int i = 0; i < linea.length; i++) {
	        if(linea[i] == _casillero_relleno) {
	            contador++;
	            // Si excede la pista del bloque actual, es incorrecto
	            if(bloqueIndex >= pistas.size() || contador > pistas.get(bloqueIndex)) {
	                if(i == pos) return false; // solo falla el casillero actual
	            }
	        } else { //línea en blanco
	            if(contador > 0) {
	                //finalizó un bloque
	                bloqueIndex++;
	                contador = 0;
	            }
	        }
	    }

	    //Si el casillero tocado está dentro de un bloque parcial o coincide con una pista, es válido
	    int bloquePos = 0;
	    int suma = 0;
	    for(int p : pistas) {
	        suma += p;
	        if(pos < suma + bloquePos) return true; //está dentro de un bloque permitido
	        bloquePos += 1; //espacio entre bloques
	    }
	    return false; //si no cae en ningún bloque permitido
	}

	public int validarCasillero(int fila, int columna) {
	    if(_tablero[fila][columna] == _casillero_inicial) return _casillero_incorrecto;
	    boolean filaValida = validarLinea(_tablero[fila], columna, _pistasEnFilas.get(fila));
	    int[] columnaActual = new int[_fila];
	    for(int i = 0; i < _fila; i++) columnaActual[i] = _tablero[i][columna];
	    boolean columnaValida = validarLinea(columnaActual, fila, _pistasEnColumnas.get(columna));
	    return (filaValida && columnaValida) ? _casillero_relleno : _casillero_incorrecto;
	}
	
	//=============================================================================================================


}


