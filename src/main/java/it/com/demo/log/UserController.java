package it.com.demo.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    @GetMapping(value = "/list")
    public List<User> getAll(){
        return userService.getAll();
    }
    @PostMapping(value = "")
    public User add(@RequestBody User user){
        return userService.add(user);
    }
    @PutMapping(value = "/{id}")
    public User updateById(User user){
        return userService.updateById(user);
    }
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Integer id){
        userService.deleteById(id);
        return "delete success";
    }
}
