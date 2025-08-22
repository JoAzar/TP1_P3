import presenter.Presenter;

public class Main {

	public static void main(String[] args) {
		
		//grafo de 5x5
		Presenter presenter = new Presenter();
		presenter.inicializarTableroModel(4);
		presenter.inicializarTableroView();
		presenter.construirTableroView();
	}

}
