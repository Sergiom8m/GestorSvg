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

        String collection = request.getParameter("collection");
        String svgName = request.getParameter("svgName");

        int status = exist.subir(collection,svgName);
        if(status==404){

            request.setAttribute("info", "The collection does not exist º   vcºb");

            System.out.println("\tRedirecting the user to index.jsp  ---> CreateCollection status code: "+status);

            RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            rd.forward(request, response);
        }else{
            exist.subirString(collection, "<svg>Enter your svg</svg>", svgName);

            String imagenSVG= exist.read(collection, svgName);

            System.out.println("Servlet- DatosXML " + collection);
            System.out.println("Servlet- DatosXML " + svgName);

            request.setAttribute("collection", collection);
            request.setAttribute("svgName", svgName);
            request.setAttribute("imagenSVG", imagenSVG);

            String imagenURI = "http://localhost:8080/exist/rest/db/" + collection + "/" + svgName + "/";
            request.setAttribute("imagenURI", imagenURI);

            System.out.println("     Redireccionando a imagenEdit.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/imagenEdit.jsp");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            rd.forward(request, response);
        }


        System.out.println("---> Exit from doGet() NewImage");
    }


}
