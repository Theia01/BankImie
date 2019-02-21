package fr.imie.bank.view;

import application.DALException;
import fr.imie.bank.DateUtils;
import fr.imie.bank.model.Person;
import fr.imie.bank.model.PersonDao;
import fr.imie.bank.model.PersonDaoCsvImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonInterface {
	@FXML private TextField TextFieldFirstName;
	@FXML private TextField TextFieldLastName;
	@FXML private TextField TextFielEmail;
	@FXML private DatePicker DatePickerBirthDay;
	@FXML private TableView tablePerson;

	PersonDao fonctionsql = new PersonDaoCsvImpl();
	
	@FXML
	public void Hello(ActionEvent e){
		System.out.print(TextFieldFirstName.getText());
	}
	
	@FXML
	public void VerificationAdd() {
		if(TextFieldFirstName.getText().isEmpty() == false &&
				TextFieldLastName.getText().isEmpty() == false &&
					TextFielEmail.getText().isEmpty() == false &&
						DatePickerBirthDay.getValue() != null){
			System.out.print("work finish");
			
			Person p = new Person(TextFieldFirstName.getText(), TextFieldLastName.getText(), TextFielEmail.getText(), DateUtils.LocaltoDate(DatePickerBirthDay.getValue()));
			try {
				fonctionsql.save(p);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				TextFieldFirstName.setText("");
				TextFieldLastName.setText("");
				TextFielEmail.setText("");
				DatePickerBirthDay.setValue(null);
			}
		}
	}
	
	public void ListPersonneDbb() {
		tablePerson.setColumns();

	}

}
