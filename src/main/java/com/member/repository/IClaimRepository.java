package com.member.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.member.entity.Claim;

public interface IClaimRepository extends MongoRepository<Claim,String>{

}
