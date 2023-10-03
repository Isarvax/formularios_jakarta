package co.edu.cue.jakartaee.controller;


import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.domain.model.Subject;
import co.edu.cue.jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import co.edu.cue.jakartaee.repositories.impl.SubjectRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.LoginService;
import co.edu.cue.jakartaee.services.SubjectService;
import co.edu.cue.jakartaee.services.impl.LoginServiceImpl;
import co.edu.cue.jakartaee.services.impl.StudentServiceImpl;
import co.edu.cue.jakartaee.services.impl.SubjectServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet({"/search_subject"})
public class SubjectServlet extends HttpServlet {
    private SubjectService service;
    private SubjectRepositoryLogicImpl repository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        repository =new SubjectRepositoryLogicImpl();
        service= new SubjectServiceImpl(repository);
        List<Subject> estudiantes = service.listar();
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            String id = req.getParameter("id");
            List<Subject> students1= estudiantes.stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
            if (students1.size() == 1) {
                Subject estudiante =students1.get(0);
                resp.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <head>");
                    out.println(" <meta charset=\"UTF-8\">");
                    out.println(" <title>Detalle de materias en busqueda</title>");
                    out.println(" </head>");
                    out.println(" <body>");
                    out.println(" <h1>Detalle de materia</h1>");
                    out.println("<ul>");
                    out.println("<li>Id: "+ estudiante.getId() + "</li>");
                    out.println("<li>Nombre: " + estudiante.getName() + "</li>");
                    out.println("</ul>");
                    out.println(" </body>");
                    out.println("</html>");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lo sentimos no existe el estudiante");
            }
        }
    }
}
