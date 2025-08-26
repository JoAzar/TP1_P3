package view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import presenter.Presenter;

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
	    frame.setVisible(true);
	}
	
	public void crearTableroView(int cantidadDeCasillas) {
		frame.getContentPane().removeAll();

	    int totalFilas = cantidadDeCasillas + 1;
	    int totalColumnas = cantidadDeCasillas + 1;

	    frame.getContentPane().setLayout(new GridLayout(totalFilas, totalColumnas, 2, 2));
	    botones = new JButton[cantidadDeCasillas][cantidadDeCasillas];

	    for (int fila = 0; fila < totalFilas; fila++) {
	        for (int col = 0; col < totalColumnas; col++) {
	            if (fila == 0 && col == 0) {
	                //Esquina superior izquierda vacía
	                frame.getContentPane().add(new JLabel(""));
	            } else if (fila == 0) {
	                //Primera fila → etiquetas de columna
	                JLabel label = new JLabel("C" + col, SwingConstants.CENTER);
	                label.setOpaque(true);
	                label.setBackground(Color.LIGHT_GRAY);
	                frame.getContentPane().add(label);
	            } else if (col == 0) {
	                //Primera columna → etiquetas de fila
	                JLabel label = new JLabel("F" + fila, SwingConstants.CENTER);
	                label.setOpaque(true);
	                label.setBackground(Color.LIGHT_GRAY);
	                frame.getContentPane().add(label);
	            } else {
	                //Botones del tablero
	                JButton boton = new JButton();
	                boton.setFocusPainted(false);
	                boton.setOpaque(true);
	                boton.setBackground(Color.WHITE);
	                
	                final int _fila = fila - 1;
	                final int _columna = col - 1;
	                
	                //boton.addActionListener(e -> presenter.asignarValorACasillero(_fila, _columna));
	                boton.addActionListener(e -> {
	                    if (_listener != null) {
	                        _listener.enCasillaSeleccionada(_fila, _columna, true); 
	                    }
	                });
	                
	                //Casilleros
	                botones[_fila][_columna] = boton;
	                
	                frame.getContentPane().add(boton);
	            }
	        }
	    }

	    //Actualiza la ventana
	    frame.revalidate();
	    frame.repaint();
			
	}
	
	//Se llama a este método desde el Presenter luego de un cambio de estado para actualizarlo en el View.
	public void actualizarCasillero(int fila, int columna, int estado) {
		JButton b = botones[fila][columna];
		
		// Casillero en BLANCO (estándar)
		b.setText("");
		b.setBackground(Color.WHITE);
		
		// Casillero en NEGRO
		if(estado == model.Tablero._casillero_relleno) {
			b.setBackground(Color.BLACK);
		}
		// Casillero en X
		else if(estado == model.Tablero._casillero_incorrecto){
			b.setText("X");
			b.setForeground(Color.RED);
			b.setBackground(Color.WHITE);
		}
	}
	
	public void crearListener(VistaListener listener) {
		_listener = listener;
	}
	
	

}
