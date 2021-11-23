package very.cool.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private IMemberManager memberManager;

    public MemberController(IMemberManager memberManager)
    {
        this.memberManager = memberManager;
    }


    @GetMapping("{id}")
    public ResponseEntity<Member> getMemberPath(@PathVariable(value = "id") int id ) {
        Member member = memberManager.getMember(id);

        if(member != null) {
            return ResponseEntity.ok().body(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(@RequestParam(value = "name") Optional<String> name) {
        List<Member> members = null;

        if(name.isPresent()) {
            members = memberManager.getMembers(name.get());
            if(members != null) {
                return ResponseEntity.ok().body(members);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        memberManager.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    //POST example
    //{
    //    "name": "Sam",
    //    "id": 3,
    //    "password": "987"
    //    "points": 1000
    //}
    @PostMapping()
    public ResponseEntity<String> createMember(@RequestBody Member member) {
        if(!memberManager.addMember(member)) {
            String entity = "Member with id " + member.getId() + " already exists";
            return new ResponseEntity<String>(entity, HttpStatus.CONFLICT);
        } else {
            String url = "member/" + member.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        if(memberManager.updateMember(member)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid student number.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("points") int points ) {
        Member member = memberManager.getMember(id);
        if(member == null) {
            return new ResponseEntity("Please provide a valid id.",HttpStatus.NOT_FOUND);
        }
        member.setUsername(name);
        member.setPoints(points);
        return ResponseEntity.noContent().build();
    }
}
