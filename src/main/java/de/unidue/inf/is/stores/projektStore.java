package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.Kommentar;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.domain.Spenden;
import de.unidue.inf.is.utils.DBUtil;

public final class projektStore implements Closeable {
	private static Connection connection;
	private boolean complete;

	public projektStore() throws StoreException {
		try {
			connection = DBUtil.getExternalConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}

	public String getTitel(int kennung) throws SQLException {
		String sql = "SELECT titel FROM dbp013.projekt WHERE kennung=?";
		String titel = "";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, kennung);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			titel = rs.getString("titel");
		}
		return titel;
	}

	public String getPrecedeTitel(int kennung) throws SQLException {
		String sql = "SELECT a.titel ,b.kategorie FROM dbp013.projekt a,dbp013.projekt b WHERE a.kennung = b.vorgaenger AND a.ersteller"
				+ "=b.ersteller AND a.ersteller = ? AND b.kennung=?";
		String titel = "Kein vorgänger";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, Benutzer.LOGGED_IN_USER);
		preparedStatement.setInt(2, kennung);

		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			titel = rs.getString("titel");
		}
		return titel;
	}

	public Projekt getProjektById(int kennung) throws SQLException {
		String sql = "SELECT p.titel,p.ersteller,p.status ,p.finanzierungslimit ,"
				+ "p.beschreibung ,p.vorgaenger,p.kategorie ,s.aktuelleSpendenSumme spendenbetrag FROM"
				+ "(SELECT kennung ,titel ,ersteller ,status ,finanzierungslimit ,beschreibung ,vorgaenger,kategorie  "
				+ "FROM dbp013.projekt WHERE kennung = ? ) p LEFT JOIN "
				+ "(SELECT  projekt,sum(spendenbetrag) AS aktuelleSpendenSumme "
				+ "FROM dbp013.spenden GROUP BY projekt) s   ON s.projekt=p.kennung";
		Projekt project = null;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, kennung);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			String beschreibung = rs.getString("beschreibung");
			String titel = rs.getString("titel");
			String ersteller = rs.getString("ersteller");
			String status = rs.getString("status");
			float spendenbetrag = rs.getFloat("spendenbetrag");
			float finanzierungslimit = rs.getFloat("finanzierungslimit");
			int kategorie = rs.getInt("kategorie");
			int vorgaenger = rs.getInt("vorgaenger");
			project = new Projekt(titel, ersteller, finanzierungslimit, status, beschreibung, vorgaenger,
					spendenbetrag);
			project.setKategorie(kategorie);
		}
		return project;
	}

	public ArrayList<Projekt> getProjekte() {
		ArrayList<Projekt> list = new ArrayList<Projekt>();
		try {
			String sql = "SELECT p.kennung ,p.titel,p.ersteller,p.kategorie,p.status AS status ,s.aktuelleSpendenSumme spendenbetrag  FROM \n"
					+ "(SELECT kennung ,titel ,ersteller,kategorie ,status FROM dbp013.projekt) p FULL OUTER JOIN \n"
					+ "(SELECT  projekt ,SUM(spendenbetrag) AS aktuelleSpendenSumme \n"
					+ "FROM dbp013.spenden GROUP BY projekt) s   ON s.projekt=p.kennung";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String status = rs.getString("status");
				int kennung = rs.getInt("kennung");
				String titel = rs.getString("titel");
				int kategorie = rs.getInt("kategorie");
				String ersteller = rs.getString("ersteller");
				float spendenbetrag = rs.getFloat("spendenbetrag");
				Projekt project = new Projekt(kennung, titel, ersteller, spendenbetrag, status, kategorie);
				list.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//to add donation:
	public boolean AddDonation(int kennung, Spenden spende) throws StoreException, SQLException {
		String sql = "INSERT INTO dbp013.spenden (spender, projekt, spendenbetrag, sichtbarkeit) VALUES (? ,?, ?, ?)";
		String selectsql = "SELECT * FROM dbp013.spenden  WHERE spender=? and projekt = ?";
		try {
			PreparedStatement preparedStatement1 = connection.prepareStatement(selectsql);
			preparedStatement1.setString(1, Benutzer.LOGGED_IN_USER);
			preparedStatement1.setInt(2, kennung);
			ResultSet st = preparedStatement1.executeQuery();
			boolean alreadydonated = st.next();
			if (!alreadydonated) {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, Benutzer.LOGGED_IN_USER);
				preparedStatement.setInt(2, kennung);
				preparedStatement.setFloat(3, spende.getSpendenbetrag());
				preparedStatement.setString(4, spende.getSichtbarkeit());
				preparedStatement.executeUpdate();
				return true;
			} else
				return false;
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}
// to add new comment to the project details page
	public void AddNewComment(int kennung, Kommentar comment) throws StoreException, SQLException {
		int commentId = 0;

		ResultSet rs = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into dbp013.kommentar (text,sichtbarkeit) values (? ,?)");
			preparedStatement.setString(2, comment.getSichtbarkeit());
			preparedStatement.setString(1, comment.getText());
			preparedStatement.executeUpdate();
			preparedStatement = connection
					.prepareStatement("select id from dbp013.kommentar order by id desc fetch first row only");
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				commentId = rs.getInt("id");
			}
			preparedStatement = connection
					.prepareStatement("insert into dbp013.schreibt (benutzer ,projekt ,kommentar) values (?,?,?)");
			preparedStatement.setString(1, Benutzer.LOGGED_IN_USER);
			preparedStatement.setInt(2, kennung);
			preparedStatement.setInt(3, commentId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}

	// to show the list of donations:

	public List<Spenden> getDonations(int kennung) throws StoreException, SQLException {
		List<Spenden> spende = new ArrayList<Spenden>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(
					"SELECT spender, projekt, spendenbetrag, sichtbarkeit from dbp013.spenden where projekt =?");
			stmt.setInt(1, kennung);
			rs = stmt.executeQuery();
			while (rs.next()) {

				int projekt = rs.getInt("projekt");
				String spender = rs.getString("spender");
				float spendenbetrag = rs.getFloat("spendenbetrag");
				String sichtbarkeit = rs.getString("sichtbarkeit");
				Spenden spenden = new Spenden(spender, projekt, spendenbetrag, sichtbarkeit);
				spende.add(spenden);
			}
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
		}
		return spende;
	}

	// for the showing the comments
	public List<Kommentar> getKommenatr(int kennung) throws StoreException, SQLException {
		List<Kommentar> kommentar = new ArrayList<Kommentar>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(
					"select s.benutzer ,k.* from dbp013.kommentar k,dbp013.schreibt s where k.id = s.kommentar AND s.projekt = ?");
			stmt.setInt(1, kennung);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString("text");
				String benutzer = rs.getString("benutzer");
				Kommentar k = new Kommentar(text, benutzer);
				kommentar.add(k);
			}
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
		}
		return kommentar;
	}
	// to edit selected Project we use:
	public void updateProjekt(Projekt projekt, int kennung) throws SQLException {
		try {
			String sql = "UPDATE dbp013.Projekt SET titel=?, finanzierungslimit=? ,kategorie=?,"
					+ "vorgaenger=? , beschreibung=? " + "WHERE kennung =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, projekt.getTitel());
			preparedStatement.setFloat(2, projekt.getFinanzierungslimit());
			preparedStatement.setInt(3, projekt.getKategorie());
			preparedStatement.setInt(4, projekt.getVorgaenger());
			preparedStatement.setString(5, projekt.getBeschreibung());
			preparedStatement.setInt(6, kennung);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}
	// to get the precede project as
	public ArrayList<Projekt> precedeProjects() throws SQLException {
		String sql = "SELECT titel,kennung FROM dbp013.projekt WHERE ersteller=?";
		ArrayList<Projekt> preccedeList = new ArrayList<Projekt>();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, Benutzer.LOGGED_IN_USER);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String precedeTitel = rs.getString("titel");
			int kennung = rs.getInt("kennung");
			Projekt precedeProjects = new Projekt(kennung, precedeTitel);
			preccedeList.add(precedeProjects);
		}
		return preccedeList;
	}

