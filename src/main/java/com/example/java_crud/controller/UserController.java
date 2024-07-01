package com.example.java_crud.controller;


import com.example.java_crud.models.User;
import com.example.java_crud.models.UserDto;
import com.example.java_crud.service.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    //ユーザーデータを取得
    @GetMapping({"","/"})
    public String showUsers(Model model){
        List<User> users = repo.findAll();
        model.addAttribute("users",users);
        return "/user/index";
    }


    //ユーザー登録画面
    @GetMapping("/create")
    public String showCreateUser(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);

        return("/user/createUser");
    }

    //ユーザー登録
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("UserDto") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "user/createUser";
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        repo.save(user);

        return "redirect:/users";
    }



    //ユーザー編集画面
    @GetMapping("/edit")

    public String showEditUser(Model model, @RequestParam int id){

        try {
            User user = repo.findById(id).get();
            model.addAttribute("user",user);

            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setGender(user.getGender());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());

            model.addAttribute("userDto", userDto);

        }catch (Exception ex){
            System.out.println("Exception :" + ex.getMessage());
            return "redirect:/users";
        }

        return "/user/EditUser";
    }


    @PostMapping("/edit")
    public String updateUser(Model model, @RequestParam int id, @ModelAttribute UserDto userDto){

        try {

            User user = repo.findById(id).get();
            model.addAttribute("user", user);

            user.setName(userDto.getName());
            user.setGender(userDto.getGender());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());

            repo.save(user);

        }catch (Exception ex){
            System.out.println("Exception :" + ex.getMessage());
            return "redirect:/users";
        }

        return "redirect:/users";
    }

    //ユーザー削除
    @GetMapping("/delete")
    public String deleteUser(@RequestParam int id){

        try {
            User user = repo.findById(id).get();

            repo.delete(user);

        }catch (Exception ex){
            System.out.println("Exception :" + ex.getMessage());
            return "redirect:/users";
        }

        return "redirect:/users";
    }


}
