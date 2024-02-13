package az.edu.orient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.edu.orient.model.User;
import az.edu.orient.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "users")
public class UserController {

  private final static List<User> users = new ArrayList<>();


  private final UserService userService;

  @GetMapping
  public List<User> users(){
    return userService.getAllUsers();
  }

  @GetMapping("hello")
  public String sayHello(){
    return "Hello github actions";
  }

  @GetMapping(path = "{id}")
  public User getUserById(@PathVariable int id){
    return userService.getUserById(id);
  }

  @PutMapping(path = "{id}")
  public User updateUserById(@PathVariable int id, @RequestBody User user){
    return userService.update(id, user);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity deleteUserById(@PathVariable int id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public User addUser(@RequestBody User user) {
    return userService.save(user);
  }

}
