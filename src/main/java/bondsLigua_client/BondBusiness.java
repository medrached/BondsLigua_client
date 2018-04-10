package bondsLigua_client;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.bondsLiga.bondsLigua_server.persistence.Bond;
import tn.esprit.bondsLiga.bondsLigua_server.services.BondEJBRemote;



public class BondBusiness {
	
	
	public BondBusiness() {
		super();
	}

	private InitialContext ctx = null;
	private final String jndiName = "/bondsLigua_server-ear/bondsLigua_server-ejb/BondEJB!tn.esprit.bondsLiga.bondsLigua_server.services.BondEJBRemote" ;
	private BondEJBRemote proxy;
	
	public void addBond(Bond bond){
		try {
			ctx = new InitialContext();
			proxy = (BondEJBRemote) ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		proxy.addBond(bond);
		
	}
	
	public void updateBond(Bond bond) {
		try {
			ctx = new InitialContext();
			proxy = (BondEJBRemote) ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		proxy.updateBond(bond);
	}
	
	public void deleteBond(int a) {
		try {
			ctx = new InitialContext();
			proxy = (BondEJBRemote) ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		proxy.deleteBond(a);
		
	}
	
	public List<Bond> DisplayAllBond(){
		try {
			ctx = new InitialContext();
			proxy = (BondEJBRemote) ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return proxy.displayAll();
		
	}

}
