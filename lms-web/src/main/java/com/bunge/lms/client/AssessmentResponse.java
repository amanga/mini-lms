package com.bunge.lms.client;

import java.io.Serializable;
import java.util.List;

import com.bunge.lms.domain.Assessment;

public class AssessmentResponse implements Serializable {

	private static final long serialVersionUID = 6753871482369466537L;

	private List<Assessment> assessments;

	public List<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(List<Assessment> assessments) {
		this.assessments = assessments;
	}

	@Override
	public String toString() {
		return "AssessmentResponse [assessments=" + assessments + "]";
	}

}
