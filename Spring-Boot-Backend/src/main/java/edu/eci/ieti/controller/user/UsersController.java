package edu.eci.ieti.controller.user;


import edu.eci.ieti.exception.UserNotFoundException;
import edu.eci.ieti.repository.user.User;
import edu.eci.ieti.repository.user.UserDto;
import edu.eci.ieti.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        URI createdUserUri = URI.create("");
        usersService.save(new User(user));
        return ResponseEntity.created(createdUserUri).body(null);
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users= usersService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> op = usersService.findById(id);

        if (op.isEmpty()) throw new UserNotFoundException(id);
        return ResponseEntity.ok(op.get());
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        User user = new User(userDto);
        Optional<User> users = usersService.findById(id);
        if (users.isPresent()){
            usersService.update(user,id);
            usersService.save(users.get());
            return ResponseEntity.ok(null);
        }else{
            throw new UserNotFoundException(id);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        if(usersService.findById(id).isEmpty()) throw new UserNotFoundException(id);
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
