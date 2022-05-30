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
	Image mikasa;
	Image mikasaCorriendo;
	Image titanMikasa;
	Image titanMikasaCorriendo;
	Color color= new Color(255,200,0);
	double valorEnCos = this.x;
	double valorEnSen = this.y;
	int vidas=3;
	boolean bigMikasa=false;
	
	public Mikasa (double x, double y) {
		this.x=x;
		this.y=y;
		mikasa = Herramientas.cargarImagen("mikasaQuieta.png");
		mikasaCorriendo = Herramientas.cargarImagen("mikasaCorriendo.png");
		titanMikasa = Herramientas.cargarImagen("bigMikasa.png");
		titanMikasaCorriendo = Herramientas.cargarImagen("bigMikasaCorriendo.png");
	}
	public void dibujarse(Entorno entorno){
//		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, color);
		
		if(bigMikasa==true){
			if(entorno.estaPresionada(entorno.TECLA_ARRIBA))
				entorno.dibujarImagen(titanMikasaCorriendo, this.x, this.y, 0, 0.08);
			else
				entorno.dibujarImagen(titanMikasa, this.x, this.y, 0, 0.1);;
		}
		else{
			if(entorno.estaPresionada(entorno.TECLA_ARRIBA))
				entorno.dibujarImagen(mikasaCorriendo, this.x, this.y, 0, 0.5);
			else
				entorno.dibujarImagen(mikasa, this.x, this.y, 0, 0.5);
		}
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
	public int tocaEnemigo(Enemigos[] enemigos){
		Enemigos e;
		for(int i=0; i<enemigos.length ;i++) {
			e = enemigos[i];
			if((this.x+this.ancho/2 >= e.x-e.ancho/2) && (this.x-this.ancho/2 <= (e.x + e.ancho/2))
				&& (this.y+this.alto/2 >= e.y-e.alto/2) && (this.y-this.alto/2 <= (e.y + e.alto/2))) {
				return i;
			}
		}
		return -1;
	}
	public int indiceCadenaEnArreglo(String[] arr, String cadena) {
		for(int i=0; i<arr.length ;i++) {
			if(arr[i].equals(cadena)) {
				return i;
			}
		}
		return -1;
	}
	public void mover(int anchoPantalla, int alturaPantalla, double velocidad, Obstaculos[] obstaculos, Enemigos[] enemigos) {
		double valorEnCos = this.x + Math.cos(this.angulo)*velocidad;
		double valorEnSen = this.y + Math.sin(this.angulo)*velocidad;
		double moverAlTocarObj = 0.5;
		String[] tocaObs = new String[obstaculos.length];
		for(int i=0; i < obstaculos.length; i++) {
			tocaObs[i] = tocaObstaculo(obstaculos[i], valorEnCos, valorEnSen);
		}
		
		if ((valorEnCos-this.ancho/2 > 0 && valorEnSen-this.alto/2 > 0) && 
			(valorEnCos+this.ancho/2 < anchoPantalla && valorEnSen+this.alto/2 < alturaPantalla)&& 
			(tocaEnemigo(enemigos) == -1)) {
			if (indiceCadenaEnArreglo(tocaObs, "abajo") != -1) {
				this.x-= moverAlTocarObj;
//				tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
			} else if ((indiceCadenaEnArreglo(tocaObs, "der") != -1)) {
				this.y+= moverAlTocarObj;
//				tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
			} else if ((indiceCadenaEnArreglo(tocaObs, "izq") != -1)) {
				this.y-= moverAlTocarObj;
//				tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
			} else if ((indiceCadenaEnArreglo(tocaObs, "arriba") != -1)) {
				this.x+= moverAlTocarObj;
//				tocaObs[indiceCadenaEnArreglo(tocaObs, "abajo")] = "";
			} else {
				this.x = valorEnCos;
				this.y = valorEnSen;
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