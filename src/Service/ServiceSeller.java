package Service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class ServiceSeller {
	
	
	private SellerDao sellerDao = DaoFactory.createSellerDao(); 
	
	
	public Seller buscarSellerPorID(Integer id) {		
		return sellerDao.findById(id);
	}
	
	public List<Seller> buscarSellerPorDepartamento(Department dep) {
		System.out.println("Buscar Vendedor");
		System.out.println(sellerDao.findBydepartment(dep));
		return null;
	}	
	
	public List<Seller> buscaTodos() {		
		return sellerDao.findAll();
	}
	
	public  void insererComNovoId(Seller newSeller) { 		
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id : " + newSeller.getId());
		
	}
	public  void substituirNomePorId(Seller seller) {		
		sellerDao.update(seller);
		System.out.println("Update completed");
	}
	
	public  void deletaPorId(int id) {			
		sellerDao.deleteById(id);
		System.out.println("Delete completed");		
		
	}
}
