package de.unidue.inf.is;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.stores.projektStore;

public class DeleteProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteProjectServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
    int kennung = Integer.parseInt((request.getParameter("kennung")));
		
    projektStore store = new projektStore();
		
		try {
			store.deleteProjekt(kennung);
			store.complete();
			store.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/viewServlet");
	}

	
}
