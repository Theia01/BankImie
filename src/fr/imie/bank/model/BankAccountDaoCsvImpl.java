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

public class BankAccountDaoCsvImpl implements BankAccountDao {
	private static final String SQL_SELECT="select * from account ORDER BY lastname";
	// private static final String SQL_SELECT_BY_NAME="SELECT * FROM contact WHERE lastname LIKE ? OR firstname LIKE ?;";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM account WHERE id=?";
	// private static final String SQL_DELETE="delete from contact where id=?";
	private static final String SQL_INSERT="INSERT INTO account(id_owner,number, balance) VALUES (?,?,?);";
	//private static final String SQL_UPDATE="UPDATE contact SET firstname=?, lastname=?,email=?,birthdate=? WHERE id=?;";
	
		@Override
		public List<BankAccount> findAll() throws DALException {
			List<BankAccount> acc =new ArrayList<>();
			BankAccount el=null;
			ResultSet rs = null;
			Statement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.createStatement();
				rs = stmt.executeQuery(SQL_SELECT);
				while (rs.next()){
					PersonDao pers = new PersonDaoCsvImpl();
					Person p = pers.findById(rs.getInt(2));
					el= new BankAccount(rs.getInt(2),rs.getBigDecimal(3),p);
					acc.add(el);
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
			return acc;
		}

		@Override
		public BankAccount findById(int id)throws DALException {
			BankAccount b =null;
			ResultSet rs = null;

			PreparedStatement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.prepareStatement(SQL_SELECT_BY_ID,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				while (rs.next()){
					PersonDao pers = new PersonDaoCsvImpl();
					Person p = pers.findById(rs.getInt(2));
					b = new BankAccount(rs.getInt(2),rs.getBigDecimal(3),p);
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
			return b;
			
		}

		@Override
		public void save(BankAccount acc)  throws DALException  {
			ResultSet rs = null;
			PreparedStatement stmt=null;
			Connection connexion =null;
			try {
			    connexion = JdbcTools.getConnection();
				stmt = connexion.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, acc.getOwner().getId());
				stmt.setInt(2, acc.getNumber());
				stmt.setString(3, acc.getBalance().toString());
				
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
		public void saveAll(List<BankAccount> list_acc) {
			for(int i =0; i<list_acc.size();i++) {
				try {
					save(list_acc.get(i));
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}

		// TODO utiliser FileWriter et BufferedReader/FileReader

	}

