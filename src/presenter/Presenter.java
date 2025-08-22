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
		_vista = new View();
	}
	
	public void construirTableroView() {
		_vista.crearTableroView(_casillas);
	}
	
//	public void asignarValoresACasilleros() {
//		
//	}
	
	public void controlarCasilleros(int casilla_x, int casilla_y, int casilleroSeleccionado) {
		_grafo.casilleroValido(casilla_x, casilla_y, casilleroSeleccionado);
	}
	
	
	
	
	
}
