package frame;
import handler.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;

public class MainFrame extends JFrame{
	/////////////////////////////////////////////////////////////////
	//Field
	/////////////////////////////////////////////////////////////////
	
	//shapeProArray
	public ArrayList<ShapePro> shapeProArray;
	
	//currentPaintProperty
	public PaintProperty currentPaintProperty;

	//currentFunction
	public int currentFunction=0;
	
	//canvas
	public MyCanvas canvas;
	
	
	//menu
	JMenuBar menuBar=new JMenuBar();
	JMenu file=new JMenu("File");
	JMenuItem create=new JMenuItem("New");
	JMenuItem save=new JMenuItem("Save");
	JMenuItem open=new JMenuItem("Open");
	JMenu lookAndFeel=new JMenu("Look And Feel");
	JMenuItem exit=new JMenuItem("Exit");
	JMenuItem mac=new JMenuItem("Mac OS");
	JMenuItem metal=new JMenuItem("Metal");
	JMenuItem motif=new JMenuItem("Motif");
	JMenuItem windows=new JMenuItem("Windows");
	
	//MainPanel
	JPanel mainPanel=new JPanel();
	
	//toolBar
	JToolBar toolBar=new JToolBar(SwingConstants.HORIZONTAL);
	JToggleButton testButton=new JToggleButton("test");
	
	//toolBox
	ButtonGroup toolBoxButtonGroup=new ButtonGroup();
	JPanel toolBox=new JPanel();
	ImageIcon rectangleButtonIcon=new ImageIcon("./src/images/RectangleButton.png");
	JToggleButton rectangleButton=new JToggleButton(rectangleButtonIcon);
	ImageIcon lineButtonIcon=new ImageIcon("./src/images/LineButton.png");
	JToggleButton lineButton=new JToggleButton(lineButtonIcon);
	ImageIcon ellipseButtonIcon=new ImageIcon("./src/images/EllipseButton.png");
	JToggleButton ellipseButton=new JToggleButton(ellipseButtonIcon);
	ImageIcon selectButtonIcon=new ImageIcon("./src/images/SelectButton.png");
	JToggleButton selectButton=new JToggleButton(selectButtonIcon);
	ImageIcon rotateButtonIcon=new ImageIcon("./src/images/RotateButton.png");
	JToggleButton rotateButton=new JToggleButton(rotateButtonIcon);
	ImageIcon scaleButtonIcon=new ImageIcon("./src/images/ScaleButton.png");
	JToggleButton scaleButton=new JToggleButton(scaleButtonIcon);
	ImageIcon shearButtonIcon=new ImageIcon("./src/images/ShearButton.png");
	JToggleButton shearButton=new JToggleButton(shearButtonIcon);
	ImageIcon textButtonIcon=new ImageIcon("./src/images/textButton.png");
	JToggleButton textButton=new JToggleButton(textButtonIcon);
	ImageIcon roundRectangleButtonIcon=new ImageIcon("./src/images/RoundRectangleButton.png");
	JToggleButton roundRectangleButton=new JToggleButton(roundRectangleButtonIcon);

	//colorChooser
	JPanel colorChooser=new JPanel();
	JPanel lineColorChooser=new JPanel();
	JPanel fillColorChooser=new JPanel();
	JLabel linelabel=new JLabel("Line Color:");
	JLabel filllabel=new JLabel("Fill Color: ");
	public JTextField lineColorIndicator;
	public JTextField fillColorIndicator;
	JButton changeLineColorButton=new JButton("Change...");
	JButton changeFillColorButton=new JButton("Change...");
	
	
	//paintPatternChooser
	ButtonGroup paintPatternChooserButtonGroup=new ButtonGroup();
	JPanel paintPatternChooser=new JPanel();
	ImageIcon lineOnlyButtonIcon=new ImageIcon("./src/images/LineOnlyButton.png");
	JToggleButton lineOnlyButton=new JToggleButton(lineOnlyButtonIcon);
	ImageIcon fillOnlyButtonIcon=new ImageIcon("./src/images/FillOnlyButton.png");
	JToggleButton fillOnlyButton=new JToggleButton(fillOnlyButtonIcon);
	ImageIcon lineAndFillButtonIcon=new ImageIcon("./src/images/LineAndFillButton.png");
	JToggleButton lineAndFillButton=new JToggleButton(lineAndFillButtonIcon);
	
	//lineChooser
	JPanel lineChooser=new JPanel();
	JPanel lineStyleChooser=new JPanel();
	JPanel lineWidthChooser1=new JPanel();
	JPanel lineWidthChooser2=new JPanel();
	JLabel lineStyleIndicator1=new JLabel("Line Style:");
	public JLabel lineStyleIndicator2=new JLabel("Continuum");
	JButton changeLineStyleButton=new JButton("Change...");
	JLabel lineWidthIndicator1=new JLabel("Line Width:");
	public JLabel lineWidthIndicator2=new JLabel("4");
	public JSlider lineWidthChooserSlider=new JSlider(1,30);
	
	//previewPanel
	public MyLittleCanvas previewCanvas;
	
	//fontChooserPanel
	JPanel fontChooser=new JPanel();
	
