package co.edu.cue.jakartaee.controller;

import co.edu.cue.jakartaee.domain.enums.Career;
import co.edu.cue.jakartaee.domain.model.Grade;
import co.edu.cue.jakartaee.repositories.impl.GradeRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.GradeService;
import co.edu.cue.jakartaee.services.impl.GradeServiceImpl;
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

@WebServlet(name = "gradeController", value = "/grade-form")
public class GradeController extends HttpServlet {
    private GradeRepositoryLogicImpl gradeRepository;
    private GradeService service;

    public GradeController() {
        gradeRepository = new GradeRepositoryLogicImpl();
        service = new GradeServiceImpl(gradeRepository);
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
        HashMap<String,String> errores=new HashMap<>();
        try {
            String name = req.getParameter("grade");
            errores=GeneratorAlerts.generateMessageGrade(name);
            if(NullValidation.nullCreateGrade(name)){
                Integer gradeNumber = Integer.parseInt(name);
                Grade grade = new Grade(gradeNumber);
                service.guardar(grade);
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
                    out.println("            <li>Grade: " + name + "</li>");
                    out.println("        </ul>");
                    out.println("    </body>");
                    out.println("</html>");
                }
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("errores",errores);
                getServletContext().getRequestDispatcher("/grade.jsp").forward(req,resp);
            }
        }catch (NumberFormatException e){
            errores.put("grade","Grade with String format");
            HttpSession session = req.getSession();
            session.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/grade.jsp").forward(req,resp);
        }


    }

    public void destroy() {
    }
}
