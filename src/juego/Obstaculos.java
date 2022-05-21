package juego;

import entorno.Entorno;
import java.awt.Color;
public class Obstaculos {
	int x;
	int y;
	int ancho=35;
	int alto=35;
	Color color=new Color(200,250,50);
	
	public Obstaculos(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
	}
	
}
