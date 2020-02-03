package contactsapp;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class ContactsAppController {

    @FXML
    private ListView<Contact> contactsListView;
    @FXML
    private ImageView contactsImageView;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField homeAddressTextField;

    //Creates empty collection that implements observable list interface
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public void initialize() {
        //bind contacts list to the ListView
        contactsListView.setItems(contacts);

        //When Item seleceted from the ListView
        contactsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> ov, Contact oldValue, Contact newValue) {
                contactsImageView.setImage(newValue.getImage());
                lastNameTextField.setText(newValue.getLastName());
                firstNameTextField.setText(newValue.getfirstName());
                emailAddressTextField.setText(newValue.getEmailAddress());
                phoneNumberTextField.setText(newValue.getPhoneNumber());
                homeAddressTextField.setText(newValue.getHomeAddress());
            }
        }
        );

        contactsListView.setCellFactory(
                new Callback<ListView<Contact>, ListCell<Contact>>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> listView) {
                return new ContactImageCell();
            }
        }
        );
    }

    @FXML
    public void addButtonPressed(ActionEvent event) {
        //Clear Selection
        contactsListView.getSelectionModel().clearSelection();

        contactsImageView.setImage(null);
        lastNameTextField.clear();
        firstNameTextField.clear();
        emailAddressTextField.clear();
        phoneNumberTextField.clear();
        homeAddressTextField.clear();
    }

    @FXML
    public void saveButtonPressed(ActionEvent event) {
        //Check if something from the listview is selected
        Contact contactSelected = contactsListView.getSelectionModel().getSelectedItem();

        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String emailAddress = emailAddressTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String homeAddress = homeAddressTextField.getText();
        Image image = contactsImageView.getImage();

        //Add new contact if nothing form the listview is selected
        if (contactSelected == null) {
            contacts.add(new Contact(lastName, firstName, emailAddress, phoneNumber, homeAddress,
                    image));

            //Clear selection for new contact
            addButton.fire();
        } else {
            contactSelected.setLastName(lastName);
            contactSelected.setfirstName(firstName);
            contactSelected.setEmailAddress(emailAddress);
            contactSelected.setPhoneNumber(phoneNumber);
            contactSelected.setHomeAddress(homeAddress);
            contactSelected.setImage(image);
        }
    }

    @FXML
    public void deleteButtonPressed(ActionEvent event) {
        //find index of the contact selected in the list
        int index = contactsListView.getSelectionModel().getSelectedIndex();
        contacts.remove(index);
    }

    @FXML
    public void contactsImageViewClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        //begin in the same directory as the app
        fileChooser.setInitialDirectory(new File("."));

        //display the filechooser and get file selection
        File file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
        String image = file.toURI().toString();

        //set image 
        contactsImageView.setImage(new Image(image));
    }

}
