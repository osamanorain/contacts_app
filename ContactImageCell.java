package contactsapp;

import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 *
 * @author osamanorain
 */
public class ContactImageCell extends ListCell<Contact> {

    private final VBox vbox = new VBox(8);
    private final ImageView thumbImageView = new ImageView();
    private final Label name = new Label();

    public ContactImageCell() {
        vbox.setAlignment(Pos.CENTER);

        thumbImageView.setPreserveRatio(true);
        thumbImageView.setFitHeight(70);
        vbox.getChildren().add(thumbImageView);

        //
        name.setTextAlignment(TextAlignment.CENTER);
        name.setWrapText(true);
        name.setFont(Font.font("System", 10));
        vbox.getChildren().add(name);

        setPrefWidth(USE_PREF_SIZE);
    }

    @Override
    protected void updateItem(Contact item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            thumbImageView.setImage(item.getImage());
            name.setText(item.toString());
            setGraphic(vbox);
        }
    }

}
