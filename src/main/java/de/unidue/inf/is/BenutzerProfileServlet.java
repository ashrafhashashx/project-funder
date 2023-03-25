package de.unidue.inf.is;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.domain.Spenden;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.stores.projektStore;

public class BenutzerProfileServlet extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {	
	List<Projekt> UserProjectsList = new ArrayList<Projekt>();
	List<Projekt> DonatedProjectsList = new ArrayList<Projekt>();
	 
 String email;
 Benutzer benutzer = new Benutzer();
 Spenden spende = new Spenden();
	//projektStore ps = new projektStore();
	UserStore us = new UserStore();
	@SuppressWarnings("resource")
	projektStore store = new projektStore();
	email = request.getParameter("email");
	try {
		int SumOfCreatedProjects = store.SumOfCreatedProjekte(email);
		int SumOfSupporetedProjects = store.SumOfSupportedProjekte(email);
		benutzer = us.getBenutzerProfile(email);
		UserProjectsList = store.getBenutzerProjekte(email);
		DonatedProjectsList = store.getSpedenteProjekte(email);
		String spender = store.GetSpender(email);
		us.complete();
		us.close();
		request.setAttribute("SumOfCreatedProjects", SumOfCreatedProjects);
		request.setAttribute("SumOfSupporetedProjects", SumOfSupporetedProjects);
		request.setAttribute("spender", spender);
		request.setAttribute("Spenden", spende);
		request.setAttribute("UsertProjecstList", UserProjectsList);
		request.setAttribute("DonatedProjectsList", DonatedProjectsList);
		request.setAttribute("email",request.getParameter("email"));
		request.setAttribute("bn", benutzer);  
	    request.getRequestDispatcher("/BenutzerDetails.ftl").forward(request, response);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	doGet(request, response);
}
}