package application;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// 171805053 Soner Cengiz
// 181805027 Ceren Aktaþ

public class AddressBook extends Application {
	private int numberOfPeople=0; // numberOfPeople supports the processing. It provides reference to the number of people in the array. 
	private int locationInArray=0; // locationInArray supports the processing. It provides reference to which people information is shown on the application.
	final static int ID = 4;
	final static int NAME = 32;
	final static int STREET = 32;
	final static int CITY = 20;
	final static int GENDER = 1;
	final static int ZIP = 5;
	final static int RECORD_SIZE =(ID+NAME + STREET + CITY + GENDER + ZIP);
	protected AddressBookObject pane = new AddressBookObject();	
	public RandomAccessFile raf;
	
	PersonObject[] peopleArray;
	{
		try {
			raf = new RandomAccessFile("address.dat", "rw");
			peopleArray=new PersonObject[100];
		}
		catch(IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setMinHeight(210);
		primaryStage.setMinWidth(550);
		primaryStage.setMaxHeight(210);
		primaryStage.setMaxWidth(550);
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Address Book 3.0"); 
		primaryStage.setScene(scene); 
		primaryStage.show();
		try {
			if (raf.length() > 0) { // It fills array if document is not empty.
				long currentPos = raf.getFilePointer();
				while(currentPos < raf.length())
				{
					readFileFillArray(peopleArray, currentPos);
					currentPos=raf.getFilePointer();
				}
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		pane.btUpdateById.setOnAction(e-> eventBtUpdateById());
		pane.btSearchById.setOnAction(e-> eventBtSearchById());
		pane.btAdd.setOnAction(e-> eventBtAdd());
		pane.btFirst.setOnAction(e -> eventBtFirst());
		pane.btNext.setOnAction(e -> eventBtNext());
		pane.btPrevious.setOnAction(e -> eventBtPrevious());
		pane.btLast.setOnAction(e -> eventBtLast());
		pane.btCleanTextFile.setOnAction(e ->cleanTextFields());
}
	public void eventBtUpdateById(){ // Update Button Event
		try {	
			boolean isDataAlreadyExist = isDataAlreadyExist();
			boolean areTextFieldsEmpty = areTextFieldsEmpty(pane.tfName.getText(), pane.tfCity.getText(), pane.tfStreet.getText()); // Method return true if text fields are empty.				
			boolean isZipValueInteger = isZipValueInteger(pane.tfZip.getText()); // isInteger method is checking the 'Zip' text field. Error will shown if user does not enter integer value in the 'Zip' text field.
			boolean isGenderValueValid = isGenderValueValid(pane.tfGender.getText()); // isGenderValueValid method is checking the 'Gender' text field. Error will shown if user does not enter string "M" or "F" in the 'Gender' text field.
			boolean isTfSUIdValidForProcess = isTfSUIdValidForProcess(pane.tfS_Uid.getText()); // isTfSUIdValidForProcess method is checking the 'Search/Update ID' text field. Error will shown if user does not enter integer value in the 'Search/Update ID' text field.
			boolean wasDataFound = false; // dataWasFound won't change if the entered id was not found.
			
			// It checks 'Search/Update ID', 'Gender' and 'Zip' text fields.
			// If text fields is not appropriate for updating, process will not execute.
			if(isTfSUIdValidForProcess == true && isZipValueInteger == true && isGenderValueValid == true && areTextFieldsEmpty == false && isDataAlreadyExist == false) {
				for(int i = 0; i<numberOfPeople; i++) {
					if(peopleArray[i].getId() == Integer.parseInt(pane.tfS_Uid.getText())) { // The loop will continue until the entered id find. 
						raf.seek(RECORD_SIZE*2*(i));
						FileOperations.writeFixedLengthString(pane.tfS_Uid.getText(), ID, raf);
						FileOperations.writeFixedLengthString(pane.tfName.getText(), NAME, raf);
						FileOperations.writeFixedLengthString(pane.tfStreet.getText(), STREET, raf);
						FileOperations.writeFixedLengthString(pane.tfCity.getText(), CITY, raf);
						FileOperations.writeFixedLengthString(pane.tfGender.getText(), GENDER, raf);
						FileOperations.writeFixedLengthString(pane.tfZip.getText(), ZIP, raf);
						peopleArray[i].setName(pane.tfName.getText());
						peopleArray[i].setStreet(pane.tfStreet.getText());
						peopleArray[i].setCity(pane.tfCity.getText());
						peopleArray[i].setGender(pane.tfGender.getText());
						peopleArray[i].setZip(pane.tfZip.getText());
						locationInArray = i;
						traverseArray(peopleArray, locationInArray);
						wasDataFound = true; // The entered id was found. So, code set dataWasFound true.
						pane.alertConf.setHeaderText("Data updated successfully");
						pane.alertConf.setContentText("");
						pane.alertConf.showAndWait();
						break;
					}
				}
				if(wasDataFound == false) { // Error screen shown if data was not found.
					pane.alertError.setContentText("Data was not found!");
					pane.alertError.setContentText("Data was not found with the ID you are looking for.");
					pane.alertError.showAndWait();
				}
			}
			else {
				pane.alertError.setHeaderText("Data was not updated!"); // Error screen shown if user enter the invalid text in the text fields.
				pane.alertError.setContentText("Please check text fields and try again!\n"
						+ "- Do not leave empty text fields.\n"
						+ "- Enter integer value in the zip text field.\n"
						+ "- Enter 'M' or 'F' value in gender text field.\n"
						+ "- Do not enter already existed data.");
				pane.alertError.showAndWait();
			}
		}
		catch(Exception ex) { // Error screen will shown if something went wrong.
			pane.alertError.setHeaderText("Record was not updated!");
			pane.alertError.setContentText("- Max 32 character can be enter in the name text field.\n"
					+ "- Max 32 character can be enter in the street text field.\n"
					+ "- Max 20 character can be enter in the city text field.\n"
					+ "- Max 1 character can be enter in the gender text field.\n"
					+ "- Max 5 character can be enter in the zip text field.\n");
			pane.alertError.showAndWait();
		}
	}
	public void eventBtSearchById(){ // Search Button Event
		try {
			boolean isTfSUIdValidForProcess = isTfSUIdValidForProcess(pane.tfS_Uid.getText()); // isTfSUIdValidForProcess method is checking the 'Search/Update ID' text field. Error will shown if user does not enter integer value in the 'Search/Update ID' text field.
			boolean wasDataFound = false; // dataWasFound won't change if the entered id was not found.
			
			if(isTfSUIdValidForProcess == true) { // It checks 'Search/Update ID' text field for process.
				for(int i = 0; i<numberOfPeople; i++) {
					if(peopleArray[i].getId() == Integer.parseInt(pane.tfS_Uid.getText())) {
						locationInArray = i;
						traverseArray(peopleArray, locationInArray);
						wasDataFound=true; // The entered id was found. So, code set dataWasFound true.
						break;
					}
				}
				if(wasDataFound==false) { // Error screen shown if data was not found.
					pane.alertError.setContentText("Data was not found!");
					pane.alertError.setContentText("Data was not found with the ID you are looking for.");
					pane.alertError.showAndWait();
				}
			}
			else {
				pane.alertWarning.setHeaderText("Warning!"); // Error screen will shown if the user does not enter integer value in the 'Search/Update ID' text field.
				pane.alertWarning.setContentText("Please check 'Search/Update ID' text field and try again!\n"
						+ "- Do not leave empty 'Search/Update ID' text field.\n"
						+ "- Enter integer value in the 'Search/Update ID' text field.");
				pane.alertWarning.showAndWait();
			}
		}
		catch(Exception ex) { // Error screen will shown if something went wrong.
			pane.alertError.setHeaderText("Error!");
			pane.alertError.setContentText("");
			pane.alertError.showAndWait();
		}
	}
	public void eventBtAdd() { // Add Button Event
		try {		
			boolean isDataAlreadyExist = isDataAlreadyExist();
			boolean isGenderValueValid = isGenderValueValid(pane.tfGender.getText()); // isGenderValueValid method is checking the 'Gender' text field. Error will shown if user does not enter string "M" or "F" in the 'Gender' text field.
			boolean isZipValueInteger = isZipValueInteger(pane.tfZip.getText()); // isInteger method is checking the 'Zip' text field. Error will shown if user does not enter integer value in the 'Zip' text field.
			boolean areTextFieldsEmpty = areTextFieldsEmpty(pane.tfName.getText(), pane.tfCity.getText(), pane.tfStreet.getText()); // Method return true if text fields are empty.
			if(isZipValueInteger == true && isGenderValueValid == true && areTextFieldsEmpty == false && isDataAlreadyExist == false) { // It checks 'Gender', 'Zip' and text fields for processing.
				writeAddressToFile(raf.length());
				readFileFillArray(peopleArray, RECORD_SIZE*2*(numberOfPeople));
				pane.alertConf.setHeaderText("Record is added successfully");
				pane.alertConf.setContentText("");
				pane.alertConf.showAndWait();
				cleanTextFields();
			}
			else { // Error screen will shown if the entered values in text fields are invalid for process.
				pane.alertError.setHeaderText("Record was not added");
				pane.alertError.setContentText("Please check text fields and try again!\n"
						+ "- Do not leave empty text fields.\n"
						+ "- Enter integer value in the zip text field.\n"
						+ "- Enter 'M' or 'F' value in gender text field.\n"
						+ "- Do not enter already existed data.");
				pane.alertError.showAndWait();
			}
			
		} catch (Exception ex) { // Error screen will shown if something went wrong.
			pane.alertError.setHeaderText("Record was not added");
			pane.alertError.setContentText("- Max 32 character can be enter in the name text field.\n"
					+ "- Max 32 character can be enter in the street text field.\n"
					+ "- Max 20 character can be enter in the city text field.\n"
					+ "- Max 1 character can be enter in the gender text field.\n"
					+ "- Max 5 character can be enter in the zip text field.\n");
			pane.alertError.showAndWait();
		}
	}
	public void eventBtFirst() { // First Button Event
		int backup = locationInArray; // In this event, I will change locationInArray integer. So, I have to keep old state of locationInArray.
		try {
			locationInArray=0;
			traverseArray(peopleArray, locationInArray); // First data will shown in the application.
		}
		catch(Exception ex) { // Error will shown if there is no data.
			locationInArray = backup; // In this event, I will change locationInArray integer. So, I have to keep old state of locationInArray.
			pane.alertError.setHeaderText("Data was not found!");
			pane.alertError.setContentText("There is no saved data anymore.");
			pane.alertError.showAndWait();
		}
	}
	public void eventBtNext() { // Next Button Event
		try {
			if(Objects.equals(pane.tfId.getText(), "")){ // Error screen will shown if 'ID' text field is blank.
				pane.alertError.setHeaderText("Data was not found!");
				pane.alertError.setContentText("");
				pane.alertError.showAndWait();
			}
			else {
				if(locationInArray==numberOfPeople-1) { // There will be no change if the last data is already showing.
					pane.alertInfo.setHeaderText("This data is last one!");
					pane.alertInfo.setContentText("");
					pane.alertInfo.showAndWait();
				}
				else {
					locationInArray++;
					traverseArray(peopleArray, locationInArray);
				}
			}
		} catch(Exception ex) { // Error screen will shown if there is no data.
			pane.alertError.setHeaderText("Data was not found!");
			pane.alertError.setContentText("");
			pane.alertError.showAndWait();
		}
	}
	public void eventBtPrevious(){ // Previous Button Event
		try {
			if(Objects.equals(pane.tfId.getText(), "")){ // Error screen will shown if the ID text was empty.
				pane.alertError.setHeaderText("Data was not found!");
				pane.alertError.setContentText("");
				pane.alertError.showAndWait();
			}
			else {
				if(locationInArray==0) { // There will be no change if the first data is already showing.
					pane.alertInfo.setHeaderText("This data is first one!");
					pane.alertInfo.setContentText("");
					pane.alertInfo.showAndWait();
				}
				else {
					locationInArray--;
					traverseArray(peopleArray, locationInArray);
				}
			}
			
		} catch(Exception ex) { // Error screen will shown if there is no data.
			pane.alertError.setHeaderText("Data was not found!");
			pane.alertError.setContentText("");
			pane.alertError.showAndWait();
		}
		
	}
	public void eventBtLast() { // Last Button Event
		int backup = locationInArray; // In this event, I will change locationInArray integer. So, I have to keep old state of locationInArray.
		try {				
			if(numberOfPeople>0) {
				locationInArray = numberOfPeople-1;
				traverseArray(peopleArray, locationInArray);
			}
			else {
				pane.alertError.setHeaderText("Data was not found!"); // Error screen will shown if there is no data.
				pane.alertError.setContentText("There is no saved data anymore.");
				pane.alertError.showAndWait();
			}
		} 
		catch(Exception ex) {
			locationInArray = backup; // In this event, I will change locationInArray integer. So, I have to keep old state of locationInArray.
		}	
	}
	public boolean isTfSUIdValidForProcess(String tfSUIdText) { // This function returns true if the searched id can parse to integer.
		try {
			int intTfSUIdText = Integer.parseInt(tfSUIdText);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	public boolean isGenderValueValid(String gender) { // This function returns true if the entered gender equals "M" or "F".
		if(Objects.equals(gender, "M") || Objects.equals(gender, "F")) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isZipValueInteger(String zip) { // This function returns true if the entered zip can parse to integer.
		try {
			int intZip = Integer.parseInt(zip); 
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	public boolean areTextFieldsEmpty(String tfNameText, String tfCityText, String tfStreetText) {
		if(Objects.equals(tfNameText, "") || Objects.equals(tfCityText, "") || Objects.equals(tfStreetText, "")) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isDataAlreadyExist() {
		for(int i = 0; i< numberOfPeople; i++) {
			if(Objects.equals(peopleArray[i].getName(), pane.tfName.getText()) && Objects.equals(peopleArray[i].getStreet(), pane.tfStreet.getText()) &&Objects.equals(peopleArray[i].getCity(), pane.tfCity.getText()) && Objects.equals(peopleArray[i].getGender(), pane.tfGender.getText()) && Objects.equals(peopleArray[i].getZip(), pane.tfZip.getText())) {
				return true;
			}
		}
		return false;
	}
	public void writeAddressToFile(long position) {
		try {
			int numberOfPeoplepp = this.numberOfPeople + 1;
			raf.seek(position);
			FileOperations.writeFixedLengthString(Integer.toString(numberOfPeoplepp), ID, raf);
			FileOperations.writeFixedLengthString(pane.tfName.getText(), NAME, raf);
			FileOperations.writeFixedLengthString(pane.tfStreet.getText(), STREET, raf);
			FileOperations.writeFixedLengthString(pane.tfCity.getText(), CITY, raf);
			FileOperations.writeFixedLengthString(pane.tfGender.getText(), GENDER, raf);
			FileOperations.writeFixedLengthString(pane.tfZip.getText(), ZIP, raf);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public void readFileFillArray(PersonObject[]people,long position) throws IOException {
		raf.seek(position);
		String id = FileOperations.readFixedLengthString(ID, raf);
		int intID= Integer.parseInt(id.trim().toString());
		String name = FileOperations.readFixedLengthString(NAME, raf).trim();
		String street = FileOperations.readFixedLengthString(STREET, raf).trim();
		String city = FileOperations.readFixedLengthString(CITY, raf).trim();
		String gender= FileOperations.readFixedLengthString(GENDER, raf).trim();
		String zip = FileOperations.readFixedLengthString(ZIP, raf).trim();

		PersonObject p= new PersonObject(intID, name, gender, street, city, zip);
		people[numberOfPeople]=p;
		numberOfPeople++;
	}
	public void cleanTextFields() { // This function clears text fields.
		pane.tfId.clear();
		pane.tfName.clear();
		pane.tfStreet.clear();
		pane.tfCity.clear();
		pane.tfGender.clear();
		pane.tfZip.clear();
	}
	public void traverseArray(PersonObject[]people,int locationInArray) { // This function shows data.
		pane.tfId.setText(String.valueOf(peopleArray[locationInArray].getId()));
		pane.tfName.setText(peopleArray[locationInArray].getName());
		pane.tfStreet.setText(peopleArray[locationInArray].getStreet());
		pane.tfCity.setText(peopleArray[locationInArray].getCity());
		pane.tfGender.setText(peopleArray[locationInArray].getGender());
		pane.tfZip.setText(peopleArray[locationInArray].getZip());

	}
	public static void main(String[] args) {
		 Application.launch(args);
	}
}

//171805053 Soner Cengiz
//181805027 Ceren Aktaþ
