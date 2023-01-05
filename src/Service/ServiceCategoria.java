package Service;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.CategoriaDao;
import model.entities.Categoria;

public class ServiceCategoria {
		
	static CategoriaDao categoriaDao = DaoFactory.createDepartamentDao(); 
		
	public static void inserirDados() {		
	Categoria newDepartment = new Categoria(3, "mercado");
	categoriaDao.insert(newDepartment);
	System.out.println("Inserted! New id : " + newDepartment.getId());
	}
		
	public static void buscaTodos() {		
	List<Categoria> list = categoriaDao.findAll();		
		for (Categoria objt : list) {
			System.out.println(objt.toString());			
		}
	}
	
	public static void deletaPorId() {			
	Scanner sc = new Scanner (System.in);
	System.out.println("Enter in for delete test:  ");
	int id = sc.nextInt();		
	categoriaDao.deleteById(id);
	System.out.println("Delete completed");	
	sc.close();
		}			
	}

