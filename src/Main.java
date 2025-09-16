import javax.swing.JOptionPane;

import model.Tablero;
import presenter.Presenter;
import view.View;

public class Main {

	public static void main(String[] args) {
		 // Presentar un menú para que el usuario elija un nivel AGREGADO PARA LOS NIVELES DESDE ACA
        Object[] niveles = {"Fácil (5x5)", "Medio (10x10)", "Difícil (15x15)"};
        String nivelElegido = (String) JOptionPane.showInputDialog(null,
            "Elige un nivel de dificultad:",
            "Nonograma",
            JOptionPane.PLAIN_MESSAGE,
            null,
            niveles,
            niveles[0]);

        if (nivelElegido == null) {
            System.exit(0); // El usuario cerró la ventana
        }
        
        int filas = 0;
        int columnas = 0;

        // Asignar las dimensiones según el nivel elegido
        if (nivelElegido.equals("Fácil (5x5)")) {
            filas = 5;
            columnas = 5;
        } else if (nivelElegido.equals("Medio (10x10)")) {
            filas = 10;
            columnas = 10;
        } else if (nivelElegido.equals("Difícil (15x15)")) {
            filas = 15;
            columnas = 15;
        }
        //HASTA ACA

		// Grafo de 5x5
		Presenter presenter = new Presenter();
		View vista = new View();
		presenter.setVista(vista);
//		Tablero tablero = new Tablero(4,4);
		presenter.inicializarTableroPresenter(filas, columnas, vista);
		
	}

}
