import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class ProcesoServidor extends Frame implements WindowListener, ActionListener {

	TextArea respuesta =new TextArea();
	TextArea info= new TextArea();
	static Hashtable<Integer,String > paises = new Hashtable<Integer,String >();
	static Hashtable<String, String> CANADA=new Hashtable<String, String>();
	static Hashtable<String, String> EUU=new Hashtable<String, String>();
	static Hashtable<String, String> MEXICO=new Hashtable<String, String>();
	
	
	public ProcesoServidor() {
	super("El Servidor");
	paises.put(0, "Canada");
	paises.put(1, "Estados unidos");
	paises.put(2, "Mexico");
	
	
	setLayout(new BorderLayout());
	setSize(500,500);
	respuesta=new TextArea();
	respuesta.setEditable(false);
	info=new TextArea();
	info.setEditable(false);
	info.append("Servidor que odfrece cuatro tipos de servicios cuenta con una lista de estados capitales \n\n");
	info.append("El servidor espera del cliente un espado y un pais para poder encontrar la capital. \n\n");
	info.append("De encontrarse los datos se regresan al cliente si no marcara un error.\n\n");

	add(info,"North");
	add(respuesta,"Center");
	addWindowListener(this);
	}
	
	public static void main(String[] args)throws IOException{
		
	ProcesoServidor servidor= new ProcesoServidor();
	servidor.setVisible(true);
	fill();
	ObjectInputStream objIn=null;
	ObjectOutputStream objOut=null;
	
	Socket socket=null;
	try (ServerSocket sSocket = new ServerSocket(4534)) {
		while(true) {
			
			try {
				socket=sSocket.accept();
				servidor.respuesta.append("conexion con cliente establecida  "+socket.getInetAddress()+"\n");
				objIn= new ObjectInputStream(socket.getInputStream());
				objOut=new ObjectOutputStream(socket.getOutputStream());
				servidor.respuesta.append("Solicitud recibidad \n");
				
				String respuesta=(String)objIn.readObject();
				String[] servicio = respuesta.split("\\|");
				String estado =servicio[0];
				int servicioSol = Integer.parseInt(servicio[1]);
				
				servidor.respuesta.append("Analisando solicitud del cliente  \n");
				String response ="";
				Integer num = 0;
				
				servidor.respuesta.append("Bucando en la lista de "+paises.get(servicioSol).toUpperCase());
				servidor.respuesta.append("Preparando respuesta .... porfavor espere \n");
				
				switch(servicioSol) {
					case 0:
						//canada
						if(CANADA.get(estado)!=null) 
							response="El pais "+paises.get(servicioSol).toUpperCase()+", Estado: "+estado+ ", Capital: "+CANADA.get(estado);
						
						else
							response="No fue posible encontrar el nombre del estado en este pais... \nEs necesario revisar que se encuentre en el pais correcto \ny que el nombre del estado este escrito de manera correcta\n";
						
						break;
					case 1:
						//Estados unudos
						if(EUU.get(estado)!=null) 
							response="El pais "+paises.get(servicioSol).toUpperCase()+", Estado: "+estado+ ", Capital: "+EUU.get(estado);
						else
							response="No fue posible encontrar el nombre del estado en este pais... \nEs necesario revisar que se encuentre en el pais correcto \ny que el nombre del estado este escrito de manera correcta\n";
							
						break;
					case 2:
						//Mexico
						if(MEXICO.get(estado)!=null) 
							response="El pais "+paises.get(servicioSol).toUpperCase()+", Estado: "+estado+ ", Capital: "+MEXICO.get(estado);
						else
							response="No fue posible encontrar el nombre del estado en este pais... \nEs necesario revisar que se encuentre en el pais correcto\ny que el nombre del estado este escrito de manera correcta\n";
						
						break;
				}
				servidor.respuesta.append("Respuesta del servidor es: "+response);
				servidor.respuesta.append("\nEnviando respuesta al cliente \n");
				objOut.writeObject("\n"+response);
				servidor.respuesta.append("Respuesta enviada\n");
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void fill() {
		//llenado de las listas
		
		CANADA.put("NEWFOUNDLAND AND LABRADOR","ST. JOHN’S");
		CANADA.put("PRINCE EDWARD ISLAND","CHARLOTTETOWN");
		CANADA.put("NOVA SCOTIA","HALIFAX ");
		CANADA.put("NEW BRUNSWICK","FREDERICTON ");
		CANADA.put("QUEBEC","QUEBEC CITY ");
		CANADA.put("ONTARIO","TORONTO");
		CANADA.put("MANITOBA","WINNIPEG");
		CANADA.put("SASKATCHEWAN","REGINA");
		CANADA.put("ALBERTA","EDMONTON");
		CANADA.put("BRITISH COLUMBIA","VICTORIA");
		CANADA.put("NUNAVUT","IQALUIT");
		CANADA.put("NORTHWEST TERRITORIES","YELLOWKNIFE");
		CANADA.put("YUKON","WHITEHORSE");
		
		
		
		EUU.put("ALABAMA", "MONTGOMERY");
		EUU.put("ALASKA", "JUNEAU");
		EUU.put("ARIZONA", "PHOENIX");
		EUU.put("ARKANSAS", "LITTLE ROCK");
		EUU.put("CALIFORNIA", "SACRAMENTO");
		EUU.put("NORTH CAROLINA", "RALEIGH");
		EUU.put("SOUTH CAROLINA", "COLUMBIA");
		EUU.put("COLORADO", "DENVER");
		EUU.put("CONNECTICUT", "HARTFORD");
		EUU.put("NORTH DAKOTA", "BISMARCK");
		EUU.put("SOUTH DAKOTA", "PIERRE");
		EUU.put("DELAWARE", "DOVER");
		EUU.put("FLORIDA", "TALLAHASSEE");
		EUU.put("GEORGIA", "ATLANTA");
		EUU.put("HAWAII", "HONOLULU");
		EUU.put("IDAHO", "BOISE");
		EUU.put("ILLINOIS", "SPRINGFIELD");
		EUU.put("INDIANA", "INDIANAPOLIS");
		EUU.put("IOWA", "DES MOINES");
		EUU.put("KANSAS", "TOPEKA");
		EUU.put("KENTUCKY", "FRANKFORT");
		EUU.put("LOUISIANA", "BATON ROUGE");
		EUU.put("MAINE", "AUGUSTA");
		EUU.put("MARYLAND", "ANNAPOLIS");
		EUU.put("MASSACHUSETTS", "BOSTON");
		EUU.put("MICHIGAN", "LANSING");
		EUU.put("MINNESOTA", "SAINT PAUL");
		EUU.put("MISSISSIPPI", "JACKSON");
		EUU.put("MISSOURI", "JEFFERSON CITY");
		EUU.put("MONTANA", "HELEN");
		EUU.put("NEBRASKA", "LINCOLN");
		EUU.put("NEVADA", "CARSON CITY");
		EUU.put("NEW JERSEY", "TRENTON");
		EUU.put("NEW YORK", " ALBANY");
		EUU.put("NEW HAMPSHIRE", "CONCORD");
		EUU.put("NEW MEXICO", "SANTA FE");
		EUU.put("OHIO", "COLUMBUS");
		EUU.put("OKLAHOMA", "OKLAHOMA CITY");
		EUU.put("OREGON", "SALEM");
		EUU.put("PENNSYLVANIA", "HARRISBURG");
		EUU.put("RHODE ISLAND", "PROVIDENCE");
		EUU.put("TENNESSEE", "NASHVILLE");
		EUU.put("TEXAS", "AUSTIN");
		EUU.put("UTAH", "SALT LAKE CITY");
		EUU.put("VERMONT", "MONTPELIER");
		EUU.put("VIRGINIA", "RICHMOND");
		EUU.put("WEST VIRGINIA", "CHARLESTON");
		EUU.put("WASHINGTON", "OLYMPIA");
		EUU.put("WISCONSIN", "MADISON");
		EUU.put("WYOMING", "CHEVENNE");

		MEXICO.put("AGUASCALIENTES", "AGUASCALIENTES");
		MEXICO.put("BAJA CALIFORNIA", "MEXICALI");
		MEXICO.put("BAJA CALIFORNIA SUR", "LA PAZ");
		MEXICO.put("CAMPECHE", "SAN FRANCISCO DE CAMPECHE");
		MEXICO.put("CHIAPAS", "TUXTLA GUTIÉRREZ");
		MEXICO.put("CHIHUAHUA", "CHIHUAHUA");
		MEXICO.put("COAHUILA", "SALTILLO");
		MEXICO.put("COLIMA", "COLIMA");
		MEXICO.put("DURANGO", "VICTORIA DE DURANGO");
		MEXICO.put("ESTADO DE MEXICO", "TOLUCA DE LERDO");
		MEXICO.put("GUANAJUATO", "GUANAJUATO");
		MEXICO.put("GUERRERO", "CHILPANCINGO DE LOS BRAVO");
		MEXICO.put("HIDALGO", "PACHUCA DE SOTO");
		MEXICO.put("JALISCO", "GUADALAJARA");
		MEXICO.put("MICHOACAN", "MORELIA");
		MEXICO.put("MORELOS", "CUERNAVACA");
		MEXICO.put("NAYARIT", "TEPIC");
		MEXICO.put("NUEVO LEÓN", "MONTERREY");
		MEXICO.put("OAXACA", "OAXACA DE JUÁREZ");
		MEXICO.put("PUEBLA", "PUEBLA DE ZARAGOZA");
		MEXICO.put("QUERETARO", "SANTIAGO DE QUERÉTARO");
		MEXICO.put("QUINTANA ROO", "CHETUMAL");
		MEXICO.put("SAN LUIS POTOSI", "SAN LUIS POTOSÍ");
		MEXICO.put("SINALOA", "CULIACÁN ROSALES");
		MEXICO.put("SONORA", "HERMOSILLO");
		MEXICO.put("TABASCO", "VILLAHERMOSA");
		MEXICO.put("TAMAULIPAS", "CIUDAD VICTORIA");
		MEXICO.put("TLAXCALA", "TLAXCALA DE XICOHTÉNCATL");
		MEXICO.put("VERACRUZ", "XALAPA-ENRÍQUEZ.");
		MEXICO.put("YUCATAN", "MÉRIDA");
		MEXICO.put("ZACATECAS", "ZACATECAS");
		
		
	}
}
