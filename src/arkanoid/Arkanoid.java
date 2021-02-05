package arkanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Arkanoid {

	private static int FPS = 60;
	private List<Actor> actores = new ArrayList<Actor>();
	private List<Actor> actoresParaIncorporar = new ArrayList<Actor>(); // Lista de actores que deben ser añadidos a actores
	private List<Actor> actoresParaEliminar = new ArrayList<Actor>(); // Lista de actores que deben ser eliminados de actores
	// Creo el JFrame y el Canvas fuera del main, para que sean accesibles a las demas clases
	private JFrame ventana = null;
	private MiCanvas canvas = null;
	Nave jugador = null;
	
	// Creo la instancia de Arkanoid
	private static Arkanoid instance = null;
	
	
	public static Arkanoid getInstance() {
		if (instance == null) {
			instance = new Arkanoid();
		}
		return instance;
	}
	
	
	/**
	 * Metodo que devuelve el Canvas
	 * @return
	 */
	public MiCanvas getCanvas() {
		return canvas;
	}


	public Arkanoid() {
		creadorActores(); // Llamo al metodo que crea a los actores
		
		ventana = new JFrame("Arkanoid"); // Creo la ventana
		
		// Doy dimensiones la ventana y le digo las coordenadas donde aparecera
		ventana.setBounds(0,0, 600, 800);
		
		// Creo el canvas y le paso la lista de actores
		canvas = new MiCanvas(actores);
		
		
		// Controlador del movimiento del raton
		canvas.addMouseMotionListener(new MouseAdapter() { // Clase anonima con adaptador
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				jugador.mueve(e.getX());
			}
		});
		
		
		// Controlador de los eventos del teclado
		canvas.addKeyListener(new KeyAdapter() { // Clase anonima con adaptador
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				jugador.keyPressed(e); // Envio el evento e al metodo creado en el objeto jugador de tipo Nave
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				jugador.keyReleased(e); // Envio el evento e al metodo creado en el objeto jugador de tipo Nave
				
			}
		});
		
		
		// Añado una plantilla (layout) a la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		// Agrego el Canvas (lienzo) al panel
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventana.setIgnoreRepaint(true);
		// Muestro la ventana en pantalla
		ventana.setVisible(true);
		
		
		// Controlador del cierre de la ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() { // Uso de WindowsListener y WindowsAdapter
			@Override
			public void windowClosing(WindowEvent e) {
				terminarAplicacion();
			}
		});
	}

	
	/**
	 * Metodo main de Arkanoid
	 * @param args
	 */
	public static void main(String[] args) {
		ResourcesCache.getInstance().cargarRecursosEnMemoria();
		
		
		Arkanoid.getInstance().bucleJuego();
	}
	
	
	/*
	 * Bucle del juego
	 */
	public void bucleJuego() {
		int millisPorFrame = 1000/FPS; // 1 segundo son 1000 millisegundos, y quiero conseguir 60 imagenes o frames por segundo
		do {
			// Bucle que comprubea constantemente si el canvas tiene el focus, en caso de no tenerlo se lo da
			if (ventana.getFocusOwner() != null && !ventana.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
			
			long millisAntesDeEscena = new Date().getTime(); // Guardo los millis que habia antes de pintar la escena
			
			canvas.paintWorld();
			
			for (Actor a : actores) { // Recorro la Lista de actores mostrando a cada uno
				a.actua();
			}
			
			// Llamo al metodo que detecta si se han dado colisiones entre los actores
			detectaColisiones();

			// Llamo al metodo que me actualiza la lista de actores
			actualizaActores();
			
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
	private List<Actor> creadorActores() {
		
		// Construyo la pelota
		Pelota pelota = new Pelota(350, 300);
		actores.add(pelota);
		
		// Construyo a el jugador
		jugador = new Nave(270, 700);
		actores.add(jugador);
		
		// Creo un array de tipo Color, para darle color a cada fila de ladrillos
		Color colores[] = new Color[] {Color.RED, Color.YELLOW, Color.PINK, Color.BLUE, Color.GREEN, Color.ORANGE};
		// Realizo el bucle for que crea los ladrillos
		int y = 40; // En y = 40 sera la primera coordenada donde se creara el primer ladrillo 
		for (int i = 0; i < 6; i++) {
			int x = 10;
			for (int j = 0; j < 12; j++) {
				Ladrillo ladrillo = new Ladrillo(x, y, colores[i]);
				x += 48;
				actores.add(ladrillo);
			}
			y += 27;
		}
		return actores;
	}
	
	
	/**
	 * Metodo llamado para añadir nuevos actores
	 */
	public void incorporaActor(Actor a) {
		this.actoresParaIncorporar.add(a); // Añado este actor a la lista de actoresParaIncorporar
	}
	
	
	/**
	 * Metodo llamado para eliminar actores
	 */
	public void eliminaActor(Actor a) {
		this.actoresParaEliminar.add(a); // Añado este actor a la lista de actoresParaEliminar
	}
	
	
	/**
	 * Metodo que actualiza la lista añadiendo y eliminando los actores determinados
	 */
	public void actualizaActores() {
		// Incorporo cada actor a la lista de actores
		for (Actor a : this.actoresParaIncorporar) {
			this.actores.add(a);
		}
		this.actoresParaIncorporar.clear(); // Limpio la lista de actores para incorporar
		
		// Elimino los actores que se deben borrar
		for (Actor a : this.actoresParaEliminar) {
			this.actores.remove(a);
		}
		this.actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar	
	}
	
	
	/**
	 * Detecta si hay colision entre dos actores, y en caso de haber, avisa a los dos
	 */
	public void detectaColisiones() {
		for (Actor actor1 : this.actores) {
			// Creo un rectangulo de las dimensiones del actor1
			Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
			// Compruebo un actor con cualquier otro actor
			for (Actor actor2 : this.actores) {
				// Evito comparar un actor consigo mismo, ya que provocara una colision
				if (!actor1.equals(actor2)) {
					// Creo un rectangulo de las dimensiones del actor2
					Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getAlto());
					// Si los dos rectangulos tienen una interseccion, aviso a los actores de dicha colisio
					if (rect1.intersects(rect2)) {
						actor1.colisionaCon(actor2); // El actor1 colisiona con el actor2
						actor2.colisionaCon(actor1); // El actor2 colisiona con el actor1
					}
				}
			}
		}
	}
	
	
	/*
	 * Metodo utilizado en proximas versiones, el cual permitira cerrar el programa al cerrar la ventana
	 */
	private void terminarAplicacion() {
		String[] opciones = {"Aceptar","Cancelar"}; // Creo un array de String con las opciones que apareceran en el cuadro de dialogo
		// Guardo en la variable eleccion el valor de la eleccion, en cual 0 significara si y 1 no
		int eleccion = JOptionPane.showOptionDialog(ventana,"¿Desea cerrar Arkanoid?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) { // Si eleccion es igual a YES (0)
			System.exit(0); // El sistema saldra de la aplicacion
		}
	}
	
}
