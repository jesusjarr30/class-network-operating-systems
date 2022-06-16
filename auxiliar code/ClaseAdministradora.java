import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class ClaseAdministradora {
	private Hashtable<Integer,Materia> tabla;//parametrisar la hastables
	private int contador;
	
	public ClaseAdministradora() {
		tabla = new Hashtable<Integer,Materia>();
		contador=1;
	}
	class Materia{
		public String clave;
		public String nombre;
		
		public Materia(String c,String n){
			clave=c;
			nombre=n;
		}
	}
	
	public int registro(String clave, String nombre) {
		Materia m=new Materia(clave,nombre);
		tabla.put(contador, m);
		imprime();
		return contador++;
	}
	public Materia consulta(int id) {
		
		Materia m=tabla.get(id);
		return m;
	}
	public void eliminar(int id) {
		tabla.remove(id);
		imprime();
	}
	public void imprime() {
		Enumeration<Integer> llaves=tabla.keys();
		Enumeration<Materia> elementos=tabla.elements();
		System.out.println("Id_sec  |   Claves	|Materia");
		while(llaves.hasMoreElements()){
			Materia m=elementos.nextElement();
			int id=llaves.nextElement();
			System.out.println(id+"	|	"+m.clave+"	|	"+m.nombre	);
		}
		
	}
	
	public static void main(String[] arg) {
		
		
		ClaseAdministradora ca=new ClaseAdministradora();
		
		
		ca.registro("I7029","SISTEMAS OPERATIVOS");
		ca.registro("I7030","SEMINARIO DE SISTEMAS OPERATIVOS");
		ca.registro("I7033","SISTEMAS OPERATIVOS DE RED");
		ca.registro("I7034","SEMINARIO DE SISTEMAS OPERATIVOS DE RED");
		 Scanner sc = new Scanner(System.in);
		 int opc=0;
		 int aux=0;
		 
		 do {
			 System.out.println("- - - - Menu de Materias - - - -");
			 System.out.println("1. Resgistrar materia");
			 System.out.println("2. Consulta materia");
			 System.out.println("3. Eliminar materia");
			 System.out.println("4. Imprimir tabla");
			 System.out.println("5. Salir\n");
			 
			 try {
				 opc=Integer.parseInt(sc.nextLine());
			 }
			 catch(Exception  e) {
				 opc=0;
				 System.out.println("Ingres una opción validad");
			 }
			 switch(opc){
			 case 1:
				 System.out.println("Dame clave de la materia");
				 String c=sc.nextLine();
				 System.out.println("Ingrese el nombre de la materia");
				 String n=sc.nextLine();
				 try {
					 int id=ca.registro(c, n);
					 System.out.println("La materia fue registrada con el ID"+id); 
				 }
				 catch(Exception e) {
					 System.out.println("Ingrese datos validos");
				 }
				 
				 break;
			 case 2:
				 try {
					 System.out.println("Ingrese id de la materia");
					 int d=Integer.parseInt(sc.nextLine());
					 Materia m=ca.consulta(d); 
					 System.out.println("LA clve de la materia es "+m.clave+" EL nombre de la materia es "+m.nombre);
				 }
				 catch(Exception e) {
					 System.out.println("No existe el dato solicitado"); 
				 }
				 break;
			 case 3:
				 try{
					 System.out.println("Ingrese el id del objeto que desea remover");
					 aux=Integer.parseInt(sc.nextLine());
					 ca.eliminar(aux);
				 }
				 catch(Exception e) {
					 System.out.println("INgrese datos validos");
				 }
					 break;
			 case 4:
				 ca.imprime();
				 break;
			 }
		 }while(opc!=5);
		
		sc.close();
	}

}
