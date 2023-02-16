package com.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.member.entity.Beneficiary;

public interface IBeneficiaryRepository extends MongoRepository<Beneficiary,String>{
	public List<Beneficiary> findByMemberId(String memberId);
	Optional<Beneficiary> findById(String id);
}
