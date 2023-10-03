package co.edu.cue.jakartaee.controller;

import co.edu.cue.jakartaee.domain.enums.Career;
import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.domain.model.Teacher;
import co.edu.cue.jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import co.edu.cue.jakartaee.repositories.impl.TeacherRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.TeacherService;
import co.edu.cue.jakartaee.services.impl.StudentServiceImpl;
import co.edu.cue.jakartaee.services.impl.TeacherServiceImpl;
import co.edu.cue.jakartaee.utilities.GeneratorAlerts;
import co.edu.cue.jakartaee.validation.NullValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {
    private TeacherRepositoryLogicImpl teacherRepository;
    private TeacherService service;

    public TeacherController() {
        teacherRepository = new TeacherRepositoryLogicImpl();
        service = new TeacherServiceImpl(teacherRepository);
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
        out.println("<h1>Teachers</h1>");
        out.println(service.listar());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        HashMap<String,String> errores=new HashMap<>(GeneratorAlerts.generateMessageTeacher(name,email));
        if(NullValidation.nullCreateStudent(name,email)){
            Teacher teacher = new Teacher( name,email);
            service.guardar(teacher);
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
                out.println("            <li>Email: " + email + "</li>");
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
