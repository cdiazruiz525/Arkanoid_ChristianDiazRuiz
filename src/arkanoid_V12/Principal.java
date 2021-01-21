package arkanoid_V12;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;



public class Principal {

	private static int FPS = 60;
	private static List<Actor> actores = new ArrayList<Actor>();
	// Creo el JFrame y el Canvas fuera del main, para que sean accesibles a las demas clases
	private static JFrame ventana = null;
	private static MiCanvas canvas = null;
	
	public static void main(String[] args) {
		
		Pelota pelota = new Pelota(null, 350, 300);
		
		actores.add(pelota);
		
		ventana = new JFrame("Arkanoid"); // Creo la ventana
		ventana.setBounds(0, 0, 800, 600); // Le doy coordenadas y dimensiones
		
		// Creo el canvas y le paso la lista de actores
		canvas = new MiCanvas(actores);

		// Añado una plantilla (layout) a la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		// Agrego el Canvas (lienzo) al panel
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		// Doy dimensiones la ventana y le digo las coordenadas donde aparecera
		ventana.setBounds(0,0, 800, 600);
		// Muestro la ventana en pantalla
		ventana.setVisible(true);

	}

}
