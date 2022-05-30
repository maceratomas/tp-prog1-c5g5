package juego;
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

	public boolean seSuperpone(Mikasa mikasa, Obstaculos[] obstaculos) {
		for(int i=0; i<obstaculos.length ;i++) {
			if(obstaculos[i]!=null) {
				if(((obstaculos[i].x + obstaculos[i].ancho/2) >= (this.x - this.ancho/2))
						&& ((this.x + this.ancho/2)<(obstaculos[i].x - obstaculos[i].ancho/2))
						&& ((this.y + this.alto/2)>(obstaculos[i].y - obstaculos[i].alto/2))
						&& ((this.y - this.alto/2)<(obstaculos[i].y + obstaculos[i].alto/2))) {				
					return true;
				}
			}
		}
		return false;
	}
	
}
