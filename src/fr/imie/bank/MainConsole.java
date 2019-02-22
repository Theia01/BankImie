package fr.imie.bank;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import application.DALException;
import fr.imie.bank.model.BankAccount;
import fr.imie.bank.model.BankAccountDao;
import fr.imie.bank.model.BankAccountDaoCsvImpl;
import fr.imie.bank.model.Person;
import fr.imie.bank.model.PersonDao;
import fr.imie.bank.model.PersonDaoCsvImpl;
import fr.imie.bank.model.PersonInterfaceGraphiqueDAOCsvlmpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainConsole {


		
			public static String continu;
			public static String ch;
			
			public static void saisie() {
				PersonDaoCsvImpl fonctionSql = new PersonDaoCsvImpl();
				List<Person> r = null;
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
				try {
					fonctionSql.save(p);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continu = scan.next();

			}
			
			public static void tolist() {
				PersonDaoCsvImpl fonctionSql = new PersonDaoCsvImpl();
				List<Person> r = null;
				try {
					r = fonctionSql.findAll();
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("");
				for(int i =0;i<r.size();i++) {
					r.toString();
				}
			}
			
			public static void delete() {

				PersonDaoCsvImpl fonctionSql = new PersonDaoCsvImpl();
				List<Person> r = null;
				
				Scanner scan = new Scanner(System.in);
				System.out.println("Entrez le nom de la personne à supprimer");
				String lname = scan.next();
				try {
					r = fonctionSql.findByName(lname);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0; i<r.size(); i++) {
						System.out.println(r.get(i).toString());
						System.out.println("Souhaitez vous supprimer cette personne ? oui / non");
						String choice = scan.next();
						if(choice.matches("oui")==true) {
							try {
								fonctionSql.deleteById(r.get(i).getId());
							} catch (DALException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
						}
				}
			}
			public static void modifier() {
				PersonDaoCsvImpl fonctionSql = new PersonDaoCsvImpl();
				List<Person> r = null;
				Scanner scan = new Scanner(System.in);
				System.out.println("Entrez le nom de la personne à modifier");
				String lname = scan.nextLine();
				try {
					r = fonctionSql.findByName(lname);
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0; i<r.size(); i++) {
						System.out.println(r.get(i).toString());
						System.out.println("Souhaitez vous modifier cette personne ? oui / non");
						String choice = scan.nextLine();
						if(choice.equalsIgnoreCase("oui")) {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							System.out.println("Saisissez un nouveau nom");
							String lastname = scan.next();
							System.out.println("Saisissez un nouveau prénom");
							String firstname = scan.next();
							System.out.println("Saisissez un nouvel email");
							String email = scan.next();
							System.out.println("Saisissez une nouvelle date de naissance JJ/MM/YYYY");
							String ddn = scan.next();
							while(ddn.matches("\\d{2}/\\d{2}/\\d{4}") == false) {
								System.out.println("Saisissez une date de naissance JJ/MM/YYYY");
								ddn = scan.next();
							}
							LocalDate d = DateUtils.toDate(ddn);
							System.out.println("");
						Person p = new Person(r.get(i).getId() , firstname, lastname, email, d);
						try {
							fonctionSql.update(p);
						} catch (DALException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						}
				
				}
			}
			
			public static void step() {
				System.out.println("Choisissez une option: \r\n 1) Créer un contact \r\n 2) Modifier un contact \r\n 3) Supprimer un contact \r\n 4) Lister les contacts \r\n Entrez le numéro correspondant à l'option voulue");
				Scanner scan = new Scanner(System.in);
				
				
				String choice0 = scan.nextLine();
				if(choice0.matches("\\d+")) {
					int choice = Integer.parseInt(choice0);
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
					} else {
						System.out.println("Ce numéro ne correspond à aucune option");
					}
				} else {
					System.out.println("Ce n'est pas un nombre");
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
