package de.unidue.inf.is.domain;



public class Projekt {

	private int kennung;
	private String titel;
    private String ersteller;
    private float spendenbetrag;
    private float finanzierungslimit;
    private String status;
    private int kategorie;
    private int vorgaenger;
    private String beschreibung;
	
    public Projekt(String titel ,float finanzierungslimit ,int kategorie ,int vorgaenger ,String beschreibung) {
		this.titel = titel;
		this.finanzierungslimit = finanzierungslimit;
		this.beschreibung = beschreibung;
		this.kategorie = kategorie;
		this.vorgaenger = vorgaenger;
    }
    public Projekt(int kennung, String titel, String ersteller, float spendenbetrag, float finanzierungslimit,
			String status, int kategorie, int vorgaenger) {
		super();
		this.kennung = kennung;
		this.titel = titel;
		this.ersteller = ersteller;
		this.spendenbetrag = spendenbetrag;
		this.finanzierungslimit = finanzierungslimit;
		this.status = status;
		this.kategorie = kategorie;
		this.vorgaenger = vorgaenger;
	}

	public Projekt() {
    }

    public Projekt(String titel, String ersteller, 
     float finanzierungslimit, String status ,String beschreibung ,int vorgaenger,float spendenbetrag) 
    {
    	
        this.titel = titel;
        this.ersteller = ersteller;
        this.vorgaenger = vorgaenger;
        this.finanzierungslimit = finanzierungslimit;
        this.status = status;
        this.beschreibung = beschreibung;
        this.spendenbetrag = spendenbetrag;
    }
    public Projekt(int kennung ,String titel, String ersteller,
    		float spendenbetrag,String status, int kategorie) 
    	    {
    	        this.kennung=kennung;
    	        this.kategorie = kategorie;
    	        this.titel = titel;
    	        this.ersteller = ersteller; 
    	        this.spendenbetrag = spendenbetrag;
    	        this.status = status;
    	    }
    
    public Projekt(int kennung ,String titel, String ersteller,String status) 
    	    {
    	       this.kennung=kennung;
    	        this.titel = titel;
    	        this.ersteller = ersteller;        
    	        this.status = status;

    	    }
    public Projekt(String titel,String ersteller,float spendenbetrag, String status) 
   	    {
   	        this.titel = titel;
   	        this.ersteller = ersteller;
   	        this.spendenbetrag = spendenbetrag;
   	        this.status = status;
   	    }

	public int getKennung() {
		return kennung;
	}

	public  void setKennung(int kennung) {
		this.kennung = kennung;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getErsteller() {
		return ersteller;
	}

	public void setErsteller(String ersteller) {
		Benutzer benutzer = new Benutzer();
		ersteller = benutzer.getName();
		this.ersteller = ersteller;
	}

	public float getSpendenbetrag() {
		return spendenbetrag;
	}

	public void setSpendenbetrag(float spendenbetrag) {
		this.spendenbetrag = spendenbetrag;
	}

	public float getFinanzierungslimit() {
		return finanzierungslimit;
	}

	public void setFinanzierungslimit(float finanzierungslimit) {
		this.finanzierungslimit = finanzierungslimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getKategorie() {
		return kategorie;
	}

	public void setKategorie(int kategorie) {
		this.kategorie = kategorie;
	}

	public int getVorgaenger() {
		return vorgaenger;
	}

	public void setVorgaenger(int vorgaenger) {
		this.vorgaenger = vorgaenger;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public Projekt(String titel, String ersteller, String status) {
		super();
		this.titel = titel;
		this.ersteller = ersteller;
		this.status = status;
	}
	public Projekt(String titel2, String ersteller2, float finanzierungslimit2, String status2, String beschreibung2,
			int vorgaenger2, float spendenbetrag2, int kategorie2) {
		// TODO Auto-generated constructor stub
	}
	public Projekt(String titel2, String ersteller2, float finanzierungslimit2, float spendenbetrag2, String status2) {
		// TODO Auto-generated constructor stub
	}
	public Projekt(String titel, String ersteller, float finanzierungslimit, String status, float spendenbetrag) {
		this.titel = titel;
		this.ersteller = ersteller;
		this.finanzierungslimit = finanzierungslimit;
		this.status = status;
	    this.spendenbetrag= spendenbetrag;
	}
	public Projekt(int kennung, String titel, String ersteller, float finanzierungslimit, String status,
			float spendenbetrag) {
        this.kennung = kennung;
		this.titel = titel;
		this.ersteller = ersteller;
		this.finanzierungslimit = finanzierungslimit;
		this.status = status;
	    this.spendenbetrag= spendenbetrag;

		// TODO Auto-generated constructor stub
	}
	public Projekt(int kennung, String titel) {
		// TODO Auto-generated constructor stub
		this.kennung = kennung;
		this.titel = titel;
	}
    
	

}