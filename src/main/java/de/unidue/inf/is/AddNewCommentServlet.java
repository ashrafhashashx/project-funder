package de.unidue.inf.is;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.unidue.inf.is.domain.Kommentar;
import de.unidue.inf.is.domain.Projekt;
import de.unidue.inf.is.stores.StoreException;
import de.unidue.inf.is.stores.projektStore;

public class AddNewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("kennung", request.getParameter("kennung"));
		request.setAttribute("titel", request.getParameter("titel"));
		request.getRequestDispatcher("/AddNewComment.ftl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Kommentar comment = new Kommentar();
		projektStore ps = new projektStore();
		request.setAttribute("kennung", request.getParameter("kennung"));
		request.setAttribute("titel", request.getParameter("titel"));
		Projekt project = new Projekt();
		project.setTitel(request.getParameter("titel"));
		int kennung = Integer.parseInt(request.getParameter("kennung"));
		comment.setText(request.getParameter("text"));

		String sichtbarkeit = request.getParameter("sichtbarkeit");
		if (sichtbarkeit != "privat") {
			sichtbarkeit = "oeffentlich";
		}
		comment.setSichtbarkeit(sichtbarkeit);
		try {
			ps.AddNewComment(kennung, comment);
		} catch (StoreException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps.complete();
		ps.close();
		response.sendRedirect("projektDetails?kennung=" + kennung);

	}
}