	//arcWidthHeightChooser
	JPanel arcWidthHeightChooser=new JPanel();
	JPanel arcWidthHeightPanel1=new JPanel();
	JPanel arcWidthHeightPanel2=new JPanel();
	JLabel arcWidthLabel=new JLabel("Arc Width");
	public JLabel arcWidthIndicator=new JLabel("30");
	JLabel arcHeightLabel=new JLabel("Arc Height");
	public JLabel arcHeightIndicator=new JLabel("30");
	public JSlider arcWidthSlider=new JSlider(1,99);
	public JSlider arcHeightSlider=new JSlider(1,99);
	
	
	//propertyPanel
	JPanel propertyPanel=new JPanel();
	
	//TabbedPane
	JTabbedPane toolBoxTab=new JTabbedPane();
	JTabbedPane paintPatternChooserTab=new JTabbedPane();
	JTabbedPane colorChooserTab=new JTabbedPane();
	JTabbedPane fillStyleChooserTab=new JTabbedPane();
	JTabbedPane lineChooserTab=new JTabbedPane();
	JTabbedPane previewTab=new JTabbedPane();
	JTabbedPane fontChooserTab=new JTabbedPane();
	
	
	
	
	
	
    /////////////////////////////////////////////////////////////////
	//Method
	/////////////////////////////////////////////////////////////////
	public MainFrame(){
		shapeProArray=new ArrayList();
		currentPaintProperty=new PaintProperty();
		canvas=new MyCanvas(shapeProArray,currentPaintProperty);
	}
	
