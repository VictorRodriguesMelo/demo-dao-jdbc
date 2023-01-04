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
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;

	public  DepartmentDaoJDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try { 
			st = conn.prepareStatement(				
					"INSERT INTO department "
					+ "(Id, Name) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);			
			
			st.setInt(1, obj.getId());		
			st.setString(2, obj.getName());	
			
			
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
		public void update(Department obj) {
			PreparedStatement st = null;
			try { 
				st = conn.prepareStatement(				
						"UPDATE department "
						+ "SET Id = ?, Name = ? "
						+ "WHERE Id = ?");				
			
				st.setString(1, obj.getName());
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
			st = conn.prepareStatement("DELETE FROM department\r\n WHERE Id = ?");
			
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
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st =conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instanciadoDepartamento(rs);			
				
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
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;	
		List <Department> list = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * from Department ");				

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
		//return null;
	}		

	private Department instanciadoDepartamento(ResultSet rs) throws SQLException {
		 Department dep = new  Department();
			dep.setId(rs.getInt("id"));
			dep.setName(rs.getString("name"));
		return dep;
	}

}
