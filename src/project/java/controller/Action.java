package project.java.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

abstract class Action {
    abstract Action execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException;

    String getJsp() {
        return "/" + this.toString().toLowerCase() + ".jsp";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceFirst("Command", "");
    }
}
