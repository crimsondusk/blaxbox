package fi.utu.ville.exercises;

import fi.utu.ville.exercises.model.SubmissionInfo;

public class BlaxBoxSubmissionInfo implements SubmissionInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702870727095225372L;

	private final String answer;

	public BlaxBoxSubmissionInfo(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

}
