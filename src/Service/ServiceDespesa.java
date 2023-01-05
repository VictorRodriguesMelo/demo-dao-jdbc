package Service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DespesaDao;
import model.entities.Categoria;
import model.entities.Despesa;

public class ServiceDespesa {	
	
	private DespesaDao despesaDao = DaoFactory.createSellerDao(); 	
	
	public Despesa buscarSellerPorID(Integer id) {
		System.out.println(despesaDao.findById(id));
		return  despesaDao.findById(id);
	} 
	
	public List<Despesa> buscarSellerPorDepartamento(Categoria dep) {
		return despesaDao.findBydepartment(dep);
	}	
	
	public List<Despesa> buscaTodos() {		
		return despesaDao.findAll();
	}
	
	public void insererComNovoId(Despesa newSeller) { 		
		despesaDao.insert(newSeller);
		System.out.println("Inserted! New id : " + newSeller.getId());		
	}
	
	public void substituirNomePorId(Despesa despesa) {		
		despesaDao.update(despesa);
		System.out.println("Update completed");
	}
	
	public  void deletaPorId(int id) {			
		despesaDao.deleteById(id);
		System.out.println("Delete completed");			
	}
}
