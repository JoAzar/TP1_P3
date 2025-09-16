package presenter;
import java.util.List;
import model.Tablero;
import view.View;
import view.VistaListener;

public class Presenter implements VistaListener {
	Tablero _tablero;
	View _vista;
	private boolean mostrandoSolucion = false;
	private int[][] tableroUsuario;
	
	public void inicializarTableroPresenter(int filas, int columnas, View vista) {
		_tablero = new Tablero( filas,  columnas); // Se crea el tablero aquí
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
	            boolean correcto = resultado[i][j];
	            
	            if(!correcto) {
	            	todoCorrecto = false;
	            }
	        }
	    }
	    
	    // Mensaje si gano o perdio
	    tableroUsuario = _tablero.tableroActual();
	    if(todoCorrecto){
	    	javax.swing.JOptionPane.showMessageDialog(null, "¡Ganaste! El tablero está completamente correcto");
	    	
	    	// Impedir que el usuario siga cambiando los casilleros + deshabilitar boton comprobar
	    	_vista.setTableroEditable(false);
	    	_vista.mostrarTablero(tableroUsuario); //Agrega las "X" si el usuario no las marcó (se ve más prolijo)
	    	_vista.mostrarBotonComprobar(false);
	    	}
	    else{
	    	javax.swing.JOptionPane.showMessageDialog(null, "Perdiste. Hay casilleros incorrectos.");
	    	
	    	// Mostrar solución y deshabilitar boton comprobar
	    	_vista.setTableroEditable(false);
	    	_vista.mostrarBotonAlternar(true);
	    	_vista.mostrarTablero(tableroUsuario); //Agrega las "X" si el usuario no las marcó (se ve más prolijo)
	    	_vista.mostrarBotonComprobar(false);
	    	_vista.setTextoBotonAlternar("Ver solución");
	    }
	    
	    
	}
	
	public String pistasToString(List<Integer> pistas) {
	    if(pistas.isEmpty()) return "0";
	    StringBuilder sb = new StringBuilder();
	    for(int n : pistas)
	        sb.append(n).append(" ");
	    return sb.toString().trim();
	}
	
//	public void reiniciarJuego() {
//		_tablero.generarSolucionRandom();
//	    _tablero.calcularPistas();
//	    _vista.crearTableroView(_tablero.obtenerFilas(), _tablero._pistasEnFilas, _tablero._pistasEnColumnas);
//	    _vista.mostrarBotonAlternar(false);
//	    _vista.mostrarBotonComprobar(true);
//	    mostrandoSolucion = false;
//	}
//	AGREGADO DESDE ACA PARA LOS NIVELES
	
	@Override
	public void reiniciarJuego() {
	    // Aquí puedes llamar a una nueva vista o un JOptionPane para elegir el nivel
	    Object[] niveles = {"Fácil", "Medio", "Difícil"};
	    String nivelElegido = (String) javax.swing.JOptionPane.showInputDialog(null,
	        "Elige un nuevo nivel de dificultad:",
	        "Reiniciar Juego",
	        javax.swing.JOptionPane.PLAIN_MESSAGE,
	        null,
	        niveles,
	        niveles[0]);

	    if (nivelElegido == null) {
	        return; // El usuario canceló la selección
	    }

	    int filas = 0;
	    int columnas = 0;

	    if (nivelElegido.equals("Fácil")) {
	        filas = Nivel.FACIL.getFilas();
	        columnas = Nivel.FACIL.getColumnas();
	    } else if (nivelElegido.equals("Medio")) {
	        filas = Nivel.MEDIO.getFilas();
	        columnas = Nivel.MEDIO.getColumnas();
	    } else if (nivelElegido.equals("Difícil")) {
	        filas = Nivel.DIFICIL.getFilas();
	        columnas = Nivel.DIFICIL.getColumnas();
	    }
	    
	    // Crear un nuevo tablero con las dimensiones elegidas
	    _tablero = new Tablero(filas, columnas);
	    _tablero.generarSolucionRandom();
	    _tablero.calcularPistas();
	    
	    // Recrear la vista con el nuevo tablero
	    _vista.crearTableroView(_tablero.obtenerFilas(), _tablero._pistasEnFilas, _tablero._pistasEnColumnas);
	    _vista.mostrarBotonAlternar(false);
	    _vista.mostrarBotonComprobar(true);
	    mostrandoSolucion = false;
	}
	// Cambiar entre el tablero del resultado del usuario y la solución
	@Override
	public void alternarVista() {
		if(!mostrandoSolucion) {
			// Mostrar tablero de solución
			_vista.mostrarTablero(_tablero.obtenerTableroSolucion());
			_vista.setTextoBotonAlternar("Ver tu resultado");
			mostrandoSolucion = true;
		}
		else {
			// Mostrar tablero respuesta del usuario
			_vista.mostrarTablero(tableroUsuario);
			_vista.setTextoBotonAlternar("Ver solución");
			mostrandoSolucion = false;
		}
		
	}
	 // Define los niveles del juego   AGREGADO PARALOS NIVELES
    public enum Nivel {
        FACIL(5, 5),
        MEDIO(10, 10),
        DIFICIL(15, 15);

        private final int filas;
        private final int columnas;

        Nivel(int filas, int columnas) {
            this.filas = filas;
            this.columnas = columnas;
        }

        public int getFilas() {
            return filas;
        }

        public int getColumnas() {
            return columnas;
        }
    }
	public void setVista(View vista) {
		// TODO Auto-generated method stub
		
	}	
}
