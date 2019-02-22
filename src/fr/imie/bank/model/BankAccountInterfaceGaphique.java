package fr.imie.bank.model;

import java.math.BigDecimal;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BankAccountInterfaceGaphique {
	private StringProperty number;
	private ObjectProperty<BigDecimal> balance;
	private ObjectProperty<PersonInterfaceGraphique> owner;

	public BankAccountInterfaceGaphique(String number, BigDecimal bal, PersonInterfaceGraphique owner) {
		super();
		this.number = new SimpleStringProperty(number);
		this.owner = new SimpleObjectProperty<PersonInterfaceGraphique>(owner);
		this.balance = new SimpleObjectProperty<BigDecimal>(bal);
	}

	public String getNumber() {
		return number.get();
	}

	public void setNumber(String number) {
		this.number.set(number);
	}

	public StringProperty numberProperty() {
		return number;
	}

	public BigDecimal getBalance() {
		return balance.get();
	}

	public void setBalance(BigDecimal balance) {
		this.balance.set(balance);
	}

	public ObjectProperty<BigDecimal> balanceProperty() {
		return balance;
	}

	public PersonInterfaceGraphique getOwner() {
		return owner.get();
	}

	public void setOwner(PersonInterfaceGraphique owner) {
		this.owner.set(owner);
	}

	public ObjectProperty<PersonInterfaceGraphique> birthdayProperty() {
		return owner;
	}
}
