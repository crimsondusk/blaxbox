package fi.utu.ville.exercises;

import java.util.Random;

import com.vaadin.server.ClassResource;
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
	private TextField tf4;
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
    private int z = r.nextInt(3);
    private int[] y = new int[3];
    private String s;
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
    private ThemeResource image1;
    private ThemeResource image2;
    private Image correct1;
    private Image correct2;
    private Image correct3;
    private Image incorrect1;
    private Image incorrect2;
    private Image incorrect3;
    private HorizontalLayout h3;
    private HorizontalLayout h4;
	private Image imageKone;
	private Image imageRatas;

    
    public static int function(int type, int x)
    {
    	int y=0;
    
    	switch(type)
    	{
    	case 0: y = 3*x-1; break;
    	case 1: y = (int) Math.pow(x, 2); break;
    	case 2: y = x+4; break;
    	case 3: y = (x-3)*3; break;
    	default: break;
    	}
    	return y;
    }

    
    
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
		answerField.setValue(oldAnswer);
		p = new HorizontalSplitPanel();
		tf1 = new TextField();
		tf1.setValue(z1+"");
		tf2 = new TextField();
		ta = new TextArea("Results list");
		ta.setHeight(2, Unit.CM);
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
		tf4 = new TextField();
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
		s = "";
		image1 = new ThemeResource("correct.jpg");
		image2 = new ThemeResource("incorrect.png");
		correct1 = new Image(null,image1);
		correct1.setWidth(5,Unit.MM);
		correct1.setHeight(5,Unit.MM);
		
		correct2 = new Image(null,image1);
		correct2.setWidth(5,Unit.MM);
		correct2.setHeight(5,Unit.MM);
		
		correct3 = new Image(null,image1);
		correct3.setWidth(5,Unit.MM);
		correct3.setHeight(5,Unit.MM);
		
		incorrect1 = new Image(null,image2);
		incorrect1.setWidth(5,Unit.MM);
		incorrect1.setWidth(5,Unit.MM);
		
		incorrect2 = new Image(null,image2);
		incorrect2.setWidth(5,Unit.MM);
		incorrect2.setWidth(5,Unit.MM);
		
		incorrect3 = new Image(null,image2);
		incorrect3.setWidth(5,Unit.MM);
		incorrect3.setWidth(5,Unit.MM);
		
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
			public void buttonClick(ClickEvent event) {
			x = Integer.parseInt(tf3.getValue());
			
			String s2 = function(z, x)+"";
				tf4.setValue(s2);
				s= s+x+" -> "+s2+"\n";
				ta.setValue(s);
			}

		
		});
			
		b3.addClickListener(new Button.ClickListener()
		{@Override 
			public void buttonClick(ClickEvent event) {
			if(tf2.getValue().equals(function(z,Integer.parseInt(tf1.getValue()))+""))
			{
				h3.addComponent(correct1);
			}
			else
			{
				h3.addComponent(incorrect1);
			}
			
			if(tf6.getValue().equals(function(z,Integer.parseInt(tf5.getValue()))+""))
			{
				h4.addComponent(correct2);
			}
			else
			{
				h4.addComponent(incorrect2);
			}
			
			if(tf8.getValue().equals(function(z,Integer.parseInt(tf7.getValue()))+""))
			{
				h5.addComponent(correct3);
			}
			else
			{
				h5.addComponent(incorrect3);
			}
		}});
		
		
		h2.addComponent(tf3);
		h2.setComponentAlignment(tf3, Alignment.MIDDLE_CENTER);
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
		h2.setComponentAlignment(tf4, Alignment.MIDDLE_CENTER);	

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
		double corr = 0;

		if(tf2.getValue().equals(function(z,Integer.parseInt(tf1.getValue()))+""))
		{
			h3.addComponent(correct1);
			corr+=0.5;
		}
		else
		{
			h3.addComponent(incorrect1);
		}
		
		if(tf6.getValue().equals(function(z,Integer.parseInt(tf5.getValue()))+""))
		{
			h4.addComponent(correct2);
			corr+=0.5;
		}
		else
		{
			h4.addComponent(incorrect2);
		}
		
		if(tf8.getValue().equals(function(z,Integer.parseInt(tf7.getValue()))+""))
		{
			h5.addComponent(correct3);
			corr+=0.5;
		}
		else
		{
			h5.addComponent(incorrect3);
		}
		execHelper.informOnlySubmit(corr, null,
				submType, null);

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
