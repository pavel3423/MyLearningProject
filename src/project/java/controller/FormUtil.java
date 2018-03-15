package project.java.controller;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class FormUtil {
    static boolean isPost(HttpServletRequest request) {
        return request.getMethod().toUpperCase().equals("POST");
    }

    static String getString(String string, String regxp) throws ParseException {
        if (string.trim().matches(regxp)) {
            return string;
        } else {
            throw new ParseException("Ошибка проверки выражения", 1);
        }
    }
}
