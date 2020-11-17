package Controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Modelo.Procesos;




/*
 * La clase admintrador fingira 
 * de parte central del sistema 
 * donde los procesos se cargan
 * desde un archivo .txt para
 * que asi puedan ser procesados 
 * por el sistema de acorde a 
 * los diferentes requerimientos
 * */
public class Administrador extends JFrame{
	
	//////////////////////////////////////////////////////////////////////////////
	//////////////////Agregacion de componentes GUI//////////////////////////////
	
	
	private JPanel contentPane;
	private JLabel lblRecursos;
	private JLabel lblRImpre;
	private JLabel lblEscaners;
	private JProgressBar pbEscaners;
	private JLabel lblModem;
	private JProgressBar pbModen;
	private JLabel lblCd;
	private JProgressBar pbCDs;
	private JLabel lblMemoria;
	private JProgressBar pbMemoriaT;
	private JLabel lblMemorias;
	private JProgressBar pbMemoriaTR;
	private JLabel lblMemoriaTiempoReal;
	private JProgressBar pbMemoriaUS;
	private JLabel lblMemoriaUsuario;
	private JLabel lblEnProcesador;
	private JLabel lblInformacionDeProceso;
	private JProgressBar pbImpresoras;
	private JScrollPane scrollPane;
	private JTextArea textAreaBitacora;
	private JScrollPane scrollPane_1;
	private JTextField txfMemoriaTotal;
	private JTextField texfMemoriaTiempoR;
	private JTextField texfMemoriaUser;
	private JTextField texfImpresoras;
	private JTextField texfEscaner;
	private JTextField texfModen;
	private JTextField texfCDS;
	
	//private JTextArea textAreaProcesInfo;
	
	JDesktopPane escritorio = new JDesktopPane();
	
	JTextArea ProcesadorAccion = new JTextArea();
	
	JTextArea textAreaProcesInfo = new JTextArea();
	
	JFrame contenedor = new JFrame("Emulador CPU");
	////////////////////////////////////////////////////////////////////////////
	
	
	boolean allFinish=true;
	public int impresora = 2, modem = 1, scanner = 1, cD = 2;
	public int TU=960,TR=64; 
	public int memoria = TU+TR;
	Procesos proceso;//Agregacion de la clase proceso
	Procesos trabajo;
	public int contador=1;
	public int tiempo=0;
	int cantidadRetroalimentacion=0;

	/*Arreglos para almacenar los procesos que se cargan desde el .txt*/
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////
	////////////Espacio prueba interfaz colas////////////////////////////////////
	
	
	
	
	Queue<Procesos>tR=new LinkedList<Procesos>();//procesos tiempo real
	Queue<Procesos>P1=new LinkedList<Procesos>();//procesos prioridad 1
	Queue<Procesos>P2=new LinkedList<Procesos>();//procesos prioridad 2
	Queue<Procesos>P3=new LinkedList<Procesos>();//procesos prioridad 3
	Queue<Procesos>all=new LinkedList<Procesos>();//cola del planificador
	
	
	ArrayList<Procesos>totalProcesos=new ArrayList<Procesos>();
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////

public Administrador(int impresora, int modem, int scanner, int cD, int memoria) {
	
		this.impresora = impresora;
		this.modem = modem;
		this.scanner = scanner;
		this.cD = cD;
		this.memoria = memoria;
	}

