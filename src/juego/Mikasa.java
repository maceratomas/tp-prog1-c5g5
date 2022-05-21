package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Mikasa {
//    Variables de instancia
	Entorno entorno;
	double x;
	double y;
	double angulo;
	int alto=50;
	int ancho=35;
	Image img1;
	Color color= new Color(255,200,0);
	double valorEnCos = this.x;
	double valorEnSen = this.y;
	
	public Mikasa (double x, double y) {
		this.x=x;
		this.y=y;
		img1 = Herramientas.cargarImagen("mikasa.png");
	}
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, color);
		
//		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
//			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);
//		else
//			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);
	}
	public boolean tocaObstaculo(Obstaculos obstaculo, double valorEnCos, double valorEnSen) {
		// primero ve si se choca en el eje x, luego lo hace con el eje y
		if((valorEnCos >= obstaculo.x-obstaculo.ancho) && (valorEnCos <= (obstaculo.x + obstaculo.ancho))
				&& (valorEnSen >= obstaculo.y-obstaculo.alto) && (valorEnSen <= (obstaculo.y + obstaculo.alto))) {
			return true;
		}
		return false;
	}
	public boolean tocaPocion(Objetos obj){
		if((x+this.ancho/2 >= obj.x-obj.ancho/2) && (x-this.ancho/2 <= (obj.x + obj.ancho/2))
				&& (y+this.alto/2 >= obj.y-obj.alto/2) && (y-this.alto/2 <= (obj.y + obj.alto/2))) {
			return true;
		}
		return false;
	}
	public boolean tocaEnemigo(Enemigos e, double x, double y){
		if((x+this.ancho/2 >= e.x-e.ancho/2) && (x-this.ancho/2 <= (e.x + e.ancho/2))
				&& (y+this.alto/2 >= e.y-e.alto/2) && (y-this.alto/2 <= (e.y + e.alto/2))) {
			return true;
		}
		return false;
	}

	public void mover(int anchoPantalla, int alturaPantalla, double velocidad, Obstaculos obstaculo, Enemigos enemigo ) {
		double valorEnCos = this.x + Math.cos(this.angulo)*velocidad;
		double valorEnSen = this.y + Math.sin(this.angulo)*velocidad;
		/*
		 * si el valorEn da un numero fuera de los bordes, no avanza -- Se traba con el
		 * --> centro del objeto, no con los bordes del objeto -- Falta que se trabe tmb con
		 * --> los obstaculos
		 */
		
		if ((valorEnCos-this.ancho/2 > 0 && valorEnSen-this.alto/2 > 0) && 
			(valorEnCos+this.ancho/2 < anchoPantalla && valorEnSen+this.alto/2 < alturaPantalla)&& 
			!(tocaObstaculo(obstaculo, valorEnCos, valorEnSen))&&!(tocaEnemigo(enemigo, valorEnCos, valorEnSen))) {
			this.x = valorEnCos;
			this.y = valorEnSen;
		}
	}
	public void rotar() {
		
	}
	
	public double radianes(double grados)
	{
		return (grados/360.0)*(2*Math.PI);
	}
	
	public void girar(double mod) 
	{
		this.angulo = this.angulo + mod;
	}
	
	public boolean colisinaObjeto(Objetos o) {
		if((y+alto/2<o.y-o.alto/2)||(y-alto/2>o.y+o.alto/2)||
			(x+ancho<o.x-o.ancho/2)||(x-ancho>o.x+o.ancho/2)) {
			return false;}
		else {
			return true;}}
}


