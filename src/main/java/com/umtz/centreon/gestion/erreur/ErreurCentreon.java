package com.umtz.centreon.gestion.erreur;

public class ErreurCentreon {
	
	private String nomServer;
	private String typeErreur;
	private String typeErreurnbWarning;
	private String typeErreurnbCritical;
	private String typeErreurnbUnknown;
	public String getNomServer() {
		return nomServer;
	}
	public void setNomServer(String nomServer) {
		this.nomServer = nomServer;
	}
	public String getTypeErreur() {
		return typeErreur;
	}
	public void setTypeErreur(String typeErreur) {
		this.typeErreur = typeErreur;
	}
	public String getTypeErreurnbWarning() {
		return typeErreurnbWarning;
	}
	public void setTypeErreurnbWarning(String typeErreurnbWarning) {
		this.typeErreurnbWarning = typeErreurnbWarning;
	}
	public String getTypeErreurnbCritical() {
		return typeErreurnbCritical;
	}
	public void setTypeErreurnbCritical(String typeErreurnbCritical) {
		this.typeErreurnbCritical = typeErreurnbCritical;
	}
	public String getTypeErreurnbUnknown() {
		return typeErreurnbUnknown;
	}
	public void setTypeErreurnbUnknown(String typeErreurnbUnknown) {
		this.typeErreurnbUnknown = typeErreurnbUnknown;
	}
	@Override
	public String toString() {
		return "ErreurCentreon [nomServer=" + nomServer + ", typeErreur=" + typeErreur + ", typeErreurnbWarning="
				+ typeErreurnbWarning + ", typeErreurnbCritical=" + typeErreurnbCritical + ", typeErreurnbUnknown="
				+ typeErreurnbUnknown + "]";
	}
	public ErreurCentreon(String nomServer, String typeErreur, String typeErreurnbWarning, String typeErreurnbCritical,
			String typeErreurnbUnknown) {
		super();
		this.nomServer = nomServer;
		this.typeErreur = typeErreur;
		this.typeErreurnbWarning = typeErreurnbWarning;
		this.typeErreurnbCritical = typeErreurnbCritical;
		this.typeErreurnbUnknown = typeErreurnbUnknown;
	}
	public ErreurCentreon() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