	/*
	 * constructor pricipal de la clase Admistrador 
	 * 
	 * */
	public Administrador() {
		
		/*
		 * Se definen los prinbcipales componentes basicos para la interfaz grafica
		 * como Areas de texto, barras de progreso.
		 * 
		 * */
		
		getContentPane().setLayout(null);
	
		contenedor.setSize(1476, 716);
		contenedor.setResizable(true);
	
		
		contenedor.getContentPane().add(escritorio);
		contentPane = new JPanel();
		escritorio.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		ProcesadorAccion.setToolTipText("Indicar");
		ProcesadorAccion.setEditable(false);


		
		
		ProcesadorAccion.setBackground(new Color(255, 255, 240));
		//ProcesadorAccion.setHorizontalAlignment(SwingConstants.CENTER);
		ProcesadorAccion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		ProcesadorAccion.setBounds(1158, 459, 181, 101);
		escritorio.add(ProcesadorAccion);
		ProcesadorAccion.setColumns(10);
		
		
	    pbImpresoras = new JProgressBar();
		pbImpresoras.setBounds(603, 99, 353, 35);
		pbImpresoras.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		pbImpresoras.setForeground(new Color(32, 178, 170));
		
		pbImpresoras.setMaximum(2);
		pbImpresoras.setStringPainted(true);
		pbImpresoras.setMinimum(0);
		//pbImpresoras.setValue(1);
	

		escritorio.add(pbImpresoras);
		
		lblRecursos = new JLabel("Recursos de sistema");
		lblRecursos.setBounds(607, 35, 219, 49);
		lblRecursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecursos.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		escritorio.add(lblRecursos);
		
		lblRImpre = new JLabel("Impresoras :");
		lblRImpre.setBounds(410, 97, 181, 35);
		lblRImpre.setHorizontalAlignment(SwingConstants.CENTER);
		lblRImpre.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		escritorio.add(lblRImpre);
		
		lblEscaners = new JLabel("Escaners :");
		lblEscaners.setBounds(410, 149, 181, 35);
		lblEscaners.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscaners.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		escritorio.add(lblEscaners);
		
		pbEscaners = new JProgressBar();
		pbEscaners.setBounds(603, 151, 353, 35);
		//pbEscaners.setValue(1);
		pbEscaners.setStringPainted(true);
		pbEscaners.setMinimum(0);
		pbEscaners.setMaximum(1);
		pbEscaners.setForeground(new Color(32, 178, 170));
		pbEscaners.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		escritorio.add(pbEscaners);
		
		lblModem = new JLabel("Modem :");
		lblModem.setBounds(410, 197, 181, 35);
		lblModem.setHorizontalAlignment(SwingConstants.CENTER);
		lblModem.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		escritorio.add(lblModem);
		
		pbModen = new JProgressBar();
		pbModen.setBounds(603, 199, 353, 35);
		
		pbModen.setStringPainted(true);
		pbModen.setMinimum(0);
		pbModen.setMaximum(1);
		pbModen.setForeground(new Color(32, 178, 170));
		pbModen.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		escritorio.add(pbModen);
		
		lblCd = new JLabel("CD's :");
		lblCd.setBounds(410, 245, 181, 35);
		lblCd.setHorizontalAlignment(SwingConstants.CENTER);
		lblCd.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		escritorio.add(lblCd);
		
		pbCDs = new JProgressBar();
		pbCDs.setBounds(603, 247, 353, 35);
		
		pbCDs.setStringPainted(true);
		pbCDs.setMinimum(0);
		pbCDs.setMaximum(2);
		pbCDs.setForeground(new Color(32, 178, 170));
		pbCDs.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		escritorio.add(pbCDs);
		
		lblMemoria = new JLabel("Memoria total :");
		lblMemoria.setBounds(410, 374, 181, 35);
		lblMemoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemoria.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		escritorio.add(lblMemoria);
		
		pbMemoriaT = new JProgressBar();
		pbMemoriaT.setBounds(603, 376, 353, 35);
		
		pbMemoriaT.setStringPainted(true);
		pbMemoriaT.setMinimum(0);
		pbMemoriaT.setMaximum(1024);
		pbMemoriaT.setForeground(new Color(0, 255, 127));
		pbMemoriaT.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		escritorio.add(pbMemoriaT);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(1021, 104, 407, 263);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		escritorio.add(scrollPane);
		
		textAreaBitacora = new JTextArea();
		textAreaBitacora.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textAreaBitacora.setBackground(new Color(255, 255, 240));
		textAreaBitacora.setEditable(false);
		scrollPane.setViewportView(textAreaBitacora);
		
		JLabel lblNewLabel = new JLabel("Bitacora de procesos");
		lblNewLabel.setBounds(1083, 42, 296, 35);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		escritorio.add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(65, 120, 296, 388);
		escritorio.add(scrollPane_1);
		textAreaProcesInfo.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		
		
		textAreaProcesInfo.setBackground(new Color(255, 255, 240));
		textAreaProcesInfo.setEditable(false);
		
		//textAreaBitacora.setText(proceso.verID());
		scrollPane_1.setViewportView(textAreaProcesInfo);
		
		lblMemorias = new JLabel("Memoria");
		lblMemorias.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemorias.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		lblMemorias.setBounds(613, 305, 219, 49);
		escritorio.add(lblMemorias);
		
		pbMemoriaTR = new JProgressBar();
	
		pbMemoriaTR.setStringPainted(true);
		pbMemoriaTR.setMinimum(0);
		pbMemoriaTR.setMaximum(64);
		pbMemoriaTR.setForeground(new Color(152, 251, 152));
		pbMemoriaTR.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		pbMemoriaTR.setBounds(603, 424, 353, 35);
		escritorio.add(pbMemoriaTR);
		
		lblMemoriaTiempoReal = new JLabel("Memoria tiempo R :");
		lblMemoriaTiempoReal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemoriaTiempoReal.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		lblMemoriaTiempoReal.setBounds(410, 422, 181, 35);
		escritorio.add(lblMemoriaTiempoReal);
		
		pbMemoriaUS = new JProgressBar();
		pbMemoriaUS.setValue(1);
		pbMemoriaUS.setStringPainted(true);
		pbMemoriaUS.setMinimum(0);
		pbMemoriaUS.setMaximum(960);
		pbMemoriaUS.setForeground(new Color(144, 238, 144));
		pbMemoriaUS.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		pbMemoriaUS.setBounds(603, 474, 353, 35);
		escritorio.add(pbMemoriaUS);
		
		lblMemoriaUsuario = new JLabel("Memoria usuario : ");
		lblMemoriaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemoriaUsuario.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		lblMemoriaUsuario.setBounds(410, 472, 181, 35);
		escritorio.add(lblMemoriaUsuario);
		
		lblEnProcesador = new JLabel("En procesador");
		lblEnProcesador.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnProcesador.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		lblEnProcesador.setBounds(1097, 405, 296, 35);
		escritorio.add(lblEnProcesador);
		
		lblInformacionDeProceso = new JLabel("Informacion de Proceso");
		lblInformacionDeProceso.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacionDeProceso.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
		lblInformacionDeProceso.setBounds(54, 42, 296, 35);
		escritorio.add(lblInformacionDeProceso);
		
		txfMemoriaTotal = new JTextField();
		txfMemoriaTotal.setBackground(new Color(224, 255, 255));
		txfMemoriaTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txfMemoriaTotal.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		txfMemoriaTotal.setText("1024");
		txfMemoriaTotal.setEditable(false);
		txfMemoriaTotal.setBounds(964, 382, 49, 27);
		escritorio.add(txfMemoriaTotal);
		txfMemoriaTotal.setColumns(10);
		
		texfMemoriaTiempoR = new JTextField();
		texfMemoriaTiempoR.setBackground(new Color(224, 255, 255));
		texfMemoriaTiempoR.setText("64");
		texfMemoriaTiempoR.setEditable(false);
		texfMemoriaTiempoR.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		texfMemoriaTiempoR.setHorizontalAlignment(SwingConstants.CENTER);
		texfMemoriaTiempoR.setColumns(10);
		texfMemoriaTiempoR.setBounds(964, 430, 49, 27);
		escritorio.add(texfMemoriaTiempoR);
		
		texfMemoriaUser = new JTextField();
		texfMemoriaUser.setBackground(new Color(224, 255, 255));
		texfMemoriaUser.setText("960");
		texfMemoriaUser.setEditable(false);
		texfMemoriaUser.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		texfMemoriaUser.setHorizontalAlignment(SwingConstants.CENTER);
		texfMemoriaUser.setColumns(10);
		texfMemoriaUser.setBounds(964, 480, 49, 27);
		escritorio.add(texfMemoriaUser);
		
		texfImpresoras = new JTextField();
		texfImpresoras.setText("2");
		texfImpresoras.setHorizontalAlignment(SwingConstants.CENTER);
		texfImpresoras.setColumns(10);
		texfImpresoras.setEditable(false);
		texfImpresoras.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		texfImpresoras.setBackground(new Color(224, 255, 255));
		texfImpresoras.setBounds(964, 105, 49, 27);
		escritorio.add(texfImpresoras);
		
		texfEscaner = new JTextField();
		texfEscaner.setText("1");
		texfEscaner.setHorizontalAlignment(SwingConstants.CENTER);
		texfEscaner.setColumns(10);
		texfEscaner.setEditable(false);
		texfEscaner.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		texfEscaner.setBackground(new Color(224, 255, 255));
		texfEscaner.setBounds(964, 157, 49, 27);
		escritorio.add(texfEscaner);
		
		texfModen = new JTextField();
		texfModen.setText("1");
		texfModen.setHorizontalAlignment(SwingConstants.CENTER);
		texfModen.setColumns(10);
		texfModen.setEditable(false);
		texfModen.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		texfModen.setBackground(new Color(224, 255, 255));
		texfModen.setBounds(964, 205, 49, 27);
		escritorio.add(texfModen);
		
		texfCDS = new JTextField();
		texfCDS.setText("2");
		texfCDS.setHorizontalAlignment(SwingConstants.CENTER);
		texfCDS.setColumns(10);
		texfCDS.setEditable(false);
		texfCDS.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		texfCDS.setBackground(new Color(224, 255, 255));
		texfCDS.setBounds(964, 253, 49, 27);
		escritorio.add(texfCDS);
	
		contenedor.setVisible(true);
		contenedor.setResizable(false);
		contenedor.setLocationRelativeTo(null);
		contenedor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	
	}
	


	
	
