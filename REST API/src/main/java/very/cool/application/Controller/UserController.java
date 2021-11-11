package very.cool.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.Interfaces.IUserManager;
import very.cool.application.Model.Member;

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
    public ResponseEntity<Member> getMemberPath(@PathVariable(value = "id") int id ) {
        Member user = userManager.getMember(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(@RequestParam(value = "name") Optional<String> name) {
        List<Member> users = null;

        if(name.isPresent()) {
            users = userManager.getMembers(name.get());
            if(users != null) {
                return ResponseEntity.ok().body(users);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        userManager.deleteMember(id);
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
    public ResponseEntity<Member> createUser(@RequestBody Member user) {
        if(!userManager.addMember(user)) {
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
    public ResponseEntity<Member> updateMember(@RequestBody Member user) {
        if(userManager.updateMember(user)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid student number.", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PutMapping("{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("points") int points ) {
        Member user = userManager.getMember(id);
        if(user == null) {
            return new ResponseEntity("Please provide a valid id.",HttpStatus.NOT_FOUND);
        }
        user.setName(name);
        user.setPoints(points);
        return ResponseEntity.noContent().build();
    }
}
