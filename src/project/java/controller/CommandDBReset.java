package project.java.controller;

import project.java.dao.dao.Init;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

public class CommandDBReset extends Action {
    @Override
    Action execute(HttpServletRequest request, HttpServletResponse response) {
        if (FormUtil.isPost(request)) {
            try {
                Init.resetDB();
                request.setAttribute(Message.MESSAGE, "База данных сброшена");
            } catch (SQLException e) {
                request.setAttribute(Message.MESSAGE, "Ошибка сброса базы данных");
            }
            request.getSession().invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return null;
    }
}
