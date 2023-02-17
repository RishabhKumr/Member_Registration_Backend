package com.member.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.member.dto.BeneficiaryDto;
import com.member.dto.ClaimDto;
import com.member.dto.MemberDto;
import com.member.dto.MemberRegisterDto;
import com.member.entity.Beneficiary;
import com.member.entity.Claim;
import com.member.entity.Member;
import com.member.payload.MessageResponse;
import com.member.repository.IBeneficiaryRepository;
import com.member.repository.IMemberRepository;
import com.member.services.RegisterService;   

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private IMemberRepository memberRepository;
	
	@Autowired
	private IBeneficiaryRepository beneficiaryRepository;
	
	@PostMapping("/registermember")
	public ResponseEntity<?> registerMember(@RequestBody MemberRegisterDto member){
		if(memberRepository.existsByMemberName(member.getMemberName())){
			return ResponseEntity.badRequest().body(new MessageResponse("Error : UserName is already Taken"));
		}
		if(memberRepository.existsByMemberEmail(member.getMemberEmail())){
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already Taken"));
		}
		
		LocalDate dobCheck = LocalDate.parse(member.getMemberDOB());
		LocalDate curDate = LocalDate.now(); 
		int age =  Period.between(dobCheck, curDate).getYears();
		if(age < 18) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Age must be greater than 18."));
		}
		Member memberObj = new Member();
		memberObj.setMemberAddress(member.getMemberAddress());
		memberObj.setMemberContactNo(member.getMemberContactNo());
		memberObj.setMemberCountry(member.getMemberCountry());
		memberObj.setMemberDOB(member.getMemberDOB());
		memberObj.setMemberEmail(member.getMemberEmail());
		memberObj.setMemberName(member.getMemberName());
		memberObj.setMemberPAN(member.getMemberPAN());
		memberObj.setMemberState(member.getMemberState());
		Member save = this.registerService.registerMember(memberObj);
		return ResponseEntity.ok(save);
	}
	
	@PutMapping("/updatemember/{memberId}")
	public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto,@PathVariable String memberId){
		
		Member result = registerService.updateMember(memberDto, memberId);
		
		LocalDate dobCheck = LocalDate.parse(result.getMemberDOB());
		LocalDate curDate = LocalDate.now(); 
		int age =  Period.between(dobCheck, curDate).getYears();
		if(age < 18) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Age must be greater than 18."));
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/searchbyid/{id}")
	public Optional<Member> findMemberById(@PathVariable String id){
		Optional<Member> member =  registerService.findById(id);
		return member;
	}
	
	@GetMapping("/searchbybeneficiaryId/{id}")
	public Optional<Beneficiary> findBeneficiaryId(@PathVariable String id){
		Optional<Beneficiary> beneficiary = registerService.searchByBeneficiaryId(id);
		return beneficiary;
	}
	
	@PostMapping("/addbeneficiary")
	public ResponseEntity<?> addBeneficiary(@RequestBody BeneficiaryDto beneficiaryDto){
	   
		List<Beneficiary> beneficiaryCheck = beneficiaryRepository.findByMemberId(beneficiaryDto.getMemberId());
		if(beneficiaryCheck.size() > 1) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Already added two beneficiaries."));
		}
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryAddress(beneficiaryDto.getBeneficiaryAddress());
		beneficiary.setBeneficiaryCountry(beneficiaryDto.getBeneficiaryCountry());
		beneficiary.setBeneficiaryDOB(beneficiaryDto.getBeneficiaryDOB());
		beneficiary.setBeneficiaryName(beneficiaryDto.getBeneficiaryName());
		beneficiary.setBeneficiaryPAN(beneficiaryDto.getBeneficiaryPAN());
		beneficiary.setBeneficiaryRelation(beneficiaryDto.getBeneficiaryRelation());
		beneficiary.setBeneficiaryState(beneficiaryDto.getBeneficiaryState());
		beneficiary.setMemberId(beneficiaryDto.getMemberId());
		Beneficiary save = this.registerService.registerBeneficiary(beneficiary);
		LocalDate dobCheck = LocalDate.parse(beneficiaryDto.getBeneficiaryDOB());
		LocalDate curDate = LocalDate.now(); 
		int age =  Period.between(dobCheck, curDate).getYears();
		if(age < 18) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Age must be greater than 18."));
		}
		return ResponseEntity.ok(save);
	}
	
	@PutMapping("/updatebeneficiary/{beneficiaryId}")
	public ResponseEntity<?> updateBeneficiary(@RequestBody BeneficiaryDto beneficiaryDto,@PathVariable String beneficiaryId){
		String result = registerService.updateBeneficiary(beneficiaryDto, beneficiaryId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/createclaim")
	public ResponseEntity<?> createClaim(@RequestBody ClaimDto claim){
		Optional<Member> member = registerService.findById(claim.getClaimerId());
		Claim save = new Claim();
		if(!member.isEmpty()) {
		claim.setClaimerName(member.get().getMemberName());
		claim.setClaimerDOB(member.get().getMemberDOB());
		Claim claimObj = new Claim();
		claimObj.setClaimAdmissionDate(claim.getClaimAdmissionDate());
		claimObj.setClaimAmount(claim.getClaimAmount());
		claimObj.setClaimDischargeDate(claim.getClaimDischargeDate());
		claimObj.setClaimerDOB(claim.getClaimerDOB());
		claimObj.setClaimerName(claim.getClaimerName());
		claimObj.setClaimFor(claim.getClaimFor());
		claimObj.setClaimName(claim.getClaimName());
		claimObj.setClaimProvider(claim.getClaimProvider());
		claimObj.setClaimType(claim.getClaimType());
		save = this.registerService.createClaim(claimObj);
		return ResponseEntity.ok(save);
		}
		else
		{
			return  ResponseEntity.ok(save);
		}
	}
	
	@GetMapping("/getallbeneficiarybymemberid/{id}")
	public ResponseEntity<?> listBeneficiaryByMemberId(@PathVariable String id){
		List<Beneficiary> list = registerService.findBeneficiaryByMemberId(id);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/deletebeneficiarybyid/{id}")
	public ResponseEntity<?> deleteBeneficiary(@PathVariable String id){
		ResponseEntity<Beneficiary> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			registerService.deleteBeneficiary(id);
		}
		catch(Exception e) {
			
			responseEntity = new ResponseEntity<Beneficiary>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
