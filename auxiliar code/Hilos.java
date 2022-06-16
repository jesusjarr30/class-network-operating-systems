import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Hilos extends Frame implements WindowListener, ActionListener {

	private TextArea ta1;
	private TextArea ta2;
	private Button btn;
	private Hilo h_1;
	private Hilo h_2;
	
	private Hilos() {
		super("Hilos");
		setLayout(new BorderLayout());
		setSize(500,500);
		
		
		//inicializar elemntos
		ta1=new TextArea();
		ta2=new TextArea();
		ta1.setEditable(false);
		ta2.setEditable(false);
		btn=new Button("Enter");
		
		add("North",ta1);
		add("Center",ta2);
		add("South",btn);
		h_1=new Hilo(ta1,1000);
		h_2=new Hilo(ta2,2000);
		h_1.start();
		h_2.start();
		btn.addActionListener(this);
		addWindowListener(this);
		
		
	}
	public class Hilo extends Thread{
		
		public TextArea t_aux;
		public long tiempo;
		public int contador;
		public boolean band;
		
		public Hilo(TextArea ta1,long t) {
		t_aux=ta1;
		tiempo=t;
		contador=0;
		band=false;
		}
		public void pausar() {
			band=true;
		}
		public synchronized void correr() {
			band=false;
			
			notify();
		}
		public void run() {
			
			while(true) {
				if(band) {
					synchronized(this) {
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				contador++;
				t_aux.append(contador+"\n");
				try{
					sleep(tiempo);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}	
	}
	public static void main(String[] args) {
		Hilos h=new Hilos();
		h.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(h_1.band==true) {
			h_1.correr();
		}
		else {
			h_1.pausar();
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
