package fr.imie.bank.view;


import application.DALException;
import fr.imie.bank.DateUtils;
import fr.imie.bank.MainApp;
import fr.imie.bank.model.Person;
import fr.imie.bank.model.PersonDao;
import fr.imie.bank.model.PersonDaoCsvImpl;
import fr.imie.bank.model.PersonInterfaceGraphique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PersonInterface {
	@FXML private TextField TextFieldFirstName;
	@FXML private TextField TextFieldLastName;
	@FXML private TextField TextFielEmail;
	@FXML private DatePicker DatePickerBirthDay;
	
	@FXML private TableView<PersonInterfaceGraphique> tablePerson;
    @FXML private TableColumn<PersonInterfaceGraphique, String> firstNameColumn;
    @FXML private TableColumn<PersonInterfaceGraphique, String> lastNameColumn;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label birthdayLabel;
    
    PersonDao fonctionsql = new PersonDaoCsvImpl();
    
    // Reference to the main application.
    private MainApp mainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonInterface() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        tablePerson.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        tablePerson.setItems(mainApp.getPersonData());
    }
    
    
	
	@FXML
	public void Hello(ActionEvent e){
		System.out.print(TextFieldFirstName.getText());
	}
	
	//Ajout d'un personne à la bdd
	@FXML
	public void VerificationAdd() {
		if(isInputAjouterValid()){
			Person p = new Person(TextFieldFirstName.getText(), TextFieldLastName.getText(), TextFielEmail.getText(), DatePickerBirthDay.getValue());
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
	
	private boolean isInputAjouterValid() {
        String errorMessage = "";

        if (TextFieldFirstName.getText() == null || TextFieldFirstName.getText().length() == 0) {
            errorMessage += "Prenom Invalide!\n";
        }
        if (TextFieldLastName.getText() == null || TextFieldLastName.getText().length() == 0) {
            errorMessage += "Nom Invalide!\n";
        }
        if (TextFielEmail.getText() == null || TextFielEmail.getText().length() == 0) {
            errorMessage += "Email invalide!\n";
        }

        if (DatePickerBirthDay.getValue() == null) {
            errorMessage += "Date de Naissance invalide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Champs Invalide");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
	
	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 *
	 * @param person the person or null
	 */
	public void showPersonDetails(PersonInterfaceGraphique person) {
		if (person != null) {
	        // Fill the labels with info from the person object.
	        firstNameLabel.setText(person.getFirstname());
	        lastNameLabel.setText(person.getLastname());
	        emailLabel.setText(person.getEmail());
	        birthdayLabel.setText(DateUtils.format(person.getBirthday()));
	        System.out.println(person.getId());
	        //birthdayLabel.setText(String.valueOf(person.getBirthday()));
		
		}else {
	        // Person is null, remove all the text.
	        firstNameLabel.setText("");
	        lastNameLabel.setText("");
	        emailLabel.setText("");
	        birthdayLabel.setText("");
	    }
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
	    int selectedIndex = tablePerson.getSelectionModel().getSelectedIndex();
	    //si l'utilisateur a bien sélectionner une personne
	    if (selectedIndex >= 0) {
	    	tablePerson.getItems().remove(selectedIndex);
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("Selectionne une personne");
	        alert.setContentText("Veuillez sélectionner une personne.");

	        alert.showAndWait();
	    }
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
	    PersonInterfaceGraphique tempPerson = new PersonInterfaceGraphique();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}
	
	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		PersonInterfaceGraphique selectedPerson = tablePerson.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("Aucune Selection");
	        alert.setHeaderText("Aucune Personne Selectionné");
	        alert.setContentText("Veuillez sélectionner une personne dans le tableau");

	        alert.showAndWait();
	    }
	}
	
	
	
}
