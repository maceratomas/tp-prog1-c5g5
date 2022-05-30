package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Image;
import java.util.Random;
public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Extras extras;
	Mikasa mikasa;
	Enemigos[] enemigos = new Enemigos[5];
	Objetos objetos;
	Obstaculos[] obstaculos = new Obstaculos[5];
	Proyectil proyectil;
	Arma arma;
	Color colorCartel=new Color(255,0,255);;
	int contadorDeAsesinatos=0;
	int altura = 600;
	int ancho = 800;
	double tiempo=60;
	Image imagenDeFondo;
	Random numero=new Random();
//	int xEnemigo= numero.nextInt(ancho);
//	int yEnemigo=numero.nextInt(altura);
	int xObs=numero.nextInt(ancho);
	int yObs=numero.nextInt(altura);
	int xObj=numero.nextInt(ancho);
	int yObj=numero.nextInt(altura);
	boolean crearObjeto=false;

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "AoT v0.1", ancho, altura);
		
		// Inicializar lo que haga falta para el juego
		mikasa= new Mikasa(ancho/2,altura/2);
//		enemigos=new Enemigos(xEnemigo,yEnemigo);
		objetos=null;
		proyectil=null;
		imagenDeFondo= Herramientas.cargarImagen("fondo.jpg");
		Random random = new Random();
		for(int i=0; i<5; i++) {
			obstaculos[i] = new Obstaculos(random.nextInt(0,ancho) , random.nextInt(0,altura));
		}

		Herramientas.loop("MusicaFondo.aiff");
		for(int i=0; i<5; i++) {
			enemigos[i] = new Enemigos(random.nextInt(0,ancho) , random.nextInt(0,altura));
			while(enemigos[i].seSuperpone(enemigos,mikasa,obstaculos)) {
				enemigos[i] = new Enemigos(random.nextInt(0,ancho) , random.nextInt(0,altura));
			}
		}

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
		Random random = new Random();
		int segJuego = (int) ((System.nanoTime())/(1000000000*10));
		if((segJuego%10==0) && (Enemigos.noMasEnemigos(enemigos)==false)) {
			for(int i=0; i<enemigos.length; i++) {
				if(enemigos[i]==null) {
					enemigos[i] = new Enemigos(random.nextInt(0,ancho) , random.nextInt(0,altura));
				}
			}
		}

		if(Enemigos.noMasEnemigos(enemigos)){
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
		
//		instrucciones de Objetos
		if((mikasa.bigMikasa==false) && ((int) tiempo%10==0)){
			crearObjeto=true;
		}

		if(crearObjeto){
			objetos=new Objetos(xObj,yObj);
			objetos.dibujarse(entorno);
		}
		
//		instrucciones de Mikasa
		mikasa.dibujarse(entorno);
		
		if((objetos!=null) && (mikasa.colisinaObjeto(objetos))){
			mikasa.alto=70;
			mikasa.ancho=55;
			mikasa.bigMikasa=true;
			objetos=null;
			crearObjeto=false;
			xObj=numero.nextInt(ancho);
			yObj=numero.nextInt(altura);
			Herramientas.play("TomarPocion.aiff");
		}
		if(mikasa.tocaEnemigo(enemigos) > -1) {
			double moverAlTocarObj = 100.0;
			String tocaEnemigo = enemigos[mikasa.tocaEnemigo(enemigos)].dondeTocaEnemigo(mikasa);
			System.out.println("toca enemigo : "+tocaEnemigo);
			
			if(mikasa.bigMikasa==false){
				mikasa.vidas-=1;
				Herramientas.play("Golpe.aiff");
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
				int i=Enemigos.golpeadoBigMikasa(mikasa, enemigos);
				enemigos[i]=null;
				mikasa.alto=50;
				mikasa.ancho=35;
				mikasa.x+=50;
				mikasa.y+=50;
				mikasa.bigMikasa=false;
				contadorDeAsesinatos+=1;
				Herramientas.play("GolpeEnemigo.aiff");
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
		for (int i=0; i<enemigos.length ;i++) {
			if(enemigos[i]!=null){
				entorno.dibujarImagen(enemigos[i].imagenEnemigo, enemigos[i].x, enemigos[i].y, 0, 0.13);
		}
	}
//		movimiento de enemigos hacia Mikasa
		for (int i=0; i<enemigos.length ;i++) {
			if(enemigos[i]!=null){
				enemigos[i].moverse(mikasa.x, mikasa.y, obstaculos);
		}
	}

//		instrucciones de Obstaculos
		for (int i=0; i<obstaculos.length ;i++) {
			entorno.dibujarImagen(obstaculos[i].imagenCasa, obstaculos[i].x, obstaculos[i].y, 0, 0.13);
		}
//		instrucciones de Proyectil
		if((entorno.estaPresionada(entorno.TECLA_ESPACIO)) && (mikasa.bigMikasa==false)) {
			proyectil= new Proyectil (mikasa.x, mikasa.y, mikasa.angulo);
			Herramientas.play("Disparo.aiff");
		}
		if(proyectil!=null){
			proyectil.dibujarse(entorno);
			proyectil.disparar();
			if(proyectil.colisionaBorde(ancho,altura)){
				proyectil=null;
				System.out.println("Toca borde");
			}
				
			if((proyectil != null) && (proyectil.tocaObstaculo(obstaculos))){
				proyectil=null;	
				Herramientas.play("GolpeMadera.aiff");
				System.out.println("Toca casa");
			}
			if((proyectil != null) && (proyectil.colisionaEnemigos(enemigos))){
				int i=Enemigos.esColisionado(proyectil, enemigos);
				proyectil=null;
				enemigos[i]=null;
				Herramientas.play("GolpeEnemigo.aiff");
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
