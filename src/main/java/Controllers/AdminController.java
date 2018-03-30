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

import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.*;
import javafx.scene.input.MouseEvent;

import javafx.stage.*;
import tn.esprit.bondsLiga.bondsLigua_server.persistence.Administrator;
import tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.collections.*;

/**
 * FXML Controller class
 *
 * @author AGORA
 */
public class AdminController implements Initializable {
	
	String jndiName="bondsLigua_server-ear/bondsLigua_server-ejb/UserManagement!tn.esprit.bondsLiga.bondsLigua_server.services.IUserManagementRemote";

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
	    private ImageView iconAdd;
	    

	    @FXML
	    private ImageView iconCroix;
	    
	    

	    @FXML
	    private ImageView iconValid;

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
	    

	    
	    @FXML
	    private Label adminConnectedUserName_LE;


	    
	    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    	if(Session.admin.getPrivileges().equals("Administrator"))
    	{
    		validateAdminAccount_BT.setVisible(false);
    		deleteAdmin_BT.setVisible(false);

    	    iconCroix.setVisible(false);
    	    iconValid.setVisible(false);
    	    createAdminAcount_BT.setVisible(false);
    	    iconAdd.setVisible(false);
    	}
    	else if(Session.admin.getPrivileges().equals("Super admin"))
    	{
    		validateAdminAccount_BT.setVisible(true);
    		deleteAdmin_BT.setVisible(true);

    	    iconCroix.setVisible(true);
    	    iconValid.setVisible(true);
    	    createAdminAcount_BT.setVisible(false);
    	    iconAdd.setVisible(false);
    	}
    	
    	else if (Session.admin.getPrivileges().equals("Supervisor"))
    	{
    		validateAdminAccount_BT.setVisible(true);
    		deleteAdmin_BT.setVisible(true);
    		iconAdd.setVisible(true);

    	    iconCroix.setVisible(true);
    	    iconValid.setVisible(true);
    	    createAdminAcount_BT.setVisible(true);
    	}
    	adminConnectedUserName_LE.setText(Session.admin.getUsername());
    	admin=new Administrator();
    	CreateAdmin_AP.setVisible(false);
    	DisplayAdmins_AP.setVisible(false);
    	DetailsAdmin_AP.setVisible(false);
    	ObservableList <String> listPrivileges = FXCollections.observableArrayList();
        listPrivileges.addAll("Supervisor","Super admin","Administrator");
        privileges_CB.setItems(listPrivileges);

        
      try {
			this.refreshListView();
		} catch (NamingException e) {

			e.printStackTrace();
		}
		
		
        
               
    }    
    
    
    
    void refreshListView() throws NamingException
    {
        	Context context=new InitialContext();
        	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
        
        
        	ObservableList<Administrator> liste = FXCollections.observableArrayList();
        		
     
        		
        	   for (Administrator a :proxy.findAll(Session.admin.getUserId())) {
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
                           setText("user name :"+ c.getUsername()+ "           Last name : "+c.getLastName()+"           first name :"+c.getFirstName()+"           Nationality : "+c.getNationality()+"           Register date: "+c.getInscriptionDate());

                    	   if(c.getValidationLevel()==0)

                    	   {
                               setStyle("-fx-background-color: #F5BCA9");

                    	   }
                    	   else if(c.getValidationLevel()==1)
                    	   {
                               setStyle("-fx-background-color: #F7BE81");

                    	   }
                    	   else if(c.getValidationLevel()==2)
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
    void addAdmin(ActionEvent event) throws NamingException {
    
    	
    	admin.setFirstName(firstname_LE.getText());
    	admin.setLastName(lastname_LE.getText());
    	admin.setUserName(username_LE.getText());
    	admin.setPwd(pwd_LE.getText());
    	admin.setEmail(email_LE.getText());
    	admin.setNationality(nationality_LE.getText());
    	admin.setValidationLevel(0);

    	
    	
    	
    	
    	admin.setPrivileges(privileges_CB.getSelectionModel().getSelectedItem());
    	
    	Date datenow = new Date();
    	 admin.setInscriptionDate(new java.sql.Date(datenow.getTime()));
    	 
    	 Date date = Date.from(birthDate_DP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	 admin.setBirthDate(date);
    	 
    	 
    	
    	 
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
    	adminis.setUserName(usernameUpdate_LE.getText());
    	adminis.setFirstName(firstNameupdate_LE.getText());
    	adminis.setLastName(lastnameUpdate_LE.getText());
    	adminis.setEmail(emailUpdate_LE.getText());
    	adminis.setPrivileges(privilegeUpdate_LE.getText());
    	adminis.setNationality(nationalityUpdate_LE.getText());
    	adminis.setValidationLevel(0);

    	

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
    	
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	proxy.upgradePrivilege(admin.getUserId());
    	this.refreshListView();

    }

    @FXML
    void deleteAdminAcount(ActionEvent event) throws NamingException {
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	proxy.deleteAdmin(admin.getUserId());
    	this.refreshListView();
    }

    @FXML
    void banAcountAdmin(ActionEvent event) throws NamingException {
    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	Logger logger = Logger.getLogger( AdminController.class.getName() );
    	logger.log( Level.FINE, "admin" );
    	proxy.banAcountAdmin(admin.getUserId());
    	this.refreshListView();
    }


    

    
    
    
    @FXML
    void searchAdmin(KeyEvent event) throws NamingException {
    	
    	
    	String word=search_LE.getText();
    	
     	Context context=new InitialContext();
     	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
     
     
     	ObservableList<Administrator> liste = FXCollections.observableArrayList();
     		
  
     		
     	   for ( Administrator a :proxy.searchAdmins(word,Session.admin.getUserId())) {
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
                        setText("user name :"+ c.getUsername()+ "           Last name : "+c.getLastName()+"           first name :"+c.getFirstName()+"           Nationality : "+c.getNationality()+"           Register date: "+c.getInscriptionDate());

                 	   if(c.getValidationLevel()==0)

                 	   {
                            setStyle("-fx-background-color: #F5BCA9");

                 	   }
                 	   else if(c.getValidationLevel()==1)
                 	   {
                            setStyle("-fx-background-color: #F7BE81");

                 	   }
                 	   else if(c.getValidationLevel()==2)
                 	   {
                            setStyle("-fx-background-color: #D0F5A9");

                 	   }
                            
                        
                    }
                }
            });
 
   
    	
    }
    
    

    @FXML
    void validateAdminAccount(ActionEvent event) throws NamingException {

    	Context context=new InitialContext();
    	IUserManagementRemote proxy=(IUserManagementRemote)context.lookup(jndiName);
    	System.out.println(admin);
    	proxy.validateAdmin(admin.getUserId());
    	this.refreshListView();
    	
    }

    @FXML
    void showAdminDetails(ActionEvent event) {
    	
    	usernameUpdate_LE.setText(admin.getUsername());
    	lastnameUpdate_LE.setText(admin.getLastName());
    	firstNameupdate_LE.setText(admin.getFirstName());
    	emailUpdate_LE.setText(admin.getEmail());
    	nationalityUpdate_LE.setText(admin.getNationality());
    	privilegeUpdate_LE.setText(admin.getPrivileges());
    	birthdateUpdate_LE.setText(admin.getBirthDate().toString());
    	registerDateUpdate_LE.setText(admin.getInscriptionDate().toString());
    	
    	
    	
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
