package fi.utu.ville.exercises;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
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
import fi.utu.ville.standardutils.ui.IntegerField;

public class BlaxBoxExecutor extends VerticalLayout implements
		Executor<BlaxBoxExerciseData, BlaxBoxSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;

	private final ExerciseExecutionHelper<BlaxBoxSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<BlaxBoxSubmissionInfo>();

	private final TextField answerField = new TextField();
	
	private IntegerField tf3;
	private Label tf4;
	private Button b1;
    private TextField tf1;
    private TextField tf2;
    private Label l1;
    private Label l2;
    private Button b3;
    private Button b2;
    private Label l3;
    private HorizontalLayout h1;
    private HorizontalLayout h2;
    private Label l4;
    private Label l5;
    private TextArea ta;
    private VerticalLayout container1;
    private VerticalLayout container2;
    private HorizontalSplitPanel p;
    private int x;
    private Random r = new Random();
    private int[] y = new int[3];
    private String resultListString;
    private TextField tf5;
    private TextField tf6;
    private TextField tf7;
    private TextField tf8;
    private Label l6;
    private Label l7;
    private HorizontalLayout h5;
    private int z1 = r.nextInt(21);
    private int z2 = r.nextInt(21);
    private int z3 = r.nextInt(21);
    private HorizontalLayout h3;
    private HorizontalLayout h4;
    private BlaxExpression problem;
	private Image imageKone;
	private Image imageRatas;
	
    
    private TextField[] inputFields;
	private TextField[] outputFields;
	private HorizontalLayout[] answerLayouts;
	private Image[] correctImages;
	private Image[] incorrectImages;
	
	private float rightAnswers = 0;
	private float answers = 0;
	private float successRate = 0;
	
	private float numberOfGos = 0;
	private float numberOfGosRate = 0;
	
