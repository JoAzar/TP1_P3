package presenter;
import java.util.List;
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
		int estado = _tablero.obtenerEstadoCasillero(fila, columna);
		boolean valido = _tablero.validarCasillero(fila, columna);
	    _vista.actualizarCasillero(fila, columna, estado, valido);
	}
	
	//ESTE METODO ES LLAMADO DESDE EL BOTON COMPROBAR DE LA VISTA Y LLAMA AL TABLERO
	public void comprobar() {
	    boolean[][] resultado = _tablero.comprobarTablero();
	    boolean todoCorrecto = true;

	    for(int i = 0; i < resultado.length; i++) {
	        for(int j = 0; j < resultado[i].length; j++) {
	            int estado = _tablero.obtenerEstadoCasillero(i, j);
	            boolean correcto = resultado[i][j];

	            //Actualiza la vista
	            _vista.actualizarCasillero(i, j, estado, correcto);

	            //Imprime en consola || todos los print hay que borrarlos para agregar un pop up que diga que ganó
	            if(correcto) System.out.println("Casillero (" + i + "," + j + ") correcto");
	            else {
	                System.out.println("Casillero (" + i + "," + j + ") incorrecto");
	                todoCorrecto = false;
	            }
	        }
	    }

	    if(todoCorrecto) System.out.println("¡El tablero está completamente correcto!");
	    else System.out.println("Hay casilleros incorrectos");
	}
	
	public String pistasToString(List<Integer> pistas) {
	    if(pistas.isEmpty()) return "0";
	    StringBuilder sb = new StringBuilder();
	    for(int n : pistas)
	        sb.append(n).append(" ");
	    return sb.toString().trim();
	}
	
	public void reiniciarJuego() {
		_tablero.generarSolucionRandom();
	    _tablero.calcularPistas();
	    _vista.crearTableroView(_tablero.obtenerFilas(), _tablero._pistasEnFilas, _tablero._pistasEnColumnas);
	}
	
}
