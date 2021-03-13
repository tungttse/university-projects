package io.github.akkhadka.webstore.controller.utility;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class UtilityMethods {
    public static String parseJson(HttpServletRequest req) throws IOException {
        StringBuilder sbUser = new StringBuilder();
        BufferedReader reader = req.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sbUser.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        var json = sbUser.toString();
        return json;
    }
}
