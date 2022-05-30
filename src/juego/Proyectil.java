package juego;
import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Color;
import java.awt.Image;
public class Proyectil {
	double x;
	double y;
	double angulo;
	int ancho=5;
	int alto=10;
	Color color=new Color(255,255,255);
	Mikasa mikasa;
	Enemigos enemigos;
	Entorno entorno;
	Obstaculos obstaculos;
	Image imagenProyectil;
	public Proyectil (double x, double y, double angulo) {
		this.x=x;
		this.y=y;
		this.angulo=angulo;
		imagenProyectil= Herramientas.cargarImagen("proyectil.png");
	}
	
	public void dibujarse(Entorno e) {
//		e.dibujarRectangulo(this.x, this.y, ancho, alto, this.angulo, color);
		e.dibujarImagen(imagenProyectil, this.x, this.y+10, this.angulo, 0.02);
		}
	
		public boolean colisionaEnemigos(Enemigos e) {
			if((x+ancho/2>e.x-e.ancho/2)&&(x-ancho/2<e.x+e.ancho/2)
				&&(y+alto/2>e.y-e.alto/2)&&(y-alto/2<e.y+e.alto/2)){
				return true;
			}
			return false;	
		}
		
		public boolean colisionaBorde(int anchoPantalla, int altoPantalla) {
			return (x+ancho/2 > anchoPantalla || x-ancho/2<0 
			|| y+alto/2>altoPantalla || y-alto/2<0);}
	
		public boolean tocaObstaculo(Obstaculos[] obstaculos){
			Obstaculos obs;
			for(int i=0; i<obstaculos.length ;i++) {
				obs = obstaculos[i];
				if((x+ancho/2>obs.x-obs.ancho/2)&&(x-ancho/2<obs.x+obs.ancho/2)
					&&(y+alto/2>=obs.y-obs.alto/2)&&(y-alto/2<obs.y+obs.alto/2)){
					return true;
				}
			}
			return false;	
		}
	
	public void disparar(Mikasa m, Enemigos e, Entorno ent, Obstaculos[] obs) {
		this.x += Math.cos(this.angulo)*2.5;
		this.y += Math.sin(this.angulo)*2.5;	
		}	
	}