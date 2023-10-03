package co.edu.cue.jakartaee.controller;

import co.edu.cue.jakartaee.domain.model.Subject;
import co.edu.cue.jakartaee.repositories.impl.SubjectRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.SubjectService;
import co.edu.cue.jakartaee.services.impl.SubjectServiceImpl;
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

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    private SubjectRepositoryLogicImpl subjectRepository;
    private SubjectService service;

    public SubjectController() {
        subjectRepository = new SubjectRepositoryLogicImpl();
        service = new SubjectServiceImpl(subjectRepository);
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
        String name = req.getParameter("subject");
        HashMap<String,String> errores=new HashMap<>(GeneratorAlerts.generateMessageSubject(name));
        if(NullValidation.nullCreateSubject(name)){
            Subject subject = new Subject( name);
            service.guardar(subject);
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
                out.println("            <li>Subject: " + name + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/grade.jsp").forward(req,resp);
        }

    }

    public void destroy() {
    }
}
