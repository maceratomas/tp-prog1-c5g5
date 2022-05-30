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
	public String[] tocaAlgunObstaculo(Obstaculos[] obstaculos, double valorEnCos, double valorEnSen) {
		String[] arr = new String[obstaculos.length];
		for(int i=0; i< obstaculos.length ;i++) {
			arr[i] = tocaObstaculo(obstaculos[i], valorEnCos, valorEnSen);
		}
		return arr;
	}
	public String dondeTocaEnemigo(Mikasa mikasa) {
		if(((this.y + this.alto/2) >= mikasa.y) && ((this.y - this.alto/2) <= mikasa.y)) {
			if((this.x + this.ancho/2) <= (mikasa.x - mikasa.ancho/2)) {
				return "der";
			}
			if((this.x - this.ancho/2) >= (mikasa.x + mikasa.ancho/2)) {
				return "izq";
			}
		}
		if(((this.x + this.ancho/2) >= mikasa.x) && ((this.x - this.ancho/2 <= mikasa.x))) {
			// --> segun el orden, funciona el de arriba o el de abajo, por eso lo comento para subirlo
			if((this.y + this.alto) >= (mikasa.y - mikasa.alto/2)) {
				return "abajo";
			}
//			if((this.y - this.alto) <= (mikasa.y + mikasa.alto/2)) {
//				return "arriba";
//			}
		}
		
		return "";
	}
	public int indiceCadenaEnArreglo(String[] arr, String cadena) {
		for(int i=0; i<arr.length ;i++) {
			if(arr[i].equals(cadena)) {
				return i;
			}
		}
		return -1;
	}
	public void moverse(double x, double y, Obstaculos[] obstaculos) {
		double movEnX, movEnY;
		double moverAlTocarObs = 1.0;
		
		if(this.x<x) {
			movEnX = this.x + Math.cos(this.angulo)*0.5;
		} else {
			movEnX = this.x + Math.cos(this.angulo)*(-0.5);
		}
		if (this.y<y) {
			movEnY = this.y + Math.cos(this.angulo)*0.5;
		}else {
			movEnY = this.y + Math.cos(this.angulo)*(-0.5);
		}
		
		String[] tocaObs = tocaAlgunObstaculo(obstaculos, movEnX, movEnY);
		
		if (indiceCadenaEnArreglo(tocaObs, "abajo") != -1) {
			this.x-= moverAlTocarObs;
//			tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
		} else if ((indiceCadenaEnArreglo(tocaObs, "der") != -1)) {
			this.y+= moverAlTocarObs;
//			tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
		} else if ((indiceCadenaEnArreglo(tocaObs, "izq") != -1)) {
			this.y-= moverAlTocarObs;
//			tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
		} else if ((indiceCadenaEnArreglo(tocaObs, "arriba") != -1)) {
			this.x-= moverAlTocarObs;
//			tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
		} else {
			this.x = movEnX;
			this.y = movEnY;
		}
		
	}
	
}
