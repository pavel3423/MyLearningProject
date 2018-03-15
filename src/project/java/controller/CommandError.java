package project.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandError extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) {
        return Actions.ERROR.command;
    }
}
