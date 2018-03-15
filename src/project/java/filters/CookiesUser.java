package project.java.filters;


import project.java.dao.dao.DAO;
import project.java.entity.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CookiesUser {

    public static void getSession(HttpServletRequest request) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IOException, SQLException, IllegalBlockSizeException, InvalidKeyException {
        HttpSession session = request.getSession();
        Object o = session.getAttribute("user");
        if (o != null) {
            session.setAttribute("user", (User) o);
        } else {
            User user = CookiesUser.getCookie(request);
            if (user != null) {
                session.setAttribute("user", user);
            }
        }
    }

    public static void setCookie(HttpServletResponse response, User user) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        DesEncrypter encrypter = DesEncrypter.getInstance();
        List<Cookie> cookies = new ArrayList<>();
        cookies.add(new Cookie("login", user.getLogin()));
        cookies.add(new Cookie("password", encrypter.encrypt(user.getPassword())));
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            cookie.setMaxAge(3600 * 60 * 60);
            response.addCookie(cookie);
        }
    }

    public static User getCookie(HttpServletRequest request) throws SQLException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IOException, IllegalBlockSizeException {
        DesEncrypter encrypter = DesEncrypter.getInstance();
        Cookie[] myCookies = request.getCookies();
        String cookieLogin = "";
        String cookiePassword = "";
        for (int i = 0; i < myCookies.length; i++) {
            Cookie cookie = myCookies[i];
            if ("login".equals(cookie.getName())) {
                cookieLogin = cookie.getValue();
            }
            if ("password".equals(cookie.getName())) {
                cookiePassword = encrypter.decrypt(cookie.getValue());
            }
        }
        if (cookieLogin.equals("")) {
            return null;
        }
        List<User> users = DAO.getDAO().user.getAll(String.format("WHERE login='%s' and password='%s'", cookieLogin, cookiePassword));
        return users.get(0);
    }
}
