package view;
import java.util.List;

public interface VistaListener {
	void enCasillaSeleccionada(int fila, int columna, boolean rellena);

	void comprobar();

	String pistasToString(List<Integer> vistaCol);

	void reiniciarJuego();

	void alternarVista();
}
