package fr.imie.bank;

import application.DALException;
import fr.imie.bank.model.BankAccount;
import fr.imie.bank.model.BankAccountDao;
import fr.imie.bank.model.BankAccountDaoCsvImpl;
import fr.imie.bank.model.Person;
import fr.imie.bank.model.PersonDao;
import fr.imie.bank.model.PersonDaoCsvImpl;

public class MainConsole {

	public static void main(String[] args) {
		
		Person t = new Person("Jean-Michel","Legros","jml@gmail.com", DateUtils.toDate("11/02/1998") );
		
		PersonDao pers = new PersonDaoCsvImpl();
		
		try {
			pers.save(t);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BankAccount b = new BankAccount(1514651654);
		
		BankAccountDao bdao = new BankAccountDaoCsvImpl();
		
		try {
			bdao.save(b);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
