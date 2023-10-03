package co.edu.cue.jakartaee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value = "/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// Establece el tipo de contenido de la respuesta HTTP como "text/html".
        resp.setContentType("text/html");

// Obtiene el método HTTP utilizado en la solicitud ya sea GET, POST, etc.
        String metodoHttp = req.getMethod();

// Obtiene la URI (Uniform Resource Identifier) de la solicitud que es la ruta de dominio de la peticion.
        String requestUri = req.getRequestURI();

// Obtiene la URL completa de la solicitud como una cadena.
        String requestUrl = req.getRequestURL().toString();

// Obtiene el contexto del servlet en el servidor web.
        String contexPath = req.getContextPath();

// Obtiene la ruta del servlet en la URL de la solicitud.
        String servletPath = req.getServletPath();

// Obtiene la dirección IP del cliente que realizó la solicitud.
        String ipCliente = req.getRemoteAddr();

// Obtiene la dirección IP del servidor local.
        String ip = req.getLocalAddr();

// Obtiene el puerto en el que el servidor está escuchando las solicitudes.
        int port = req.getLocalPort();

// Obtiene el esquema de la solicitud (generalmente "http" o "https").
        String scheme = req.getScheme();

// Obtiene el valor del encabezado "host" de la solicitud, que representa el nombre de dominio o dirección IP del servidor.
        String host = req.getHeader("host");

// Construye una URL completa utilizando los valores anteriores.
        String url = scheme + "://" + host + contexPath + servletPath;

// Construye otra URL completa que incluye la dirección IP y el puerto del servidor local.
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Cabeceras HTTP Request</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>");
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>"+ cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