	/*
	 * Metodo que permite que el archivo txt con los procesos se pueda cargar
	 * debe contar con la estructura especificada en el enunciado de proyecto
	 * 
	 * */
	        public void cargarArchivoTxt() {
		
		
		try {
		
            JOptionPane.showMessageDialog(null, "Cargar Procesos","Cargar txt",JOptionPane.PLAIN_MESSAGE);

			/*
			 * se crea un nuevo BufferedReader para poder leer desde el sistema el archivo al cual se le pasa como
			 * parametro la direccion de memoria en la pc donde esta alojado el archivo 
			 * 
			 * */		          
				BufferedReader lector = new BufferedReader(new FileReader("C:\\Users\\Owner\\Desktop\\Proceso.txt"));// se crea bufferedreader para leer el documento
									
									String s,s2 =new String();
									/*
									 * se crean las variables para obtener los datos
									 * 
									 * */
									int iD = 0,tiempoCpuRestante=0,tiempoLlegada,prioridad,tiempoCPU,mbt,impresoras,escaners,modems,cds;
									/*
									 * Ciclo que permite recorrer el archivo 
									 * 
									 * */
									while((s = lector.readLine()) != null&&contador<=1000) {
										iD ++;
										
										s2+= s+"\n";
										/*
										 * se lee un arreglo separando los datos con un coma 
										 * 
										 * */
										String[]informacion = s.split(",");
										/*
										 * Ahora se asignan a la variable correspondiente
										 * 
										 * */
										tiempoLlegada = Integer.parseInt(informacion[0]);
										prioridad= Integer.parseInt(informacion[1]);
										tiempoCPU= Integer.parseInt(informacion[2]);
										mbt= Integer.parseInt(informacion[3]);
										impresoras= Integer.parseInt(informacion[4]);
										escaners= Integer.parseInt(informacion[5]);
										modems= Integer.parseInt(informacion[6]);
										cds= Integer.parseInt(informacion[7]);
										tiempoCpuRestante=tiempoCPU;
										
										/*
										 * Los procesos se cargan en las estructuras de datos definidas para administrarlos
										 * en la cola all se cargan todos y esta sera la principal del planificador
										 * en el ArrayList totalProcesos para actualizar la informacion por proceso
										 * 
										 * */
										
										
										all.add(new Procesos(iD,tiempoLlegada,prioridad,tiempoCPU,tiempoCpuRestante,mbt,impresoras,escaners,modems,cds));
										totalProcesos.add(new Procesos(iD,tiempoLlegada,prioridad,tiempoCPU,tiempoCpuRestante,mbt,impresoras,escaners,modems,cds));
										contador++;//contador para verificar la cantidad de procesos
								

									}
							
									lector.close();// se cierra el archivo una vez que se completa la accion
									
								} catch (Exception e) {
									System.out.println(e);
									JOptionPane.showMessageDialog(null, e, "Error de lectura", JOptionPane.WARNING_MESSAGE);
											System.exit(0);
										}
					
		JOptionPane.showMessageDialog(null, "Cargar Procesos","Iniciar Procesos",JOptionPane.PLAIN_MESSAGE);

	}
	        
