package fr.imie.bank.model;

import java.util.List;

import application.DALException;

public interface PersonDao {

	public List<Person> findAll() throws DALException ;

	public Person findById(int id) throws DALException;

	public void save(Person person)  throws DALException;

	public void saveAll(List<Person> people);

}