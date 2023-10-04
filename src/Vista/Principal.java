package Vista;

import Controlador.Administrador;

public class Principal {
	
	  

	public static void main(String[] args) {
	
		Administrador admin=new Administrador();//Agregaccion, Creacion de la clase Administrador
	
		admin.cargarArchivoTxt();//llamada al metodo de la clase administrador
		admin.soltarProceso();//llamada al metodo de la clase administrador
		admin.confirmador();//llamada al metodo de la clase administrador
		//Estos es un comentario

	   
	}

}
