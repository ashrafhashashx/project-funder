package de.unidue.inf.is.domain;

public final class Konto{

	private String email;
    private String geheimzahl;

    public Konto() {
    }

	public Konto(String email,String geheimzahl) {
        this.email = email;
        this.geheimzahl =geheimzahl;

    }


    public String getEmail() {
        return email;
    }
    public String getGeheimzahl() {
        return geheimzahl;
    }


}