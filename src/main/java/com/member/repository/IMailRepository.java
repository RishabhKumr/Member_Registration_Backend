package com.member.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.member.entity.Mail;

public interface IMailRepository extends MongoRepository<Mail,String>{

}
