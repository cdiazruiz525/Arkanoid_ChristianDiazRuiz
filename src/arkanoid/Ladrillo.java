package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {
	
	// Propiedades de ladrillo
	private String nombre; // Nombre que recibe el ladrillo
	private Color color;
	
	// Constructores de ladrillo
	public Ladrillo() {
		super();
	}
	
	
	public Ladrillo(String nombre, int x, int y, Color color) {
		super(x, y);
		this.nombre = nombre;
		this.color = color;
		this.ancho = 40;
		this.alto = 20;
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

	/**
	 * Metodo que añade una accion del actor ante la colision
	 */
	@Override
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		if (a instanceof Pelota) { // Si colisiona con una instancia de la nave
			Arkanoid.getInstance().eliminaActor(this); // Añado a este actor, a la lista de eliminados
		}
	}
	
	
	// Getters y setters

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
