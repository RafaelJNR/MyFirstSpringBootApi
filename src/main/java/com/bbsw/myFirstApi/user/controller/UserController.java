package com.bbsw.myFirstApi.user.controller;

import com.bbsw.myFirstApi.user.dto.UserDTO;
import com.bbsw.myFirstApi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userdata/getall")
    public ResponseEntity<?> getAllUser(){

        try{
            return ResponseEntity.ok(userService.findAllUsers());
        }catch(Exception e){
            System.out.println("Error de Servidor");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/userdata")
    public ResponseEntity<UserDTO> getUserData(@RequestParam String username){

        try{

            UserDTO userdto = userService.findUser(username);

            return ResponseEntity.ok(userdto);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/userdata/searchbynameandpassword")
    public ResponseEntity<UserDTO> getUserDataByUsernameAndPassword(@RequestParam String username, String password){

        try{
            return ResponseEntity.ok(userService.findUserByPasswordAndUsername(username, password));
        }catch(Exception e){
            System.out.println("Error de Servidor");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/userdata")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDto){

        if (userService.createUser(userDto)==null){
            return ResponseEntity.status(HttpStatus.FOUND).body(userDto);

        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
        }

    }

    @DeleteMapping("/userdata")
    public ResponseEntity<?> deleteUser(@RequestParam String username){

        try{
            UserDTO userDto = userService.findUser(username);
            userService.deleteUserByUsername(userDto);

            return ResponseEntity.noContent().build();

        }catch(Exception e){
            System.out.println("Server error.");
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/userdata")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDto){

        if(userService.updateUser(userDto)==null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
        }else{
            return ResponseEntity.status(HttpStatus.FOUND).body(userDto);
        }



    }

}
