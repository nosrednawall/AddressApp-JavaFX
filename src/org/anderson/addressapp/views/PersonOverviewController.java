package org.anderson.addressapp.views;

import org.anderson.addressapp.MainApp;
import org.anderson.addressapp.models.Person;
import org.anderson.addressapp.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// referencia ao main da aplicacao
	private MainApp mainApp;

	/**
	 * O construtor. O construtor é chamado antes do método inicialize().
	 */
	public PersonOverviewController() {

	}

	@FXML
	private void initialize() {
		// inicializa a tabela de pessoa com duas colunas
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		// limpar os detalhes antes de inserir outros dados
		showPersonDetails(null);

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Adiciona os dados da observable list na tabela
		personTable.setItems(mainApp.getPersonData());
	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			// Preenche as labels com informações do objeto person.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			// Person é null, remove todo o texto.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		
		if(selectedIndex >= 0) {

			personTable.getItems().remove(selectedIndex);
		}else {
			Alert alert =  new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhuma pessoa selecionada");
			alert.setContentText("Por favor, selecione uma pessoa na tabela");
			
			alert.showAndWait();
		}
	}
	
	/**
	 * Chamado quando o usuário clica no botão novo. Abre uma janela para editar
	 * detalhes da nova pessoa.
	 */
	@FXML
	private void handleNewPerson() {
	    Person tempPerson = new Person();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}

	/**
	 * Chamado quando o usuário clica no botão edit. Abre a janela para editar
	 * detalhes da pessoa selecionada.
	 */
	@FXML
	private void handleEditPerson() {
	    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nada seleciondo.
	        Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Nenhuma seleção");
	            alert.setHeaderText("Nenhuma Pessoa Selecionada");
	            alert.setContentText("Por favor, selecione uma pessoa na tabela.");
	            alert.showAndWait();
	    }
	}
}
