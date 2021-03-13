package io.github.akkhadka.webstore.controller.admin.api;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseJsonHelper {

    public static void responseByJson(HttpServletResponse resp, Object obj) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        PrintWriter out = resp.getWriter();
        out.print(json);
    }

}