// to create new project
	public void addToProjekt(Projekt ProjektToAdd) throws StoreException {
		try {
			String sql = "INSERT INTO dbp013.projekt (titel, beschreibung, finanzierungslimit, ersteller, vorgaenger, kategorie)  VALUES\n"
					+ "(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ProjektToAdd.getTitel());
			preparedStatement.setString(2, ProjektToAdd.getBeschreibung());
			preparedStatement.setFloat(3, ProjektToAdd.getFinanzierungslimit());
			preparedStatement.setString(4, Benutzer.LOGGED_IN_USER);
			preparedStatement.setInt(5, ProjektToAdd.getVorgaenger());
			preparedStatement.setInt(6, ProjektToAdd.getKategorie());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}
	// to delete selected project we use
	public void deleteProjekt(int kennung) throws SQLException {
		String sql = "DELETE From dbp013.Projekt WHERE kennung = ? AND ersteller=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, kennung);
		preparedStatement.setString(2, Benutzer.LOGGED_IN_USER);
		preparedStatement.executeUpdate();
	}
	// to show the Projects of each User which he supported/Created:
	public List<Projekt> getBenutzerProjekte(String email) throws StoreException, SQLException {
		List<Projekt> ProjectList = new ArrayList<Projekt>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = connection.prepareStatement("SELECT titel,ersteller,status,finanzierungslimit,kennung,kategorie "
				+ "FROM dbp013.projekt WHERE ersteller = ?");
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		while (rs.next()) {
			String titel = rs.getString("titel");
			String ersteller = rs.getString("ersteller");
			String status = rs.getString("status");
			float finanzierungslimit = rs.getFloat("finanzierungslimit");
			int kennung = rs.getInt("kennung");
			int kategorie = rs.getInt("kategorie");
			Projekt projekt = new Projekt(titel, ersteller, status);
			projekt.setKennung(kennung);
			projekt.setFinanzierungslimit(finanzierungslimit);
			projekt.setKategorie(kategorie);
			ProjectList.add(projekt);
		}
		return ProjectList;
	}
	// to get the sum of created projects in profile page
	public int SumOfCreatedProjekte(String email) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int SumOfProject = 0;
		stmt = connection
				.prepareStatement("SELECT  COUNT(p.kennung) AS SumOfProjects FROM dbp013.projekt p WHERE ersteller= ?");
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		if (rs.next()) {
			SumOfProject = rs.getInt("SumOfProjects");
		}
		return SumOfProject;
	}

	// to get the sum of supported projects which the user donated for in profile
	// page
	public int SumOfSupportedProjekte(String email) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int SumOfProject = 0;
		stmt = connection.prepareStatement(
				"SELECT  COUNT(p.kennung) AS SumOfProjects FROM dbp013.spenden s, dbp013.projekt p WHERE "
						+ "s.projekt =p.kennung AND s.spender= ?");
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		if (rs.next()) {
			SumOfProject = rs.getInt("SumOfProjects");
		}
		return SumOfProject;
	}

