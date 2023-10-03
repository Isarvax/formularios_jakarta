package co.edu.cue.jakartaee.controller;
import java.io.*;
import java.util.HashMap;

import co.edu.cue.jakartaee.domain.enums.Career;
import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.StudentService;
import co.edu.cue.jakartaee.services.impl.StudentServiceImpl;
import co.edu.cue.jakartaee.utilities.GeneratorAlerts;
import co.edu.cue.jakartaee.validation.NullValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {

    private StudentRepositoryLogicImpl studentRepository;
    private StudentService service;

    public StudentController() {
        studentRepository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.listar());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String career = req.getParameter("career");
        HashMap<String,String> errores=new HashMap<>(GeneratorAlerts.generateMessageStudent(name,career));
        if(NullValidation.nullCreateStudent(name,career)){
            Student student = new Student( name, Career.fromValue(career));
            service.guardar(student);
            System.out.println(service.listar());

            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Resultado form</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Resultado form!</h1>");

                out.println("        <ul>");
                out.println("            <li>Name: " + name + "</li>");
                out.println("            <li>Career: " + career + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
        }

    }

    public void destroy() {
    }
}