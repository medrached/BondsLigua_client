package bondsLigua_client;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Utils.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import tn.esprit.bondsLiga.bondsLigua_server.services.IHelloServiceRemote;
import tn.esprit.bondsLiga.bondsLigua_server.services.TodoService;
import tn.esprit.bondsLiga.bondsLigua_server.services.TodoServiceRemote;

public class HelloService extends Application{
	 @Override
	    public void start(Stage stage) throws Exception {
	    	Navigation nav = new Navigation();
	        Parent root = FXMLLoader.load(getClass().getResource(nav.getTest()));
	        Scene scene = new Scene(root);        
	        stage.setTitle("MyJavaFX");
	        stage.setScene(scene);
	        stage.show(); 
	 }

	public static void main(String[] args) throws NamingException  {
	      launch(args);
	/*//jndiProperties.put("jboss.naming.client.ejb.context", true);
	String jndiname= "bondsLigua_server-ear/bondsLigua_server-ejb/HelloService!tn.esprit.bondsLiga.bondsLigua_server.services.IHelloServiceRemote";
	Context context=new InitialContext();
	IHelloServiceRemote proxy=(IHelloServiceRemote)context.lookup(jndiname);
	
	System.out.println(proxy.sayHello("hello"));;
*/
	}

}
