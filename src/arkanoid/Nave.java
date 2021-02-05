package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Nave extends Actor {
	
	// Propiedades de la nave
	private int ancho = 45, alto = 10; // Dimensiones de la nave en pantalla
	private boolean izquierda, derecha; // Variable booleanas que indican la direccion del movimiento de la nave
	private static int VELOCIDAD = 5; // Velocidad estatica de la nave
	
	public Nave() {
		super();
	}
	
	
	public Nave(int x, int y) {
		super(x, y);
		this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_NAVE));
	}
	
	// Acciones de nave
	
//	@Override
//	public void paint(Graphics g) {
//		// Creo un rectangulo rojo
//		g.setColor(Color.RED); // Selecciono el color de la brocha
//		g.fillRect(this.x, this.y, this.ancho, this.alto);
//
//	}

	@Override
	public void actua() {
		
		MiCanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al canvas de Arkanoid
		
		if (this.izquierda == true) { // Si el boolean izquierda esta en true
			this.x -= VELOCIDAD; // A la coordenada x le resto la VELOCIDAD
		}
		
		if (this.derecha == true) {
			this.x += VELOCIDAD;
		}
		
		// Compruebo si la nave sale por la derecha
		if (this.x > (canvas.getWidth() - this.ancho)) { // Si la nave sobrepasa las coordenadas
			this.x = canvas.getWidth() - this.ancho; // Muevo la nave al punto limite
		}
		
		// Compruebo si la nave sale por la izquierda
		if (this.x < 0) { // Si la nave sobrepasa las coordenadas
			this.x = 0; // Muevo la nave al punto limite
		}	
	}
	
	public void mueve(int x) {
		this.x = x;
		// Hago la referencia a la instancia del objeto canvas
		MiCanvas canvas = Arkanoid.getInstance().getCanvas();
		
		// Compruebo si la nave sale por la derecha
		if (this.x > (canvas.getWidth() - this.ancho)) { // Si la nave sobrepasa las coordenadas
			this.x = canvas.getWidth() - this.ancho; // Muevo la nave al punto limite
		}
		
		// Compruebo si la nave sale por la izquierda
		if (this.x < 0) { // Si la nave sobrepasa las coordenadas
			this.x = 0; // Muevo la nave al punto limite
		}	

	}
	
	/**
	 * Metodo que determina que tecla ha sido pulsada para controlar la direccion de la nave
	 * @param e
	 */
	public void keyPressed (KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT: // Si se detecta que ha sido presionada la flecha izquierda
			this.izquierda = true; // Cambio el izquierda a true;
			break;
			
		case KeyEvent.VK_RIGHT:
			this.derecha = true;
			break;
		}
	}
	
	/**
	 * Metodo que controla que tecla ha sido dejada de presionar
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT: // Si se detecta que ha sido liberada la flecha izquierda
			this.izquierda = false; // Cambio el izquierda a false;
			break;
			
		case KeyEvent.VK_RIGHT:
			this.derecha = false;
			break;
		}
	}
	
	
}
