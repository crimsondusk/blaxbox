package fi.utu.ville.exercises;

import java.io.File;

import org.vaadin.risto.stepper.IntStepper;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.model.Editor;
import fi.utu.ville.exercises.model.EditorHelper;
import fi.utu.ville.exercises.model.EditorHelper.EditedExerciseGiver;
import fi.utu.ville.standardutils.AFFile;
import fi.utu.ville.standardutils.AbstractFile;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.SimpleFileUploader;
import fi.utu.ville.standardutils.StandardUIFactory;
import fi.utu.ville.standardutils.SimpleFileUploader.UploaderListener;
import fi.utu.ville.standardutils.ui.AbstractEditorLayout;
import fi.utu.ville.exercises.model.VilleContent;
import fi.utu.ville.exercises.model.VilleUI;

public class BlaxBoxEditor extends VilleContent implements
		Editor<BlaxBoxExerciseData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4600841604409240872L;
	
	private EditorHelper<BlaxBoxExerciseData> editorHelper;

	private IntStepper numberOfOperations;

	private Localizer localizer;
	
	private AbstractEditorLayout layout;
	
	private CheckBox checkboxadd = new CheckBox("adding");
	
	private CheckBox checkboxsubtract = new CheckBox("subtracting");
	
	private CheckBox checkboxmultiplicate = new CheckBox("multiplication");
	
	private CheckBox checkboxdivision = new CheckBox("division");
	
	


	public BlaxBoxEditor() {
		super(null);
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void initialize(VilleUI ui, Localizer localizer, BlaxBoxExerciseData oldData,
			EditorHelper<BlaxBoxExerciseData> editorHelper) {
		this.init(ui);
		this.localizer = localizer;

		this.editorHelper = editorHelper;

		editorHelper.getTempManager().initialize();
		if (oldData != null) {
			doLayout(oldData);
		}
		else {
			oldData = new BlaxBoxExerciseData(1, true, false, false, false);
			doLayout(oldData);
		}
	}

	private BlaxBoxExerciseData getCurrentExercise() {
		return new BlaxBoxExerciseData(numberOfOperations.getValue(), checkboxadd.getValue(), checkboxsubtract.getValue(), checkboxmultiplicate.getValue(), checkboxdivision.getValue());
	}

	@Override
	public boolean isOkToExit() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void doLayout() {
		
	}
	
	public void doLayout(BlaxBoxExerciseData oldData) {

		this.setMargin(false);
		this.setSpacing(false);
		this.setWidth("100%");

		layout = StandardUIFactory.getTwoColumnView();
		addComponent(layout);
		
		layout.setTitle("Editor");

		layout.addToLeft(editorHelper.getInfoEditorView());

		layout.addToTop(editorHelper
				.getControlbar(new EditedExerciseGiver<BlaxBoxExerciseData>() {

					@Override
					public BlaxBoxExerciseData getCurrExerData(
							boolean forSaving) {
						return getCurrentExercise();
					}
				}));


		VerticalLayout editlayout = new VerticalLayout();

		Label questionTextCapt = new Label(
				localizer.getUIText("How many operations?"/*BlaxBoxUiConstants.QUESTION*/));
		questionTextCapt.addStyleName(BlaxBoxThemeConsts.TITLE_STYLE);
		numberOfOperations = new IntStepper();
		numberOfOperations.setValue(oldData.getAmount());
		numberOfOperations.setMinValue(1);
		numberOfOperations.setWidth("40px");
		
		
		editlayout.addComponent(questionTextCapt);
		editlayout.addComponent(numberOfOperations);
		editlayout.addComponent(checkboxadd);
		editlayout.addComponent(checkboxsubtract);
		editlayout.addComponent(checkboxmultiplicate);
		editlayout.addComponent(checkboxdivision);
		layout.addToRight(editlayout);

	}
	
	@Override
	public String getViewName(){
		return "StubExercise";
	}
}
