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
	int vidas=3;
	
	public Mikasa (double x, double y) {
		this.x=x;
		this.y=y;
		img1 = Herramientas.cargarImagen("mikasa.png");
	}
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, color);
		
//		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
//			entorno.dibujarImagen(img1, this.x, this.y, 0, 0.2);
//		else
//			entorno.dibujarImagen(img1, this.x, this.y, 0, 0.2);
	}
	public String tocaObstaculo(Obstaculos obstaculo, double valorEnCos, double valorEnSen) {
		int alto = obstaculo.alto;
		int ancho = obstaculo.ancho;
		
		if((valorEnSen >= obstaculo.y - alto) && (valorEnSen <= (obstaculo.y + alto))){
			if((valorEnCos <= obstaculo.x+ ancho +1.0) && (valorEnCos >= obstaculo.x+ ancho -1)) {
				return "der";
			}
			if ((valorEnCos <= obstaculo.x+ ancho) && (valorEnCos >= obstaculo.x- ancho)) {
				return "izq";
			}
		}
		if ((valorEnCos >= obstaculo.x - ancho) && (valorEnCos <= (obstaculo.x + ancho))) {
			if ((valorEnSen <= obstaculo.y-alto+1) && (valorEnSen >= obstaculo.y-obstaculo.alto-1)) {
				return "arriba";
			}
			if ((valorEnSen <= obstaculo.y+alto+1) && (valorEnSen >= obstaculo.y+alto-1)) {
				return "abajo";
			}	
		}
		return "";
	}
	public boolean tocaPocion(Objetos obj){
		if((x+this.ancho/2 >= obj.x-obj.ancho/2) && (x-this.ancho/2 <= (obj.x + obj.ancho/2))
				&& (y+this.alto/2 >= obj.y-obj.alto/2) && (y-this.alto/2 <= (obj.y + obj.alto/2))) {
			return true;
		}
		return false;
	}
	public boolean tocaEnemigo(Enemigos e){
		if((this.x+this.ancho/2 >= e.x-e.ancho/2) && (this.x-this.ancho/2 <= (e.x + e.ancho/2))
				&& (this.y+this.alto/2 >= e.y-e.alto/2) && (this.y-this.alto/2 <= (e.y + e.alto/2))) {
			return true;
		}
		return false;
	}

	public void mover(int anchoPantalla, int alturaPantalla, double velocidad, Obstaculos obstaculo, Enemigos enemigo ) {
		double valorEnCos = this.x + Math.cos(this.angulo)*velocidad;
		double valorEnSen = this.y + Math.sin(this.angulo)*velocidad;
		double moverAlTocarObj = 0.5;
		String tocaObs = tocaObstaculo(obstaculo, valorEnCos, valorEnSen);
		
		if ((valorEnCos-this.ancho/2 > 0 && valorEnSen-this.alto/2 > 0) && 
			(valorEnCos+this.ancho/2 < anchoPantalla && valorEnSen+this.alto/2 < alturaPantalla)&& 
			!(tocaEnemigo(enemigo)) ) {
			if (tocaObs == "") {
				this.x = valorEnCos;
				this.y = valorEnSen;
			} else if (tocaObs == "der") {
//				System.out.println("colisiona: der");
				this.y+= moverAlTocarObj;
			} else if (tocaObs == "izq") {
//				System.out.println("colisiona: izq");
				this.y-= moverAlTocarObj;
			} else if (tocaObs == "arriba") {
//				System.out.println("colisiona: arriba");
				this.x+= moverAlTocarObj;
			} else {
//				System.out.println("colisiona: abajo");
				this.x-= moverAlTocarObj;
			}
		}
	}
	public void rotar() {
		this.angulo+=180;
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


