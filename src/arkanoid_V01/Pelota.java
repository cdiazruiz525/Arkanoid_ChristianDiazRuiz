package arkanoid_V01;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	// Propiedades de la pelota
	private String nombre; // Nombre que recibe la pelota
	private int velocidadX = -5; // Velocidad de la pelota
	private int velocidadY = 5; // En positivo para que la pelota sea lanzada hacia abajo desde un inicio
	private int ancho = 15, alto = 15; // Dimensiones de la pelota en pantalla
	
	
	// Constructores de Pelota
	
	public Pelota() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pelota(String nombre, int x, int y) {
		super(x, y);
		this.nombre = nombre;
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
		// Establezco el movimiento horizontal, eje x
		this.x += this.velocidadX;
		// Si la pelota toca el borde, rebota
		if (this.x < 0 || this.x > 575) {
			this.velocidadX = -this.velocidadX;
		}
		
		// Establezco el movimiento vertical, eje y
		this.y += this.velocidadY;
		// Si la pelota toca el borde, rebota
		if (this.y < 0 || this.y > 755) {
			this.velocidadY = -this.velocidadY;
		}	

	}

	// Getters y Setters

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


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


	public int getAncho() {
		return ancho;
	}


	public void setAncho(int ancho) {
		this.ancho = ancho;
	}


	public int getAlto() {
		return alto;
	}


	public void setAlto(int alto) {
		this.alto = alto;
	}
	
}
