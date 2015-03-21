package fi.utu.ville.exercises;

import fi.utu.ville.exercises.model.ExerciseData;
import fi.utu.ville.standardutils.AbstractFile;

public class BlaxBoxExerciseData implements ExerciseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716445297446246493L;

	private final String question;
	private final boolean addallowed;
	private final boolean subtractionallowed;
	private final boolean multiplicationallowed;
	private final boolean divisionallowed;
	

	/*
	public BlaxBoxExerciseData(String question, AbstractFile imgFile) {
		this.question = question;
		this.imgFile = imgFile;
	}

	*/

	public BlaxBoxExerciseData(String question, boolean addallowed, boolean subtractionallowed, boolean multiplicationallowed, boolean divisionallowed) {
		this.question = question;
		this.addallowed = addallowed;
		this.subtractionallowed = subtractionallowed;
		this.multiplicationallowed = multiplicationallowed;
		this.divisionallowed = divisionallowed;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public boolean getAddAllowed() {
		return addallowed;
	}

	public boolean getSubAllowed() {
		return subtractionallowed;
	}
	
	public boolean getMultiAllowed() {
		return multiplicationallowed;
	}
	
	public boolean getDivAllowed() {
		return divisionallowed;
	}

}
