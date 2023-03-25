package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;



public final class UserStore implements Closeable {

    private static Connection connection;
    private boolean complete;
    public UserStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    public void addUser(User userToAdd) throws StoreException {
        try {
            PreparedStatement preparedStatement = connection
                            .prepareStatement("insert into user (firstname, lastname) values (?, ?)");
            preparedStatement.setString(1, userToAdd.getFirstname());
            preparedStatement.setString(2, userToAdd.getLastname());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    public  Benutzer getBenutzerProfile(String remail) throws SQLException, IOException {
    	connection = DBUtil.getExternalConnection();
    	Benutzer benutzer = null;
   	PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM dbp013.benutzer  WHERE email='"+remail+"'");
    	ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()) {
		String email = rs.getString("email");
		String name  = rs.getString("name");
		benutzer = new Benutzer(name, email);
		
	    
	    	
		} 	return benutzer;
			
    }
    

    public void complete() {
        complete = true;
    }

   @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}