private BlaxBoxExerciseData exerciseData;
	public BlaxBoxExecutor() {

		
	}

	
	@Override
	public void initialize(Localizer localizer,
			BlaxBoxExerciseData exerciseData, BlaxBoxSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		this.exerciseData = exerciseData;
		answerField.setCaption(localizer.getUIText(BlaxBoxUiConstants.ANSWER));
		doLayout(exerciseData, oldSubm != null ? oldSubm.getAnswer() : "");
	}

	private boolean isAnswerCorrect (String answerString, String correctAnswerString)
	{
		double answer, correctAnswer;

		try
		{
			answer = Double.parseDouble (answerString);
			correctAnswer = Double.parseDouble (correctAnswerString); // *should* succeed
		}
		catch (NumberFormatException e)
		{
			// failed to parse the number to double
			return false;
		}

		return (Math.abs (answer - correctAnswer) < 0.01);
	}

	private boolean checkUserAnswer (int i)
	{
		if (i < 0 || i >= 3)
			return false;

		problem.setInput (0, inputFields[i].getValue());
		boolean r = isAnswerCorrect (outputFields[i].getValue(), problem.evaluate());

		if (r)
		{
			answerLayouts[i].removeComponent (incorrectImages[i]);
			answerLayouts[i].addComponent (correctImages[i]);
		}
		else
		{
			answerLayouts[i].removeComponent (correctImages[i]);
			answerLayouts[i].addComponent (incorrectImages[i]);
		}

		return r;
	}

	public void doLayout(BlaxBoxExerciseData exerciseData, String oldAnswer) {
		answerField.setValue(oldAnswer);
		p = new HorizontalSplitPanel();
		tf1 = new TextField();
		tf1.setValue(z1+"");
		tf2 = new TextField();
		ta = new TextArea("Results list");
		ta.setHeight(null);
		ta.setWidth("100px");
		ta.addStyleName("resultsArea");
		l1 = new Label(" -> ");
		b1 = new Button("GO!");
		//l2 = new Label("Get it ? Click the button to continue.");	
		container1 = new VerticalLayout();
		container2 = new VerticalLayout();
		b3 = new Button("OK");
		l3 = new Label();
		l4 = new Label();
		//l5 = new Label(" -> ");
		tf3 = new IntegerField();
		tf3.setWidth("80px");
		tf3.addStyleName("header1");
		tf3.setMaxLength(2);
		tf4 = new Label();
		tf4.setWidth("120px");
		tf4.addStyleName("header1");
		tf5 = new TextField();
		tf5.setValue(z2+"");
		tf6 = new TextField();
		tf7 = new TextField();
		tf7.setValue(z3+"");
		tf8 = new TextField();
		l6 = new Label(" -> ");
		l7 = new Label(" -> ");
		l5 = new Label(" -> ");
		resultListString = "";

		ThemeResource correctResource = new ThemeResource("correct.jpg");
		ThemeResource incorrectResource = new ThemeResource("incorrect.png");
		correctImages = new Image[3];
		incorrectImages = new Image[3];

		for (int i = 0; i < 3; ++i)
		{
			correctImages[i] = new Image (null, correctResource);
			correctImages[i].setWidth (5, Unit.MM);
			correctImages[i].setHeight (5, Unit.MM);
 
			incorrectImages[i] = new Image (null, incorrectResource);
			incorrectImages[i].setWidth (5, Unit.MM);
			incorrectImages[i].setWidth (5, Unit.MM);
		}

		
		Label successPercentLabel = new Label("Success rate: " + Float.toString(successRate) + "%");
		Label successPerRun = new Label("Right answers per runs: " + Float.toString(numberOfGosRate));
		
		// Generate a problem
		BlaxExpressionProfile profile = new BlaxExpressionProfile();
		profile.numInputs = 1;
		profile.minOperators = profile.maxOperators = exerciseData.getAmount();
		profile.operators = new ArrayList<String>();

		if (exerciseData.getAddAllowed()) profile.operators.add ("Addition");
		if (exerciseData.getSubAllowed()) profile.operators.add ("Subtraction");
		if (exerciseData.getMultiAllowed()) profile.operators.add ("Multiplication");
		if (exerciseData.getDivAllowed()) profile.operators.add ("Division");

		problem = new BlaxExpression (profile);

		ThemeResource resourceKone = new ThemeResource("kone.png");
		imageKone = new Image(null, resourceKone);		
		ThemeResource resourceRatas = new ThemeResource("ratas.png");
		imageRatas = new Image(null, resourceRatas);				
		
		 h1 = new HorizontalLayout();
	 h2 = new HorizontalLayout();
		h3= new HorizontalLayout();
		h4= new HorizontalLayout();
		h5= new HorizontalLayout();
		b1.addClickListener(new Button.ClickListener()
		{@Override 
			public void buttonClick(ClickEvent event)
			{
				String x = tf3.getValue();
				problem.setInput (0, x);

				String resultString = problem.evaluate();
				tf4.setValue (resultString);
				resultListString += (x + " -> " + resultString + "\n");
				ta.setValue (resultListString);
			}
		});
			
		b3.addClickListener(new Button.ClickListener()
		{@Override 
			public void buttonClick(ClickEvent event)
			{
				for (int i = 0; i < 3; ++i)
					checkUserAnswer (i);
			}
		});
		
		
		h2.addComponent(tf3);
		h2.setComponentAlignment(tf3, Alignment.BOTTOM_CENTER);
		//h1.addComponent(l5);
		//h1.addComponent(tf4);
		container1.addComponent(h1);
		container1.addComponent(h2);
		container1.addComponent(b1);
		container1.addComponent(ta);

		container1.setComponentAlignment(h1, Alignment.MIDDLE_CENTER);
		container1.setComponentAlignment(b1, Alignment.MIDDLE_CENTER);
		container1.setComponentAlignment(h2, Alignment.MIDDLE_CENTER);
		container1.setComponentAlignment(ta, Alignment.MIDDLE_CENTER);
		
		//h2.addComponent(l2);
		
		//Label insertPicture = new Label("*insert picture here*");
		h2.addComponent(imageKone);
		h2.addComponent(tf4);
		h2.setComponentAlignment(imageKone, Alignment.MIDDLE_CENTER);
		h2.setComponentAlignment(tf4, Alignment.BOTTOM_CENTER);	

		h3.addComponent(tf1);
		h3.addComponent(l1);
		h3.addComponent(tf2);
		h4.addComponent(tf5);
		h4.addComponent(l6);
		h4.addComponent(tf6);
		h5.addComponent(tf7);
		h5.addComponent(l7);
		h5.addComponent(tf8);
		
		container2.addComponent(h3);
		container2.addComponent(h4);
		container2.addComponent(h5);
		container2.addComponent(b3);

//		container2.addComponent(successPercentLabel);
//		container2.addComponent(successPerRun);
		
		p.setFirstComponent(container1);
		p.setSecondComponent(container2);
		addComponent(p);

		inputFields = new TextField[] {tf1, tf5, tf7};
		outputFields = new TextField[] {tf2, tf6, tf8};
		answerLayouts = new HorizontalLayout[] {h3, h4, h5};

		container1.setMargin(true);
		container1.setSpacing(true);
		container2.setMargin(true);
		container2.setSpacing(true);
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
		this.removeAllComponents();
		doLayout(exerciseData, "");
		
	}

	@Override
	public ExecutionState getCurrentExecutionState() {
		return execHelper.getState();
	}

	@Override
	public void askSubmit(SubmissionType submType)
	{
		double corr = 0.0;

		for (int i = 0; i < 3; ++i)
		{
			if (checkUserAnswer (i))
				corr += 1.0;
		}
		
		
		execHelper.informOnlySubmit (corr/3, null, submType, null);
	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
