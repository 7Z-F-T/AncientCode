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
	MyCanvas canvas;
	
	
	//menu
	JMenuBar menuBar=new JMenuBar();
	JMenu file=new JMenu("File");
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
	JToolBar toolBox=new JToolBar("Tool Box",SwingConstants.VERTICAL);
	
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

	
	//propertyPanel
	JPanel propertyPanel=new JPanel();
	
	
	
	
	
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
    	setSize(800,600);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//menu
    	menuBar.add(file);
    	menuBar.add(lookAndFeel);
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
    	toolBox.add(selectButton);
    	toolBox.add(rectangleButton);
    	toolBox.add(lineButton);
    	toolBox.add(ellipseButton);
       	toolBox.add(rotateButton);
    	toolBox.add(scaleButton);
    	
    	//mainPanel
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(toolBar,"North");
    	mainPanel.add(propertyPanel,"East");
    	mainPanel.add(toolBox,"West");
    	mainPanel.add(canvas);
    	
    	//frame
    	add(mainPanel);
    	setJMenuBar(menuBar);
    	 
    	//only a test,deletable
    	shapeProArray.add(new ShapePro(new Ellipse2D.Double(10, 10, 100, 50),currentPaintProperty.currentStroke,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,0,0,false,1.0,1.0));
    	//SwingUtilities.updateComponentTreeUI(frame);
    	
        /////////////////////////////////////////////////////////////////
    	//Add Handler
    	/////////////////////////////////////////////////////////////////
    	
    	//add menu handler
    	exit.addActionListener(new MenuHandler.Exit(this));
    	mac.addActionListener(new MenuHandler.Mac(this));
    	metal.addActionListener(new MenuHandler.Metal(this));
    	motif.addActionListener(new MenuHandler.Motif(this));
    	windows.addActionListener(new MenuHandler.Windows(this));
    	
    	//add tool box handler
    	rectangleButton.addActionListener(new ToolBoxHandler.RectangleButton(this));
    	lineButton.addActionListener(new ToolBoxHandler.LineButton(this));
    	ellipseButton.addActionListener(new ToolBoxHandler.EllipseButton(this));
    	selectButton.addActionListener(new ToolBoxHandler.SelectButton(this));
        rotateButton.addActionListener(new ToolBoxHandler.RotateButton(this));
    	scaleButton.addActionListener(new ToolBoxHandler.ScaleButton(this));
    	
    	//add canvas handler
    	canvas.addMouseMotionListener(new DrawHandler(this,canvas,shapeProArray));
    	canvas.addMouseListener(new DrawHandler(this,canvas,shapeProArray));
    }
    public void run(){
    	setVisible(true);
    }
}
