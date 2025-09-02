import model.Tablero;
import presenter.Presenter;
import view.View;

public class Main {

	public static void main(String[] args) {
		
		//grafo de 5x5
		Presenter presenter = new Presenter();
		View vista = new View();
		Tablero tablero = new Tablero(5,5);
		presenter.inicializarTableroPresenter(tablero, vista);
		
	}

}
