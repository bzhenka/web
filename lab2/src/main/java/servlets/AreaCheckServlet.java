package servlets;

import beans.Result;
import beans.ResultsList;
import com.google.gson.Gson;
import utils.AreaChecker;
import utils.CoordinatesValidator;
import utils.Errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/AreaCheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = time.format(formatter);

        double x, y, r;
        try {
            x = Double.parseDouble(request.getParameter("X"));
            y = Double.parseDouble(request.getParameter("Y"));
            r = Double.parseDouble(request.getParameter("R"));
            CoordinatesValidator validator = new CoordinatesValidator(x, y, r);

            if (!validator.checkData()){
                System.out.println("Значение не правильное");
                Errors.sendError(response, 422, "Значения не прошли проверку");
                return;
            }
            ResultsList list = (ResultsList) request.getSession().getAttribute("results");
            if (list == null){
                list = new ResultsList();
                getServletContext().setAttribute("results", list);
            }

            long duration = Duration.between(time, LocalDateTime.now()).toMillis();

            Result result = new Result(x, y, r, AreaChecker.isInArea(x, y, r), formattedDateTime, duration);
            list.addResult(result);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(result));
        } catch (IOException e) {
            Errors.sendError(response, 500, "Внутренняя ошибка сервера");
        }
    }
}
