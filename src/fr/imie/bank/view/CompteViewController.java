package fr.imie.bank.view;


import java.math.BigDecimal;

import application.DALException;
import fr.imie.bank.model.BankAccountInterfaceGaphique;
import fr.imie.bank.model.BankAccountInterfaceGraphiqueDaoCsvImpl;
import fr.imie.bank.model.PersonInterfaceGraphique;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CompteViewController {
	
	@FXML private TableView<BankAccountInterfaceGaphique> tablePersonCompte;
    @FXML private TableColumn<BankAccountInterfaceGaphique, String> numeroCompteColumn;
    @FXML private TableColumn<BankAccountInterfaceGaphique, BigDecimal> soldeColumn;
    
    private ObservableList<BankAccountInterfaceGaphique> compteData = FXCollections.observableArrayList();
    private BankAccountInterfaceGraphiqueDaoCsvImpl a = new BankAccountInterfaceGraphiqueDaoCsvImpl();
	
	private Stage dialogStage;
	private BankAccountInterfaceGaphique compte;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	public boolean isOkClicked() {
			dialogStage.close();
		return false;
	}

	public void AfficheCompte(PersonInterfaceGraphique selectedPerson) {
		try {
			compteData.add(a.findById(selectedPerson.idProperty().get()));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("--------------------------------------");
		}
		
		numeroCompteColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		soldeColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
	}

}
