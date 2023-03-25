//package de.unidue.inf.is;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import de.unidue.inf.is.domain.Projekt;
//
//
//
///**
// * Einfaches Beispiel, das die Vewendung der Template-Engine zeigt.
// */
//
//public final class ProjektServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    private static List<Projekt> ProjektList = new ArrayList<>();
//
//    // Just prepare static data to display on screen
//    static {
//        ProjektList.add(new Projekt("Bill", "Gates",7000,"offen"));
//       
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Put the Projekt list in request and let freemarker paint it.
//        request.setAttribute("Projekts", ProjektList);
//        //here we get the coniformation of erstellen
//        request.getRequestDispatcher("/ProjektErstellen.ftl").forward(request, response);
//    }
//
//
//   
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
//                    IOException {
//      /*  String titel = request.getParameter("titel");
//        String ersteller = request.getParameter("ersteller");
//        float finanzierungslimit =Integer.parseInt(request.getParameter("finanzierungslimit"));
//        if (null != titel && null != ersteller && !titel.isEmpty() && !ersteller.isEmpty()) {
//            synchronized (ProjektList) {
//            	ProjektList.add(new Projekt(titel, ersteller,finanzierungslimit));
//            }
//        }
//        doGet(request, response);
//       // Projekt projektToAdd = new Projekt("titel","ersteller","finanzierungslimit"); */
//    	 request.getRequestDispatcher("/ProjektErstellen/ProjektCreated.ftl").forward(request, response);
//    }
//    }