// for search 
	public ArrayList<Projekt> search(String stitel) throws SQLException {
		ArrayList<Projekt> listProject = new ArrayList<>();
		String sql = "SELECT p.titel,p.ersteller,p.status ,p.finanzierungslimit ,"
				+ "p.beschreibung ,p.vorgaenger ,s.aktuelleSpendenSumme spendenbetrag FROM"
				+ "(SELECT kennung ,titel ,ersteller ,status ,finanzierungslimit ,beschreibung ,vorgaenger "
				+ "FROM dbp013.projekt WHERE titel LIKE '%?%' ) p LEFT JOIN "
				+ "(SELECT  projekt,sum(spendenbetrag) AS aktuelleSpendenSumme "
				+ "FROM dbp013.spenden GROUP BY projekt) s   ON s.projekt=p.kennung";
		PreparedStatement prst = connection.prepareStatement(sql);
		prst.setString(1, stitel);
		ResultSet rs = prst.executeQuery();
		while (rs.next()) {
			String beschreibung = rs.getString("beschreibung");
			String titel = rs.getString("titel");
			String ersteller = rs.getString("ersteller");
			String status = rs.getString("status");
			System.out.print(titel + " creator:" + ersteller);
			;
			// float spendenbetrag = rs.getFloat("spendenbetrag");
			float finanzierungslimit = rs.getFloat("finanzierungslimit");
			int vorgaenger = rs.getInt("vorgaenger");
			int kategorie = rs.getInt("kategorie");
			Projekt project = new Projekt(titel, ersteller, finanzierungslimit, status, beschreibung, vorgaenger,
					kategorie);
			listProject.add(project);
		}
		return listProject;
	}

	// to get projects whichh user has donated to
	public List<Projekt> getSpedenteProjekte(String email) throws StoreException, SQLException {
		List<Projekt> ProjectList = new ArrayList<Projekt>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = connection.prepareStatement("SELECT  s.* ,p.* FROM dbp013.spenden s, dbp013.projekt p WHERE "
				+ "s.projekt =p.kennung AND s.spender= ?");
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		while (rs.next()) {
			int kennung = rs.getInt("kennung");
			int kategorie = rs.getInt("kategorie");
			String titel = rs.getString("titel");
			float finanzierungslimit = rs.getFloat("finanzierungslimit");
			float spendenbetrag = rs.getFloat("spendenbetrag");
			String status = rs.getString("status");
			String ersteller = rs.getString("ersteller");

			Projekt projekt = new Projekt(kennung, titel, ersteller, finanzierungslimit, status, spendenbetrag);
			projekt.setSpendenbetrag(spendenbetrag);
			projekt.setKategorie(kategorie);
			ProjectList.add(projekt);
		}

		return ProjectList;
	}

	public String GetSpender(String spender) throws SQLException {
		String sql = "SELECT spender from dbp013.spenden WHERE spender = ?";
		PreparedStatement prst = connection.prepareStatement(sql);
		prst.setString(1, spender);
		prst.executeQuery();
		ResultSet rs = prst.executeQuery();
		if (rs.next()) {
			String spenderEmail = rs.getString("spender");
			return spenderEmail;
		}
		return "sorry";
	}

	public List<Spenden> GetSpendenBetrag(String spender, int kennung) throws SQLException {
		List<Spenden> spendeList = new ArrayList<Spenden>();
		String sql = "SELECT spendenbetrag from dbp013.spenden WHERE spender = ? ,kennung=?";
		PreparedStatement prst = connection.prepareStatement(sql);
		prst.setString(1, spender);
		prst.setInt(2, kennung);
		prst.executeQuery();
		ResultSet rs = prst.executeQuery();
		while (rs.next()) {
			Float spendenbetrag = rs.getFloat("spendenbetrag");
			Spenden spende = new Spenden(spender, spendenbetrag);
			spendeList = add(spende);
		}
		return spendeList;
	}
	// to show the list of all projects

	private List<Spenden> add(Spenden spende) {
		// TODO Auto-generated method stub
		return null;
	}

	public void viewProjekte(String titel, String ersteller, int finanzirungslimit) {
		// TODO Auto-generated method stub

	}

	public void complete() {
		complete = true;
	}
// to avoid auto commit :
	public void close() throws IOException {
		if (connection != null) {
			try {
				if (complete) {
					connection.commit();
				} else {
					connection.rollback();
				}
			} catch (SQLException e) {
				throw new StoreException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new StoreException(e);
				}
			}
		}
	}

}
