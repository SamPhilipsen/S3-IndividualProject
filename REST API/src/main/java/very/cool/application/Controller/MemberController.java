package very.cool.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import very.cool.application.Interfaces.IMemberManager;
import very.cool.application.Model.Member;
import very.cool.application.DTO.MemberDTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<MemberDTO> getMemberPath(@PathVariable(value = "id") int id ) {
        Member member = memberManager.getMember(id);

        if(member != null) {
            MemberDTO memberDTO = new MemberDTO(member.getUsername(), member.getPassword(), member.getId(), member.getPoints());
            return ResponseEntity.ok().body(memberDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getMembers(@RequestParam(value = "name") Optional<String> name) {
        List<Member> members = null;
        List<MemberDTO> DTOMembers = new ArrayList<MemberDTO>();

        if(name.isPresent()) {
            members = memberManager.getMembers(name.get());
        }
        else {
            members = memberManager.getMembers();
        }
        if(members != null) {
            for(Member member : members) {
                DTOMembers.add(new MemberDTO(member.getUsername(), member.getPassword(), member.getId(), member.getPoints()));
            }
            return ResponseEntity.ok().body(DTOMembers);
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<String> createMember(@RequestBody MemberDTO memberDTO) {
        Member member = new Member(memberDTO.getUsername(), memberDTO.getPassword(), memberDTO.getId(), memberDTO.getPoints());

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
    public ResponseEntity<Member> updateMember(@RequestBody MemberDTO memberDTO) {
        Member member = new Member(memberDTO.getUsername(), memberDTO.getPassword(), memberDTO.getId(), memberDTO.getPoints());

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
