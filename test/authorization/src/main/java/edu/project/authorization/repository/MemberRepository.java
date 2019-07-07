package edu.project.authorization.repository;

import edu.project.authorization.domain.MemberVO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<MemberVO, Long> {
    public MemberVO findByUsername(String username);
}
