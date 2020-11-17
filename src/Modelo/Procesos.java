package Modelo;

public class Procesos {
	/*
	 * declaracion de los principales atributos esenciales de un proceso 
	 * 
	 * */
	
	private int iD,tiempoLlegada,prioridad,tiempoCpu,memoriaRequerida,impresaras,escaner,modem,cD;
	private String estatus;
	private int prioridadActual,tiempoCpuRestante,impresorasAsig,modemsAsig,escanerAsig,cdAsig, posicionMemoria;
	
	

	
	
	public Procesos() {
		
	}
	
	/*
	 * Cosntructor principal de carga de procesos al sistema
	 * 
	 * */
	
	public Procesos(int iD, int tiempoLlegada, int prioridad, int tiempoCpu,int tiempoCpuRestante, int memoriaRequerida, int impresaras, int escaner,
			int modem, int cD) {
		super();
		
		this.iD= iD;
		this.tiempoLlegada = tiempoLlegada;
		this.prioridad = prioridad;
		this.tiempoCpu = tiempoCpu;
		this.tiempoCpuRestante=tiempoCpuRestante;
		this.memoriaRequerida = memoriaRequerida;
		this.impresaras = impresaras;
		this.escaner = escaner;
		this.modem = modem;
		this.cD = cD;
	}

/*
 * 
 * creacion de metodos Getters y Setters para los procesos
 * 
 * */

	public String informacionProceso() {
		
		return null;
	}



	public int getiD() {
		return iD;
	}
	



	public void setiD(int iD) {
		this.iD = iD;
	}



	public int getTiempoLlegada() {
		return tiempoLlegada;
	}



	public void setTiempoLlegada(int tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}



	public int getPrioridad() {
		return prioridad;
	}



	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}



	public int getTiempoCpu() {
		return tiempoCpu;
	}



	public void setTiempoCpu(int tiempoCpu) {
		this.tiempoCpu = tiempoCpu;
	}



	public int getMemoriaRequerida() {
		return memoriaRequerida;
	}



	public void setMemoriaRequerida(int memoriaRequerida) {
		this.memoriaRequerida = memoriaRequerida;
	}



	public int getImpresaras() {
		return impresaras;
	}



	public void setImpresaras(int impresaras) {
		this.impresaras = impresaras;
	}



	public int getEscaner() {
		return escaner;
	}



	public void setEscaner(int escaner) {
		this.escaner = escaner;
	}



	public int getModem() {
		return modem;
	}



	public void setModem(int modem) {
		this.modem = modem;
	}



	public int getcD() {
		return cD;
	}



	public void setcD(int cD) {
		this.cD = cD;
	}



	public String getEstatus() {
		return estatus;
	}



	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}



	public int getPrioridadActual() {
		return prioridadActual;
	}



	public void setPrioridadActual(int prioridadActual) {
		this.prioridadActual = prioridadActual;
	}



	public int getTiempoCpuRestante() {
		return tiempoCpuRestante;
	}



	public void setTiempoCpuRestante(int tiempoCpuRestante) {
		this.tiempoCpuRestante = tiempoCpuRestante;
	}



	public int getImpresorasAsig() {
		return impresorasAsig;
	}



	public void setImpresorasAsig(int impresorasAsig) {
		this.impresorasAsig = impresorasAsig;
	}



	public int getModemsAsig() {
		return modemsAsig;
	}



	public void setModemsAsig(int modemsAsig) {
		this.modemsAsig = modemsAsig;
	}



	public int getEscanerAsig() {
		return escanerAsig;
	}



	public void setEscanerAsig(int escanerAsig) {
		this.escanerAsig = escanerAsig;
	}



	public int getCdAsig() {
		return cdAsig;
	}



	public void setCdAsig(int cdAsig) {
		this.cdAsig = cdAsig;
	}



	public int getPosicionMemoria() {
		return posicionMemoria;
	}



	public void setPosicionMemoria(int posicionMemoria) {
		this.posicionMemoria = posicionMemoria;
	}

/*
 * Uso del metodo toStrin para visalizar en consola
 * 
 * */

	@Override
	public String toString() {
		return "\n\nProcesos "
				+"\nNumero de procesos : "+ iD
				+ "\nTiempoLlegada : " + tiempoLlegada 
				+ "\nPrioridad : " + prioridad
				+ "\nPrioridad Actual : " + getPrioridadActual() 
				+ "\nTiempo CPU : " + tiempoCpu
				+"\nTiempo CPU Restante: "+getTiempoCpuRestante()
				+ "\nMemoria Requerida : " + memoriaRequerida 
				+ "\nImpresoras solicitadas : " + impresaras 
				+ "\nEscaner solicito : " + escaner
				+ "\nModem solicitado : " + modem 
				+ "\nCD's solicitados : " + cD ;
	}
	
	/*
	 * el metodo verID retorna la identifiacion del proceso 
	 * ademas del tiempo que lleva el CPU activo
	 * 
	 * */
	
	public String verID(int contador) {
		return 
				"\n        ID Proceso: "+getiD()
		+"\n\n   Tiempo activo: "+contador+" sgds";
	}
	
	
	
	
	
	
	

}

	