package arkanoid_V01;

import java.awt.Color;
import java.awt.Graphics;

public class Nave extends Actor {
	
	// Propiedades de la nave
	private String nombre; // Nombre que recibe la nave
	private int ancho = 45, alto = 10; // Dimensiones de la nave en pantalla
	
	public Nave() {
		super();
	}
	
	
	public Nave(String nombre, int x, int y) {
		super(x, y);
		this.nombre = nombre;
	}
	
	// Acciones de nave
	
	@Override
	public void paint(Graphics g) {
		// Creo un rectangulo rojo
		g.setColor(Color.RED); // Selecciono el color de la brocha
		g.fillRect(this.x, this.y, this.ancho, this.alto);

	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub

	}

	// Getters y Setters
	
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
