package very.cool.application.Controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.Interfaces.IUserManager;
import very.cool.application.Model.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserManager userManager;

    public UserController(IUserManager userManager)
    {
        this.userManager = userManager;
    }


    @GetMapping("{id}")
    public ResponseEntity<User> getUserPath(@PathVariable(value = "id") int id ) {
        User user = userManager.getUser(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "name") Optional<String> name) {
        List<User> users = null;

        if(name.isPresent()) {
            users = userManager.getUsers(name.get());
        }
        else {
            users = userManager.getUsers();
        }

        if(users != null) {
            return ResponseEntity.ok().body(users);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "points") Optional<Integer> points) {
        List<User> users = null;

        if(points.isPresent()) {
            users = userManager.getUsers(points.get());
        }
        else {
            users = userManager.getUsers();
        }
        if(users != null) {
            return ResponseEntity.ok().body(users);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        userManager.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    //POST example
    //{
    //    "name": "Sam",
    //    "id": 3,
    //    "password": "987"
    //    "points": 1000
    //}
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(!userManager.addUser(user)) {
            String entity = "User with id " + user.getId() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "user/" + user.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if(userManager.updateUser(user)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid student number.", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("points") int points ) {
        User user = userManager.getUser(id);
        if(user == null) {
            return new ResponseEntity("Please provide a valid id.",HttpStatus.NOT_FOUND);
        }
        user.setName(name);
        user.setPoints(points);
        return ResponseEntity.noContent().build();
    }
}
