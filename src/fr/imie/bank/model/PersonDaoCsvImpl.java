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

public class PersonDaoCsvImpl implements PersonDao<Person> {
	private static final String SQL_SELECT="select * from contact ORDER BY lastname";
	private static final String SQL_SELECT_BY_NAME="SELECT * FROM contact WHERE lastname LIKE ? OR firstname LIKE ?;";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM contact WHERE id=?";
	// private static final String SQL_DELETE="delete from contact where id=?";
	private static final String SQL_INSERT="INSERT INTO contact(firstname,lastname,email,birthdate) VALUES (?,?,?,?);";
	//private static final String SQL_UPDATE="UPDATE contact SET firstname=?, lastname=?,email=?,birthdate=? WHERE id=?;";
	
	
	
	@Override
	public List<Person> findAll() throws DALException {
			List<Person> rep =new ArrayList<>();
			Person el=null;
			ResultSet rs = null;
			Statement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.createStatement();
				rs = stmt.executeQuery(SQL_SELECT);
				while (rs.next()){
					el= new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() );
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
	public List<Person> findByName() throws DALException {
			List<Person> rep =new ArrayList<>();
			Person el=null;
			ResultSet rs = null;
			Statement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.createStatement();
				rs = stmt.executeQuery(SQL_SELECT_BY_NAME);
				while (rs.next()){
					el= new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() );
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
	public Person findById(int id) throws DALException {
		Person p=null;
		ResultSet rs = null;

		PreparedStatement stmt=null;
		Connection connexion =null;
		try {
		    connexion = JdbcTools.getConnection();
			stmt = connexion.prepareStatement(SQL_SELECT_BY_ID,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()){

				p= new Person(id,rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate());


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
	}
		return p;
		
	}

	@Override
	public void save(Person person) throws DALException  {
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
	public void saveAll(List<Person> people) {
		for(int i =0; i<people.size();i++) {
			try {
				save(people.get(i));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	// TODO utiliser FileWriter et BufferedReader/FileReader

}
