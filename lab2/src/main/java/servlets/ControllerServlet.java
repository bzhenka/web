package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import utils.Errors;

@WebServlet("/Controller")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            if ((request.getParameter("X") != null) && (request.getParameter("Y") != null) && (request.getParameter("R") != null)){
                System.out.println(1);
                request.getRequestDispatcher("/AreaCheck").forward(request, response);
            } else {
                System.out.println(2);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }catch (IOException | ServletException e) {
            Errors.sendError(response, 500, e.toString());
        }
    }
}
