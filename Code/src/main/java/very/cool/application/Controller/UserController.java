package very.cool.application.Controller;

import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;
import very.cool.application.Model.User;
import very.cool.application.Repository.UserDataStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private static UserDataStorage userDataStorage = new UserDataStorage();

    @GetMapping("/hello")
    @ResponseBody
    public String SayHello()
    {
        String msg = "Hello, your resources work!";
        return msg;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserPath(@PathVariable(value = "id") int id ) {
        User user = userDataStorage.getUser(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "points") Optional<Integer> points) {
        List<User> users = null;

        if(points.isPresent()) {
            users = userDataStorage.getUsers(points.get());
        }
        else {
            users = userDataStorage.getUsers();
        }
        if(users != null) {
            return ResponseEntity.ok().body(users);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        userDataStorage.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    //POST example
    //{
    //    "name": "Sam",
    //    "id": 3,
    //    "points": 1000
    //}
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(!userDataStorage.addUser(user)) {
            String entity = "User with id " + user.getId() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "user/" + user.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if(userDataStorage.updateUser(user)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid student number.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("points") int points ) {
        User user = userDataStorage.getUser(id);
        if(user == null) {
            return new ResponseEntity("Please provide a valid id.",HttpStatus.NOT_FOUND);
        }
        user.setName(name);
        user.setPoints(points);
        return ResponseEntity.noContent().build();
    }
}
