package de.unidue.inf.is;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.projektStore;

public class newProjektServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Projekt> listPre = new ArrayList<Projekt>();
		request.setAttribute("vorgaenger",request.getParameter("vorgaenger"));
		projektStore as = new projektStore();
		try {
			listPre = as.precedeProjects();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			request.setAttribute("PrecedeList",listPre);
			System.out.println("PrecedeList:"+listPre);
		request.setAttribute("email",request.getParameter("email"));
		System.out.print("test"+request.getParameter("email"));
		as.complete();
		as.close();
		
	//	response.sendRedirect("ProjektErstellen");
		request.getRequestDispatcher("/ProjektErstellen.ftl").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		projektStore store = new projektStore();
		Projekt projekt = new Projekt();
		
		projekt.setTitel(request.getParameter("titel"));
		projekt.setFinanzierungslimit(Float.parseFloat(request.getParameter("finanzirungslimit")));
		projekt.setKategorie(Integer.parseInt(request.getParameter("kategorie")));
		Integer vor =Integer.parseInt(request.getParameter("vorgaenger"));
	    
		projekt.setVorgaenger(vor);	
		projekt.setBeschreibung(request.getParameter("beschreibung"));	
		store.addToProjekt(projekt);
		store.complete();
		store.close();
		response.sendRedirect("viewServlet");


	}

	
}

