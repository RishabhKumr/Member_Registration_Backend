package com.member.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.member.entity.Member;

public interface IMemberRepository extends MongoRepository<Member,String>{
	Boolean existsByMemberName(String memberName);
	Boolean existsByMemberEmail(String memberEmail);
}
