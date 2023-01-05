package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Service.ServiceDespesa;
import model.entities.Categoria;
import model.entities.Despesa;

public class Program {

	public static void main(String[] args) {	
	    //Chamar o Servico
	    ServiceDespesa serviceDespesa = new ServiceDespesa();	
	    //Retorno Seller
	    Despesa despesa = null;
	    //Retorno lista Seller
	    List<Despesa> lista = new ArrayList<>();        
	    
	    
	    System.out.println("Busca seller por ID");
		despesa = serviceDespesa.buscarSellerPorID(2);		
		imprimirSeller(despesa);
		
		
		System.out.println("Busca todos: ");
		lista = serviceDespesa.buscaTodos();		    
		imprimirListaSeller(lista);		
		
		System.out.println("Adiciona : ");		
	
		serviceDespesa.insererComNovoId(
			new Despesa(null, "Mario", Calendar.getInstance().getTime(), 100D, new Categoria(2, "")));
		System.out.println("deletaPorId : ");		
		serviceDespesa.deletaPorId(3);
		
		System.out.println("buscarSellerPorID : ");
		despesa = serviceDespesa.buscarSellerPorID(2);
	
		System.out.println("Substitui: ");
		despesa.setDescricao("Carlos");
		serviceDespesa.substituirNomePorId(despesa);	
		
		lista = new ArrayList<>();    	
        System.out.println("Buscar seller por departamento: ");
        Categoria department = new Categoria(2, null);
        lista = serviceDespesa.buscarSellerPorDepartamento(department);
	    imprimirListaSeller(lista);	
       
		
		
		
	}
	
	private static void imprimirListaSeller(List<Despesa> lista) {
		for (Despesa despesa : lista) {
//			System.out.println(seller.getDescricao());
//			System.out.println(seller.getId());
//			System.out.println(seller.getValor());
//			System.out.println(seller.getDepartment());	
			imprimirSeller(despesa);
		}
	}
	
	private static void imprimirSeller(Despesa despesa) {
		System.out.println("-------------------------------------------------------");		
		
		System.out.println(despesa.getId());
		System.out.println(despesa.getData());
		System.out.println(despesa.getValor());		
		System.out.println(despesa.getDescricao());			
		System.out.println(despesa.getDepartment().toString());
		
		
		System.out.println("-------------------------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
}
