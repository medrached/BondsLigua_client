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
import tn.esprit.bondsLiga.bondsLigua_server.services.IHelloServiceRemote;
import tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote;

/**
 * FXML Controller class
 *
 * @author AGORA
 */
public class ClientController implements Initializable {
 
    @FXML
    private Label clientConnectedUserNameTF;

    @FXML
    private Label clientConnectedMoneyTF;
	
	String jndiName="bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	clientConnectedUserNameTF.setText(Session.client.getUsername());
    	clientConnectedMoneyTF.setText(String.valueOf(Session.client.getCurrentMoneyAccount())+"  $");
    }    

   
}
