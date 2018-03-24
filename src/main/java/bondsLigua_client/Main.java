package bondsLigua_client;




import javax.naming.NamingException;

import Utils.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


@SuppressWarnings({ "unused", "restriction" })
public class Main extends Application{
	 @Override
	    public void start(Stage stage) throws Exception {
	    	Navigation nav = new Navigation();
	        Parent root = FXMLLoader.load(getClass().getResource(nav.getTest()));
	        Scene scene = new Scene(root);        
	        stage.setTitle("Intellix 2.0 Login interface");
	        stage.setScene(scene);
	        stage.show();
	      
	 }

	public static void main(String[] args) throws NamingException  {
	      launch(args);
	      
	
	}

}
