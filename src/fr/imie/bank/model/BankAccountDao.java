package fr.imie.bank.model;

import java.util.List;

import application.DALException;

public interface BankAccountDao {
	
		public List<BankAccount> findAll()  throws DALException;

		public BankAccount findById(int id)  throws DALException;

		public void save(BankAccount person)  throws DALException;

		public void saveAll(List<BankAccount> people);
		
	
}
