package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Extras extras;
	Mikasa mikasa;
	Enemigos enemigos;
	Objetos objetos;
	Obstaculos obstaculos;
	Proyectil proyectil;
	Arma arma;
	Color colorCartel=new Color(255,0,255);;
	int contadorDeAsesinatos=0;
	int altura = 600;
	int ancho = 800;
	double tiempo=60;
	Image imagenDeFondo;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "AoT v0.1", ancho, altura);
		
		// Inicializar lo que haga falta para el juego
		mikasa= new Mikasa(400,200);
		enemigos=new Enemigos(400,400);
		objetos=new Objetos(250,200);
		obstaculos=new Obstaculos(200,400);
		proyectil=null;
		imagenDeFondo= Herramientas.cargarImagen("fondo.jpg");
		
	
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {

		if((mikasa.vidas==0) || (tiempo<0)){
			entorno.escribirTexto("GAME OVER", 340, 270);
			return;
		}

		if(contadorDeAsesinatos==10){
			entorno.escribirTexto("Victoria derrotaste a todos los titanes", 320, 270);
			return;
		}
//  	Contador de tiempo
		tiempo-=0.01;
//  	Cosas que aparecen en pantalla
		entorno.dibujarImagen(imagenDeFondo, ancho/2, altura/2, 0, 01);
		entorno.escribirTexto("Vidas de Mikasa= " + mikasa.vidas, 50, 20);
		entorno.escribirTexto("Tiempo= " + (int)tiempo, 650, 20);
		entorno.escribirTexto("Enemigos asesinados= "+ contadorDeAsesinatos, 320, 20);

//		instrucciones de Mikasa
		mikasa.dibujarse(entorno);
		
		if(mikasa.colisinaObjeto(objetos)){
			mikasa.alto=70;
			mikasa.ancho=55;
			mikasa.bigMikasa=true;
//			objetos=null;
		}
		if(mikasa.tocaEnemigo(enemigos)) {
			double moverAlTocarObj = 100.0;
			String tocaEnemigo = enemigos.dondeTocaEnemigo(mikasa);
			System.out.println("toca enemigo : "+tocaEnemigo);
			
			if(mikasa.bigMikasa==false){
				mikasa.vidas-=1;
//				System.out.println("entro al if lado:");
				if (tocaEnemigo == "der") {
					mikasa.x += moverAlTocarObj;
//					System.out.println("der");
				}
				if (tocaEnemigo == "izq") {
					mikasa.x -= moverAlTocarObj;
//					System.out.println("izq");
				}
				if (tocaEnemigo == "arriba") {
					mikasa.y -= moverAlTocarObj;
//					System.out.println("arriba");
				}
				if (tocaEnemigo == "abajo"){
					mikasa.y += moverAlTocarObj;
//					System.out.println("abajo");
				}
			}
			else{
				mikasa.alto=50;
				mikasa.ancho=35;
				mikasa.x+=50;
				mikasa.y+=50;
				mikasa.bigMikasa=false;
			}
		}

//		movimiento
		if (entorno.estaPresionada(entorno.TECLA_DERECHA))
			mikasa.girar(mikasa.radianes(1.5));
		
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			mikasa.girar(mikasa.radianes(-1.5));
		
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
			mikasa.mover(ancho, altura, 2.0, obstaculos, enemigos);
		
		if(entorno.estaPresionada(entorno.TECLA_ABAJO))
			mikasa.mover(ancho, altura, -1.5, obstaculos, enemigos);
		
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && entorno.estaPresionada(entorno.TECLA_DERECHA))
			mikasa.rotar();
		
		if(entorno.estaPresionada(entorno.TECLA_SHIFT)&&entorno.estaPresionada(entorno.TECLA_ARRIBA)) 
			mikasa.mover(ancho, altura, 2.5 , obstaculos, enemigos);
		
		if(entorno.estaPresionada(entorno.TECLA_SHIFT)&&entorno.estaPresionada(entorno.TECLA_DERECHA))
			mikasa.girar(mikasa.radianes(2));
		
		if(entorno.estaPresionada(entorno.TECLA_SHIFT)&&entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			mikasa.girar(mikasa.radianes(-2));

		if((entorno.estaPresionada(entorno.TECLA_DERECHA)) && (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)))
			mikasa.rotar();
//		instrucciones de enemigos
		enemigos.dibujarse(entorno);
//		movimiento de enemigos hacia Mikasa
		enemigos.moverse(mikasa.x, mikasa.y, obstaculos);
//		instrucciones de Objetos
		objetos.dibujarse(entorno);
//		instrucciones de Obstaculos
		obstaculos.dibujarse(entorno);
//		instrucciones de Proyectil
		if((entorno.estaPresionada(entorno.TECLA_ESPACIO)) && (mikasa.bigMikasa==false)) {
			proyectil= new Proyectil (mikasa.x, mikasa.y, mikasa.angulo);
		}
		if(proyectil!=null){
			proyectil.dibujarse(entorno);
			proyectil.disparar(mikasa, enemigos, entorno, obstaculos);
			if(proyectil.colisionaBorde(ancho,altura)){
				proyectil=null;
				System.out.println("Toca borde");
			}
				
			if((proyectil != null) && (proyectil.tocaObstaculo(obstaculos))){
				proyectil=null;	
				System.out.println("Toca casa");
			}
			if((proyectil != null) && (proyectil.colisionaEnemigos(enemigos))){
				proyectil=null;
				contadorDeAsesinatos+=1;
				System.out.println("toca enemigo");
			}
		}
		if(mikasa.bigMikasa==false){
		arma= new Arma(mikasa.x, mikasa.y, mikasa.angulo);
		arma.dibujarse(entorno);
		}
	
		
	}
		

	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
