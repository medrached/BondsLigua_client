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
import javafx.scene.input.MouseEvent;

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
	    
	    
	    @FXML
	    private Button detailsAdmin_BT;
	    
	    @FXML
	    private Button validateAdminAccount_BT;
	    

	    @FXML
	    private AnchorPane DetailsAdmin_AP;
	    
	    @FXML
	    private Button back_BT;
	    

	    @FXML
	    private TextField firstNameupdate_LE;
	    
	    @FXML
	    private TextField usernameUpdate_LE;
	    
	    @FXML
	    private TextField emailUpdate_LE;
	    
	    @FXML
	    private TextField lastnameUpdate_LE;
	    
	    @FXML
	    private TextField birthdateUpdate_LE;
	    
	    @FXML
	    private TextField registerDateUpdate_LE;
	    
	    @FXML
	    private TextField privilegeUpdate_LE;
	    
	    

	    @FXML
	    private TextField nationalityUpdate_LE;
	    
	    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    	admin=new Administrator();
    	
    	CreateAdmin_AP.setVisible(false);
    	DisplayAdmins_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);
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
        		
     
        		
        	   for (Administrator a :proxy.findAll()) {
                   liste.add(a);
               }
        	   listeAdministrators_LV.setItems(liste);
        	
        	   
               listeAdministrators_LV.setCellFactory(lv -> new ListCell<Administrator>() {
                   @Override
                   protected void updateItem(Administrator c, boolean empty) {
                       super.updateItem(c, empty);
                       if (empty) {
                           setText(null);
                           setStyle("");
                       } else {
                           setText("user name :"+ c.getUsername()+ "           Last name : "+c.getLast_name()+"           first name :"+c.getFirst_name()+"           Nationality : "+c.getNationality()+"           Register date: "+c.getInscription_date());

                    	   if(c.getValidation_level()==0)

                    	   {
                               setStyle("-fx-background-color: #F5BCA9");

                    	   }
                    	   else if(c.getValidation_level()==1)
                    	   {
                               setStyle("-fx-background-color: #F7BE81");

                    	   }
                    	   else if(c.getValidation_level()==2)
                    	   {
                               setStyle("-fx-background-color: #D0F5A9");

                    	   }
                               
                           
                       }
                   }
               });
    
      

    }
    
    
    
    
    @FXML
    void show_Create_Admin_Acount_Interface(ActionEvent event) {
    	CreateAdmin_AP.setVisible(true);
    	DisplayAdmins_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);


    	

    }

    @FXML
    void show_display_administrator_interface(ActionEvent event) {
    	DisplayAdmins_AP.setVisible(true);
    	CreateAdmin_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);



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
    	this.refreshListView();
      	DisplayAdmins_AP.setVisible(true);
    	CreateAdmin_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);
    	firstname_LE.clear();
    	lastname_LE.clear();
    	username_LE.clear();
    	pwd_LE.clear();
    	email_LE.clear();
    	nationality_LE.clear();
    	
    }
    
    

    @FXML
    void chooseAdministrator(MouseEvent event) {
    	
    	admin=listeAdministrators_LV.getSelectionModel().getSelectedItem();
    
    	    }

    
    @FXML
    void updateAcountAdmin(ActionEvent event) throws NamingException {
   	 
    	Administrator adminis=admin;
    	adminis.setUsername(usernameUpdate_LE.getText());
    	adminis.setFirst_name(firstNameupdate_LE.getText());
    	adminis.setLast_name(lastnameUpdate_LE.getText());
    	adminis.setEmail(emailUpdate_LE.getText());
    	adminis.setPrivileges(privilegeUpdate_LE.getText());
    	adminis.setNationality(nationalityUpdate_LE.getText());
    	adminis.setValidation_level(0);
    	//birthdate
    	//register date
    	

    	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	proxy.updateAdmin(adminis);
    	
    	this.refreshListView();
      	DisplayAdmins_AP.setVisible(true);
    	CreateAdmin_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);
    	
    	

    }

    @FXML
    void upgradeAdminPrivileges(ActionEvent event) throws NamingException {
    	
    	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	proxy.upgradePrivilege(admin.getUser_id());
    	this.refreshListView();

    }

    @FXML
    void deleteAdminAcount(ActionEvent event) throws NamingException {
    	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	System.out.println(admin);
    	proxy.deleteAdmin(admin.getUser_id());
    	this.refreshListView();
    }

    @FXML
    void BanAcountAdmin(ActionEvent event) throws NamingException {
    	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	System.out.println(admin);
    	proxy.banAcountAdmin(admin.getUser_id());
    	this.refreshListView();
    }


    

    @FXML
    void searchAdmin(ActionEvent event) throws NamingException {
    	String word=search_LE.getText();
    	
    	 String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
     	Context context=new InitialContext();
     	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
     
     
     	ObservableList<Administrator> liste = FXCollections.observableArrayList();
     		
  
     		
     	   for (Administrator a :proxy.searchAdmins(word)) {
                liste.add(a);
            }
     	   listeAdministrators_LV.setItems(liste);
     	
     	   
            listeAdministrators_LV.setCellFactory(lv -> new ListCell<Administrator>() {
                @Override
                protected void updateItem(Administrator c, boolean empty) {
                    super.updateItem(c, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText("user name :"+ c.getUsername()+ "           Last name : "+c.getLast_name()+"           first name :"+c.getFirst_name()+"           Nationality : "+c.getNationality()+"           Register date: "+c.getInscription_date());

                 	   if(c.getValidation_level()==0)

                 	   {
                            setStyle("-fx-background-color: #F5BCA9");

                 	   }
                 	   else if(c.getValidation_level()==1)
                 	   {
                            setStyle("-fx-background-color: #F7BE81");

                 	   }
                 	   else if(c.getValidation_level()==2)
                 	   {
                            setStyle("-fx-background-color: #D0F5A9");

                 	   }
                            
                        
                    }
                }
            });
 
   
    	
    }
    
    

    @FXML
    void validateAdminAccount(ActionEvent event) throws NamingException {

    	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	System.out.println(admin);
    	proxy.validateAdmin(admin.getUser_id());
    	this.refreshListView();
    	
    }

    @FXML
    void showAdminDetails(ActionEvent event) {
    	
    	usernameUpdate_LE.setText(admin.getUsername());
    	lastnameUpdate_LE.setText(admin.getLast_name());
    	firstNameupdate_LE.setText(admin.getFirst_name());
    	emailUpdate_LE.setText(admin.getEmail());
    	nationalityUpdate_LE.setText(admin.getNationality());
    	privilegeUpdate_LE.setText(admin.getPrivileges());
    	birthdateUpdate_LE.setText(admin.getBirthDate().toString());
    	registerDateUpdate_LE.setText(admin.getInscription_date().toString());
    	
    	
    	
    	DisplayAdmins_AP.setVisible(false);
    	CreateAdmin_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(true);
    	
    }
    
    @FXML
    void goBaackAdmin(ActionEvent event) {
    	DisplayAdmins_AP.setVisible(true);
    	CreateAdmin_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);
    }

    
    
}
