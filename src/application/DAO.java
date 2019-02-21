package application;
import java.time.LocalDate;
import java.util.List;


public interface DAO<T> {
	
	/*
	public T selectById(int id) throws DALException;
	
	public List<T> selectAll() throws DALException;
	
	public void update(T data) throws DALException;
	
	public void insert(T data) throws DALException;
	
	public void delete(int id) throws DALException;
*/
	public List<T> selectAll() throws DALException;

	public List<Person> selectbyname(String request) throws DALException;

	void delete(int id) throws DALException;

	void createContact(String fname, String lname, String email, LocalDate date) throws DALException;


	void updateContact(int id, String fname, String lname, String email, LocalDate date) throws DALException;

	Person selectbyid(int id) throws DALException;
	
}

