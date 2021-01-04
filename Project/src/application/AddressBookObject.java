package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//171805053 Soner Cengiz
//181805027 Ceren Aktaþ

public class AddressBookObject extends BorderPane{
	// Widgets declared ---------------------------------------------------------------------------------------------------------------
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertError = new Alert(AlertType.ERROR);
	Alert alertWarning = new Alert(AlertType.WARNING);
	Alert alertConf = new Alert(AlertType.CONFIRMATION);
	Button btAdd = new Button("Add");
	Button btFirst = new Button("First");
	Button btNext = new Button("Next");
	Button btPrevious = new Button("Previous");
	Button btLast = new Button("Last");
	Button btUpdateById = new Button("UpdateById");
	Button btSearchById = new Button("SearchById");
	Button btCleanTextFile = new Button("CleanTextFile");
	TextField tfId = new TextField();
	TextField tfS_Uid = new TextField();
	TextField tfName = new TextField();
	TextField tfStreet = new TextField();
	TextField tfCity = new TextField(); 
	TextField tfGender = new TextField(); 
	TextField tfZip = new TextField();
	
	// Widgets declared +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public AddressBookObject(){
		FlowPane pane = new FlowPane();
		HBox pane2 = new HBox(11);
		pane.setPadding(new Insets(10, 50, 0, 60));
		pane.getChildren().addAll(new Label("ID      "), tfId, new Label("Search/Update ID"),
				tfS_Uid, new Label("Name"), tfName, new Label("Street"), tfStreet, new Label("City   "), 
				tfCity, new Label("Gender"), tfGender, new Label("Zip"), tfZip);
		pane.setHgap(8);
		pane.setVgap(5);
		tfId.setPrefWidth(60);
		tfId.setDisable(true);
		tfS_Uid.setPrefWidth(190);
		tfName.setPrefWidth(360);
		tfStreet.setPrefWidth(360);
		tfCity.setPrefWidth(200);
		tfGender.setPrefWidth(28);
		tfZip.setPrefWidth(52);
		pane2.getChildren().addAll(btAdd, btFirst, btNext, btPrevious, btLast, btUpdateById, btSearchById, btCleanTextFile);
		setCenter(pane);
		setBottom(pane2);
	}
		
}

//171805053 Soner Cengiz
//181805027 Ceren Aktaþ
