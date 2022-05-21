package juego;
import java.awt.Color;
import entorno.Entorno;


public class Enemigos {
//	variables de instancia
	double x;
	double y;
	double angulo;
	int ancho=75;
	int alto=100;
	Color color= new Color(255,0,0);
//	objeto
	
	public Enemigos(double x, double y) {
		this.x=x;
		this.y=y;
//		imagen
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}
	
	public void moverse(double x, double y) {
		if(this.x<x) {
			this.x+=Math.cos(this.angulo)*0.5;}
		else {	
			this.x+=Math.cos(this.angulo)*(-0.5);}
		if (this.y<y) {
			this.y+=Math.cos(this.angulo)*0.5;}
		else {
			this.y+=Math.cos(this.angulo)*(-0.5);
		}
	}
	
}
