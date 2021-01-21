package arkanoid_V12;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;


public class MiCanvas extends Canvas {
	
	// Canvas posee una Lista de actores, que por defecto sera nula
	List<Actor> actores = null;
	
	/*
	 * Constructor de MiCanvas, el cual recibe una lista de actores
	 */
	public MiCanvas(List<Actor> actores) {
		super();
		this.actores = actores;
	}

	/*
	 * Metodo paint, que establece el fondo y pinta a cada actor de la lista
	 */
	@Override
	public void paint(Graphics g) {
		// Aplico el color del f
		this.setBackground(Color.WHITE);
		
		// Pinto cada uno de los actores
		for (Actor a : this.actores) {
			a.paint(g);
		}
		
	}
	
	
}
