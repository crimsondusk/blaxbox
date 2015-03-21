package fi.utu.ville.exercises;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionVisualizer;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class BlaxBoxSubmissionViewer extends VerticalLayout implements
		SubmissionVisualizer<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6260031633710031462L;
	private BlaxBoxExerciseData exer;
	private BlaxBoxSubmissionInfo submInfo;

	private Localizer localizer;
	
	public BlaxBoxSubmissionViewer() {
	}

	@Override
	public void initialize(BlaxBoxExerciseData exercise,
			BlaxBoxSubmissionInfo dataObject, Localizer localizer,
			TempFilesManager tempManager) throws ExerciseException {
		this.localizer = localizer;
		this.exer = exercise;
		this.submInfo = dataObject;
		doLayout();
	}

	private void doLayout() {
//		this.addComponent(new Label(localizer.getUIText(BlaxBoxUiConstants.QUESTION) + 
//				": " + exer.getQuestion()));
		Label answ = new Label(localizer.getUIText(BlaxBoxUiConstants.ANSWER) + 
				": "  + submInfo.getAnswer());
		answ.addStyleName(BlaxBoxThemeConsts.ANSWER_STYLE);
		this.addComponent(answ);
	}

	@Override
	public Component getView() {
		return this;
	}

	@Override
	public String exportSubmissionDataAsText() {
	//		return localizer.getUIText(BlaxBoxUiConstants.QUESTION, "\n", 
	//				exer.getQuestion(), submInfo.getAnswer());
		return "";
		
	}

}
