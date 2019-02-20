package application;

import java.time.LocalDate;
import java.util.Scanner;

import application.Repertoire;

import java.time.format.DateTimeFormatter;

public class Saisie {
	public static String continu;
	public static Repertoire rep = new Repertoire();

	public static String ch;
	
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
		Person p = new Person(lname,fname,email,d);
		System.out.println("Si vous souhaitez ajouter un autre contact, écrivez \"oui\"");
		rep.addContact(p);
		continu = scan.next();

	}
	
	public static void tolist(Repertoire r) {
		System.out.println("");
		r.listContact();
	}
	
	public static void delete(Repertoire r) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de la personne à supprimer");
		String lname = scan.nextLine();
		for(int i=0; i<r.contacts.size(); i++) {
			if(r.contacts.get(i).getLastname().matches(lname)) {
				System.out.println(r.contacts.get(i).toString());
				System.out.println("Souhaitez vous supprimer cette personne ? oui / non");
				String choice = scan.next();
				if(choice.matches("oui")==true) {
					r.deleteContact(i);
				}
			}
		}
	}
	public static void modifier(Repertoire r) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez le nom de la personne à modifier");
		String lname = scan.nextLine();
		for(int i=0; i<r.contacts.size(); i++) {
			if(r.contacts.get(i).getLastname().matches(lname)) {
				System.out.println(r.contacts.get(i).toString());
				System.out.println("Souhaitez vous modifier cette personne ? oui / non");
				String choice = scan.nextLine();
				if(choice.equalsIgnoreCase("oui")) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
					System.out.println("Saisissez un nouveau nom");
					String lastname = scan.nextLine();
					System.out.println("Saisissez un nouveau prénom");
					String firstname = scan.nextLine();
					System.out.println("Saisissez un nouvel email");
					String email = scan.nextLine();
					System.out.println("Saisissez une nouvelle date de naissance");
					String ddn = scan.nextLine();
					while(ddn.matches("\\d{2}/\\d{2}/\\d{4}") == false) {
						System.out.println("Saisissez une date de naissance");
						ddn = scan.nextLine();
					}
					LocalDate d = LocalDate.parse(ddn, formatter);
					System.out.println(d);
					System.out.println("");
					
					r.modContact(i, firstname, lastname, email, d);
				}
			}
		}
	}
	
	public static void step() {
		System.out.println("Choisissez une option: \r\n 1) Créer un contact \r\n 2) Modifier un contact \r\n 3) Supprimer un contact \r\n 4) Lister les contacts \r\n Entrez le numéro correspondant à l'option voulue");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if(choice==1) {
			do {
				saisie();
			} while (continu.matches("oui") == true );
		} else if(choice==2) {
			modifier(rep);
		} else if (choice ==3) {
			delete(rep);
		} else if (choice ==4) {
			tolist(rep);
		} else {
			System.out.println("Ce numéro ne correspond à aucune option");
		}
		
		System.out.println("Souhaitez vous effectuer une autre action ? oui/non");
		ch = scan.next();
	}
	
	public static void main(String[] args) {
		
	
		do {
		step();
		} while (ch.matches("oui")==true);
		System.out.println("Au revoir !");
	}

}
