package com.utmz.centreon.batch;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;


import com.utmz.centreon.service.TraitementService;



public class main {
	
	
	public main() throws UnsupportedEncodingException, JSONException {
		  
	      try {
	    	  while(true) {
	    	  TraitementService t=new TraitementService();
	    	  //t.EnvoieDonne();
	    	  t.EnvoieDonne();
	        // et faire une pause
	        Thread.sleep(5000);
	        }
	      }
	      catch (InterruptedException ex) {}
	    
	  }

	public static void main(String[] args) throws UnsupportedEncodingException, JSONException {
		// TODO Auto-generated method stub

		new main();
		
		
	}

}
