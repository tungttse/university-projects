package io.github.akkhadka.webstore.controller;

import com.google.gson.Gson;
import io.github.akkhadka.webstore.controller.utility.PathVariable;

import io.github.akkhadka.webstore.controller.utility.UtilityMethods;
import io.github.akkhadka.webstore.controller.viewmodels.Json.JSONResult;
import io.github.akkhadka.webstore.controller.viewmodels.modelMapper.UserMapper;
import io.github.akkhadka.webstore.controller.viewmodels.UserViewModel;
import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.service.UserService;
import io.github.akkhadka.webstore.service.UserServiceImpl;
import io.github.akkhadka.webstore.service.customexception.UserAlreadyExistsException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController extends HttpServlet {
    private UserService userService;
    Gson jsonMapper ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(username!=null){
            PrintWriter writer = resp.getWriter();
            User user = userService.findbyUserName(username);
            JSONResult result = new JSONResult();
            if(user!=null){
                result.setResult("hasDupUser");
                result.setValue("true");
                String jsonReponse = jsonMapper.toJson(result);
                writer.print(jsonReponse);
            }else{
                result.setResult("hasDupUser");
                result.setValue("false");
                String jsonReponse = jsonMapper.toJson(result);
                writer.print(jsonReponse);
            }
        }
        else {
            req.getRequestDispatcher(PathVariable.signupPage)
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = UtilityMethods.parseJson(req);
        UserViewModel userVm = jsonMapper.fromJson(json, UserViewModel.class);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if(userVm.getBrokenRules().size()==0){
            var user = UserMapper.ToModel(userVm);
            try {
                userService.signUp(user);
                resp.setStatus(201);
                resp.setContentType("application/json");
                JSONObject obj = new JSONObject();
                obj.put("success","User registered successfully");
                resp.getWriter().write(obj.toString());
            }
             catch (UserAlreadyExistsException userAlreadyExistsException) {
                 resp.setStatus(500);
                 JSONObject obj = new JSONObject();
                 obj.put("error","Username already exists");
                 resp.getWriter().write(obj.toString());
             }

        }else{
            resp.setStatus(500);
            resp.getWriter().write(jsonMapper.toJson(userVm.getBrokenRules()));
        }




    }



    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        jsonMapper = new Gson();
    }
}

