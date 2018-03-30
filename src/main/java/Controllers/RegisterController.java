/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import Utils.Navigation;
import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.*;
import javafx.scene.input.MouseEvent;

import javafx.stage.*;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Administrator;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Client;
import tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.collections.*;

/**
 * FXML Controller class
 *
 * @author AGORA
 */
public class RegisterController implements Initializable {
	
	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";

    private Client client;
    

    @FXML
    private DatePicker birthDateDP;

    @FXML
    private TextField firstNameLE;

    @FXML
    private TextField usernameLE;

    @FXML
    private TextField nationalityLE;

    @FXML
    private TextField lastnameLE;

    @FXML
    private TextField emailLE;

    @FXML
    private Button registerBT;

    @FXML
    private TextField registerDateLE;

    
    @FXML
    private PasswordField passwordTF;
    
    @FXML
    private PasswordField passwordConfirmTF;
	    
    private int validate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	validate=0;
    	
    	if(validate>=2)
    	{
    	   	registerBT.setDisable(false);
    	}
    	else{
    		registerBT.setDisable(true);
    	}
 

    	
               
    }    
    
    
    
    
    @FXML
    void registerClient(ActionEvent event) throws NamingException, IOException {
    	
    	client=new Client();
     	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	
    	client.setUserName(usernameLE.getText());
    	client.setCardNumber(proxy.generateBankingCode());
    	//
    	client.setFirstName(firstNameLE.getText());
    	client.setLastName(lastnameLE.getText());
    	client.setEmail(emailLE.getText());
    	client.setPwd(passwordTF.getText());
    	Date datenow = new Date();
   	 client.setInscriptionDate(new java.sql.Date(datenow.getTime()));
   	 
	 Date date = Date.from(birthDateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
   	 client.setBirthDate(date);
   	 client.setNationality(nationalityLE.getText());
   	 
  
	proxy.createClient(client);
	Navigation nav=new Navigation();
		
	 Parent root = FXMLLoader.load(getClass().getResource(nav.getTest()));
	 Scene scene = new Scene(root);     
      Stage st = new Stage();
      st.setTitle("Intellix 2.0 Administrator interface ");
      st.setScene(scene);
      st.show();
     
      Stage stage = (Stage) registerBT.getScene().getWindow();
      stage.close();
   	 
   	 
   	 

    }

    @FXML
    void validatePassword(KeyEvent event) {
    	if(passwordTF.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
    		validate=1;
    	}
    	

    }

    @FXML
    void passwordConform(KeyEvent event) {
    	
    	
    	if(passwordTF.getText().equals(passwordConfirmTF.getText()))
    	{
    		validate=2;
    	}
    	
    	
    	if(validate>=2)
    	{
    	   	registerBT.setDisable(false);
    	}
    	else{
    		registerBT.setDisable(true);
    	}
    	
    	
    	
    }

    
    
}
