package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Benutzer;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.projektStore;

public class viewServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Projekt> projektList = new ArrayList<>();

	protected void doGet(HttpServletRequest request1, HttpServletResponse response1)
			throws ServletException, IOException {
		projektStore store = new projektStore();
		request1.setAttribute("email", request1.getParameter("ersteller"));
		projektList = store.getProjekte();
		store.complete();
		store.close();
		request1.setAttribute("Projekts", projektList);
		request1.setAttribute("loggedInUser", Benutzer.LOGGED_IN_USER);
		request1.getRequestDispatcher("/view_main.ftl").forward(request1, response1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ersteller = request.getParameter("email");
		Projekt projekt = new Projekt();
		projekt.setErsteller(ersteller);
		doGet(request, response);
	}
}
