package model.dao;

import db.DB;
import model.dao.Impl.CategoriaDaoJDBC;
import model.dao.Impl.DespesaDaoJDBC;

public class DaoFactory { 
	
	public static DespesaDao createSellerDao() {
		return new DespesaDaoJDBC(DB.getConnection());
	}	
	public static CategoriaDao createDepartamentDao() {
		return new CategoriaDaoJDBC(DB.getConnection());
	}


}
