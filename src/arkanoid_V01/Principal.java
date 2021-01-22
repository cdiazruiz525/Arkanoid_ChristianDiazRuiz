package arkanoid_V01;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Principal {

	private static int FPS = 60;
	private static List<Actor> actores = new ArrayList<Actor>();
	// Creo el JFrame y el Canvas fuera del main, para que sean accesibles a las demas clases
	private static JFrame ventana = null;
	private static MiCanvas canvas = null;
	
	public static void main(String[] args) {
		
		creadorActores();
		
		ventana = new JFrame("Arkanoid"); // Creo la ventana
		
		// Creo el canvas y le paso la lista de actores
		canvas = new MiCanvas(actores);

		// Añado una plantilla (layout) a la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		// Agrego el Canvas (lienzo) al panel
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		// Doy dimensiones la ventana y le digo las coordenadas donde aparecera
		ventana.setBounds(0,0, 600, 800);
		// Muestro la ventana en pantalla
		ventana.setVisible(true);
		
		bucleJuego(); // Ejecuto el bucle que ejecuta el juego
	}
	
	/*
	 * Bucle del juego
	 */
	public static void bucleJuego() {
		int millisPorFrame = 1000/FPS; // 1 segundo son 1000 millisegundos, y quiero conseguir 60 imagenes o frames por segundo
		do {
			long millisAntesDeEscena = new Date().getTime(); // Guardo los millis que habia antes de pintar la escena
			
			canvas.repaint(); // Repinto la escena
			
			for (Actor a : actores) { // Recorro la Lista de actores mostrando a cada uno
				a.actua();
			}
			
			long millisDespuesDeEscena = new Date().getTime(); // Guardo los millis despues de pintar la escena
			// Con millisDepuesDeEscena menos millisAntesDeEscena, consigo los millis que tarda en procesar una escena
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeEscena - millisAntesDeEscena);
			// Con millisPausa consigo la cantidad de millisegundos que no se tiene que pintar la escena de nuevo
			int millisPausa = millisPorFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa; // Compruebo si millisPausa es menor que cero, en caso afirmativo millisPausa valdra 0
			// Mando a dormir al proceso principal durante los milllisPausa
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while (true);
	}
	
	/*
	 * Metodo que crea a cada actor y los introduce dentro la lista actores
	 */
	public static void creadorActores() {
		Pelota pelota = new Pelota(null, 350, 300);
		actores.add(pelota);
		Nave nave = new Nave(null, 270, 700);
		actores.add(nave);
		Ladrillo ladrillo = new Ladrillo(null, 10, 10);
		actores.add(ladrillo);
	}
	
	
	/*
	 * Metodo utilizado en proximas versiones, el cual permitira cerrar el programa al cerrar la ventana
	 */
//	public static void terminarProceso() {
//		String[] opciones = {}; // Creo un array de String con las opciones que apareceran en el cuadro de dialogo
//		// Guardo en la variable eleccion el valor de la eleccion, en cual 0 significara si y 1 no
//		int eleccion = JOptionPane.showOptionDialog(ventana,"¿Desea cerrar Arkanoid?","Salir de la aplicación",
//		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
//		if (eleccion == JOptionPane.YES_OPTION) { // Si eleccion es igual a YES (0)
//			System.exit(0); // El sistema saldra de la aplicacion
//		}
//	}
	

}
