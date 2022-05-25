package juego;

import entorno.Entorno;
import java.awt.Color;
import entorno.Herramientas;
import java.awt.Image;
public class Obstaculos {
	int x;
	int y;
	int ancho=65;
	int alto=55;
	Color color=new Color(200,250,50);
	Image imagenCasa;
	
	public Obstaculos(int x, int y) {
		this.x=x;
		this.y=y;
		imagenCasa=Herramientas.cargarImagen("casa.png");
	}
	
	public void dibujarse(Entorno e) {
//		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(imagenCasa, this.x, this.y, 0, 0.13);
	}
	
}
