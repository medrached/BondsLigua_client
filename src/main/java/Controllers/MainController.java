/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.scene.control.TextField;
import Utils.Navigation;
import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Administrator;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Client;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Trader;
import tn.esprit.bondsLiga.bondsLigua_server.services.IHelloServiceRemote;
import tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote;

/**
 * FXML Controller class
 *
 * @author AGORA
 */
public class MainController implements Initializable {
 
//	bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote
	
	private final String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";

    @FXML
    private TextField pwd_LE;

    @FXML
    private Button login_BT;

    @FXML
    private TextField username_LE;
    
    @FXML
    private Button registerBT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODo
    }    

    @FXML
    private void login(ActionEvent event) throws NamingException, IOException {
    	
    	
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
      	
      	
      	if(proxy.adminExists(username_LE.getText(), pwd_LE.getText()) && proxy.returnAdminConnected(username_LE.getText(), pwd_LE.getText()).getValidationLevel()==2){
      	   Administrator ad =new Administrator();
   	        ad=proxy.returnAdminConnected(username_LE.getText(), pwd_LE.getText());
   	        Session.admin=ad;
      	 
      	
      		Navigation nav=new Navigation();
      		
       	 Parent root = FXMLLoader.load(getClass().getResource(nav.getAdmin()));
   	        Scene scene = new Scene(root);     
   	        Stage st = new Stage();
   	        st.setTitle("Intellix 2.0 Administrator interface ");
   	        st.setScene(scene);
   	        st.show();
   	       
   	        Stage stage = (Stage) login_BT.getScene().getWindow();
   	        stage.close();
   	        
   	     
   	        
   	      
   	        
   	        
   	        
   	        
   	                    	
      	}
      	
      	
      	
      	else if(proxy.traderExists(username_LE.getText(), pwd_LE.getText()))
      	{
      		
      		
       	  Trader t =new Trader();
	        t=proxy.returnTraderConnected(username_LE.getText(), pwd_LE.getText());
	        Session.trader=t;
  	 
      		Navigation nav=new Navigation();
      		
          	 Parent root = FXMLLoader.load(getClass().getResource(nav.getTrader()));
      	        Scene scene = new Scene(root);     
      	        Stage st = new Stage();
      	        st.setTitle("Intellix 2.0 Administrator interface ");
      	        st.setScene(scene);
      	        st.show();
      	       
      	        Stage stage = (Stage) login_BT.getScene().getWindow();
      	        stage.close();
      	}
      	
      	
      	
      	
      	else if(proxy.clientExists(username_LE.getText(), pwd_LE.getText())){
      		
      	  
      		Client  c=proxy.returnClientConnected(username_LE.getText(), pwd_LE.getText());
 	        Session.client=c;
    	 
      		Navigation nav=new Navigation();
      		
          	 Parent root = FXMLLoader.load(getClass().getResource(nav.getClient()));
      	        Scene scene = new Scene(root);     
      	        Stage st = new Stage();
      	        st.setTitle("Intellix 2.0 Administrator interface ");
      	        st.setScene(scene);
      	        st.show();
      	       
      	        Stage stage = (Stage) login_BT.getScene().getWindow();
      	        stage.close();
      	}
      	
      	
      	
      	
      	else
      	{
      		username_LE.setStyle("-fx-background-color:#ff8080;");
      		pwd_LE.setStyle("-fx-background-color:#ff8080;");
      		
      	}
      	
    	
    }
    
    @FXML
    void goToRegister(ActionEvent event) throws IOException {
    	Navigation nav=new Navigation();
  		
     	 Parent root = FXMLLoader.load(getClass().getResource(nav.getRegister()));
 	        Scene scene = new Scene(root);     
 	        Stage st = new Stage();
 	        st.setTitle("Intellix 2.0 Administrator interface ");
 	        st.setScene(scene);
 	        st.show();
 	       
 	        Stage stage = (Stage) login_BT.getScene().getWindow();
 	        stage.close();
    	
    }
  
}
