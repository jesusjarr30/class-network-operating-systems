import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

public class Chat extends Frame implements ActionListener,WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField ip_campo;
	private TextField tf_resp;
	private TextArea ta_chat;
	private Button btn;
	private Receptor re;
	
	public Chat() {
		super("Conexion chat");
		ta_chat=new TextArea();
		ta_chat.setEditable(false);
		btn=new Button("Enviar");
		setLayout(new BorderLayout());
		
		PanelS ps =new PanelS();
		
		add("North",ps);
		add("Center",ta_chat);
		add("South",btn);
		
		
		re=new Receptor();
		re.start();
		btn.addActionListener(this);
		addWindowListener(this);
		setSize(600,600);
		
	}
	class PanelS extends Panel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Label lb_ip;
		private Label lb_mes;
		
		public PanelS() {
			
			lb_ip=new Label("IP :");
			lb_mes=new Label("El mensaje");
			add(lb_ip);
			ip_campo=new TextField(20);
			add(ip_campo);
			add(lb_mes);
			tf_resp=new TextField(20);
			add(tf_resp);
			
		
		}
	}
	class Receptor extends Thread{
		private DatagramSocket socket_rep;
		public void run() {
			int puerto_in=4562;
			try {
				socket_rep=new DatagramSocket(puerto_in);
				byte[] buffer =new byte[1024];
				DatagramPacket dp;
				dp= new DatagramPacket(buffer,buffer.length);
				while(true) {
					try {
						socket_rep.receive(dp);
						ta_chat.append("Ip del emisor: "+dp.getAddress().getHostAddress()+": ");
						ta_chat.append((new String(buffer,0,dp.getLength()))+"\n");
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Chat v=new Chat();
		v.setVisible(true);
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ip=ip_campo.getText();
		String mensaje=tf_resp.getText();
		DatagramPacket dp;
		
		byte[] buffer;
		buffer =mensaje.getBytes();
		try {
			DatagramSocket socket_e= new DatagramSocket();
			try {
				dp=new DatagramPacket(buffer,buffer.length,InetAddress.getByName(ip),4562);
				try {
					socket_e.send(dp);
					socket_e.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
