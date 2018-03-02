package com.utmz.centreon.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.umtz.centreon.gestion.erreur.ErreurCentreon;
import com.utmz.centreon.dao.UserDao;
import com.utmz.centreon.model.User;
import com.utmz.centreon.modelApi.centreon.CentreonHostStatus;
import com.utmz.centreon.modelApi.centreon.userDB;

public class TraitementService {


	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	public void EnvoieDonne() throws JSONException, UnsupportedEncodingException 
	{
		
		List<User> listUserLogin= ListLoginConnecter();
		if(listUserLogin.size()>0)
		{
			for(User user:listUserLogin)
			{
				
				String url="http://174.138.7.116:8080/CWS/api/centreon";
					
				HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
				JsonObjectBuilder jsonMessage = Json.createObjectBuilder();
				JsonObjectBuilder jsonNotif = Json.createObjectBuilder();
				
				ErreurCentreon erreur= getProbleme( user.getLogin(), url);;
				
				String totalErreur=null;
				if(erreur.getTypeErreurnbCritical()!=null)
				{
					totalErreur=erreur.getTypeErreurnbCritical()+"\n";
				}
				if(erreur.getTypeErreurnbUnknown()!=null)
				{
					totalErreur=totalErreur+""+erreur.getTypeErreurnbUnknown()+"\n";
				}
				if(erreur.getTypeErreurnbWarning()!=null)
				{
					totalErreur=totalErreur+""+erreur.getTypeErreurnbWarning()+"\n";
				}
				if(totalErreur!=null)
				{	
						jsonNotif.add("body",totalErreur.toString());
						jsonNotif.add("title","Information");
						jsonMessage.add("to", user.getTokenPhoneId());
						String toto="\"fMY_PsxkNmQ:APA91bGZ_ketC24dItzL9bvkNFNfiFaKWuEah15DT8UXaeTx-yZTQHA3l8XWO_97bmPs6SYDj603feLh2F6ENQTcFFRfxHPnvIdEZert26drI1UcUVjHlKjsl53XlrVKfBv7t3bF8Nvd\"";
						jsonMessage.add("notification", jsonNotif);
						
						JsonObject jsonObject = jsonMessage.build();
						
						System.out.println("test  JSON String\n"+jsonObject);
						
						 String value=jsonObject.toString();
						    StringEntity entity;
							try {
								entity = new StringEntity(value);
							
						    
						    HttpPost request = new HttpPost("https://fcm.googleapis.com/fcm/send");
						    request.addHeader("content-type", "application/json");
						    request.addHeader("Authorization","key=AAAA2oJaAnE:APA91bFIe45SndYexkVrDa6O94e-iioCPipbqYO3B-9R5h6LTjE0-zFCLmSSQ6_mHe8w7fzGS_2lf051BuFZVwm8gYIudXUWx4yPY6iyiGCws1NM6sbPmUOWlCliTPc3fEaGUOul5j22");
						    request.setEntity(entity);
						    HttpResponse response = httpClient.execute(request);
						    System.out.println(response.getStatusLine().getStatusCode());
						    if(response.getStatusLine().getStatusCode() != 200) {
						    		throw new RuntimeException("Raté, code HTTP : " + response.getStatusLine().getStatusCode() + "\n"
						    					+ response.toString());
						    }
						    else if (response.getStatusLine().getStatusCode() == 200) {
						    		System.out.println("response : " + EntityUtils.toString(response.getEntity()));
						    }
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
			}
		
		}
	
	}

	public List<User> ListLoginConnecter()
	{
		List<User> lesLogin = new ArrayList<>();

				try {
					com.mashape.unirest.http.HttpResponse<String> response = Unirest.get("http://174.138.7.116:8080/CWS/api/listUsers")
							  .header("Content-Type", "application/json")
							  .header("Cache-Control", "no-cache")
							  .header("Postman-Token", "60a643aa-0779-263b-8471-ef5ebe0912b3")
							  .asString();
					ObjectMapper m1 = new ObjectMapper();
					userDB[] users = null;
						try {
							users = m1.readValue(response.getBody(), userDB[].class);
							User use= new User();
							 for(int i=0; i< users.length; i++) {
								 
									if(users[i].getTokenPhoneId()!=null)
									{
//										use.setId(users[i].getId());
//										use.setIpCentreon(users[i].getIpCentreon());
//										use.setLogin(users[i].getLogin());
//										use.setLoginCentreon(users[i].getLoginCentreon());
//										use.setPassword(users[i].getPassword());
//										use.setPasswordCentreon(users[i].getPasswordCentreon());
//										use.setTokenPhoneId(users[i].getTokenPhoneId());
//										
										lesLogin.add(new User(users[i].getId(),users[i].getLogin(),users[i].getPassword(),
												users[i].getIpCentreon(),users[i].getLoginCentreon(),users[i].getPasswordCentreon(),users[i].getTokenPhoneId()));
									}
								 
							 }
							
							
						} catch (JsonParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				} catch (UnirestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return lesLogin;
	
		
		
		
		
		
	}

	
	public ErreurCentreon getProbleme(String login,String url)
	{

		ErreurCentreon probleme= new ErreurCentreon();
		try {
			com.mashape.unirest.http.HttpResponse<String> response = Unirest.post("http://174.138.7.116:8080/CWS/api/centreon")
					  .header("Content-Type", "application/json")
					  .header("Cache-Control", "no-cache")
					  .header("Postman-Token", "79ffa498-5adc-bcb5-8253-ad8d57785759")
					  .body("{\n\t\"login\":\"admin@ynov.com\"\n\t\n}")
					  .asString();
			ObjectMapper m1 = new ObjectMapper();
			CentreonHostStatus[] hosts = null;
				try {
					hosts = m1.readValue(response.getBody(), CentreonHostStatus[].class);
					
					 if(hosts!=null)
			            {
						 for(int i=0; i< hosts.length; i++) {
					
						           // probleme.setNomServer(hosts[i].getName());
						            
						            	if(hosts[i].getNbWarning()>0)
						            {
						        			String warning ="Attention "+hosts[i].getNbWarning() + " services sont à vérifier";
						        			probleme.setTypeErreur(warning);
						        	
						            }
						            if(hosts[i].getNbCritical()>0)
						            {
						            		String critial="Attention "+hosts[i].getNbCritical()+ " services  dans un etat critique";
						            		probleme.setTypeErreurnbCritical(critial);
						            	
						            }
						            if(hosts[i].getNbUnknown()>0)
						            {
						            	String unknown="Attention "+hosts[i].getNbUnknown()+ " services  dans un etat inconnue";
						        		probleme.setTypeErreurnbCritical(unknown);
						            	
						            }
				            }
					}
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return probleme;
	}
	
}


