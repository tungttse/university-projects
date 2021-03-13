package io.github.akkhadka.webstore.controller;

import com.google.gson.Gson;
import io.github.akkhadka.webstore.controller.utility.PathVariable;
import io.github.akkhadka.webstore.controller.utility.UtilityMethods;
import io.github.akkhadka.webstore.controller.viewmodels.UserViewModel;
import io.github.akkhadka.webstore.controller.viewmodels.modelMapper.UserMapper;
import io.github.akkhadka.webstore.service.UserService;
import io.github.akkhadka.webstore.service.UserServiceImpl;
import io.github.akkhadka.webstore.service.customexception.UserNotFoundException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ProfileController extends HttpServlet {
    private UserService userService;
    Gson jsonMapper ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username =(String) req.getSession(false).getAttribute("username");
        UserViewModel userVm = UserMapper.ToView(userService.findbyUserName(username));
        req.setAttribute("user",userVm);
        req.getRequestDispatcher(PathVariable.userProfilePage)
                .forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        jsonMapper = new Gson();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = UtilityMethods.parseJson(req);
        UserViewModel userVm = jsonMapper.fromJson(json, UserViewModel.class);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if(userVm.getBrokenRules().size()==0){
            var user = UserMapper.ToModel(userVm);
            try {
                userService.updateProfile(user);
                resp.setStatus(201);
                resp.setContentType("application/json");
                JSONObject obj = new JSONObject();
                obj.put("success","User Updated successfully");
                resp.getWriter().write(obj.toString());
            }
            catch(UserNotFoundException ex){
                resp.setStatus(500);
                JSONObject obj = new JSONObject();
                obj.put("error","User not found to update");
                resp.getWriter().write(obj.toString());
            }


        }else{
            resp.setStatus(500);
            resp.getWriter().write(jsonMapper.toJson(userVm.getBrokenRules()));
        }
    }
}
