package de.unidue.inf.is;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Spenden;
import de.unidue.inf.is.stores.StoreException;
import de.unidue.inf.is.stores.projektStore;

public class SpendenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("kennung", request.getParameter("kennung"));
		request.setAttribute("feedback", "warum wollen Sie wieder spenden??!!");
		request.getRequestDispatcher("/ProjektSpende.ftl").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Spenden spende = new Spenden();
		projektStore store = new projektStore();
		int kennung = Integer.parseInt(request.getParameter("kennung"));
		// System.out.print(kennung + "afer showi ");
		spende.setSpendenbetrag(Float.parseFloat(request.getParameter("spendenbetrag")));
		String sichtbarkeit = request.getParameter("sichtbarkeit");

		if (sichtbarkeit != "privat") {
			sichtbarkeit = "oeffentlich";
		}
		spende.setSichtbarkeit(sichtbarkeit);
		try {
			if (store.AddDonation(kennung, spende)) {
				request.setAttribute("feedback", "tahnk youu my fereend!!");
				store.complete();
				store.close();
//				System.out.print(kennung + "before send redirect");
				response.sendRedirect("projektDetails?kennung=" + kennung);
			} else {
				request.setAttribute("feedback", "warum wollen Sie wieder spenden??!!");

				store.complete();
				store.close();
				System.out.print(kennung + "before send redirect");
				response.sendRedirect("projektDetails?kennung=" + kennung);
			}
		} catch (StoreException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}