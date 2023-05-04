package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteSvg extends HttpServlet {

    private HTTPeXist exist;

    public DeleteSvg() {
        super();

        System.out.println("---> Enter into init() DeleteSvg");

        exist = new HTTPeXist("http://localHost:8080");

        System.out.println("---> Exit from init() DeleteSvg");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("---> Entering doGet() DeleteSvg");

        String collection = request.getParameter("collection");
        String resourceName = request.getParameter("svgName");
        int status = exist.delete(collection, resourceName);

        if (status == 200) {
            request.setAttribute("info", resourceName+" has been succesfully deleted from " + collection);
        } else {
            request.setAttribute("info", "An error occurred. Try again. (Code "+status+")");
        }

        System.out.println("\tRedirecting the user to index.jsp  ---> DeleteSvg status code: "+status);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Exit from doGet() DeleteSvg");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }
}
