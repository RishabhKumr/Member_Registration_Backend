package com.member.services;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.member.dto.BeneficiaryDto;
import com.member.dto.MemberDto;
import com.member.entity.Beneficiary;
import com.member.entity.Claim;
import com.member.entity.Member;
import com.member.repository.IBeneficiaryRepository;
import com.member.repository.IClaimRepository;
import com.member.repository.IMemberRepository;

@Service
public class RegisterService {
	
	@Autowired
	private IMemberRepository memberRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private IBeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private IClaimRepository claimRepository;
	
	/* Register Member */
	public Member registerMember(Member member) {
		Long sequence = sequenceGeneratorService.generateSequence(Member.SEQUENCE_NAME);
		SecureRandom random = new SecureRandom();
		int tmp = random.nextInt(999);
		String memberId = "R"+tmp+sequence;
		member.setMemberId(memberId);
		memberRepository.save(member);
		return member;
	}
	
	/* Register Beneficiary */
	public Beneficiary registerBeneficiary(Beneficiary beneficiary) {
		Long sequence = sequenceGeneratorService.generateSequence(Beneficiary.SEQUENCE_NAME);
		SecureRandom random = new SecureRandom();
		int tmp = random.nextInt(999);
		String beneficiaryId = "B"+tmp+sequence;
		beneficiary.setBeneficiaryId(beneficiaryId);
		beneficiaryRepository.save(beneficiary);
		return beneficiary;
	}
	
	/* Create Claim */
	public Claim createClaim(Claim claim) {
		Long sequence = sequenceGeneratorService.generateSequence(Claim.SEQUENCE_NAME);
		SecureRandom random = new SecureRandom();
		int tmp = random.nextInt(999);
		String claimId = "C"+tmp+sequence;
		claim.setClaimId(claimId);
		claimRepository.save(claim);
		return claim;
	}
	
	/* Update Member Details */
	public Member updateMember(MemberDto memberDto,String id) {
		String status="Not able to update Member, Kindly Check logs!";
		Member existingMember = memberRepository.findById(id).orElseThrow(
				() -> new com.member.exceptions.MemberNotFoundException("Member","Id",id));
		if(existingMember != null) {
			existingMember.setMemberAddress(memberDto.getMemberAddress());
			existingMember.setMemberContactNo(memberDto.getMemberContactNo());
			existingMember.setMemberCountry(memberDto.getMemberCountry());
			existingMember.setMemberEmail(memberDto.getMemberEmail());
			existingMember.setMemberPAN(memberDto.getMemberPAN());
			existingMember.setMemberState(memberDto.getMemberState());
			existingMember.setMemberDOB(memberDto.getMemberDOB());
			memberRepository.save(existingMember);
			status="Updated Successfully!";
		}
		return existingMember;
	}
	
	/* Find Member By Id */
	public Optional<Member> findById(String memberId) {
		Optional<Member> member = memberRepository.findById(memberId);
		return member;
	}
	
	/* Update Beneficiary Details */
	public String updateBeneficiary(BeneficiaryDto beneficiaryDto,String id) {
		String status="Not able to update Beneficiary, Kindly Check logs!";
		Beneficiary existingBeneficiary = beneficiaryRepository.findById(id).orElseThrow(
				() -> new com.member.exceptions.BeneficiaryNotFoundExceptionHandler("Beneficiary","Id",id));
		if(existingBeneficiary != null) {
			existingBeneficiary.setBeneficiaryAddress(beneficiaryDto.getBeneficiaryAddress());
			existingBeneficiary.setBeneficiaryCountry(beneficiaryDto.getBeneficiaryCountry());
			existingBeneficiary.setBeneficiaryName(beneficiaryDto.getBeneficiaryName());
			existingBeneficiary.setBeneficiaryPAN(beneficiaryDto.getBeneficiaryPAN());
			existingBeneficiary.setBeneficiaryState(beneficiaryDto.getBeneficiaryState());
			existingBeneficiary.setBeneficiaryDOB(beneficiaryDto.getBeneficiaryDOB());
			beneficiaryRepository.save(existingBeneficiary);
			status="Updated existingBeneficiary Details Successfully!";
		}
		return status;
	}
	
	public List<Beneficiary> findBeneficiaryByMemberId(String memberId){
		List<Beneficiary> list = beneficiaryRepository.findByMemberId(memberId);
		return list;
	}
	
	public void deleteBeneficiary( String id)
	{
		beneficiaryRepository.deleteById(id);
	}
	
	public Optional<Beneficiary> searchByBeneficiaryId(String id){
		Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(id);
		return beneficiary;
	}
}
