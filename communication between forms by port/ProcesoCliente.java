import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ProcesoCliente extends Frame implements WindowListener,ActionListener{

	TextArea respuesta =new TextArea();
	TextArea instrucciones = new TextArea();
	TextField estado=new TextField();
	Choice opciones=new Choice();
	Button boton = new Button();
	Socket socket =null;
	Panel panel =new PanelS();
	ObjectInputStream objIn=null;
	ObjectOutputStream objOut=null;
	
	
	public ProcesoCliente() {
		super("CLiente");
		
		setLayout(new BorderLayout());
		respuesta= new TextArea();
		respuesta.setEditable(false);
		instrucciones=new TextArea();
		instrucciones.setEditable(false);
		instrucciones.append("\nPara este programa el cliente solicitara el nombre de un estado de norte america\n");
		instrucciones.append("y se debera selecionar el pais al que pertenece el estado \n");
		instrucciones.append("el servidor se encargara de mandar como respuesta la capital de dicho estado\n");
		instrucciones.append("Si se ingresan datos incorrectos el servidor le indicara mediante mensaje de error\n");
		instrucciones.append("Nota: evitar usar ascentos para evitar errore,\n");
		instrucciones.append("los nombres de los estados de canada y EUU estan en ingles.\n");
		instrucciones.append("ejemplo Nueva York tendria que ser escrita como New York.\n");
		add("North",panel);
		add("South",respuesta);
		add("Center", instrucciones);
		boton.addActionListener(this);
		setSize(600,600);
		addWindowListener(this);
		
	}
	public static void main(String[] args) {
		ProcesoCliente cliente=new ProcesoCliente();
		cliente.setVisible(true);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando =e.getActionCommand();
		if (comando.equalsIgnoreCase("Buscar")) {
			//try {
			respuesta.append("Conectando con el seervidor\n");
				try {
					socket =new Socket("127.0.0.1",4534);
					objOut=new ObjectOutputStream(socket.getOutputStream());
					objIn= new ObjectInputStream(socket.getInputStream());
					respuesta.append("Conexion exitosa r\n");
					
					respuesta.append("Preparando datos para el envio \n");
					String estadoRes=estado.getText().toUpperCase();
					estadoRes+="|"+opciones.getSelectedIndex();
					respuesta.append("enviando \n");
					objOut.writeObject(estadoRes);
					
					respuesta.append("Extrayendo respuesta \n");
					String respuestaSer=(String)objIn.readObject();
					respuesta.append(respuestaSer+"\n\n");
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
	private class PanelS extends Panel{
		public PanelS() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			add(new Label("Estado :"));
			estado=new TextField(10);
			add(estado);
			opciones=new Choice();
			opciones.add("Canada");
			opciones.add("Estados Unidos");
			opciones.add("México");
			add (opciones);
			boton= new Button("Buscar");
			add(boton);
			
		}
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

}
