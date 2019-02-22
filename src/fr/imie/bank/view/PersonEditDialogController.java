package fr.imie.bank.view;

import application.DALException;
import fr.imie.bank.DateUtils;
import fr.imie.bank.model.PersonInterfaceGraphique;
import fr.imie.bank.model.PersonInterfaceGraphiqueDAOCsvlmpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PersonEditDialogController {
	@FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField birthdayField;
    
    private Stage dialogStage;
    private PersonInterfaceGraphique person;
    private boolean okClicked = false;
    
    private PersonInterfaceGraphiqueDAOCsvlmpl fonctionSql = new PersonInterfaceGraphiqueDAOCsvlmpl();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(PersonInterfaceGraphique person) {
        this.person = person;

        firstNameField.setText(person.getFirstname());
        lastNameField.setText(person.getLastname());
        emailField.setText(person.getEmail());
        birthdayField.setText(DateUtils.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstname(firstNameField.getText());
            person.setLastname(lastNameField.getText());
            person.setEmail(emailField.getText());
            person.setBirthday(DateUtils.parse(birthdayField.getText()));
            try {
				fonctionSql.update(person);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            okClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Prenom Invalide!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Nom Invalide!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Email invalide!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Date de Naissance invalide!\n";
        } else {
            if (!DateUtils.validDate(birthdayField.getText())) {
                errorMessage += "Date de Naissance invalide! Utilisez le format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs Invalide");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
