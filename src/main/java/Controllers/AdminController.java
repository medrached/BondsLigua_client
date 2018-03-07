/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Utils.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.*;
import javafx.stage.*;
import javafx.scene.paint.Color;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Administrator;
import tn.esprit.bondsLiga.bondsLigua_server.services.IHelloServiceRemote;
import tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.*;

/**
 * FXML Controller class
 *
 * @author AGORA
 */
public class AdminController implements Initializable {

    private Administrator admin;
    

		@FXML
	    private TextField pwd_LE;

	    @FXML
	    private TextField email_LE;

	    @FXML
	    private TextField nationality_LE;

	    @FXML
	    private TextField username_LE;

	    @FXML
	    private TextField firstname_LE;

	    @FXML
	    private TextField lastname_LE;

	    @FXML
	    private ComboBox<String> privileges_CB;
	    
	    @FXML
	    private Button createAdminAccount_BT;
	    
	    @FXML
	    private DatePicker birthDate_DP;
	    
	    @FXML
	    private Button createAdminAcount_BT;
	    
	    @FXML
	    private Button displayAdministrators_BT;
	    
	    @FXML
	    private AnchorPane CreateAdmin_AP;

	    @FXML
	    private AnchorPane DisplayAdmins_AP;
	    
	    @FXML
	    private ListView<Administrator> listeAdministrators_LV;
	    
	    @FXML
	    private TextField search_LE;

	    @FXML
	    private Button updateAdmin_BT;
	    
	    @FXML
	    private Button upgradePrivliegeAdmin_BT;
	    
	    @FXML
	    private Button deleteAdmin_BT;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    	admin=new Administrator();
    	
    	CreateAdmin_AP.setVisible(false);
    	DisplayAdmins_AP.setVisible(false);
    	ObservableList <String> listPrivileges = FXCollections.observableArrayList();
        listPrivileges.addAll("supervisor","super admin","admin");
        privileges_CB.setItems(listPrivileges);
        
        try {
			this.refreshListView();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
               
    }    
    
    
    
    void refreshListView() throws NamingException
    {
    	  String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
      	Context context=new InitialContext();
      	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
        ObservableList<Administrator> liste = FXCollections.observableArrayList();

        for (Administrator admin :proxy.findAll()) {
            liste.add(admin);
        }
        listeAdministrators_LV.setItems(liste);
        
        
      

    }
    
    
    
    
    @FXML
    void show_Create_Admin_Acount_Interface(ActionEvent event) {
    	CreateAdmin_AP.setVisible(true);
    	DisplayAdmins_AP.setVisible(false);


    	

    }

    @FXML
    void show_display_administrator_interface(ActionEvent event) {
    	DisplayAdmins_AP.setVisible(true);
    	CreateAdmin_AP.setVisible(false);



    }


    
    @FXML
    void add_admin(ActionEvent event) throws NamingException {
    
    	admin.setFirst_name(firstname_LE.getText());
    	admin.setLast_name(lastname_LE.getText());
    	admin.setUsername(username_LE.getText());
    	admin.setPwd(pwd_LE.getText());
    	admin.setEmail(email_LE.getText());
    	admin.setNationality(nationality_LE.getText());
    	admin.setValidation_level(0);
    	//date d'aujourd'hui
    	//admin.setInscription_date();
    	admin.setPrivileges(privileges_CB.getSelectionModel().getSelectedItem());
    	
    	Date datenow = new Date();
    	 admin.setInscription_date(new java.sql.Date(datenow.getTime()));
    	 
    	 Date date = Date.from(birthDate_DP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	 admin.setBirthDate(date);
    	 
    	 
    	
    	 
    	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	proxy.createAdmin(admin);
    	
    	
    }
    
    

    @FXML
    void chooseAdministrator(ActionEvent event) {
    	
    	admin=listeAdministrators_LV.getSelectionModel().getSelectedItem();
    }
    
    
    @FXML
    void updateAcountAdmin(ActionEvent event) {

    }

    @FXML
    void upgradeAdminPrivileges(ActionEvent event) {

    }

    @FXML
    void deleteAdminAcount(ActionEvent event) {

    }

    @FXML
    void BanAcountAdmin(ActionEvent event) {

    }


    

    @FXML
    void searchAdmin(ActionEvent event) {

    }
    
    
}
