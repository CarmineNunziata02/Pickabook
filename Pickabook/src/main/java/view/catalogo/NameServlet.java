package view.catalogo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import catalogoManagement.ProdottoIDS;
import com.google.gson.Gson;

@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        Gson json = new Gson();


        try(PrintWriter out = response.getWriter()) {
            ProdottoIDS prodottoIDS = new ProdottoIDS(ds);
            ArrayList<String> nomi = prodottoIDS.doRetrieveAllProductsName();
            out.write(json.toJson(nomi));
        }catch (Exception e){
            logger.log(Level.ALL , ERROR , e);
        }

    }


    private static final Logger logger = Logger.getLogger(NameServlet.class.getName());
    private static final String ERROR = "Errore";

}
