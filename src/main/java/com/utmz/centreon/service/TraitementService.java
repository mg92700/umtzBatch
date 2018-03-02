package com.utmz.centreon.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.umtz.centreon.gestion.erreur.ErreurCentreon;
import com.utmz.centreon.dao.UserDao;
import com.utmz.centreon.dto.CentreonDto;
import com.utmz.centreon.model.User;

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
		if(ListLoginConnecter().size()>0)
		{
			for(String login:ListLoginConnecter())
			{
				User user = userDao.findByLogin(login);	
				String url="http://174.138.7.116:8080/CWS/api/centreon";
					
				HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
				JsonObjectBuilder jsonMessage = Json.createObjectBuilder();
				JsonObjectBuilder jsonNotif = Json.createObjectBuilder();
				
				ErreurCentreon erreur= getProbleme( login, url);
				
				String totalErreur=null;
				if(erreur.getTypeErreurnbCritical()!=null)
				{
					totalErreur=erreur.getTypeErreurnbCritical()+"/n";
				}
				if(erreur.getTypeErreurnbUnknown()!=null)
				{
					totalErreur=totalErreur+""+erreur.getTypeErreurnbUnknown()+"/n";
				}
				if(erreur.getTypeErreurnbWarning()!=null)
				{
					totalErreur=totalErreur+""+erreur.getTypeErreurnbWarning()+"/n";
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

	public List<String> ListLoginConnecter()
	{
		List<String> lesLogin = new ArrayList<String>();
		for(User u: userDao.findAll() )
		{
			if(u.getTokenPhoneId()!=null)
			{
				lesLogin.add(u.getLogin());
			}
			
		}
		
		return lesLogin;
		
		
		
	}

	
	public ErreurCentreon getProbleme(String login,String url)
	{
		ErreurCentreon probleme= new ErreurCentreon();
		List<CentreonDto> listServers=new ArrayList<>();
		try 
		{
		        JSONObject postDataParams = new JSONObject();
		        postDataParams.put("login", login);
		        URL obj = new URL(url);
		        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		        conn.setDoOutput(true);
		        conn.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
		        conn.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
		        conn.connect();
		
		        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		        wr.writeBytes(postDataParams.toString());
		        wr.flush();
		        wr.close();
		
		        int responseCode = conn.getResponseCode();

	        if (responseCode == HttpsURLConnection.HTTP_OK) 
	        {

	            BufferedReader in = new BufferedReader(new
	                    InputStreamReader(
	                    conn.getInputStream()));
	
	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	
	            while ((line = in.readLine()) != null) {
	
	                sb.append(line);
	                break;
	            }
	            CentreonDto server = null ;
	            JSONArray list = new JSONArray(sb.toString());
	            for(int i = 0 ; i < list.length() ; i++)
	            {
	
	                int id=Integer.parseInt(list.getJSONObject(i).getString("id"));
	                String name= list.getJSONObject(i).getString("name");
	                String adress=list.getJSONObject(i).getString("adress");
	                int statusHost=Integer.parseInt(list.getJSONObject(i).getString("statusHost"));
	                int nbOk=Integer.parseInt(list.getJSONObject(i).getString("nbOk"));
	                int nbWarning=Integer.parseInt( list.getJSONObject(i).getString("nbWarning"));
	                int nbCritical=Integer.parseInt(list.getJSONObject(i).getString("nbCritical"));
	                int nbUnknown=Integer.parseInt(list.getJSONObject(i).getString("nbUnknown"));
	                server = new CentreonDto(id,name,adress,statusHost,nbOk,nbWarning,nbCritical,nbUnknown);
	                
	            }
	            if(server!=null)
	            {
			            probleme.setNomServer(server.getName());
			            
			            	if(server.getNbWarning()>0)
			            {
			        			String warning ="Attention "+server.getNbWarning() + " services sont à vérifier";
			        			probleme.setTypeErreur(warning);
			        	
			            }
			            if(server.getNbCritical()>0)
			            {
			            		String critial="Attention "+server.getNbCritical()+ " services sont dans un etat critique";
			            		probleme.setTypeErreurnbCritical(critial);
			            	
			            }
			            if(server.getNbUnknown()>0)
			            {
			            	String unknown="Attention "+server.getNbCritical()+ " services sont dans un etat critique";
			        		probleme.setTypeErreurnbCritical(unknown);
			            	
			            }
	            }
	            return probleme;
			}
		    else 
		    {
		    	probleme.setTypeErreur("0");
		    	return probleme;
		        	
		    }
	   }
		catch(Exception  e)
	{
	   probleme.setTypeErreur("0");
	   return probleme;
	        	
	}
	        
	}
	
}


