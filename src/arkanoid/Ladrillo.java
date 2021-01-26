package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {
	
	// Propiedades de ladrillo
	private String nombre; // Nombre que recibe el ladrillo
	private int ancho = 40, alto = 20; // Dimensiones del ladrillo en pantalla
	private Color color;
	
	// Constructores de ladrillo
	public Ladrillo() {
		super();
	}
	
	
	public Ladrillo(String nombre, int x, int y, Color color) {
		super(x, y);
		this.nombre = nombre;
		this.color = color;
	}
	
	// Acciones de ladrillo

	@Override
	public void paint(Graphics g) {
		// Creo un rectangulo blanco
		g.setColor(color); // Selecciono el color de la brocha
		g.fillRect(this.x, this.y, this.ancho, this.alto);

	}

	@Override
	public void actua() {
		

	}

	// Getters y setters
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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
