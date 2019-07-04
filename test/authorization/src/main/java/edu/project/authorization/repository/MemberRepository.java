package edu.project.authorization.repository;

import edu.project.authorization.domain.MemberVO;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberVO, Long> {
}
