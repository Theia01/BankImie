package fr.imie.bank.model;

import java.util.List;

import application.DALException;

public interface PersonDao<quiTuVeux> {

	public List<quiTuVeux> findAll() throws DALException ;

	public quiTuVeux findById(int id) throws DALException;

	public void save(quiTuVeux person)  throws DALException;

	public void saveAll(List<quiTuVeux> people);

	public List<quiTuVeux> findByName() throws DALException;

	public void deleteAll() throws DALException;
	
	public void deleteById(int id) throws DALException;

	void update(quiTuVeux person) throws DALException;
	
	public quiTuVeux lastId() throws DALException;

}