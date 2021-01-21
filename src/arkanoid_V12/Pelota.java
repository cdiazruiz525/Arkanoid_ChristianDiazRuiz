package arkanoid_V12;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	// Propiedades de la pelota
	private String nombre; // Nombre que recibe la pelota
	private int velocidadX = -5; // Velocidad de la pelota
	private int velocidadY = -5;
	
	public Pelota() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Pelota(String nombre, int x, int y) {
		super(x, y);
		this.nombre = nombre;
	}



	@Override
	public void paint(Graphics g) {

		// Creo un circulo verde
		g.setColor(Color.GREEN);
		g.fillOval(this.x, this.y, 30, 30);

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
