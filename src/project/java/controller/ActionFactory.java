package project.java.controller;

import javax.servlet.http.HttpServletRequest;

class ActionFactory {
    Action defineCommand(HttpServletRequest request) {
        Action command;
        String action = request.getParameter("command");
        if (action == null) {
            action = "index";
        }
        try {
            Actions actEnum = Actions.valueOf(action.toUpperCase());
            command = actEnum.command;
        } catch (IllegalArgumentException e) {
            command = Actions.ERROR.command;
        }
        return command;
    }
}
