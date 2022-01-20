package very.cool.application.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import very.cool.application.Interfaces.IMemberData;
import very.cool.application.Model.Member;

import java.util.List;

//MemberDalJPA
@Repository
@RequiredArgsConstructor
public class MemberDataStorage implements IMemberData {

    IMemberRepository repo;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberDataStorage(IMemberRepository fakeRepo) {
        this.repo = fakeRepo;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Member> getMembers() { return repo.findAll(); }

    public List<Member> getMembers(String name) {
        return repo.getMembersByUsername(name);
    }

    public List<Member> getMembers(int points) {
        return repo.getMembersByPoints(points);
    }

    public Member getMember(int id) {
        return repo.getMemberById(id);
    }

    public boolean deleteMember(int id) {
        Member member = getMember(id);
        if(member != null) {
            repo.delete(member);
            return true;
        }
        else return false;
    }

    public boolean addMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        repo.save(member);
        return true;
    }

    public boolean updateMember(Member member) {
        Member oldMember = this.getMember(member.getId());
        if(oldMember != null) {
           oldMember.setUsername(member.getUsername());
           oldMember.setPoints(member.getPoints());
           oldMember.setPassword(member.getPassword());
           repo.save(oldMember);
           return true;
        }
        else return false;
    }
}
