package com.utmz.centreon.modelApi.centreon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CentreonHostStatus {

	private int id;
	private String name;
	private String adress;
	private int statusHost;
	private int nbOk;
	private int nbWarning;
	private int nbCritical;
	private int nbUnknown;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getStatusHost() {
		return statusHost;
	}
	public void setStatusHost(int statusHost) {
		this.statusHost = statusHost;
	}
	public int getNbOk() {
		return nbOk;
	}
	public void setNbOk(int nbOk) {
		this.nbOk = nbOk;
	}
	public int getNbWarning() {
		return nbWarning;
	}
	public void setNbWarning(int nbWarning) {
		this.nbWarning = nbWarning;
	}
	public int getNbCritical() {
		return nbCritical;
	}
	public void setNbCritical(int nbCritical) {
		this.nbCritical = nbCritical;
	}
	public int getNbUnknown() {
		return nbUnknown;
	}
	public void setNbUnknown(int nbUnknown) {
		this.nbUnknown = nbUnknown;
	}
	
	

	
	
	
}
