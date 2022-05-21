package juego;
import java.awt.Color;
import entorno.Entorno;
public class Objetos {
	int x;
	int y;
	int ancho=18;
	int alto=20;
	Color color=new Color(0,255,255);
	
	
	public Objetos(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
	}
	
	
}
