package fr.imie.bank;

import java.math.BigDecimal;
import java.text.ParseException;

import application.DALException;
import fr.imie.bank.model.BankAccount;
import fr.imie.bank.model.BankAccountDao;
import fr.imie.bank.model.BankAccountDaoCsvImpl;
import fr.imie.bank.model.Person;
import fr.imie.bank.model.PersonDao;
import fr.imie.bank.model.PersonDaoCsvImpl;

public class MainConsole {

	public static void main(String[] args) {
		BigDecimal s = new BigDecimal(122.30);
		Person t = null;
		try {
			t = new Person("Jean-Michel","Legros","jml@gmail.com", DateUtils.toDate("11/02/1998") );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PersonDao pers = new PersonDaoCsvImpl();
		try {
			pers.save(t);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BankAccount b = new BankAccount(1514651654, s,t);
		
		BankAccountDao bdao = new BankAccountDaoCsvImpl();
		
		try {
			bdao.save(b);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
