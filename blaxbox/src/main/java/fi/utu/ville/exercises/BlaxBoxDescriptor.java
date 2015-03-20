package fi.utu.ville.exercises;

import com.vaadin.server.Resource;

import fi.utu.ville.exercises.helpers.GsonPersistenceHandler;
import fi.utu.ville.exercises.model.ExerciseTypeDescriptor;
import fi.utu.ville.exercises.model.PersistenceHandler;
import fi.utu.ville.exercises.model.SubmissionStatisticsGiver;
import fi.utu.ville.exercises.model.SubmissionVisualizer;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.StandardIcon;
import fi.utu.ville.standardutils.StandardIcon.IconVariant;

public class BlaxBoxDescriptor implements
		ExerciseTypeDescriptor<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743225101617556960L;

	public static final BlaxBoxDescriptor INSTANCE = new BlaxBoxDescriptor();

	private BlaxBoxDescriptor() {

	}

	@Override
	public PersistenceHandler<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> newExerciseXML() {
		// You can also implement your own PersistenceHandler if you want (see JavaDoc for more info)
		return new GsonPersistenceHandler<BlaxBoxExerciseData, BlaxBoxSubmissionInfo>(
				getTypeDataClass(), getSubDataClass());
	}

	@Override
	public BlaxBoxExecutor newExerciseExecutor() {
		return new BlaxBoxExecutor();
	}

	@Override
	public BlaxBoxEditor newExerciseEditor() {
		return new BlaxBoxEditor();
	}

	@Override
	public Class<BlaxBoxExerciseData> getTypeDataClass() {
		return BlaxBoxExerciseData.class;
	}

	@Override
	public Class<BlaxBoxSubmissionInfo> getSubDataClass() {
		return BlaxBoxSubmissionInfo.class;
	}

	@Override
	public SubmissionStatisticsGiver<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> newStatisticsGiver() {
		return new BlaxBoxStatisticsGiver();
	}

	@Override
	public SubmissionVisualizer<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> newSubmissionVisualizer() {
		return new BlaxBoxSubmissionViewer();
	}

	@Override
	public String getTypeName(Localizer localizer) {
		return localizer.getUIText(BlaxBoxUiConstants.NAME);
	}

	@Override
	public String getTypeDescription(Localizer localizer) {
		return localizer.getUIText(BlaxBoxUiConstants.DESC);
	}

	@Override
	public Resource getSmallTypeIcon() {
		return BlaxBoxIcon.SMALL_TYPE_ICON.getIcon();
	}

	@Override
	public Resource getMediumTypeIcon() {
		return BlaxBoxIcon.SMALL_TYPE_ICON.getIcon();
	}

	@Override
	public Resource getLargeTypeIcon() {
		return BlaxBoxIcon.SMALL_TYPE_ICON.getIcon();
	}
	
	@Override
	public String getHTMLIcon(){
		return StandardIcon.RawIcon.dribbble.variant(IconVariant.ORANGE);
	}
	
	@Override
	public boolean isManuallyGraded() {
		return false;
	}

}