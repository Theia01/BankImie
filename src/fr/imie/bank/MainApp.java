package fr.imie.bank;

import java.io.IOException;
import java.text.ParseException;

import fr.imie.bank.model.PersonInterfaceGraphique;
import fr.imie.bank.model.PersonInterfaceGraphiqueDAOCsvlmpl;
import fr.imie.bank.view.PersonEditDialogController;
import fr.imie.bank.view.PersonInterface;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ListIterator;

import application.DALException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<PersonInterfaceGraphique> personData = FXCollections.observableArrayList();
    private PersonInterfaceGraphiqueDAOCsvlmpl a = new PersonInterfaceGraphiqueDAOCsvlmpl();
	
    /**
     * Constructor
     */
    public MainApp() {
    	// Add data from bdd
    	try {
    		ListIterator<PersonInterfaceGraphique> li = a.findAll().listIterator();
    		while(li.hasNext()) {
    			personData.add(li.next());}
    	} catch (DALException e) {e.printStackTrace();}
    	
    	// Add some sample data
    	try {
			personData.add(new PersonInterfaceGraphique("Hans", "Muster", "h@hjk", DateUtils.toDate("11/11/1111")));
			personData.add(new PersonInterfaceGraphique("Johan", "Murder", "h@hjk", DateUtils.toDate("11/12/1666")));
			personData.add(new PersonInterfaceGraphique("Alexandre", "LoverAbsent", "h@hjk", DateUtils.toDate("11/09/1169")));
			
		} catch (ParseException e) {e.printStackTrace();}
		
    }
    
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<PersonInterfaceGraphique> getPersonData() {
        return personData;
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BankImie");

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
         // Give the controller access to the main app.
            PersonInterface controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(PersonInterfaceGraphique person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
