package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class NewImage extends HttpServlet {

     private HTTPeXist exist;

    public NewImage(){
        super();
        System.out.println("---> Enter into init() NewImage");

        exist = new HTTPeXist("http://localHost:8080");

        System.out.println("---> Exit from init() NewImage");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("---> Enter into doGet() NewImage");

        String svgName = request.getParameter("svgName");
        String collection = request.getParameter("collection");

        exist.create(collection, svgName);
        exist.subirString(collection, "<svg>Write here the image's code</svg>", svgName);

        String irudiSVG= exist.read(collection, svgName);

        request.setAttribute("collection", collection);
        request.setAttribute("svgName", svgName);
        request.setAttribute("imagenSVG", irudiSVG);
        String irudiURI = "http://localhost:8080/exist/rest/db/" + collection + "/" + svgName + "/";
        request.setAttribute("imagenURI", irudiURI);

        System.out.println("\tRedirecting the user to imagenEdit.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/imagenEdit.jsp");
        rd.forward(request, response);

        System.out.println("---> Exit from doGet() NewImage");
    }


}
