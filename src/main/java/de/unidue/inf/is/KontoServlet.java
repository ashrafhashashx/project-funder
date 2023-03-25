package de.unidue.inf.is;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Konto;
import de.unidue.inf.is.stores.KontoStore;



/**
 * Einfaches Beispiel, das die Verwendung des {@link KontoStore}s zeigt.
 */
public final class KontoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // mach was
        Konto KontoToAdd = new Konto("Manfred", "Mustermann");

        try (KontoStore KontoStore = new KontoStore()) {
            KontoStore.addKonto(KontoToAdd);
            // KontoStore.somethingElse();
            KontoStore.complete();
        }

        // mach noch mehr
    }
}
