package Service;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ServiceDepartment {
		
	static DepartmentDao departmentDao = DaoFactory.createDepartamentDao(); // Posso?
		
	public static void inserirDados() {		
	// Inserindo dados
	Department newDepartment = new Department(22, "Computers");
	departmentDao.insert(newDepartment);
	System.out.println("Inserted! New id : " + newDepartment.getId());
	}
	
	
	public static void buscaTodos() {
	// Busca todos		
	List<Department> list = departmentDao.findAll();		
		for (Department objt : list) {
			System.out.println(objt);			
		}
}
	
	public static void deletaPorId() {
	//Deleta por ID			
	Scanner sc = new Scanner (System.in);
	System.out.println("Enter in for delete test:  ");
	int id = sc.nextInt();		
	departmentDao.deleteById(id);
	System.out.println("Delete completed");	
	sc.close();
		}			
	}

