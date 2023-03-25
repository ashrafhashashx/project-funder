package de.unidue.inf.is;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.projektStore;

@WebServlet("/projektEditeren")
public class EditProjektServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Projekt> listPre = new ArrayList<Projekt>();
		request.setAttribute("vorgaenger",request.getParameter("vorgaenger"));
		try (projektStore store = new projektStore()) {
			try {
				listPre = store.precedeProjects();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("PrecedeList",listPre);
		request.setAttribute("kennung", request.getParameter("kennung"));
		request.getRequestDispatcher("/projektEditeren.ftl").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int kennung = Integer.parseInt(request.getParameter("kennung"));
		projektStore store = new projektStore();
		Projekt projekt = new Projekt();
		try {
			projekt = store.getProjektById(kennung);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projekt.setTitel(request.getParameter("titel"));
		projekt.setFinanzierungslimit(Float.parseFloat(request.getParameter("finanzierungslimit")));
		projekt.setKategorie(Integer.parseInt(request.getParameter("kategorie")));
		projekt.setVorgaenger(Integer.parseInt(request.getParameter("vorgaenger")));
		projekt.setBeschreibung(request.getParameter("beschreibung"));

		try {
			store.updateProjekt(projekt, kennung);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		store.complete();

		try {
			store.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("projektDetails?kennung=" + kennung);

	}
}