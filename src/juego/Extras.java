package juego;

public class Extras {
	int ancho=800;
	int alto=600;
	
	public void resolucion(int ancho, int alto) {
		this.ancho=ancho;
		this.alto=alto;
	}
	
//	public boolean colisina(double py, double pAncho, double ey, double eAncho, double px, double pAlto, double ex, double eAlto) {
//		if(py+pAlto/2<ey-eAlto/2)
//			return true;
//		if(py-pAlto/2>ey+eAlto/2)
//			return true;
//		if(px+pAncho<ex-eAncho/2)
//			return true;
//		if(px-pAncho>ex+eAncho/2)
//			return true;
//		return false;}
}
