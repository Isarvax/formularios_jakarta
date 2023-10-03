package co.edu.cue.jakartaee.controller;

import co.edu.cue.jakartaee.domain.model.Estudiante;
import co.edu.cue.jakartaee.domain.model.Producto;
import co.edu.cue.jakartaee.services.EstudianteService;
import co.edu.cue.jakartaee.services.LoginService;
import co.edu.cue.jakartaee.services.impl.EstudianteServiceImpl;
import co.edu.cue.jakartaee.services.impl.LoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/students.json", "/students_json"})
public class StudentJson extends HttpServlet {
    private EstudianteService<Estudiante> service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(jsonStream, Producto.class);
        resp.setContentType("text/html;charset=UTF-8");
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Detalle de producto desde json</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Detalle de producto desde json!</h1>");
                out.println("<ul>");
                out.println("<li>Id: "+ producto.getId() + "</li>");
                out.println("<li>Nombre: " + producto.getNombre() + "</li>");
                out.println("<li>Tipo: " + producto.getTipo() + "</li>");
                out.println("<li>Precio: " + producto.getPrecio() + "</li>");
                out.println("</ul>");
                out.println(" </body>");
                out.println("</html>");
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        service=new EstudianteServiceImpl();
        List<Estudiante> estudiantes = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(estudiantes);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}
