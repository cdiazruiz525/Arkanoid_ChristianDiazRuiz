package arkanoid;

import java.awt.Color;
import java.awt.Graphics;


public class Pelota extends Actor {
	
	// Propiedades de la pelota
	private int velocidadX = -5; // Velocidad de la pelota
	private int velocidadY = 5; // En positivo para que la pelota sea lanzada hacia abajo desde un inicio
	
	
	// Constructores de Pelota
	
	public Pelota() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pelota(int x, int y) {
		super(x, y);
		this.ancho = 15;
		this.alto = 15;
		this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_PELOTA));
	}

	// Acciones de Pelota

	@Override
	public void paint(Graphics g) {
		// Pinto la Pelota
		g.setColor(Color.WHITE);
		g.fillOval(this.x, this.y, this.ancho, this.alto);

	}

	@Override
	public void actua() {
		// Hago la referencia a la instancia del objeto canvas
		MiCanvas canvas = Arkanoid.getInstance().getCanvas();
		
		// Establezco el movimiento horizontal, eje x
		this.x += this.velocidadX;
		// Si la pelota toca el borde del canvas, rebota
		if (this.x < 0 || (this.x + this.ancho) > canvas.getWidth()) {
			this.velocidadX = -this.velocidadX;
			ResourcesCache.getInstance().playSonido("Arkanoid-SFX-01.wav");
		}
		
		// Establezco el movimiento vertical, eje y
		this.y += this.velocidadY;
		// Si la pelota toca el borde del canvas, rebota
		if (this.y < 0 || (this.y + this.alto) > canvas.getHeight()) {
			this.velocidadY = -this.velocidadY;
			ResourcesCache.getInstance().playSonido("Arkanoid-SFX-01.wav");
		}	

	}

	// Getters y Setters

	public int getVelocidadX() {
		return velocidadX;
	}


	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}


	public int getVelocidadY() {
		return velocidadY;
	}


	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	
}
