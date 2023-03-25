package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Kommentar {
 private int id;
 private String text;
 private String commentWriter;
 private Timestamp datum;
 private String sichtbarkeit;
	
 
 private static int currentIdCount = 0;

	public static int generateId() {
		// TODO Auto-generated method stub
		return currentIdCount++;
	}
 
 public Kommentar() {
		// TODO Auto-generated constructor stub
	}
	public Kommentar(int id, String text,  String commentWriter ,Timestamp datum, String sichtbarkeit) {
		this.id = id;
		this.text = text;
		this.commentWriter = commentWriter;
		this.datum = datum;
		this.sichtbarkeit = sichtbarkeit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getDatum() {
		return datum;
	}
	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}
	public String getSichtbarkeit() {
		return sichtbarkeit;
	}
	public void setSichtbarkeit(String sichtbarkeit) {
		this.sichtbarkeit = sichtbarkeit;
	}
	public Kommentar(String text, String commentWriter) {
		super();
		this.text = text;
		this.commentWriter = commentWriter;
	}


	

}
