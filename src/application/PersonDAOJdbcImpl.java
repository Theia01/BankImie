package  application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class PersonDAOJdbcImpl implements PersonDAO{
		private static final String SQL_SELECT="select * from contact ORDER BY lastname";
		private static final String SQL_SELECT_BY_NAME="SELECT * FROM contact WHERE lastname LIKE ? OR firstname LIKE ?;";
		private static final String SQL_SELECT_BY_ID = "SELECT * FROM contact WHERE id=?";
		private static final String SQL_DELETE="delete from contact where id=?";
		private static final String SQL_INSERT="INSERT INTO contact(firstname,lastname,email,birthdate) VALUES (?,?,?,?);";
		private static final String SQL_UPDATE="UPDATE contact SET firstname=?, lastname=?,email=?,birthdate=? WHERE id=?;";
		//private static final String SQL_DELETE_BY_ID="delete from produit where id=?";
		//private static final String SQL_INSERT="INSERT INTO `produit`(`refProd`,`libelle`,`marque`,`prixUnitaire`,`qteStock`,`dateLimiteConso`,`poids`,`parfum`,`temperatureConservation`,`couleur`,`typeMine`,`typeCP`,`type`) VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		@Override
		public List<Person> selectAll() throws DALException {
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
					Date d = rs.getDate(5);
					LocalDate date = Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

					el= new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), date);
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
		public List<Person> selectbyname(String request) throws DALException {
			List<Person> rep =new ArrayList<>();
			Person el=null;
			ResultSet rs = null;

			PreparedStatement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.prepareStatement(SQL_SELECT_BY_NAME,Statement.RETURN_GENERATED_KEYS);
				request = "%" + request + "%";
				stmt.setString(1, request);
				stmt.setString(2, request);
				rs = stmt.executeQuery();
				while (rs.next()){
					Date d = rs.getDate(5);
					LocalDate date = Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

					el= new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), date);
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
		}
			return rep;
			
		}
		
		
		@Override
		public Person selectbyid(int id) throws DALException {
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
					Date d = rs.getDate(5);
					LocalDate date = Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

					p= new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), date);
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
		public void delete(int id) throws DALException  {
			ResultSet rs = null;
			PreparedStatement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.prepareStatement(SQL_DELETE,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
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
			
		}
		
		
		@Override
		public void createContact(String fname, String lname, String email, LocalDate date) throws DALException  {
			ResultSet rs = null;
			PreparedStatement stmt=null;
			Connection connexion =null;
			Date d = java.sql.Date.valueOf(date);

			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setString(3, email);
				stmt.setDate(4, (java.sql.Date) d);
				rs = stmt.executeQuery();
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
			
		}
		
		@Override
		public void updateContact(int id, String fname, String lname, String email, LocalDate date) throws DALException  {
			ResultSet rs = null;
			PreparedStatement stmt=null;
			Connection connexion =null;
			Date d = java.sql.Date.valueOf(date);

			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.prepareStatement(SQL_UPDATE,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setString(3, email);
				stmt.setDate(4, (java.sql.Date) d);
				stmt.setInt(5, id);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DALException("Update failed", e);
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
}
