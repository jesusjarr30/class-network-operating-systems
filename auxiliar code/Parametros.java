
public class Parametros {

	
	public static void main(String[] args) {
		short s=1456;
		int entero=456324562;
		long l=994215221455214211l;
		byte[] res_empaquetado;
		
		//para short
		res_empaquetado=empaqueta(s);
		for(int i=0; i<res_empaquetado.length;i++) 
			System.out.print(res_empaquetado[i]+" ");		
		System.out.print("\nArmar el short "+arma(res_empaquetado)+"\n");
		
		//para int
		res_empaquetado=empaqueta(entero);
		for(int i=0; i<res_empaquetado.length;i++) 
			System.out.print(res_empaquetado[i]+" ");	
		System.out.print("\nArmar el entero "+arma_int(res_empaquetado)+"\n");
		
		//para long
		res_empaquetado=empaqueta(l);
		for(int i=0; i<res_empaquetado.length;i++)
			System.out.print(res_empaquetado[i]+" ");
		System.out.print("\narmar el long "+arma_long(res_empaquetado));
		
		
		
	}
	public static byte[] empaqueta(short s) {
		byte[] arreglo=new byte[2];//el dos es por el tamaño del short
		for (int i=0;i<arreglo.length;i++) {
			arreglo[i]=(byte) s;
			s=(short) (s>>>8);
		}
		
		return arreglo;
	}
	
	public static byte[] empaqueta(int s) {
		byte[] arreglo=new byte[4];
		for (int i=0;i<arreglo.length;i++) {
			arreglo[i]=(byte) s;
			s=s>>>8;
		}
		
		return arreglo;
	}
	public static byte[] empaqueta(long s) {
		byte[] arreglo=new byte[8];
		for (int i=0;i<arreglo.length;i++) {
			arreglo[i]=(byte) s;
			s=s>>>8;
		}
		return arreglo;
	}
	public static short arma(byte[] arreglo) {
		short aux;
		short valor=0;
		for(int i=1;i>=0;i--) {
			valor=(short)(valor<<8);
			aux=arreglo[i];
			aux=(short)(aux&0x00FF);
			valor=(short) (valor|aux);	
		}
		return valor;
		
	
	}
	public static int arma_int(byte[] arreglo) {
		int aux;
		int valor=0;
		for(int i=3;i>=0;i--) {
			valor=valor<<8;
			aux=arreglo[i];
			aux=aux&0x000000FF;
			valor=valor|aux;	
		}
		return valor;
	
	}
	public static long arma_long(byte[] arreglo) {
		long aux;
		long valor=0;
		for(int i=7;i>=0;i--) {
			valor=valor<<8;
			aux=arreglo[i];
			aux=aux&0x00000000000000FFl;
			valor=valor|aux;	
		}
		return valor;
	}
}
