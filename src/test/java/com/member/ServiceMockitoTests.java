package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import com.member.dto.BeneficiaryDto;
import com.member.dto.MemberDto;
import com.member.entity.Beneficiary;
import com.member.entity.Claim;
import com.member.entity.DatabaseSequence;
import com.member.entity.Member;
import com.member.repository.IBeneficiaryRepository;
import com.member.repository.IClaimRepository;
import com.member.repository.IMemberRepository;
import com.member.services.RegisterService;
import com.member.services.SequenceGeneratorService;

@SpringBootTest(classes = { ServiceMockitoTests.class })
public class ServiceMockitoTests {
	@Mock
	IBeneficiaryRepository beneficiaryRepo;
	
	@Mock
	IClaimRepository claimRepository;
	
	@Mock
	IMemberRepository memberRepo;
	
	@Mock
	SequenceGeneratorService sequenceGeneratorService;
	
	@InjectMocks
	RegisterService registerService;
	
	@Test
	@Order(1)
	public void test_registermember() {

		Member member = new Member("R7717","test","test","test","test","Rcdc@gmail.com","test","test","1997-10-03");
		Long sequence = sequenceGeneratorService.generateSequence(Member.SEQUENCE_NAME);
		SecureRandom random = new SecureRandom();
		int tmp = random.nextInt(999);
		String memberId = "R"+tmp+sequence;
		member.setMemberId(memberId);
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(registerService.registerMember(member),member);
		
	}
	
	@Test
	@Order(2)
	public void test_registerBeneficiary() {
		Beneficiary beneficiary = new Beneficiary("B001","test","1997-10-03","test","test","test","test","test","test");
		Long sequence = sequenceGeneratorService.generateSequence(Beneficiary.SEQUENCE_NAME);
		SecureRandom random = new SecureRandom();
		int tmp = random.nextInt(999);
		String beneficiaryId = "B"+tmp+sequence;
		beneficiary.setBeneficiaryId(beneficiaryId);
		when(beneficiaryRepo.save(beneficiary)).thenReturn(beneficiary);
		assertEquals(registerService.registerBeneficiary(beneficiary),beneficiary);
	}
	
	@Test
	@Order(3)
	public void test_createClaim() {
		Claim claim = new Claim("C87112","test","test","test","test","test","test","test","test","test","1997-10-03");
		Long sequence = sequenceGeneratorService.generateSequence(Claim.SEQUENCE_NAME);
		SecureRandom random = new SecureRandom();
		int tmp = random.nextInt(999);
		String claimId = "C"+tmp+sequence;
		claim.setClaimId(claimId);
		when(claimRepository.save(claim)).thenReturn(claim);
		assertEquals(registerService.createClaim(claim),claim);
	}
	
	@Test
	@Order(4)
	public void test_updateMember() {
		String id="R7717";
		MemberDto memberDTO = new MemberDto("test","test","test","test","test","test","1997-10-03");
		Optional<Member> memberOptional =  Optional.of(new Member("R7717","test","test","test","test","test","test","test","1997-10-03"));
		when(memberRepo.findById(id)).thenReturn(memberOptional);
		Member member = memberOptional.get();
		when(memberRepo.save(member)).thenReturn(member);
		assertEquals(registerService.updateMember(memberDTO, id),member);
		
		
	}
	
	@Test
	@Order(5)
	public void test_findById() {
		String memberId = "R1011";
		Optional<Member> member = memberRepo.findById(memberId);
		assertEquals(registerService.findById(memberId),member);
	}
	
	@Test
	@Order(6)
	public void test_updateBeneficiary() {
		BeneficiaryDto beneficiaryDto = new BeneficiaryDto("test","1997-10-03","test","test","test","test","test","R1011");
		String id = "B1012";
		Optional<Beneficiary> beneficiaryOptional = Optional.of(new Beneficiary("test","test","1997-10-03","test"
				,"test","test","test","test","test"));
		when(beneficiaryRepo.findById(id)).thenReturn(beneficiaryOptional);
		Beneficiary beneficiary = beneficiaryOptional.get();
		when(beneficiaryRepo.save(beneficiary)).thenReturn(beneficiary);
		String status="Updated existingBeneficiary Details Successfully!";
		assertEquals(registerService.updateBeneficiary(beneficiaryDto, id),status);
		
	}
	
	@Test
	@Order(7)
	public void test_findBeneficiaryByMemberId() {
		String memberId = "R1001";
		Beneficiary beneficiary = new Beneficiary("test","test","1997-10-03","test"
				,"test","test","test","test","test");
		List<Beneficiary> list = new LinkedList<Beneficiary>();
		list.add(beneficiary);
		when(beneficiaryRepo.findByMemberId(memberId)).thenReturn(list);
		assertEquals(registerService.findBeneficiaryByMemberId(memberId),list);
	}
	
	@Test
	@Order(8)
	public void test_deleteBeneficiary() {
		String id = "B1782";
//  	RegisterService serv = mock(RegisterService.class);
//		beneficiaryRepo.deleteById(id);
//		doNothing().when(serv).deleteBeneficiary(id);
//		verify(serv,times(0)).deleteBeneficiary(id);
		registerService.deleteBeneficiary(id);
		verify(beneficiaryRepo,times(1)).deleteById(id);
	}
	
	@Test
	@Order(9)
	public void test_searchByBeneficiaryId() {
		String id = "B1782";
		Optional<Beneficiary> beneficiaryOptional = Optional.of(new Beneficiary("test","test","1997-10-03","test"
				,"test","test","test","test","test"));
		when(beneficiaryRepo.findById(id)).thenReturn(beneficiaryOptional);
		assertEquals(registerService.searchByBeneficiaryId(id),beneficiaryOptional);
	}
	
	@InjectMocks
	SequenceGeneratorService sequencegen;
	
	@Mock
	MongoOperations mongoOperations;
	
	@Test
	@Order(10)
	public void test_generateSequence() {
		
		String seqName = "beneficiary_sequence";
		 DatabaseSequence counter = null;
		 when(mongoOperations.findAndModify(query(where("memberId").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class)).thenReturn(counter);
		 assertEquals(sequencegen.generateSequence(seqName),1);
	}
}
