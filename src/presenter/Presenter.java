package presenter;
import model.Grafo;
import view.View;

public class Presenter {
	Grafo _grafo;
	View _vista;
	int _casillas;
	
	public void inicializarTableroModel(int casillas) {
		_grafo = new Grafo(casillas);
		_casillas = casillas;
	}
	
	public void inicializarTableroView() {
		_vista = new View(this);
	}
	
	public void construirTableroView() {
		_vista.crearTableroView(_casillas);
	}
	
	public void asignarValorACasillero(int fila, int columna) {
		_grafo.cambiarEstadoCasillero(fila, columna);
		int _estado = _grafo.obtenerValorDeMatriz(fila, columna);
		_vista.actualizarCasillero(fila, columna, _estado);
	}
	
//	public void asignarValoresACasilleros() {
//		
//	}
	
	public void controlarCasilleros(int casilla_x, int casilla_y, int casilleroSeleccionado) {
		_grafo.casilleroValido(casilla_x, casilla_y, casilleroSeleccionado);
	}
	
	
	
	
	
}