	        /*
	         * El metodo soltarProceso es el encargado de revisar la cola del administrador 
	         * verificando si hay recusos o memoria para que un proceso ingrese a las 
	         * diferentes colas del CPU
	         * 
	         * */
	
	
	
	public void soltarProceso() {
		
		/* 
		 * La verificacion consta de los siguientes pasos 
		 * 
		 * */
		
		if(all.peek()==null) {//se pregunta si la cola del administrador esta vacia 
			
			//no se hace nada si esta vacia 
			
		}else {//si hay procesos
			
		
		if(all.peek().getPrioridad()==0) {//se pregunta por la prioridad del proceso
		
			
			if(all.peek().getMemoriaRequerida()<=TR) {//se verifica si hay memoria 
				
				 trabajo =all.poll();// si hay memoria saca el proceso de la cola
				 			 				 
					System.out.println("Memoria Tiempo real : "+getTR());
					
					/*
					 * A continuacion asigna la memoria al proceso
					 * 
					 * */
					
				 setTR(getTR()-trabajo.getMemoriaRequerida());
				 setMemoria(getTU()+getTR());
				 pbMemoriaT.setValue(getMemoria());
				 txfMemoriaTotal.setText(verMem());
				 pbMemoriaTR.setValue(getTR());
				 texfMemoriaTiempoR.setText(verMemTR());
				 
				 
						
		
				
				 if(tR.peek()==null) {//Comprueba si la cola esta limpia puede acceder a CPU de inmediato
					 
					 textAreaBitacora.append("Proceso  "+ trabajo.getiD()+" iniciado\n");//Actualizacion de bitacora

						CPU(trabajo);
						
						
					}else {//si hay procesos simplemente se agrega a la cola 
						
						 textAreaBitacora.append("Proceso  "+ trabajo.getiD()+" iniciado\n");
						tR.add(trabajo);
						
					}
				 
				
			}else {
				 textAreaBitacora.append("Proceso "+all.peek().getiD()+" esperando memoria"+"\n");
				System.out.println("Proceso esperando memoria: "+"Proceso# "+all.peek().getiD());
			}
				
		}//if prioridad 0
		}
		
		/*
		 * De la manera anterior el algoritmo sigue comprobando en la prioridades, 1,2 o 3
		 * en las colas de prioridad 1, 2 o 3 se verifican si las colas de mayor prioridad 
		 * poseen elementos, si no los poseene el porceso usa de inmediato el CPu 
		 * 
		 * */
		
		if(all.peek()==null) {
			
		}else {
			
			
		 if(all.element().getPrioridad()==1) {
			 
			 if(all.peek().getMemoriaRequerida()<=memoria&&all.peek().getImpresaras()<=getImpresora()&&all.peek().getEscaner()<=getScanner()
					 &&all.peek().getModem()<=getModem()&&all.peek().getcD()<=getcD()) {//Comprobacion de recusos o memoria
				 
				 
				 trabajo =all.poll();
				 
				 /*
					 * Asiganacion de recuros al proceso tales como memoria, impresoras, escaners, modems o cd's
					 * 
					 * */
				 
				 setTU(getTU()-trabajo.getMemoriaRequerida());
		         setMemoria(getTU()+getTR());
		         
				 pbMemoriaT.setValue(getMemoria());
				 txfMemoriaTotal.setText(verMem());
				 pbMemoriaUS.setValue(getTU());
				 texfMemoriaUser.setText(verMemTU());
				 pbEscaners.setValue(getScanner());
				 texfEscaner.setText(verEscaner());
				 pbImpresoras.setValue(getImpresora());
				 texfImpresoras.setText(verImpresoras());
				 pbModen.setValue(getModem());
				 texfModen.setText(verModem());
				 pbCDs.setValue(getcD());
				 texfCDS.setText(verCDS());
				 setImpresora(getImpresora()-trabajo.getImpresaras());
				 setScanner(getScanner()-trabajo.getEscaner());
				 setModem(getModem()-trabajo.getModem());
				 setcD(getcD()-trabajo.getcD());
				 
				 trabajo.setImpresorasAsig(trabajo.getImpresaras());
				 trabajo.setEscanerAsig(trabajo.getEscaner());
				 trabajo.setModemsAsig(trabajo.getModem());
				 trabajo.setCdAsig(trabajo.getcD());
				 
				 
				 System.out.println("Memoria P1 Ac: "+getMemoria());
				 
				 if(tR.peek()==null&&P1.peek()==null) {
					 
						
					 textAreaBitacora.append("Proceso "+ trabajo.getiD()+" iniciado\n");
						 CPU(trabajo);
					
						 
					 }else {
						 
					
						 textAreaBitacora.append("Proceso "+ trabajo.getiD()+" iniciado\n");
						 P1.add(trabajo);
				
					 }
				 
				 
			 }else {
				 textAreaBitacora.append("Proceso  "+ all.peek().getiD()+" esperando recursos\n");
				 System.out.println("Proceso esperando memoria o recursos: "+all.peek().getiD());
			 }
			 
			
			
		}
	}
	
	
		if(all.peek()==null) {
			
		}else {
			
			 if(all.peek().getPrioridad()==2) {
				 
				 if(all.peek().getMemoriaRequerida()<=memoria&&all.peek().getImpresaras()<=getImpresora()&&all.peek().getEscaner()<=getScanner()
						 &&all.peek().getModem()<=getModem()&&all.peek().getcD()<=getcD()) {//Comprobacion de recusos o memoria
						
					 trabajo =all.poll();
					 
					/*
					 * Asiganacion de recuros al proceso tales como memoria, impresoras, escaners, modems o cd's
					 * 
					 * */
					 
					 setTU(getTU()-trabajo.getMemoriaRequerida());
					 setMemoria(getTU()+getTR());
					 
					 pbMemoriaT.setValue(getMemoria());
					 txfMemoriaTotal.setText(verMem());
					 pbMemoriaUS.setValue(getTU());
					 texfMemoriaUser.setText(verMemTU());
					 pbEscaners.setValue(getScanner());
					 texfEscaner.setText(verEscaner());
					 pbImpresoras.setValue(getImpresora());
					 texfImpresoras.setText(verImpresoras());
					 pbModen.setValue(getModem());
					 texfModen.setText(verModem());
					 pbCDs.setValue(getcD());
					 texfCDS.setText(verCDS());
					 setImpresora(getImpresora()-trabajo.getImpresaras());
					 setScanner(getScanner()-trabajo.getEscaner());
					 setModem(getModem()-trabajo.getModem());
					 setcD(getcD()-trabajo.getcD());
					 
					 trabajo.setImpresorasAsig(trabajo.getImpresaras());
					 trabajo.setEscanerAsig(trabajo.getEscaner());
					 trabajo.setModemsAsig(trabajo.getModem());
					 trabajo.setCdAsig(trabajo.getcD());
					 
					 if(tR.peek()==null&&P1.peek()==null&&P2.peek()==null) {
						 
						 textAreaBitacora.append("Proceso "+ trabajo.getiD()+" iniciado\n");
							 
							 CPU(trabajo);
						
							 
						 }else {
					 
							 P2.add(trabajo);
							 textAreaBitacora.append("Proceso2 "+ trabajo.getiD()+" iniciado\n");
						 }
					 
					 
				 }else {
					 textAreaBitacora.append("Proceso "+ all.peek().getiD()+" esperando recursos\n");
					
					 System.out.println("Proceso esperando memoria o recursos: "+all.peek().getiD());
				 }
				 
				
				
			}
		}
		
		if(all.peek()==null) {
			
		}else {
		
			if(all.element().getPrioridad()==3) {
				 
				 if(all.peek().getMemoriaRequerida()<=memoria&&all.peek().getImpresaras()<=getImpresora()&&all.peek().getEscaner()<=getScanner()
						 &&all.peek().getModem()<=getModem()&&all.peek().getcD()<=getcD()) {//Comprobacion de recusos o memoria
						
					 trabajo =all.poll();
					 
					 /*
						 * Asiganacion de recuros al proceso tales como memoria, impresoras, escaners, modems o cd's
						 * 
						 * */
					 
					 setTU(getTU()-trabajo.getMemoriaRequerida());
					 setMemoria(getTU()+getTR());
					 
					 pbMemoriaT.setValue(getMemoria());
					 txfMemoriaTotal.setText(verMem());
					 pbMemoriaUS.setValue(getTU());
					 texfMemoriaUser.setText(verMemTU());
					 pbEscaners.setValue(getScanner());
					 texfEscaner.setText(verEscaner());
					 pbImpresoras.setValue(getImpresora());
					 texfImpresoras.setText(verImpresoras());
					 pbModen.setValue(getModem());
					 texfModen.setText(verModem());
					 pbCDs.setValue(getcD());
					 texfCDS.setText(verCDS());
					 setImpresora(getImpresora()-trabajo.getImpresaras());
					 setScanner(getScanner()-trabajo.getEscaner());
					 setModem(getModem()-trabajo.getModem());
					 setcD(getcD()-trabajo.getcD());
					 
					 trabajo.setImpresorasAsig(trabajo.getImpresaras());
					 trabajo.setEscanerAsig(trabajo.getEscaner());
					 trabajo.setModemsAsig(trabajo.getModem());
					 trabajo.setCdAsig(trabajo.getcD());
					 
					 if(tR.peek()==null&&P1.peek()==null&&P2.peek()==null&&P3.peek()==null) {
						 
						 textAreaBitacora.append("Proceso  "+ trabajo.getiD()+" iniciado\n");
							 
							 CPU(trabajo);
						
							 
						 }else {
							 
							 textAreaBitacora.append("Proceso "+ trabajo.getiD()+" iniciado\n");
							 P3.add(trabajo);
					
						 }
					 
					 
				 }else {
					 textAreaBitacora.append("Proceso "+ all.peek().getiD()+" esperando recursos\n");
					 System.out.println("Proceso esperando memoria o recursos: "+all.peek().getiD());
				 }
				 
				
				
			}
		}
		/*
		 * Comprobacion que permite retroalimentar el CPU
		 * 
		 * */

		if(tR.peek()!=null||P1.peek()!=null||P2.peek()!=null||P3.peek()!=null) {
			retroalimentarCPU();
		}
		
	
	
}
	
	
	/*
	 * El metodo CPU recibe un proceso por parametro y verifica su tiempo de cpu
	 * de esa manera si es > a 0 le resta 1, si es = a 0 recupera los recursos
	 * 
	 * */
	

	
	public void CPU(Procesos proceso) {
		tiempo++;
		String recurso="";
		String almacenamientoProcesos="";
	
		
	
		
		/*
		 * ver proceso en cpu en tiempo real
		 * ademas muestra el tiempo que lleva activo
		 * 
		 * */
		
		ProcesadorAccion.setText(proceso.verID(tiempo));
		
		
		 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" en Ejecución \n");
		 
		 
          if(proceso.getTiempoCpuRestante()!=0) {
			
			/*
			 * Se resta 1 al tiempo actual del proceso
			 * 
			 * */
		
			proceso.setTiempoCpuRestante(proceso.getTiempoCpuRestante()-1);
					
			System.out.println("********************************");
			System.out.println("Espacio CPU");
			System.out.println(proceso.toString());
			System.out.println("********************************");
		
		
			if(proceso.getTiempoCpuRestante()==0) {
				
				if(proceso.getPrioridad()==0) {
					
					
					 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" Finalizado de prioridad 0 \n");
						System.out.println("proceso muerto: "+proceso.getiD());
						
						 
						setTR(getTR()+proceso.getMemoriaRequerida());//cambiar por memoria tiempo real
						 setMemoria(getTU()+getTR());
						/*
						 * Se actualiza la interfaz
						 * 
						 * */
					
						 pbMemoriaTR.setValue(getTR());
						 pbMemoriaT.setValue(getMemoria()); 
						 txfMemoriaTotal.setText(verMem());
						 pbEscaners.setValue(getScanner());
						 texfEscaner.setText(verEscaner());
						 pbImpresoras.setValue(getImpresora());
						 texfImpresoras.setText(verImpresoras());
						 pbModen.setValue(getModem());
						 texfModen.setText(verModem());
						 pbCDs.setValue(getcD());
						 texfCDS.setText(verCDS());
						 texfMemoriaTiempoR.setText(verMemTR());
						 /*
						  * Se actualizan los recuros
						  * 
						  * */
						 
						 setImpresora(getImpresora()+proceso.getImpresaras());
						 setScanner(getScanner()+proceso.getEscaner());
						 setModem(getModem()+proceso.getModem());
						 setcD(getcD()+proceso.getcD());
				}else {
				 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" Finalizado \n");//se imprime en bitacora
				System.out.println("proceso muerto: "+proceso.getiD());
				/*
				 * Se actualizan los componenetes graficos y recursos
				 * 
				 * */
								 
					setTU(getTU()+proceso.getMemoriaRequerida());
					 setMemoria(getTU()+getTR());
					 txfMemoriaTotal.setText(verMem());
				 pbMemoriaT.setValue(getMemoria());
				 pbMemoriaUS.setValue(getTU());	
				 texfMemoriaUser.setText(verMemTU());
				 pbEscaners.setValue(getScanner());
				 texfEscaner.setText(verEscaner());
				 pbImpresoras.setValue(getImpresora());
				 texfImpresoras.setText(verImpresoras());
				 pbModen.setValue(getModem());
				 pbCDs.setValue(getcD());
				 texfCDS.setText(verCDS());
				 setImpresora(getImpresora()+proceso.getImpresaras());
				 setScanner(getScanner()+proceso.getEscaner());
				 setModem(getModem()+proceso.getModem());
				 setcD(getcD()+proceso.getcD());
				}
				
