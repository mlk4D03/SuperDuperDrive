package com.udacity.jwdnd.course1.cloudstorage.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping
    String getErrorPage(Model model, HttpServletRequest request) {
        model.addAttribute("link", "/home");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode.equals(HttpStatus.NOT_FOUND.value())) {
                model.addAttribute("error", true);
                model.addAttribute("errorMessage", "We're very sorry. The page you were looking" +
                        " for slipped through a crack and can't be found!");
                return "result";
            } else if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
                model.addAttribute("error", true);
                model.addAttribute("errorMessage", "Ups!. There was an internal server error.");
                return "result";
            }
        }
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", "Ups!. There was an error.");
        return "result";

    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
