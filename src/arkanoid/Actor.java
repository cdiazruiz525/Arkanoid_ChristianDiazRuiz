package arkanoid;

import java.awt.Graphics;

/*
 * Clase abstracta de Actor
 */
public abstract class Actor {
	
	// Propiedades de actor
	protected int x, y; // Coordenadas x, y
	
	// Constructores de actor
	public Actor() {
		super();
	}
	
	
	public Actor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public abstract void paint(Graphics g); // Metodo que pinta a actor
	
	public abstract void actua(); // Metodo que permite a cada actor realizar sus acciones
	
	// Getters y Setters de actor
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}
