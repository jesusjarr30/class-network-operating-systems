

import java.util.Scanner;

public class Encapsulamiento {
	MiFecha fecha;
	
	Encapsulamiento(){
		fecha = new MiFecha();
	}
	
	
	public static void main(String [] arg) {
		Encapsulamiento p=new Encapsulamiento();
	
		int opc=0;//opciones
		int resp=0;//repuesta
		Scanner sc =new Scanner(System.in);
		do {
			System.out.println("La fecha es :"+p.fecha.dameDia()+"/"+p.fecha.dameMes()+"/"+p.fecha.dameAnio());
			System.out.println("1. Cambiar el dia");
			System.out.println("2. Cambiar mes");
			System.out.println("3. Cambiar año");
			System.out.println("4. Salir");
			opc=sc.nextInt();
			switch(opc) {
			case 1:
				System.out.println("Ingresa nuevo dia");
				resp=sc.nextInt();
				if(!p.fecha.fijaDia(resp)) {
					System.out.println("No fue posible realizar el cambio dia");
				}
				break;
			case 2:
				System.out.println("Ingresa nuevo mes");
				resp=sc.nextInt();
				if(!p.fecha.fijaMes(resp)) {
					System.out.println("No fue posible realizar el cambio el mes");
				}
				break;
			case 3:
				System.out.println("Ingresa nuevo año");
				resp=sc.nextInt();
				if(!p.fecha.fijaAnio(resp)) {
					System.out.println("No fue posible realizar el cambio de año ");
				}
				break;
			}
			
		}while(opc!=4);
		sc.close();
		
	}
	
	
	
	public class MiFecha{
		private int dia;
		private int mes;
		private int year;
		
		MiFecha(){
			dia=1;
			mes=6;
			year=2000;
		}
		//get
		public int dameDia() {
			return dia;
		}
		public int dameMes() {
			return mes;
		}
		public int dameAnio() {
			return year;
		}
		//set
		public boolean fijaDia(int cambio) {
			if(verifica(cambio,mes,year)) {
				dia=cambio;
				return true;
			}
			return false;
			
			
			
		}
		public boolean fijaMes(int cambio) {
			if(verifica(dia,cambio,year)) {
				mes=cambio;
				return true;
			}
			return false;
			
			
		}
		public boolean fijaAnio(int cambio) {
			if(verifica(dia,mes,cambio)) {
				year=cambio;
				return true;
			}
			return false;
		}
		
		private boolean verifica(int d, int m, int y) {
			boolean bis=false;//año bisiesto
			boolean val=false;//variable de control
			
			//so ser divisible entre cero
			//NO se debe ser divisible por 100
			//debe ser divisible entre 400
			if((y % 4 ==0) && (y % 100 != 0 || y % 400==0) ) {
				bis=true;
			}
			//validar mes
			if(m>=1 && m<=12 ) {
				//meses con 31 dias
				if(m==1 || m==3|| m==5 || m==7||m==8||m==10||m==12) {
					if(d>=1 && d<=31) {
						val=true;
					}
				}
				else {
					//meses con 30 dias
					if(m== 4||m==6||m==9||m==11) {
						if(d>=1 && d<=30) {
							val=true;
						}
						
					}
					else {
						//febrero
						if(m==2) {
							if(bis) {
								if(d>=1 && d<=29) {
									val=true;
								}
								
							}
							else {
								if(d>=1 && d<=28) {
									val=true;
								}
							}
						}
						
						
					}
				}
					
				
			}
			
			return val;
		}
		
		
	}
	
	
	
	
}
