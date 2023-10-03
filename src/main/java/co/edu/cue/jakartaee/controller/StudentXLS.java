package co.edu.cue.jakartaee.controller;

import co.edu.cue.jakartaee.domain.model.StudentDto;
import co.edu.cue.jakartaee.services.EstudianteService;
import co.edu.cue.jakartaee.services.LoginService;
import co.edu.cue.jakartaee.services.impl.EstudianteDTOServiceImpl;
import co.edu.cue.jakartaee.services.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/students.xls", "/students.html", "/students"})
public class StudentXLS extends HttpServlet {
    private EstudianteService<StudentDto> service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service= new EstudianteDTOServiceImpl();
        List<StudentDto> students = service.listar();
        resp.setContentType("text/html;charset=UTF-8");
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            String servletPath = req.getServletPath();
            boolean esXls = servletPath.endsWith(".xls");
            if (esXls) {
                resp.setContentType("application/vnd.ms-excel");
                resp.setHeader("Content-Disposition", "attachment;filename=students.xls");
            }
            try (PrintWriter out = resp.getWriter()) {
                if(!esXls) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <head>");
                    out.println(" <meta charset=\"UTF-8\">");
                    out.println(" <title>Listado de Estudiantes</title>");
                    out.println(" </head>");
                    out.println(" <body>");
                    out.println(" <h1>Listado de Estudiantes!</h1>");
                    out.println("<p><a href=\"" + req.getContextPath() + "/students.xls" + "\">exportar a xls</a></p>");
                }
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>id</th>");
                out.println("<th>nombre</th>");
                out.println("<th>email</th>");
                out.println("<th>semestre</th>");
                out.println("</tr>");
                students.forEach(p ->{
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getName() + "</td>");
                    out.println("<td>" + p.getEmail() + "</td>");
                    out.println("<td>" + p.getSemester() + "</td>");
                    out.println("</tr>");
                });
                out.println("</table>");
                if(!esXls) {
                    out.println(" </body>");
                    out.println("</html>");
                }
            }
        }
    }
}