				/*
				 * Obtener memoria en timepo real por proceso cada quantum
				 * 
				 * */
				System.out.println("Memoria Actualizada: "+getMemoria());
				System.out.println("Memoria Tiempo Real: "+getTR());
			}
			
			/*
			 * serie de verificaciones para ver a donde ira el proceso
			 * en general se verifica que prioridad tiene y asi se envia
			 * a alguna cola, se suspende momentaneamente, si no hay 
			 * mas procesos en cola este seguira asignado en CPU
			 * 
			 * */
			
			if(proceso.getPrioridad()==0&&proceso.getTiempoCpuRestante()!=0) {
				 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" Suspendido \n");

				tR.add(proceso);
				
			}if(proceso.getPrioridad()==1&&proceso.getTiempoCpuRestante()!=0) {
				 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" Suspendido \n");

				if(proceso.getPrioridadActual()==2||proceso.getPrioridadActual()==3) {
					proceso.setPrioridadActual(3);
					P3.add(proceso);
				}else {
				
				proceso.setPrioridadActual(2);
				P2.add(proceso);
				}
				
				
				
			}if(proceso.getPrioridad()==2&&proceso.getTiempoCpuRestante()!=0) {
				 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" Suspendido \n");

				if(proceso.getPrioridadActual()==3) {
					proceso.setPrioridadActual(3);
					P3.add(proceso);
				}else {
				
				proceso.setPrioridadActual(3);
				P3.add(proceso);
				
				}
				
			}if(proceso.getPrioridad()==3&&proceso.getTiempoCpuRestante()!=0) {
				 textAreaBitacora.append("Proceso  "+ proceso.getiD()+" Suspendido \n");

				P3.add(proceso);
			
			}
			
		
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		
		}
	//actualizar(proceso);//                    NO borrar para una mejor explicaccion 
	
	recurso="\nUbicacion de memoria : "+getMemoria()+"\nRecursos"
			+"\nImpresora Asignada : "+proceso.getImpresorasAsig()
			+"\nEscaner Asignado : "+proceso.getEscanerAsig()
			+"\nModem Asignado : "+proceso.getModemsAsig()
			+"\nCD's Asignados : "+proceso.getCdAsig();
	almacenamientoProcesos+=proceso.toString();
	
	
	textAreaProcesInfo.append(almacenamientoProcesos+recurso);
	
	/*
	 * Se llama a Soltar proceso para ver si hay algun proceso esperando 
	 * o el siguinete puede entrar ya que hay recursos disponibles
	 * 
	 * */

	soltarProceso();
}
	/*
	 * El metodo de retroalimentarCPU se encarga de verificar las colas de manera que 
	 * por orden de prioridad asignara los proceso que esten cargados en las colas de
	 * tiempo real o prioridad 1, 2 o 3
	 * 
	 * Asi si un proceso entra y dada su prioridad ya hay procesos en cola 
	 * este solo se agregara a la cola respectiva y este metodo las revisara 
	 * de manera continua para liberar recursos y memoria
	 * 
	 * */
	
	public void retroalimentarCPU() {
		
	
		/*
		 * Se verifica que las colas este vacias por orden de prioridad
		 * asi si una cola con un orden de atencion superior esta vacia 
		 * se comprueba la siguiente, para asi asignar el CPU al proceso 
		 * que este de primero en la cola 
		 * 
		 * */
	
		if(tR.peek()==null) {//se verifica si esta vacia 
			//Cola vacia
		}else {
				/*
				 * Si no esta vacia
				 * 
				 * */
				if(tR.peek().getTiempoCpuRestante()!=0) {
					/*
					 * Con el metodo poll() de la collection Queue se toma el primer
					 * elemento de cola y se lo asignamos a un proceso para enviarlo a CPU
					 * 
					 * */
					trabajo =tR.poll();
					CPU(trabajo);
					
				}else {
					//no hace nada
				}
		}
		/*
		 * Las comprobaciones van de manera escalonada asi que 
		 * solo enviara procesos que por orden de prioridad 
		 * esten esperando el CPU
		 * 
		 * */
				
				if(P1.peek()==null) {
					//Cola vacia
				}else {
				
				if(P1.peek().getTiempoCpuRestante()!=0) {
					trabajo =P1.poll();
					CPU(trabajo);
					
				}else {
					
					//Cola vacia
				}
				}
		
				
				if(P2.peek()==null) {
					//Cola vacia
				}else {
			
			if(P2.peek().getTiempoCpuRestante()!=0) {
				trabajo =P2.poll();
				CPU(trabajo);
				
			}else {
				//Cola vacia
			}
				}

				if(P3.peek()==null) {
					//Cola vacia
				}else {
			
			if(P3.peek().getTiempoCpuRestante()!=0) {
				trabajo =P3.poll();
				CPU(trabajo);
				
			}else {
				//Cola vacia
			}
				}
	
		
		
	}
	
