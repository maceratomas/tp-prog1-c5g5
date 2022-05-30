package juego;
import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;
public class Arma {
    double x;
	double y;
	double angulo;
	int ancho=20;
	int alto=10;
    Color color= new Color(255,0,0);
    Image arma;


    public Arma(double x, double y, double angulo){
        this.x=x;
        this.y=y;
        this.angulo=angulo;
        arma= Herramientas.cargarImagen("Arma.png");

    }

    public void dibujarse (Entorno entorno){
 //       entorno.dibujarRectangulo(this.x, this.y, ancho, alto, this.angulo, color);
        entorno.dibujarImagen(arma, this.x+2, this.y+10, this.angulo, 0.1);
    }

}
