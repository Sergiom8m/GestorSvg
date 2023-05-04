package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CreateCollection extends HttpServlet {

    private HTTPeXist exist;

    public CreateCollection() {
        super();

        System.out.println("---> Enter into init() CreateCollection");

        exist = new HTTPeXist("http://localHost:8080");

        System.out.println("---> Exit from init() CreateCollection");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         System.out.println("---> Enter into doGet() CreateCollection");

        String collection = request.getParameter("collection");

        int status = exist.create(collection, "");

        if (status == 201) {
            request.setAttribute("info", "Collection " + collection + " succesfully created");
        } else {
            request.setAttribute("info", "An error occurred. Try again. (Code "+status+")");
        }

        System.out.println("\tRedirecting the user to index.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Exit from doGet() CreateCollection");

    }
}
