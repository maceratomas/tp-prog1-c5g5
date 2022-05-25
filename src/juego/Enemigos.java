package juego;
import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class Enemigos {
//	variables de instancia
	double x;
	double y;
	double angulo;
	int ancho=60;
	int alto=100;
	Color color= new Color(255,0,0);
	Image imagenEnemigo;
//	objeto
	
	public Enemigos(double x, double y) {
		this.x=x;
		this.y=y;
		imagenEnemigo= Herramientas.cargarImagen("enemigo.png");
	}
	
	public void dibujarse(Entorno entorno) {
//		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, color);
		entorno.dibujarImagen(imagenEnemigo, this.x, this.y, this.angulo, 0.2);
	}
	public String tocaObstaculo(Obstaculos obstaculo, double valorEnCos, double valorEnSen) {
		int alto = obstaculo.alto;
		int ancho = obstaculo.ancho;
		// impacta con el centro del enemigo
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
	
	public void moverse(double x, double y, Obstaculos obstaculo) {
		double movEnX, movEnY;
		double moverAlTocarObs = 1.0;
		
		if(this.x<x) {
//			this.x+=Math.cos(this.angulo)*0.5;
			movEnX = this.x + Math.cos(this.angulo)*0.5;
		} else {	
//			this.x+=Math.cos(this.angulo)*(-0.5);
			movEnX = this.x + Math.cos(this.angulo)*(-0.5);
		}
		if (this.y<y) {
//			this.y+=Math.cos(this.angulo)*0.5;
			movEnY = this.y + Math.cos(this.angulo)*0.5;
		}else {
//			this.y+=Math.cos(this.angulo)*(-0.5);
			movEnY = this.y + Math.cos(this.angulo)*(-0.5);
		}
		
		String tocaObs = tocaObstaculo(obstaculo, movEnX, movEnY);
		
		if (tocaObs == "") {
			this.x = movEnX;
			this.y = movEnY;
		} else if (tocaObs == "der") {
//			System.out.println("E: colisiona: der");
			this.y+= moverAlTocarObs;
		} else if (tocaObs == "izq") {
//			System.out.println("E: colisiona: izq");
			this.y+= moverAlTocarObs;
		} else if (tocaObs == "arriba") {
//			System.out.println("E: colisiona: arriba");
			this.x+= moverAlTocarObs;
		} else {
//			System.out.println("E: colisiona: abajo");
			this.x+= moverAlTocarObs;
		}
		
	}
	
}
