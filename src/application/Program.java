package application;

import java.util.Date;
import java.util.List;

import Service.ServiceSeller;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
	
	    //Chamar o Servico
	    ServiceSeller serviceSeller = new ServiceSeller();	
	    //Retorno Seller
	    Seller seller = null;
	    //Retorno lista Seller
	    List<Seller> lista = null;
	    
	    
	    
	    //Teste update banco ok
//		seller = serviceSeller.buscarSellerPorID(2);
//		imprimirSeller(seller);
//		seller.setName("Carlos");
//		serviceSeller.substituirNomePorId(seller);	
//		seller = serviceSeller.buscarSellerPorID(2);
//		imprimirSeller(seller);
		
		lista = serviceSeller.buscaTodos();
		imprimirListaSeller(lista);
        
        
//        Department department = new Department();
//        department.setId(1);
//		lista = serviceSeller.buscarSellersPorIdDepartamento(department);
//		imprimirListaSeller(lista);
//		
//		serviceSeller.deletaPorId(4);
//		
//		serviceSeller.insererComNovoId( 
//				new Seller(null, "Victor", "victor@victor.com", new Date(), 4000.00, department));
	}
	
	private static void imprimirListaSeller(List<Seller> lista) {
		for (Seller seller : lista) {
			System.out.println(seller.getEmail());
			System.out.println(seller.getName());
			System.out.println(seller.getId());
			System.out.println(seller.getBaseSalary());
			System.out.println(seller.getBrithDate());
			System.out.println(seller.getDepartment().getName());
		}
	}
	
	private static void imprimirSeller(Seller seller) {
		System.out.println("-------------------------------------------------------");
		
		System.out.println(seller.getEmail());
		System.out.println(seller.getName());
		System.out.println(seller.getId());
		System.out.println(seller.getBaseSalary());
		System.out.println(seller.getBrithDate());
		System.out.println(seller.getDepartment().getName());
		
		System.out.println("-------------------------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
}
