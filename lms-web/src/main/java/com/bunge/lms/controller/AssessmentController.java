package com.bunge.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bunge.lms.client.AssessmentResponse;
import com.bunge.lms.domain.Assessment;
import com.bunge.lms.service.AssessmentService;

@RestController
@RequestMapping("/asm")
public class AssessmentController {

	@Autowired
	private AssessmentService asmService;

	@ResponseBody()
	@Transactional
	public AssessmentResponse fetchAllAssessments() {
		AssessmentResponse asmResponse = new AssessmentResponse();
		try {
			List<Assessment> asms = asmService.fetchAll();
			asmResponse.setAssessments(asms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asmResponse;
	}

}
