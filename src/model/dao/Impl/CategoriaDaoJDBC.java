package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CategoriaDao;
import model.entities.Categoria;

public class CategoriaDaoJDBC implements CategoriaDao{
	
	private Connection conn;

	public  CategoriaDaoJDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Categoria obj) {
		PreparedStatement st = null;
		try { 
			st = conn.prepareStatement(				
					"INSERT INTO categoria "
					+ "(id, descricao) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);			
			
			st.setInt(1, obj.getId());		
			st.setString(2, obj.getDescricao());	
			
			
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
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());				
		}
			finally {
				DB.closeStatement(st);
		}		
	}
	@Override	
		public void update(Categoria obj) {
			PreparedStatement st = null;
			try { 
				st = conn.prepareStatement(				
						"UPDATE categoria "
						+ "SET id = ?, descricao = ? "
						+ "WHERE id = ?");				
			
				st.setString(1, obj.getDescricao());
				st.setInt(2, obj.getId());	
				
				 st.executeUpdate();
			}
				catch(SQLException e) {
					throw new DbException(e.getMessage());				
			}
				finally {
					DB.closeStatement(st);
			}		
		}
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM categoria WHERE id = ?");
			
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
	@Override
	public Categoria findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st =conn.prepareStatement(
					"SELECT despesa.*,categoria.descricao as DepName "
					+ "FROM despesa INNER JOIN categoria "
					+ "ON despesa.id_categoria = categoria.id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Categoria dep = instanciadoDepartamento(rs);				
				return dep;			
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
	@Override
	public List<Categoria> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;	
		List <Categoria> list = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * from categoria ");	
			rs = st.executeQuery();	
			while (rs.next()) {				
				 list.add(instanciadoDepartamento(rs));					
			}
			return list;
		}
		catch(SQLException e ) {
			throw new DbException(e.getMessage());			
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}		

	private Categoria instanciadoDepartamento(ResultSet rs) throws SQLException {
		 	Categoria dep = new  Categoria();
			dep.setId(rs.getInt("id"));	
			dep.setDescricao(rs.getString("descricao"));					
		return dep;
	}
}
