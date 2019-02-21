package application;



import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class Test {

	public static void main(String[] args) throws DALException, SQLException {

		PersonDAO persDAO = new PersonDAOJdbcImpl();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDate d = LocalDate.parse("24/05/1999", formatter);
		System.out.print(persDAO.selectbyid(5).getFirstname());

	}

}
