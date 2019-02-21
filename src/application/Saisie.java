package application;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.Repertoire;

import java.time.format.DateTimeFormatter;

public class Saisie {
	public static PersonDAO persDAO = new PersonDAOJdbcImpl();
	public static String continu;
	public static String ch;
	public static List<Person> rep;
	
	public static void saisie() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		Scanner scan = new Scanner(System.in);
		System.out.println("Saisissez un nom");
		String lname = scan.nextLine();
		System.out.println("Saisissez un prénom");
		String fname = scan.nextLine();
		System.out.println("Saisissez un email");
		String email = scan.nextLine();
		System.out.println("Saisissez une date de naissance");
		String ddn = scan.nextLine();
		while(ddn.matches("\\d{2}/\\d{2}/\\d{4}") == false) {
			System.out.println("Saisissez une date de naissance");
			ddn = scan.nextLine();
		}
		LocalDate d = LocalDate.parse(ddn, formatter);
		System.out.println(d);
		System.out.println("");
		try {
			persDAO.createContact(fname, lname, email, d);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Si vous souhaitez ajouter un autre contact, écrivez \"oui\"");
		continu = scan.next();

	}
	
	public static void tolist() {
		System.out.println("");
		try {
			persDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de la personne à supprimer");
		String lname = scan.next();
		try {
			rep = persDAO.selectbyname(lname);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<rep.size(); i++) {
				System.out.println(rep.get(i).toString());
				System.out.println("Souhaitez vous supprimer cette personne ? oui / non");
				String choice = scan.next();
				if(choice.matches("oui")==true) {
					try {
						persDAO.delete(rep.get(i).getId());
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
		}
	} 
	
	public static void modifier() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de la personne à modifier");
		String lname = scan.next();
		try {
			rep = persDAO.selectbyname(lname);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=0; i< rep.size() ; i++) {
				System.out.println(rep.get(i).toString());
				System.out.println("Souhaitez vous modifier cette personne ? oui / non");
				String choice = scan.next();
				if(choice.equalsIgnoreCase("oui")) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
					System.out.println("Saisissez un nouveau nom");
					String lastname = scan.next();
					System.out.println("Saisissez un nouveau prénom");
					String firstname = scan.next();
					System.out.println("Saisissez un nouvel email");
					String email = scan.next();
					System.out.println("Saisissez une nouvelle date de naissance");
					String ddn = scan.next();
					while(ddn.matches("\\d{2}/\\d{2}/\\d{4}") == false) {
						System.out.println("Saisissez une date de naissance");
						ddn = scan.nextLine();
					}
					LocalDate d = LocalDate.parse(ddn, formatter);
					System.out.println("");
					
					try {
						persDAO.updateContact(rep.get(i).getId(), firstname, lastname, email, d);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
	}
	
	public static void write() throws DALException {
		FileWriter fileWriter;
		List<Person> plist = persDAO.selectAll();
		try {
			fileWriter = new FileWriter("MonFichier.csv",false);
			fileWriter.append("First Name,Last Name,Email,BirthDate");
			for(int i=0; i<plist.size();i++) {
				fileWriter.append("\r\n");
				fileWriter.append(plist.get(i).getFirstname() + "," + plist.get(i).getLastname() + "," + plist.get(i).getEmail() + "," + plist.get(i).getBirthday());
			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void step() {
		System.out.println("Choisissez une option: \r\n 1) Créer un contact \r\n 2) Modifier un contact \r\n 3) Supprimer un contact \r\n 4) Lister les contacts \r\n 5) Quitter le programme \r\n Entrez le numéro correspondant à l'option voulue");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if(choice==1) {
			do {
				saisie();
			} while (continu.matches("oui") == true );
		} else if(choice==2) {
			modifier();
		} else if (choice ==3) {
			delete();
		} else if (choice ==4) {
			tolist();
		} else if(choice ==5){
			System.exit(0);
		} else {
			System.out.println("Ce numéro ne correspond à aucune option");
		}
		
		System.out.println("Souhaitez vous effectuer une autre action ? oui/non");
		ch = scan.next();
	}
	
	public static void main(String[] args) {
		
		try {
			write();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		do {
		step();
		} while (ch.matches("oui")==true);
		System.out.println("Au revoir !");
	}

}
