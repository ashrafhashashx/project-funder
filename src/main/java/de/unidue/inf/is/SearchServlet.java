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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Projekt> projektList = new ArrayList<Projekt>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stitel;
	projektStore store = new projektStore();
		request.setAttribute("stitel",(request.getParameter("stitel")));
		stitel = (request.getParameter("stitel"));
		System.out.println(stitel);
		try {
			projektList = store.search(stitel);
			System.out.println(projektList+"After search");
			store.complete();
			store.close();
			request.setAttribute("projektList", projektList);
			request.getRequestDispatcher("/search.ftl").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}