public void actualizar(Procesos proceso) {
	String almacenamientoProcesos="";
	   textAreaProcesInfo.setText("");

	for(int i =0;i>=totalProcesos.size();i++) {
		if(proceso.getiD()==i) {
			
		totalProcesos.add(i, proceso);
		i++;
		totalProcesos.remove(i);
		i--;
		}else {
			System.out.println("No funciono la actualizacion");
		}
		
	}
	almacenamientoProcesos+=proceso.toString();
	for(int i =0;i>=totalProcesos.size();i++) {
		almacenamientoProcesos+=proceso.toString();
		textAreaProcesInfo.append(totalProcesos.get(i).toString());
	}
	
	
	
}

/*
 * metodo de confirmacion de que todos los prcesos han terminado
 * 
 * */
	
	public void confirmador() {
		
		if(tR.peek()==null&&P1.peek()==null&&P2.peek()==null&&P3.peek()==null&&all.peek()==null) {
			System.out.println("Procesos Finalizados");
			/*
			 * se recuperan todos los recursos
			 * 
			 * */
			 pbMemoriaTR.setValue(getTR());
			 txfMemoriaTotal.setText(verMem());
			 pbMemoriaT.setValue(getMemoria());
			 pbEscaners.setValue(getScanner());
			 texfEscaner.setText("1");
			 pbImpresoras.setValue(getImpresora());
			 texfImpresoras.setText("2");
			 pbModen.setValue(getModem());
			 texfModen.setText("1");
			 pbCDs.setValue(getcD());
			 texfCDS.setText("2");
			 ProcesadorAccion.setText("\n\n  Procesos Terminados");
			//System.exit(0);
			allFinish=true;
		}else {
			
		}
		
	}
	
    /*
     * Declaracion de getters y setters requeridos en la clase Administrador
     * 
     * */

	public int getTU() {
		return TU;
	}

	public void setTU(int tU) {
		TU = tU;
	}

	public int getTR() {
		return TR;
	}

	public void setTR(int tR) {
		TR = tR;
	}

	public int getImpresora() {
		return impresora;
	}

	public void setImpresora(int impresora) {
		this.impresora = impresora;
	}

	public int getModem() {
		return modem;
	}

	public void setModem(int modem) {
		this.modem = modem;
	}

	public int getScanner() {
		return scanner;
	}

	public void setScanner(int scanner) {
		this.scanner = scanner;
	}

	public int getcD() {
		return cD;
	}

	public void setcD(int cD) {
		this.cD = cD;
	}

	public int getMemoria() {
		return memoria;
	}

	public void setMemoria(int memoria) {
		this.memoria = memoria;
	}
	public String verMem() {
		return ""+getMemoria();
	}

	public Queue<Procesos> gettR() {
		return tR;
	}

	public void settR(Queue<Procesos> tR) {
		this.tR = tR;
	}
	public String verImpresoras() {
		return ""+getImpresora();
	}
	public String verEscaner() {
		return ""+getScanner();
	}
	public String verModem() {
		return ""+getModem();
	}
	public String verMemTR() {
		return ""+getTR();
	}
	public String verMemTU() {
		return ""+getTU();
	}
	public String verCDS() {
		return ""+getcD();
	}
	
}