	public void initialize(){
    	////UI initialize////
		
    	setTitle("Artist Pro");
    	setSize(1200,750);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    	//menu
    	menuBar.add(file);
    	menuBar.add(lookAndFeel);
    	file.add(create);
    	file.add(open);
    	file.add(save);
    	file.addSeparator();
    	file.add(exit);
    	lookAndFeel.add(mac);
    	lookAndFeel.add(metal);
    	lookAndFeel.add(motif);
    	lookAndFeel.add(windows);
    	
    	
    	//toolBar
    	toolBar.add(testButton);
    	
    	//toolBox
    	toolBoxButtonGroup.add(rectangleButton);
    	toolBoxButtonGroup.add(lineButton);
    	toolBoxButtonGroup.add(ellipseButton);	
    	toolBoxButtonGroup.add(selectButton);
    	toolBoxButtonGroup.add(rotateButton);
    	toolBoxButtonGroup.add(scaleButton);
    	toolBoxButtonGroup.add(shearButton);
    	toolBoxButtonGroup.add(textButton);
    	toolBoxButtonGroup.add(roundRectangleButton);
    	toolBox.setLayout(new GridLayout(9,1));
    	toolBox.add(rectangleButton);
    	toolBox.add(roundRectangleButton);
    	toolBox.add(ellipseButton);
    	toolBox.add(lineButton);
    	toolBox.add(textButton);
    	toolBox.add(selectButton);
       	toolBox.add(rotateButton);
    	toolBox.add(scaleButton);
    	toolBox.add(shearButton);
    	
    	//paintPatternChooser
    	paintPatternChooserButtonGroup.add(lineOnlyButton);
    	paintPatternChooserButtonGroup.add(fillOnlyButton);
    	paintPatternChooserButtonGroup.add(lineAndFillButton);
    	paintPatternChooser.setLayout(new FlowLayout());
    	paintPatternChooser.add(lineOnlyButton);
    	paintPatternChooser.add(fillOnlyButton);
    	paintPatternChooser.add(lineAndFillButton);
    	
    	//colorChooser
    	colorChooser.setLayout(new GridLayout(2,1));
    	colorChooser.add(lineColorChooser);
    	colorChooser.add(fillColorChooser);
    	lineColorIndicator=new JTextField("   ");
    	lineColorIndicator.disable();
    	lineColorIndicator.setBackground(currentPaintProperty.currentLineColor);
    	fillColorIndicator=new JTextField("   ");
    	fillColorIndicator.disable();
    	fillColorIndicator.setBackground(currentPaintProperty.currentFillColor);
    	lineColorChooser.add(linelabel);
    	lineColorChooser.add(lineColorIndicator);
    	lineColorChooser.add(changeLineColorButton);
    	fillColorChooser.add(filllabel);
    	fillColorChooser.add(fillColorIndicator);
    	fillColorChooser.add(changeFillColorButton);
    	
    	//lineChooser
    	lineChooser.setLayout(new GridLayout(3,1));
    	lineStyleChooser.add(lineStyleIndicator1);
    	lineStyleChooser.add(lineStyleIndicator2);
    	lineStyleChooser.add(changeLineStyleButton);
    	lineWidthChooser1.add(lineWidthIndicator1);
    	lineWidthChooser1.add(lineWidthIndicator2);
    	lineWidthChooser2.add(lineWidthChooserSlider);
    	lineChooser.add(lineStyleChooser);
    	lineChooser.add(lineWidthChooser1);
    	lineChooser.add(lineWidthChooser2);
    	lineWidthChooserSlider.setValue(4);
    	
    	//previewPanel
    	previewCanvas=new MyLittleCanvas(currentPaintProperty);
    	
    	//paintPatternChooser
    	lineAndFillButton.setSelected(true);
    	
    	//arcWidthHeightChooser
    	arcWidthSlider.setValue(30);
    	arcHeightSlider.setValue(30);
    	arcWidthHeightPanel1.add(arcWidthLabel);
    	arcWidthHeightPanel1.add(arcWidthIndicator);
    	arcWidthHeightPanel1.add(arcWidthSlider);
    	arcWidthHeightPanel2.add(arcHeightLabel);
    	arcWidthHeightPanel2.add(arcHeightIndicator);
    	arcWidthHeightPanel2.add(arcHeightSlider);
    	arcWidthHeightChooser.setLayout(new GridLayout(2,1));
    	arcWidthHeightChooser.add(arcWidthHeightPanel1);
    	arcWidthHeightChooser.add(arcWidthHeightPanel2);
    	
    	
    	//TabbedPane
    	toolBoxTab.add("Toolbox",toolBox);
    	paintPatternChooserTab.add("Paint Pattern",paintPatternChooser);
    	paintPatternChooserTab.add("Line Style Chooser",lineChooser);
    	colorChooserTab.add("Color Chooser",colorChooser);
    	lineChooserTab.add("Line Style",lineChooser);
    	lineChooserTab.add("RoundRectangle Arc",arcWidthHeightChooser);
    	previewTab.add("Preview",previewCanvas);
    	
        
    	    	
    	//propertyPanel
    	propertyPanel.setLayout(new GridLayout(5,1));
    	propertyPanel.add(paintPatternChooserTab);
    	propertyPanel.add(colorChooserTab);
    	propertyPanel.add(lineChooserTab);
    	propertyPanel.add(previewTab);
    	    	
    	//mainPanel
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(toolBar,"North");
    	mainPanel.add(propertyPanel,"East");
    	mainPanel.add(toolBoxTab,"West");
    	mainPanel.add(canvas);
    	    	
    	//frame
    	add(mainPanel);
    	setJMenuBar(menuBar);
    	 
    	//only a test,deletable
    	//shapeProArray.add(new ShapePro(new Ellipse2D.Double(-100, -80, 200, 160),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0));   
    	
        /////////////////////////////////////////////////////////////////
    	//Add Handler
    	/////////////////////////////////////////////////////////////////
    	
    	//add menu handler
    	exit.addActionListener(new MenuHandler.Exit(this));
    	mac.addActionListener(new MenuHandler.Mac(this));
    	metal.addActionListener(new MenuHandler.Metal(this));
    	motif.addActionListener(new MenuHandler.Motif(this));
    	windows.addActionListener(new MenuHandler.Windows(this));
    	save.addActionListener(new MenuHandler.Save(this));
    	open.addActionListener(new MenuHandler.Open(this));
    	create.addActionListener(new MenuHandler.Create(this));
    	
    	//add tool box handler
    	rectangleButton.addActionListener(new ToolBoxHandler.RectangleButton(this));
    	lineButton.addActionListener(new ToolBoxHandler.LineButton(this));
    	ellipseButton.addActionListener(new ToolBoxHandler.EllipseButton(this));
    	selectButton.addActionListener(new ToolBoxHandler.SelectButton(this));
        rotateButton.addActionListener(new ToolBoxHandler.RotateButton(this));
    	scaleButton.addActionListener(new ToolBoxHandler.ScaleButton(this));
    	shearButton.addActionListener(new ToolBoxHandler.ShearButton(this));
    	textButton.addActionListener(new ToolBoxHandler.TextButton(this));
    	roundRectangleButton.addActionListener(new ToolBoxHandler.RoundRectangleButton(this));
    	
    	//add canvas handler
    	canvas.addMouseMotionListener(new DrawHandler(this,canvas,shapeProArray));
    	canvas.addMouseListener(new DrawHandler(this,canvas,shapeProArray));
    	
    	//add colorChooser handler
    	changeLineColorButton.addActionListener(new ColorChooserHandler.LineColorChooser(this));
    	changeFillColorButton.addActionListener(new ColorChooserHandler.FillColorChooser(this));
    	
    	//add lineChooser handler
    	changeLineStyleButton.addActionListener(new LineChooserHandler.LineStyleChooser(this));
    	lineWidthChooserSlider.addChangeListener(new LineChooserHandler.LineWidthChooser(this));
    	
    	//add paintPatternChooser handler
    	lineOnlyButton.addActionListener(new PaintPatternChooserHandler.LineOnlyButton(this));
    	fillOnlyButton.addActionListener(new PaintPatternChooserHandler.FillOnlyButton(this));
    	lineAndFillButton.addActionListener(new PaintPatternChooserHandler.LineAndFillButton(this));
    	
    	//add arcWidhtHeightChooser handler
    	arcWidthSlider.addChangeListener(new ArcWidthHeightChooserHandler.ArcWidthChooser(this));
    	arcHeightSlider.addChangeListener(new ArcWidthHeightChooserHandler.ArcHeightChooser(this));
    }
    public void run(){
    	setVisible(true);
    }
}
