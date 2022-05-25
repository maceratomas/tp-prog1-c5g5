package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;
public class Objetos {
	int x;
	int y;
	int ancho=18;
	int alto=20;
	Color color=new Color(0,255,255);
	Image pocion;
	
	
	public Objetos(int x, int y) {
		this.x=x;
		this.y=y;
		pocion=Herramientas.cargarImagen("pocion.png");
	}
	
	public void dibujarse(Entorno e) {
//		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(pocion, this.x, this.y, 0, 0.13);
	}
	
	
}
