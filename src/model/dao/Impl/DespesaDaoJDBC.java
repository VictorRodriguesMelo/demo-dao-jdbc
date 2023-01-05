package model.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DespesaDao;
import model.entities.Categoria;
import model.entities.Despesa;

public class DespesaDaoJDBC implements DespesaDao {
	private Connection conn;
	
	public  DespesaDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	// inserindo dados 
	@Override
	public void insert(Despesa obj) {
		PreparedStatement st = null;
		try { 
			st = conn.prepareStatement(				
					"INSERT INTO despesa "
					+ "(data, valor, descricao, id_categoria) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);	
			
			st.setDate(1, new Date(obj.getData().getTime()));
			st.setDouble(2, obj.getValor());
			st.setString(3, obj.getDescricao());
			st.setInt(4, obj.getDepartment().getId());		
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected ");
			}				
		}catch(SQLException e) {
				throw new DbException(e.getMessage());				
		}finally {
				DB.closeStatement(st);
		}
	}
	@Override
	public void update(Despesa obj) {
		PreparedStatement st = null;
		try { 
			st = conn.prepareStatement(				
					"UPDATE despesa "
					+ "SET data = ?, valor = ?, descricao = ?, id_categoria = ? "
					+ "WHERE id = ?");	
			
			st.setDate(1, new Date(obj.getData().getTime()));
			st.setDouble(2, obj.getValor());
			st.setString(3, obj.getDescricao());
			st.setInt(4, obj.getDepartment().getId());
			st.setInt(5, obj.getId());			
		
			st.executeUpdate();
			 
		}catch(SQLException e) {
				throw new DbException(e.getMessage());				
		}finally {
			DB.closeStatement(st);				
		}
			
	}
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM despesa WHERE id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());			
		}
		finally {
			DB.closeStatement(st);
		}		
	}	
	
	// buscando por ID
	@Override   
	public Despesa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st =conn.prepareStatement(
					"SELECT despesa.*,categoria.descricao as DepName "
					+ "FROM despesa INNER JOIN categoria "
					+ "ON despesa.id_categoria = categoria.id "
					+ "WHERE despesa.id = ?");			
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Categoria dep = instanciadoDepartamento(rs);				
				Despesa obj = instanciandoVendedor(rs,dep);				
				return obj;			
			}
			return null; 
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());			
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}
	
	// Buscando por departamento
	@Override
	public List<Despesa> findBydepartment(Categoria categoria) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List <Despesa> list = new ArrayList<>();
		Map<Integer, Categoria> map = new HashMap<>();
		
		try {
			st = conn.prepareStatement(
					"SELECT despesa.*,categoria.descricao as DepName "
					+ "FROM despesa INNER JOIN categoria "
					+ "ON despesa.id_categoria = categoria.id "
					+ "WHERE categoria.id = ? "
					+ "ORDER BY DepName");					
					
			st.setInt(1, categoria.getId());
			rs = st.executeQuery();			
			
			while (rs.next()) {		
				Categoria dep = map.get(rs.getInt("id_categoria"));
				if(dep == null) {
					 dep = instanciadoDepartamento(rs);	
					 map.put(rs.getInt("id_categoria"), dep);					
				}						
				Despesa obj = instanciandoVendedor(rs,dep);				
				list.add(obj);		
			}
			return list;
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());			
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}
	
	// Buscando todos vendedores
	@Override
	public List<Despesa> findAll() {		
		PreparedStatement st = null;
		ResultSet rs = null;		
		List <Despesa> list = new ArrayList<>();
		try {
			st = conn.prepareStatement(			
			  " select s.* , d.descricao as DepName  from despesa s "
				+ " inner join categoria d on s.id_categoria = d.id "
				);	

			rs = st.executeQuery();			
			
			while (rs.next()) {		
				Categoria dep = instanciadoDepartamento(rs);						
				Despesa obj = instanciandoVendedor(rs,dep);				
				list.add(obj);		
			}
			return list;
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());			
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}	

	private Despesa instanciandoVendedor(ResultSet rs, Categoria dep) throws SQLException {
		 Despesa obj = new Despesa();		 
		  	obj.setId(rs.getInt("id"));
		  	obj.setDescricao(rs.getString("descricao"));
		  	obj.setData(rs.getDate("data"));
		  	obj.setValor(rs.getDouble("valor"));
		  	obj.setDepartment(dep);

		return obj;
	}	

	private Categoria instanciadoDepartamento(ResultSet rs) throws SQLException {
		 Categoria dep = new  Categoria();
			dep.setId(rs.getInt("id_categoria"));
			dep.setDescricao(rs.getString("DepName"));
			
		return dep;
	}
}
