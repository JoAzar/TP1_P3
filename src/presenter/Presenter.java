package presenter;
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
		_vista.crearTableroView(_tablero.tamano());
	}
	
	//LLAMA A LA INTERFACE DE VISTA PARA COMUNICARSE CON LA CLASE VISTA
	@Override
	public void enCasillaSeleccionada(int fila, int columna, boolean rellena) {
	    int estado = rellena ? Tablero._casillero_relleno : Tablero._casillero_incorrecto;
	    _tablero.cambiarEstadoCasillero(fila, columna);
	    _vista.actualizarCasillero(fila, columna, estado);
	}
	
	
	
	
	
	
	
	
	
}
