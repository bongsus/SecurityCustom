package com.bongsus.www.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bongsus.www.domain.entity.MemberEntity;

/**
 * Description : 회원 저장소<br>
 * Date : 2021. 9. 6.<br>
 * History :<br>
 * - 2021. 9. 6.	작성자 : Bongstar, 설명 : 최초작성<br>
 *
 * @author Bongstar
 * @version 1.0
 */

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	
	Optional<MemberEntity> findByEmail(String userEmail);

}
