package utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Errors {
    public static void sendError(HttpServletResponse response, int status, String errorMessage) throws IOException {
        Gson json = new Gson();
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("error", errorMessage);
        jsonResponse.put("status", "Error");

        response.setContentType("application/json");
        response.getWriter().write(json.toJson(jsonResponse));
        response.setStatus(status);
    }
}
