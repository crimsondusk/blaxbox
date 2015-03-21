package fi.utu.ville.exercises;

import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.helpers.ExerciseExecutionHelper;
import fi.utu.ville.exercises.model.ExecutionSettings;
import fi.utu.ville.exercises.model.ExecutionState;
import fi.utu.ville.exercises.model.ExecutionStateChangeListener;
import fi.utu.ville.exercises.model.Executor;
import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionListener;
import fi.utu.ville.exercises.model.SubmissionType;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class BlaxBoxExecutor extends VerticalLayout implements
		Executor<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;

	private final ExerciseExecutionHelper<BlaxBoxSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<BlaxBoxSubmissionInfo>();

	private final TextField answerField = new TextField();

	public BlaxBoxExecutor() {

	}

	@Override
	public void initialize(Localizer localizer,
			BlaxBoxExerciseData exerciseData, BlaxBoxSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		answerField.setCaption(localizer.getUIText(BlaxBoxUiConstants.ANSWER));
		doLayout(exerciseData, oldSubm != null ? oldSubm.getAnswer() : "");
	}

	private void doLayout(BlaxBoxExerciseData exerciseData, String oldAnswer) {
		this.addComponent(new Label(exerciseData.getQuestion()));
		answerField.setValue(oldAnswer);
		this.addComponent(answerField);
		this.addComponent(new Label("adding allowed: " + Boolean.toString(exerciseData.getAddAllowed())));
		this.addComponent(new Label("subtracting allowed: " + Boolean.toString(exerciseData.getSubAllowed())));
		this.addComponent(new Label("multiplicating allowed: " + Boolean.toString(exerciseData.getMultiAllowed())));
		this.addComponent(new Label("division allowed: " + Boolean.toString(exerciseData.getDivAllowed())));
		
	}

	@Override
	public void registerSubmitListener(
			SubmissionListener<BlaxBoxSubmissionInfo> submitListener) {
		execHelper.registerSubmitListener(submitListener);
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void shutdown() {
		// nothing to do here
	}

	@Override
	public void askReset() {
		// nothing to do here
	}

	@Override
	public ExecutionState getCurrentExecutionState() {
		return execHelper.getState();
	}

	@Override
	public void askSubmit(SubmissionType submType) {
		double corr = 1.0;

		String answer = answerField.getValue();
		execHelper.informOnlySubmit(corr, new BlaxBoxSubmissionInfo(answer),
				submType, null);

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
