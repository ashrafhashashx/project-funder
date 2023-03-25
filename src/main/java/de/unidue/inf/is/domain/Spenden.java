package de.unidue.inf.is.domain;

public final class Spenden {

	private String spender;
	private int projekt;
	private float spendenbetrag;
	private String sichtbarkeit;
	public Spenden() {
		// TODO Auto-generated constructor stub
	}
	public Spenden(String spender, int projekt, float spendenbetrag, 
			String sichtbarkeit) {
		super();
		this.spender = spender;
		this.projekt = projekt;
		this.spendenbetrag = spendenbetrag;
		this.sichtbarkeit = sichtbarkeit;
	}
	public Spenden(float spendenbetrag) {
		super();
		
		this.spendenbetrag = spendenbetrag;
	}
	public Spenden(String spender, float spendenbetrag) {
		this.spender = spender;
		this.spendenbetrag = spendenbetrag;
		// TODO Auto-generated constructor stub
	}
	public String getSpender() {
		return spender;
	}
	public void setSpender(String spender) {
		this.spender = spender;
	}
	public int getProjekt() {
		return projekt;
	}
	public void setProjekt(int projekt) {
		this.projekt = projekt;
	}
	public float getSpendenbetrag() {
		return spendenbetrag;
	}
	public void setSpendenbetrag(float spendenbetrag) {
		this.spendenbetrag = spendenbetrag;
	}
	public String getSichtbarkeit() {
		return sichtbarkeit;
	}
	public void setSichtbarkeit(String sichtbarkeit) {
		this.sichtbarkeit = sichtbarkeit;
	}

}
