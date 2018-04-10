/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Trader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Utils.Session;
import bondsLigua_client.BondBusiness;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.beans.EventHandler;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Bond;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.BondZeroCoupon;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Trader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class ClientController implements Initializable {

    private static final Trader NULL = null;
	@FXML
    private Label clientConnectedUserNameTF;
    @FXML
    private Label clientConnectedMoneyTF;
    @FXML
    private Tab onglet1;
    @FXML
    private ImageView deleteZCBond;
    @FXML
    private ImageView updateZCBond;
    @FXML
    private ImageView addZCBond;
    @FXML
    private TextField interestRate_txt;
    @FXML
    private TextField price_txt;
    @FXML
    private ImageView back;
    @FXML
    private DatePicker startingDate_txt;
    @FXML
    private DatePicker maturityDate_txt;
    @FXML
    private TextField amount_txt;
    @FXML
    private TableView<Bond> tab= new TableView<Bond>();
    @FXML
    private TableColumn<?, ?> reference_col;
    @FXML
    private TableColumn<?, ?> startingDate_col;
    @FXML
    private TableColumn<?, ?> maturityDate_col;
    @FXML
    private TableColumn<?, ?> amount_col;
    @FXML
    private TableColumn<?, ?> price_col;
    @FXML
    private TableColumn<?, ?> paymentPeriod_col;
    @FXML
    private TableColumn<?, ?> interestRate_col;
    ObservableList<Bond> data = FXCollections.observableArrayList();
    @FXML
    private TextField period_txt;
    @FXML
    private Tab onglet2;
    @FXML
    private Tab onglet3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	clientConnectedUserNameTF.setText(Session.client.getUsername());
    	clientConnectedMoneyTF.setText(String.valueOf(Session.client.getCurrentMoneyAccount())+"  $");
		try {
			afficherrevenu();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tab.getSelectionModel().selectedItemProperty().
            addListener((observable, oldValue, newValue) -> {
            
                    try {
						showPubDetails(newValue);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                
                   
            });
    }    

    private void afficherrevenu() throws NamingException {
    	BondBusiness b=new BondBusiness();		
		reference_col.setCellValueFactory(new PropertyValueFactory<>("reference"));
		 startingDate_col.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
		 maturityDate_col.setCellValueFactory(new PropertyValueFactory<>("maturityDate"));
		 amount_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
		 price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		 paymentPeriod_col.setCellValueFactory(new PropertyValueFactory<>("paymentPeriod"));
		 interestRate_col.setCellValueFactory(new PropertyValueFactory<>("interestRate"));
		    List<Bond> o = b.DisplayAllBond();
			for (Bond e : o) {
				if(e.getIdIssuer()==Session.client.getUserId())
				{data.add(e); 
				for(int i = 0; i < o.size(); i++) {
		            System.out.println(o.get(i).getStartingDate());
		        }
				}
				
			}
			
			tab.setItems(data);	
    }
    
    @FXML
    private void deleteZCBond(MouseEvent event) throws NamingException {
    	BondBusiness b=new BondBusiness();
    	
	       b.deleteBond(((Bond) tab.getSelectionModel().getSelectedItem()).getReference());
	     tab.getItems().clear();

		afficherrevenu();
    }

    @FXML
    private void updateZCBond(MouseEvent event) throws NamingException {
    	if (isInputValid()) {
    	BondBusiness b=new BondBusiness();
        if (tab.getSelectionModel().getSelectedItem() != null) {
            BondZeroCoupon r = (BondZeroCoupon) tab.getSelectionModel().getSelectedItem();
            r.setStartingDate(startingDate_txt.getEditor().getText());
            r.setMaturityDate(maturityDate_txt.getEditor().getText());
            r.setAmount(Double.parseDouble(amount_txt.getText()));
            r.setPrice(Double.parseDouble(price_txt.getText()));
            r.setPaymentPeriod(Integer.parseInt(period_txt.getText()));
            r.setInterestRate(Double.parseDouble(interestRate_txt.getText()));
        	
        	
        	
        	 b.updateBond(r);
			 tab.getItems().clear();

				afficherrevenu();
				amount_txt.setText("");
				price_txt.setText("");
				interestRate_txt.setText("");
				period_txt.setText("");
        }
        }
    }

    @FXML
    private void addZCBond(MouseEvent event) throws NamingException {

    	if (isInputValid()) {
    	BondBusiness b=new BondBusiness();
    	Trader t=null;
    	
   		BondZeroCoupon z=new BondZeroCoupon(
   				Session.client.getUserId(),
   				startingDate_txt.getEditor().getText(),
   				maturityDate_txt.getEditor().getText(),
   				Double.parseDouble(amount_txt.getText()),
   				Double.parseDouble(price_txt.getText()),
                Integer.parseInt(period_txt.getText()),
                t,
                Double.parseDouble(interestRate_txt.getText())
        );
   		b.addBond(z);
   		tab.getItems().clear();

			afficherrevenu();
			amount_txt.setText("");
			price_txt.setText("");
			interestRate_txt.setText("");
			period_txt.setText("");
    		}

    }

    @FXML
    private void back(MouseEvent event) {
    }
    
    void showPubDetails(Bond b) throws ParseException  {

    	BondZeroCoupon e=(BondZeroCoupon) b;
    	String idnew;
    	/*idnew = Integer.toString(e.getReference());
       
        e.setStartingDate(startingDate_txt.getEditor().getText());
        e.setMaturityDate(maturityDate_txt.getEditor().getText());
        e.setAmount(Double.parseDouble(amount_txt.getText()));
        e.setPrice(Double.parseDouble(price_txt.getText()));
        e.setPaymentPeriod(Integer.parseInt(period_txt.getText()));
        e.setInterestRate(Double.parseDouble(interestRate_txt.getText()));
*/
        
        idnew = Integer.toString(e.getReference());
        SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");
        String sdate = outFmt.format(inFmt.parse(e.getStartingDate()));


        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(sdate, formatter);
            startingDate_txt.setValue(date);

        } catch (DateTimeParseException exc) {

        }
        SimpleDateFormat inFmt2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outFmt2 = new SimpleDateFormat("dd/MM/yyyy");
        String mdate = outFmt2.format(inFmt2.parse(e.getMaturityDate()));


        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(mdate, formatter);
            maturityDate_txt.setValue(date);

        } catch (DateTimeParseException exc) {

        }


        amount_txt.setText(String.valueOf(e.getAmount()));
        price_txt.setText(String.valueOf(e.getPrice()));
        period_txt.setText(String.valueOf(e.getPaymentPeriod()));
        interestRate_txt.setText(String.valueOf(e.getInterestRate()));
    	
    }
    
    public boolean ValidDate(DatePicker t1,DatePicker t2) {
        if (t1.getValue().isBefore(t2.getValue())) {
            return false;
        }
        return true;
    }
    
    private boolean isInputValid(){
    	if ((ValidDate(startingDate_txt,maturityDate_txt))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Ajout non effecué");
            alert.setContentText("Starting date doit etre avant maturity date!");
            alert.show();
            return false;
    	}

    	else if (!((Double.parseDouble(interestRate_txt.getText()) < 100))){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Ajout non effecué");
            alert.setContentText("Interest Rate est un pourcentage!");
            alert.show();
            return false;
    		}
			else {
				return true;


	        }
    	
    }
    
}
