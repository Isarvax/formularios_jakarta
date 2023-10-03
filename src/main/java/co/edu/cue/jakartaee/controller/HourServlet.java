package co.edu.cue.jakartaee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet({"/hour"})
public class HourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("refresh", "1");
        LocalTime hora = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>La hora actualizada</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>La hora actualizada!</h1>");
            out.println("<h3>"+ hora.format(df) + "</h3>");
            out.println(" </body>");
            out.println("</html>");
        }

    }

    /* /**getRequestDispatcher().forward()*******:

    Forward (Reenvío): Este método realiza un reenvío interno dentro del servidor web. No cambia la URL en el navegador del
    cliente. El cliente no sabe que se ha producido un reenvío en el servidor.

    Redirección Interna: La solicitud se procesa internamente en el servidor y se envía al recurso de destino. La URL en el
    navegador del cliente permanece sin cambios.

    Acceso a Recursos en el Mismo Contexto: Se utiliza cuando deseas acceder a recursos dentro del mismo contexto de la
    aplicación web. La URL relativa ("/students.html") debe ser válida dentro del contexto actual.

    Mantiene los Atributos de la Solicitud: Los atributos de la solicitud (request attributes) pueden compartirse entre el
    recurso actual y el recurso de destino durante el reenvío.

    Flujo de Control Compartido: El flujo de control se comparte entre el recurso actual y el recurso de destino.
    Esto significa que las solicitudes y respuestas son procesadas en serie.


    ************sendRedirect()***************:
    Redirección Externa: Este método envía una respuesta al navegador del cliente con un código de estado HTTP 302
            (Redirección temporal) y una nueva URL. El cliente crea una nueva solicitud a la URL especificada.

    Cambio de URL en el Navegador: El navegador del cliente recibe la nueva URL y realiza una nueva solicitud al servidor
    utilizando esa URL. El cliente es consciente de que se ha producido una redirección.

    Acceso a Recursos en Diferentes Contextos: Se utiliza cuando deseas redirigir a recursos que pueden estar fuera del
    contexto actual de la aplicación web.

    No Mantiene los Atributos de la Solicitud: Los atributos de la solicitud no se comparten entre el recurso actual y
    el recurso de destino durante la redirección. Cada solicitud se considera independiente.

    Flujo de Control Separado: El flujo de control se separa entre la solicitud actual y la nueva solicitud después de
    la redirección. Las solicitudes y respuestas son procesadas de forma independiente*/
}
