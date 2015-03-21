package fi.utu.ville.exercises;

import java.util.Random;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
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

	
	private TextField tf3;
	private TextField tf4;
	private Button b1;
    private TextField tf1;
    private TextField tf2;
    private Label l1;
    private Label l2;
    private Button b3;
    private Button b4;
    private Label l3;
    private Label l4;
    private Label l5;
    private VerticalLayout container1;
    private VerticalLayout container2;
    private HorizontalSplitPanel p;
    private int x;
    private Random r = new Random();
    private int z = r.nextInt(3);
    private int[] y = new int[3];
	public BlaxBoxExecutor() {

		
	}

	
	@Override
	public void initialize(Localizer localizer,
			BlaxBoxExerciseData exerciseData, BlaxBoxSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		
	}

	private void doLayout(BlaxBoxExerciseData exerciseData, String oldAnswer) {
		this.addComponent(new Label(exerciseData.getAmount() + ""));
		this.addComponent(new Label("adding allowed: " + Boolean.toString(exerciseData.getAddAllowed())));
		this.addComponent(new Label("subtracting allowed: " + Boolean.toString(exerciseData.getSubAllowed())));
		this.addComponent(new Label("multiplicating allowed: " + Boolean.toString(exerciseData.getMultiAllowed())));
		this.addComponent(new Label("division allowed: " + Boolean.toString(exerciseData.getDivAllowed())));
		p = new HorizontalSplitPanel();
		tf1 = new TextField();
		tf2 = new TextField();
		l1 = new Label(" -> ");
		b1 = new Button("GO!");
		l2 = new Label("Get it ? Click the button to continue.");	
		container1 = new VerticalLayout();
		container2 = new VerticalLayout();
		b3 = new Button("OK");
		b4 = new Button("Previous");
		l3 = new Label();
		l4 = new Label();
		l5 = new Label(" -> ");
		tf3 = new TextField();
		tf4 = new TextField();
		l5 = new Label("->");
		
		
		b1.addClickListener(new Button.ClickListener()
		{@Override 
			public void buttonClick(ClickEvent event) {
			x = Integer.parseInt(tf3.getValue());
			y[0]=(x+3);
			y[1] = 3*x-1;
			y[2] = (int)Math.pow(x, 2);
				tf4.setValue(y[z]+"");
			}

		
		});
			
		this.addComponent(new Label(exerciseData.getAmount() + ""));
		HorizontalLayout h1 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		HorizontalLayout h3 = new HorizontalLayout();
		HorizontalLayout h4 = new HorizontalLayout();
		
		h1.addComponent(tf3);
		h1.addComponent(l5);
		h1.addComponent(tf4);
		container1.addComponent(h1);
		container1.addComponent(b1);
		container1.addComponent(h2);
		h2.addComponent(l2);
		
		h3.addComponent(tf1);
		h3.addComponent(l1);
		h3.addComponent(tf2);
		h4.addComponent(b3);
		container2.addComponent(h3);
		container2.addComponent(h4);

		p.setFirstComponent(container1);
		p.setSecondComponent(container2);
		addComponent(p);
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

		

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
