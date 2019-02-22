package fr.imie.bank.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import application.DALException;
import application.JdbcTools;
import fr.imie.bank.DateUtils;

public class PersonInterfaceGraphiqueDAOCsvlmpl implements PersonDao<PersonInterfaceGraphique> {
	private static final String SQL_SELECT="select * from contact ORDER BY lastname";
	private static final String SQL_SELECT_BY_NAME="SELECT * FROM contact WHERE lastname LIKE ? OR firstname LIKE ?;";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM contact WHERE id=?";
	private static final String SQL_DELETE_BY_ID="delete from contact where id=?";
	private static final String SQL_DELETE_ALL="delete from contact";
	private static final String SQL_INSERT="INSERT INTO contact(firstname,lastname,email,birthdate) VALUES (?,?,?,?);";
	private static final String SQL_UPDATE="UPDATE contact SET firstname=?, lastname=?,email=?,birthdate=? WHERE id=?;";
	private static final String SQL_LAST_ID="SELECT * FROM `contact`  ORDER BY id DESC LIMIT 1;";
	
	@Override
    public PersonInterfaceGraphique lastId() throws DALException {
    ResultSet rs = null;
    PersonInterfaceGraphique p = new PersonInterfaceGraphique();
    PreparedStatement stmt=null;
    Connection connexion =null;
    try {
        connexion = JdbcTools.getConnection();
        stmt = connexion.prepareStatement(SQL_LAST_ID,Statement.RETURN_GENERATED_KEYS);
        rs = stmt.executeQuery();
        while (rs.next()){

            p= new PersonInterfaceGraphique(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate());


        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
            throw new DALException("select last failed", e);
        
    }
    finally {
        try {
            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if(connexion!=null){
                connexion.close();
            }
        } catch (SQLException e) {
            throw new DALException("Close failed", e);
        }
}
    return p;
        
    }
	
	
	
	
	@Override
	public List<PersonInterfaceGraphique> findAll() throws DALException {
			List<PersonInterfaceGraphique> rep = new ArrayList<>();
			PersonInterfaceGraphique el=null;
			ResultSet rs = null;
			Statement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.createStatement();
				rs = stmt.executeQuery(SQL_SELECT);
				while (rs.next()){
					el= new PersonInterfaceGraphique(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() );
					rep.add(el);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DALException("selectAll failed", e);
			}
			finally {
				try {
					if (rs != null){
						rs.close();
					}
					if (stmt != null){
						stmt.close();
					}
					if(connexion!=null){
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DALException("Close failed", e);
				}
				for(int i=0;i<rep.size();i++) {
					System.out.println(rep.get(i).toString());
				}
		}
			return rep;
			
		
		
	}
	
	@Override
	public List<PersonInterfaceGraphique> findByName(String name) throws DALException {
			List<PersonInterfaceGraphique> rep =new ArrayList<>();
			PersonInterfaceGraphique el=null;
			ResultSet rs = null;
			Statement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.createStatement();
				rs = stmt.executeQuery(SQL_SELECT_BY_NAME);
				while (rs.next()){
					el= new PersonInterfaceGraphique(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() );
					rep.add(el);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DALException("select by name failed", e);
			}
			finally {
				try {
					if (rs != null){
						rs.close();
					}
					if (stmt != null){
						stmt.close();
					}
					if(connexion!=null){
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DALException("Close failed", e);
				}
				for(int i=0;i<rep.size();i++) {
					System.out.println(rep.get(i).toString());
				}
		}
			return rep;	
	}
	

	@Override
	public PersonInterfaceGraphique findById(int id) throws DALException {
		PersonInterfaceGraphique p=null;
		ResultSet rs = null;

		PreparedStatement stmt=null;
		Connection connexion =null;
		try {
		    connexion = JdbcTools.getConnection();
			stmt = connexion.prepareStatement(SQL_SELECT_BY_ID,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()){

				p= new PersonInterfaceGraphique(id,rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate());


			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				throw new DALException("select by id failed", e);
			
		}
		finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				if(connexion!=null){
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DALException("Close failed", e);
			}
	}
		return p;
		
	}

	@Override
	public void save(PersonInterfaceGraphique person) throws DALException  {
		ResultSet rs = null;
		PreparedStatement stmt=null;
		Connection connexion =null;
		try {
		    connexion = JdbcTools.getConnection();
			stmt = connexion.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, person.getFirstname());
			stmt.setString(2, person.getLastname());
			stmt.setString(3, person.getEmail());
			stmt.setDate(4,  DateUtils.convertUtilToSql(person.getBirthday()));
			
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("save failed", e);
		}
		finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				if(connexion!=null){
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DALException("Close failed", e);
			}
	}
		
	}
	
	@Override
	public void saveAll(List<PersonInterfaceGraphique> people) {
		for(int i =0; i<people.size();i++) {
			try {
				save(people.get(i));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteById(int id) throws DALException{
		ResultSet rs = null;
		PreparedStatement stmt=null;
		Connection connexion =null;
		try {
		    connexion = JdbcTools.getConnection();
			stmt = connexion.prepareStatement(SQL_DELETE_BY_ID,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("delete by id failed", e);
		}
		finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				if(connexion!=null){
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DALException("Close failed", e);
			}
	}
	}

	@Override
	public void deleteAll() throws DALException {
		ResultSet rs = null;
		PreparedStatement stmt=null;
		Connection connexion =null;
		try {
		    connexion = JdbcTools.getConnection();
			stmt = connexion.prepareStatement(SQL_DELETE_ALL,Statement.RETURN_GENERATED_KEYS);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("delete all failed", e);
		}
		finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				if(connexion!=null){
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DALException("Close failed", e);
			}
	}
	}


	@Override
	public void update(PersonInterfaceGraphique person) throws DALException  {
		ResultSet rs = null;
		PreparedStatement stmt=null;
		Connection connexion =null;
		try {
		    connexion = JdbcTools.getConnection();
			stmt = connexion.prepareStatement(SQL_UPDATE,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, person.getFirstname());
			stmt.setString(2, person.getLastname());
			stmt.setString(3, person.getEmail());
			stmt.setDate(4,  DateUtils.convertUtilToSql(person.getBirthday()));
			stmt.setInt(5, person.getId().get());
			
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("update failed", e);
		}
		finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				if(connexion!=null){
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DALException("Close failed", e);
			}
	}
		
	}




	@Override
	public List<PersonInterfaceGraphique> findByName() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO utiliser FileWriter et BufferedReader/FileReader

}
