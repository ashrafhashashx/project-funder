package de.unidue.inf.is;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.unidue.inf.is.domain.Kommentar;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.domain.Spenden;
import de.unidue.inf.is.stores.projektStore;

public class projektDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int kennung;
		List<Spenden> spende = new ArrayList<Spenden>();
		List<Kommentar> comment = new ArrayList<Kommentar>();
		Projekt project = new Projekt();
		projektStore store = new projektStore();
		request.setAttribute("kennung",(request.getParameter("kennung")));
		kennung = Integer.parseInt((request.getParameter("kennung")));
      //  int vor =  Integer.parseInt(request.getParameter("kennung"));
     //   project = as.getPrecedeTitel(vor);
        String PreTitle = null;
		try {
			PreTitle = store.getPrecedeTitel(kennung);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			project = store.getProjektById(kennung);
			comment = store.getKommenatr(kennung);
			spende = store.getDonations(kennung);
			store.complete();
			store.close();
			request.setAttribute("PreTitle", PreTitle);
			request.setAttribute("comments", comment);
			request.setAttribute("spende", spende);
			request.setAttribute("Projekt", project);
			request.setAttribute("kennung", kennung);
			request.getRequestDispatcher("/projektDetails.ftl").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
