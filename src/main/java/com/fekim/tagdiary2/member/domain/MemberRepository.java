package com.fekim.tagdiary2.member.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m " +
            "from Member m " +
            "where m.fromSocial = :social and m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email, @Param("social") boolean social);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m " +
            "from Member m " +
            "where m.fromSocial = :social and m.name = :name")
    Optional<Member> findByName(@Param("name") String name, @Param("social") boolean social);

}
