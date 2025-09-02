package presenter;
import java.util.*;
import model.Tablero;
import view.View;
import view.VistaListener;

public class Presenter implements VistaListener {
	Tablero _tablero;
	View _vista;
	
	public void inicializarTableroPresenter(Tablero tablero, View vista) {
		_tablero = tablero;
		_vista = vista;
		_vista.crearListener(this);
		_tablero.generarSolucionRandom();
		_tablero.calcularPistas();
		_vista.crearTableroView(_tablero.obtenerFilas(), _tablero._pistasEnFilas, _tablero._pistasEnColumnas);
		_vista.mostrar();
	}
	
	//LLAMA A LA INTERFACE DE VISTA PARA COMUNICARSE CON LA CLASE VISTA
	@Override
	public void enCasillaSeleccionada(int fila, int columna, boolean rellena) {
		_tablero.cambiarEstadoCasillero(fila, columna);
		int estado = _tablero.validarCasillero(fila, columna);
	    _vista.actualizarCasillero(fila, columna, estado);
	}
	
	
	private List<List<Integer>> obtenerPistasFilas() {
        List<List<Integer>> pistas = new ArrayList<>();
        for (int i = 0; i < _tablero.obtenerFilas(); i++) {
            pistas.add(_tablero.obtenerPistasFila(i));
        }
        return pistas;
    }
	
	private List<List<Integer>> obtenerPistasColumnas() {
        List<List<Integer>> pistas = new ArrayList<>();
        for (int i = 0; i < _tablero.obtenerColumnas(); i++) {
            pistas.add(_tablero.obtenerPistasColumna(i));
        }
        return pistas;
    }
	
}
