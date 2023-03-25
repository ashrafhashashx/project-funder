/*package de.unidue.inf.is.stores;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.utils.DBUtil;


public class ProjectFunctions {
 
	static Connection db2Conn;
	public static  Projekt getProjektById(int kennung) throws SQLException {
	
		db2Conn = DBUtil.getExternalConnection();
		
        String sql = "SELECT titel ,ersteller ,status FROM dbp013.Projekt WHERE kennung ="+kennung;
        PreparedStatement prst = db2Conn.prepareStatement(sql);
        ResultSet rs =prst.executeQuery();
     
                String titel                  = rs.getString(2);
       	    	String ersteller              = rs.getString(6);
       	    //  float aktuelleSpendenSumme    = rs.getFloat("aktuelleSpendenSumme");
       	    	String status                 = rs.getString(4);
       	   	int    aktuelleSpendenSumme   = rs.getInt("aktuelleSpendenSumme");
       	    	String status                 = rs.getString("status");
       	    	
   Projekt project = new Projekt(kennung ,titel,ersteller ,status);
       	    
       	   
     
             return project;
    }
    
	public static void addProjekt(Projekt projekt) throws SQLException {
		try {
	
	    String sql = "INSERT INTO dbp013.Projekt (titel, finanzierungslimit ,"
	    		+ "kategorie ,vorgaenger ,beschreibung)"
	    		+ " VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement preparedStatement = db2Conn
	            .prepareStatement(sql);
	    preparedStatement.setString(1, projekt.getTitel());
	    preparedStatement.setFloat(2, projekt.getFinanzierungslimit());
	    preparedStatement.setInt(3, projekt.getKategorie());
	    preparedStatement.setInt(4, projekt.getVorgaenger());
	    preparedStatement.setString(5, projekt.getBeschreibung());
	    preparedStatement.executeUpdate();
		 } catch (SQLException e) {
	        throw new StoreException(e);
	    }
	}
    
    public static void deleteProjekt(int kennung) throws SQLException {
   
        String sql = "DELETE From dbp013.Projekt WHERE kennung =" + kennung;
       PreparedStatement preparedStatement = db2Conn
                .prepareStatement(sql);
          preparedStatement.executeUpdate();

    }
    
    public static void updateProjekt(Projekt projekt,int kennung) throws SQLException {
    	
    	 String sql = "UPDATE dbp013.Projekt SET titel=?, finanzirungslimit=? ,kategorie=?"
         		+ "vorgaenger=? , beschreibung=? "
         		+ "WHERE kennung ="+ kennung;
         PreparedStatement preparedStatement = db2Conn.prepareStatement(sql);
         preparedStatement.setString(1,projekt.getTitel());
         preparedStatement.setFloat(2, projekt.getFinanzierungslimit());
         preparedStatement.setInt(3, projekt.getKategorie());
         preparedStatement.setInt(4, projekt.getVorgaenger());
         preparedStatement.setString(5, projekt.getBeschreibung());
         preparedStatement.executeUpdate();
        
    }

    public static List<Projekt> getAllProjekts() throws SQLException {

    	ArrayList<Projekt> projektList = new ArrayList<>();
  	  //  ArrayList<Spenden> spendenList = new ArrayList<>();
  		
  		String allProjects = "select p.titel,p.ersteller,p.status ,s.aktuelleSpendenSumme spendenbetrag  From\n" + 
  				"(select kennung ,titel,ersteller,status FROM dbp013.projekt) p full outer join \n" + 
  				"(SELECT  projekt,sum(spendenbetrag) AS aktuelleSpendenSumme \n" + 
  				"FROM dbp013.spenden GROUP BY projekt) s   on s.projekt=p.kennung";
  		
  	     PreparedStatement prst = db2Conn.prepareStatement(allProjects);
  	    ResultSet rs =prst.executeQuery();
  	    while (rs.next()) {
           
            int kennung                   = rs.getInt("kennung");
  	    	String titel                  = rs.getString("titel");
  	    	String ersteller              = rs.getString("ersteller");
  	    	float aktuelleSpendenSumme    = rs.getFloat("aktuelleSpendenSumme");
  	    	String status                 = rs.getString("status");
  	    	int    aktuelleSpendenSumme   = rs.getInt("aktuelleSpendenSumme");
  	    	String status                 = rs.getString("status");
  	    	
  	     
  	    	Projekt project = new Projekt(kennung ,titel ,ersteller ,aktuelleSpendenSumme ,status);
  	    	// Spenden spende = new Spenden(spendenbetrag);
  	    	projektList.add(project);
  	    	// spendenList.add(spende);
    }
		return projektList;
		
    }
}
    */
  
