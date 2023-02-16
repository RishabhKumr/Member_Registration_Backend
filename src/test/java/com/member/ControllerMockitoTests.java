package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.member.controller.RegistrationController;
import com.member.dto.BeneficiaryDto;
import com.member.dto.MemberDto;
import com.member.entity.Beneficiary;
import com.member.entity.Claim;
import com.member.entity.Member;
import com.member.payload.MessageResponse;
import com.member.repository.IBeneficiaryRepository;
import com.member.repository.IClaimRepository;
import com.member.repository.IMailRepository;
import com.member.repository.IMemberRepository;
import com.member.services.RegisterService;

@SpringBootTest(classes = { ControllerMockitoTests.class })
public class ControllerMockitoTests {
	
	@Mock
	IBeneficiaryRepository beneficiaryRepo;
	
	@Mock
	IClaimRepository claimRepository;
	
	@Mock
	IMailRepository mailRepo;
	
	@Mock
	IMemberRepository memberRepo;
	
	@Mock
	RegisterService registerService;
	
	@Mock
	IMemberRepository memberRepository;
	
	@InjectMocks
	RegistrationController registerController;
	
	@Test
	@Order(1)
	public void test_registerMember() {
		Member member = new Member("null","test","test","test","test","Rcdc@gmail.com","test","test","1997-10-03");
		Member member2 = new Member("R7717","test","test","test","test","Rcdc@gmail.com","test","test","1997-10-03");
		when(registerService.registerMember(member)).thenReturn(member);
		if(memberRepository.existsByMemberName(member.getMemberName())){
			
			assertEquals(registerController.registerMember(member),ResponseEntity.badRequest().body(new MessageResponse("Error : UserName is already Taken")));
		}
		if(memberRepository.existsByMemberEmail(member.getMemberEmail())){
			
			assertEquals(registerController.registerMember(member),ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already Taken")));
		}
		
		LocalDate dobCheck = LocalDate.parse(member.getMemberDOB());
		LocalDate curDate = LocalDate.now(); 
		int age =  Period.between(dobCheck, curDate).getYears();
		if(age < 18) {
			assertEquals(registerController.registerMember(member),ResponseEntity.badRequest().body(new MessageResponse("Error : Age must be greater than 18.")));
		}
		
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		assertEquals(registerController.registerMember(member),res.ok(member));	
	}
	
	@Test
	@Order(2)
	public void test_updateMember() {
		String memberId = "test";
		MemberDto memberDto = new MemberDto("test","test","test","test","test","test","1997-10-03");
		Member member = new Member("null","test","test","test","test","test","test","test","1997-10-03"); 
       
		
		LocalDate dobCheck = LocalDate.parse(memberDto.getMemberDOB());
		LocalDate curDate = LocalDate.now(); 
		int age =  Period.between(dobCheck, curDate).getYears();
		when(registerService.updateMember(memberDto, memberId)).thenReturn(member);
		if(age < 18) {
			assertEquals(registerController.updateMember(memberDto, memberId),ResponseEntity.badRequest().body(new MessageResponse("Error : Age must be greater than 18.")));
		}
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		assertEquals(registerController.updateMember(memberDto, memberId),res.ok(member));
	}
	
	@Test
	@Order(3)
	public void test_findMemberById() {
		String memberId = "R7717";
		Optional<Member> memberOptional =  Optional.of(new Member("R7717","test","test","test",
				"test","test","test","test","1997-10-03"));
		when(registerService.findById(memberId)).thenReturn(memberOptional);
		assertEquals(registerController.findMemberById(memberId),memberOptional);
	}
	
	@Test
	@Order(4)
	public void test_findBeneficiaryId() {
		String memberId = "R1001";
		Optional<Beneficiary> beneficiary = Optional.of(new Beneficiary("test","test","1997-10-03","test"
				,"test","test","test","test","test"));
		when(registerService.searchByBeneficiaryId(memberId)).thenReturn(beneficiary);
		assertEquals(registerController.findBeneficiaryId(memberId),beneficiary);
	}
	
	@Test
	@Order(5)
	public void test_addbeneficiary() {
		Beneficiary beneficiary = new Beneficiary("test","test","1997-10-03","test"
				,"test","test","test","test","test");
		//List<Beneficiary> beneficiaryCheck = beneficiaryRepository.findByMemberId(beneficiary.getMemberId());
		List<Beneficiary> beneficiaryCheck = new LinkedList<Beneficiary>();
		when(beneficiaryRepo.findByMemberId(beneficiary.getMemberId())).thenReturn(beneficiaryCheck);
		if(beneficiaryCheck.size() > 1) {
			assertEquals(registerController.addBeneficiary(beneficiary),ResponseEntity.badRequest()
					.body(new MessageResponse("Error : Already added two beneficiaries.")));
		}
		
		when(registerService.registerBeneficiary(beneficiary)).thenReturn(beneficiary);
		LocalDate dobCheck = LocalDate.parse(beneficiary.getBeneficiaryDOB());
		LocalDate curDate = LocalDate.now(); 
		int age =  Period.between(dobCheck, curDate).getYears();
		if(age < 18) {
			assertEquals(registerController.addBeneficiary(beneficiary),ResponseEntity.badRequest()
					.body(new MessageResponse("Error : Age must be greater than 18.")));
		}
		String info = ""+beneficiary.getBeneficiaryName()+" Beneficiary Added Successfully."
				+ ". Here is your Member ID:"+beneficiary.getMemberId();
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		assertEquals(registerController.addBeneficiary(beneficiary),res.ok(beneficiary));
	}
	
	@Test
	@Order(6)
	public void test_updateBeneficiary() {
		BeneficiaryDto beneficiaryDto = new BeneficiaryDto("test","1997-10-03","test","test","test","test","test");
		String id = "B1012";
		String status = "Updated existingBeneficiary Details Successfully!";
		when(registerService.updateBeneficiary(beneficiaryDto, id)).thenReturn(status);
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		assertEquals(registerController.updateBeneficiary(beneficiaryDto, id),res.ok(status));
	}
	@Test
	@Order(7)
	public void test_createClaim() {
		Claim claim = new Claim("C87112","test","test","test","test","test","test","test","test","test","1997-10-03");
		Optional<Member> memberOptional =  Optional.of(new Member("test",
				"test","test","test","test","test","test","test","1997-10-03"));
		when(registerService.findById(claim.getClaimerId())).thenReturn(memberOptional);
//		claim.setClaimerName(memberOptional.get().getMemberName());
//		claim.setClaimerDOB(memberOptional.get().getMemberDOB());
		when(registerService.createClaim(claim)).thenReturn(claim);
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		assertEquals(registerController.createClaim(claim),res.ok(claim));
		
	}
	@Test
	@Order(8)
	public void test_getallbeneficiarybymemberid() {
		Beneficiary beneficiary = new Beneficiary("test","test","1997-10-03","test"
				,"test","test","test","test","test");
		List<Beneficiary> list = new LinkedList<>();
		list.add(beneficiary);
		String id = "test";
		when(registerService.findBeneficiaryByMemberId(id)).thenReturn(list);
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		assertEquals(registerController.listBeneficiaryByMemberId(id),res.ok(list));
	}
	@Test
	@Order(9)
	public void test_deleteBeneficiary() {
		ResponseEntity<Beneficiary> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		String id = "B1011";
		
		registerService.deleteBeneficiary(id);
		
		assertEquals(registerController.deleteBeneficiary(id),responseEntity);
	}
}
