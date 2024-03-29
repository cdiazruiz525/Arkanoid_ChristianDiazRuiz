package arkanoid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;


public class MiCanvas extends Canvas {
	
	// Canvas posee una Lista de actores, que por defecto sera nula
	List<Actor> actores = null;
	// BufferStrategy para conseguir tecnica de doble buffer
	private BufferStrategy strategy = null;
	
	/*
	 * Constructor de MiCanvas, el cual recibe una lista de actores
	 */
	public MiCanvas(List<Actor> actores) {
		super();
		this.actores = actores;
	}

	
	/**
	 * M�todo responsable del pintado de toda la escena, se ejecuta una vez por cada ciclo del programa
	 */
	public void paintWorld() {
		if (strategy == null) {
			// El Canvas se dibujara en pantalla con una estrategia de doble buffer
			this.createBufferStrategy(2);
			// Obtengo una referencia a la estrategia de doble buffer
			strategy = this.getBufferStrategy();
			// Resuelve un problema de sincronizaci�n de memoria de v�deo en Linux
			Toolkit.getDefaultToolkit().sync();
		}
		// Obtengo la brocha que me permite pintar en el doble buffer
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		// Pinto un rectangulo negro que ocupe toda la escena
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Pinto cada uno de los actores
		for (Actor a : this.actores) {
			a.paint(g);
		}
		
		// Muestro en la pantalla real lo que he pintado en la virtual 
		strategy.show();
	}
	
	
}
