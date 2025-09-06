package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class View {
	private JFrame frame;
	private JButton[][] botones;
	private VistaListener _listener;
	
	public View() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Nonograma");
	    frame.setBounds(100, 100, 300, 300); //posición X e Y, tamaño ancho | alto
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	
	public void mostrar() {
		frame.setVisible(true);
	}
	
	public void crearTableroView(int cantidadDeCasillas, List<List<Integer>> pistasFilas, List<List<Integer>> pistasColumnas) {
	    frame.getContentPane().removeAll();

	    int totalFilas = cantidadDeCasillas + 1;
	    int totalColumnas = cantidadDeCasillas + 1;

	    // Panel principal con BorderLayout
	    JPanel panelPrincipal = new JPanel(new BorderLayout());

	    // Panel del tablero (GridLayout)
	    JPanel panelTablero = new JPanel(new GridLayout(totalFilas, totalColumnas, 2, 2));
	    botones = new JButton[cantidadDeCasillas][cantidadDeCasillas];

	    for(int fila = 0; fila < totalFilas; fila++) {
	        for(int col = 0; col < totalColumnas; col++) {
	            if(fila == 0 && col == 0) panelTablero.add(new JLabel(""));
	            else if (fila == 0) {
	                List<Integer> vistaCol = pistasColumnas.get(col - 1);
	                JLabel label = new JLabel(_listener.pistasToString(vistaCol), SwingConstants.CENTER);
	                label.setOpaque(true);
	                label.setBackground(Color.LIGHT_GRAY);
	                panelTablero.add(label);
	            } else if (col == 0) {
	                List<Integer> vistaFila = pistasFilas.get(fila - 1);
	                JLabel label = new JLabel(_listener.pistasToString(vistaFila), SwingConstants.CENTER);
	                label.setOpaque(true);
	                label.setBackground(Color.LIGHT_GRAY);
	                panelTablero.add(label);
	            } else {
	                JButton boton = new JButton();
	                boton.setFocusPainted(false);
	                boton.setOpaque(true);
	                boton.setBackground(Color.WHITE);

	                final int _fila = fila - 1;
	                final int _columna = col - 1;

	                boton.addActionListener(e -> {
	                    if(_listener != null) _listener.enCasillaSeleccionada(_fila, _columna, true); 
	                });

	                botones[_fila][_columna] = boton;
	                panelTablero.add(boton);
	            }
	        }
	    }

	    //Botón comprobar
	    JButton btnComprobar = new JButton("Ver resultado");
	    btnComprobar.addActionListener(e -> {
	        if(_listener != null) _listener.comprobar(); 
	    });
	    
	    //Botón jugar de nuevo
	    JButton btnReiniciar = new JButton("Jugar de nuevo");
	    btnReiniciar.addActionListener(e -> {
	        if (_listener != null) _listener.reiniciarJuego();
	    });
	    panelPrincipal.add(btnReiniciar, BorderLayout.NORTH);

	    //Panel principal
	    panelPrincipal.add(panelTablero, BorderLayout.CENTER);
	    panelPrincipal.add(btnComprobar, BorderLayout.SOUTH);
	    frame.setContentPane(panelPrincipal);
	    frame.revalidate();
	    frame.repaint();
	}

	
	
	
	//Se llama a este método desde el Presenter luego de un cambio de estado para actualizarlo en el View.
	public void actualizarCasillero(int fila, int columna, int estado, boolean valido) {
		JButton b = botones[fila][columna];		
		//Casillero BLANCO
	    b.setText("");
	    b.setBackground(Color.WHITE);
	    //Casillero VELDE
	    if(estado == model.Tablero._casillero_relleno) b.setBackground(Color.GREEN);
	    //Casillero X
	    else if(estado == model.Tablero._casillero_incorrecto) {
	        b.setText("X");
	        b.setForeground(Color.RED);
	        b.setBackground(Color.WHITE);
	    }   
	}
	

	public void crearListener(VistaListener listener) {
		_listener = listener;
	}
	
	

}
