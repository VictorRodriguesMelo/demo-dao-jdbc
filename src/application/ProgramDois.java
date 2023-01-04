package application;

import Service.ServiceDepartment;

public class ProgramDois {

	public static void main(String[] args) {		
		
		ServiceDepartment.inserirDados();		
		
		ServiceDepartment.buscaTodos();
			
		ServiceDepartment.deletaPorId();	
		
	}

}
