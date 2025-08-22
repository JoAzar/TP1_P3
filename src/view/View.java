package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class View {
	private JFrame frame;

	public View() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Cosodoku no me acuerdo el nombre 123");
	    frame.setBounds(100, 100, 300, 300); //posición X e Y, tamaño ancho | alto
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // Hacer visible la ventana
	    frame.setVisible(true);
	}
	
	public void crearTableroView(int cantidadDeCasillas) {
		frame.getContentPane().removeAll();

	    int totalFilas = cantidadDeCasillas + 1;
	    int totalColumnas = cantidadDeCasillas + 1;

	    frame.getContentPane().setLayout(new GridLayout(totalFilas, totalColumnas, 2, 2));

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
	                boton.setBackground(new Color(192, 97, 203));
	                frame.getContentPane().add(boton);
	            }
	        }
	    }

	    //Actualiza la ventana
	    frame.revalidate();
	    frame.repaint();
			
	}
	
	

}
