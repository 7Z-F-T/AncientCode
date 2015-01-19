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
	
	//backShapeProArray
	public ArrayList<ArrayList<ShapePro>> backShapeProArrayArray;
	
	//frontShapeProArray
	public ArrayList<ArrayList<ShapePro>> frontShapeProArrayArray;
	
	//clipboardShapePro
	public ShapePro clipboardShapePro;
	
	//currentPaintProperty
	public PaintProperty currentPaintProperty;

	//currentFunction
	public int currentFunction=0;
	
	//canvas
	public MyCanvas canvas;
	
	//flips
	public Flip repaintFlip=new Flip(1);//关注图像发生更改
	public Flip retellFlip=new Flip(1);//关注聊天内容发生更改
	
	//chat text
	public StringBuffer chatRecord=new StringBuffer();
	public String chatText=new String();
	public String nickName=new String("User");
	
	//menu
	JMenuBar menuBar=new JMenuBar();
	JMenu file=new JMenu("File");
	JMenuItem create=new JMenuItem("New");
	JMenuItem save=new JMenuItem("Save...");
	JMenuItem open=new JMenuItem("Open...");
	JMenu lookAndFeel=new JMenu("Look And Feel");
	JMenuItem exit=new JMenuItem("Exit");
	JMenuItem mac=new JMenuItem("Mac OS");
	JMenuItem metal=new JMenuItem("Metal");
	JMenuItem motif=new JMenuItem("Motif");
	JMenuItem windows=new JMenuItem("Windows");
	JMenu network=new JMenu("Network");
	JMenuItem createServer=new JMenuItem("Create Server...");
	JMenuItem connectToServer=new JMenuItem("Connect To Server...");
	JMenu edit=new JMenu("Edit");
	JMenuItem copy=new JMenuItem("Copy");
	JMenuItem cut=new JMenuItem("Cut");
	JMenuItem paste=new JMenuItem("Paste");
	JMenuItem delete=new JMenuItem("Delete");
	JMenu export=new JMenu("Export To Image");
	JMenuItem toJPEG=new JMenuItem("To JPEG Image...");
	JMenuItem toPNG=new JMenuItem("To PNG Image...");
	JMenuItem toBMP=new JMenuItem("To BMP Image...");
	JMenu help=new JMenu("Help");
	JMenuItem about=new JMenuItem("About Artist Pro...");
	JMenuItem undo=new JMenuItem("Undo");
	JMenuItem redo=new JMenuItem("Redo");
	
	//MainPanel
	JPanel mainPanel=new JPanel();
	
	//toolBar
	JToolBar toolBar=new JToolBar();
	ImageIcon saveButtonIcon=new ImageIcon("images/saveButton.png");
	JButton saveButton=new JButton(saveButtonIcon);
	ImageIcon openButtonIcon=new ImageIcon("images/openButton.png");
	JButton openButton=new JButton(openButtonIcon);
	ImageIcon newButtonIcon=new ImageIcon("images/newButton.png");
	JButton newButton=new JButton(newButtonIcon);
	ImageIcon cutButtonIcon=new ImageIcon("images/cutButton.png");
	JButton cutButton=new JButton(cutButtonIcon);
	ImageIcon copyButtonIcon=new ImageIcon("images/copyButton.png");
	JButton copyButton=new JButton(copyButtonIcon);
	ImageIcon pasteButtonIcon=new ImageIcon("images/pasteButton.png");
	JButton pasteButton=new JButton(pasteButtonIcon);
	ImageIcon undoButtonIcon=new ImageIcon("images/undoButton.png");
	JButton undoButton=new JButton(undoButtonIcon);
	ImageIcon redoButtonIcon=new ImageIcon("images/redoButton.png");
	JButton redoButton=new JButton(redoButtonIcon);
	
	//toolBox
	ButtonGroup toolBoxButtonGroup=new ButtonGroup();
	JPanel toolBox=new JPanel();
	ImageIcon rectangleButtonIcon=new ImageIcon("images/RectangleButton.png");
	JToggleButton rectangleButton=new JToggleButton(rectangleButtonIcon);
	ImageIcon lineButtonIcon=new ImageIcon("images/LineButton.png");
	JToggleButton lineButton=new JToggleButton(lineButtonIcon);
	ImageIcon ellipseButtonIcon=new ImageIcon("images/EllipseButton.png");
	JToggleButton ellipseButton=new JToggleButton(ellipseButtonIcon);
	ImageIcon selectButtonIcon=new ImageIcon("images/SelectButton2.png");
	JToggleButton selectButton=new JToggleButton(selectButtonIcon);
	ImageIcon rotateButtonIcon=new ImageIcon("images/RotateButton2.png");
	JToggleButton rotateButton=new JToggleButton(rotateButtonIcon);
	ImageIcon scaleButtonIcon=new ImageIcon("images/ScaleButton2.png");
	JToggleButton scaleButton=new JToggleButton(scaleButtonIcon);
	ImageIcon shearButtonIcon=new ImageIcon("images/ShearButton2.png");
	JToggleButton shearButton=new JToggleButton(shearButtonIcon);
	ImageIcon textButtonIcon=new ImageIcon("images/textButton.png");
	JToggleButton textButton=new JToggleButton(textButtonIcon);
    ImageIcon roundRectangleButtonIcon=new ImageIcon("images/RoundRectangleButton.png");
	JToggleButton roundRectangleButton=new JToggleButton(roundRectangleButtonIcon);

	

	//colorChooser
	JPanel colorChooser=new JPanel();
	JPanel lineColorChooser=new JPanel();
	JPanel fillColorChooser=new JPanel();
	JPanel fillColorChooser2=new JPanel();
	JLabel linelabel=new JLabel("Line Color:  ");
	JLabel filllabel=new JLabel("Fill Color:    ");
	JLabel filllabel2=new JLabel("Fill Color 2: ");
	public JTextField lineColorIndicator;
	public JTextField fillColorIndicator;
	public JTextField fillColorIndicator2;
	JButton changeLineColorButton=new JButton("Change...");
	JButton changeFillColorButton=new JButton("Change...");
	JButton changeFillColorButton2=new JButton("Change...");
	
	
	//paintPatternChooser
	ButtonGroup paintPatternChooserButtonGroup=new ButtonGroup();
	JPanel paintPatternChooser=new JPanel();
	ImageIcon lineOnlyButtonIcon=new ImageIcon("images/LineOnlyButton.png");
	public JToggleButton lineOnlyButton=new JToggleButton(lineOnlyButtonIcon);
	ImageIcon fillOnlyButtonIcon=new ImageIcon("images/FillOnlyButton.png");
	public JToggleButton fillOnlyButton=new JToggleButton(fillOnlyButtonIcon);
	ImageIcon lineAndFillButtonIcon=new ImageIcon("images/LineAndFillButton.png");
	public JToggleButton lineAndFillButton=new JToggleButton(lineAndFillButtonIcon);
	public JToggleButton gradientFillButton=new JToggleButton("Set"); 
	public JCheckBox gradientFillCheck=new JCheckBox("Gradient Paint");
	JPanel paintPatternChooserPanel1=new JPanel();
	JPanel paintPatternChooserPanel2=new JPanel();
	
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
	
	//faceIcon
	public ImageIcon smileIcon=new ImageIcon("images/smile.gif");
	public ImageIcon cryIcon=new ImageIcon("images/cry.gif");
	public ImageIcon faintIcon=new ImageIcon("images/faint.gif");
	public ImageIcon byeIcon=new ImageIcon("images/bye.gif");
	public ImageIcon angryIcon=new ImageIcon("images/angry.gif");
	public ImageIcon bsIcon=new ImageIcon("images/bs.gif");
	public ImageIcon omgIcon=new ImageIcon("images/omg.gif");
	public ImageIcon hahaIcon=new ImageIcon("images/haha.gif");
	
	//chatPanel
	JPanel chatPanel=new JPanel();
	public JTextPane publicWords=new JTextPane();
	//public JTextField privateWords=new JTextField(18);
	public JTextArea privateWords=new JTextArea(1,18);
	JButton faceButton=new JButton(smileIcon);
	JButton sendButton=new JButton("Send");
	public JScrollPane chatRecordScroll=new JScrollPane(publicWords,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	
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
	JTabbedPane chatTab=new JTabbedPane();
	
	//key stroke
	KeyStroke key_cut=KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK);
	KeyStroke key_copy=KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK);
	KeyStroke key_paste=KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK);
	KeyStroke key_delete=KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0);
	KeyStroke key_open=KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK);
	KeyStroke key_new=KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK);
	KeyStroke key_save=KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK);
	KeyStroke key_undo=KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK);
	KeyStroke key_redo=KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK);
	
	
	
	
    /////////////////////////////////////////////////////////////////
	//Method
	/////////////////////////////////////////////////////////////////
	public MainFrame(){
		shapeProArray=new ArrayList<ShapePro>();
		backShapeProArrayArray=new ArrayList<ArrayList<ShapePro>>();
		frontShapeProArrayArray=new ArrayList<ArrayList<ShapePro>>();
		currentPaintProperty=new PaintProperty();
		canvas=new MyCanvas(shapeProArray,currentPaintProperty);
	}
	
	public void initialize(){
    	////UI initialize////
		
    	setTitle("Artist Pro");
    	setSize(1200,760);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    	//menu
    	menuBar.add(file);
    	menuBar.add(edit);
    	menuBar.add(network);
    	menuBar.add(lookAndFeel);
    	menuBar.add(help);
    	file.add(create);
    	file.add(open);
    	file.add(save);
    	file.addSeparator();
    	file.add(export);
    	export.add(toJPEG);
    	export.add(toBMP);
    	export.add(toPNG);
    	file.addSeparator();
    	file.add(exit);
    	lookAndFeel.add(mac);
    	lookAndFeel.add(metal);
    	lookAndFeel.add(motif);
    	lookAndFeel.add(windows);
    	network.add(createServer);
    	network.addSeparator();
    	network.add(connectToServer);
    	edit.add(undo);
    	edit.add(redo);
    	edit.addSeparator();
    	edit.add(cut);
    	edit.add(copy);
    	edit.add(paste);
    	edit.add(delete);
    	help.add(about);
    	
    	
    	
    	
    	//toolBar
    	toolBar.add(newButton);
    	toolBar.add(openButton);
    	toolBar.add(saveButton);
    	toolBar.addSeparator();
    	toolBar.add(cutButton);
    	toolBar.add(copyButton);
    	toolBar.add(pasteButton);
    	toolBar.addSeparator();
    	toolBar.add(undoButton);
    	toolBar.add(redoButton);
    	
    	
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
    	rectangleButton.setToolTipText("Draw Rectangle");
    	lineButton.setToolTipText("Draw Line");
    	ellipseButton.setToolTipText("Draw Ellipse");
    	roundRectangleButton.setToolTipText("Draw Round Rectangle");
    	textButton.setToolTipText("Insert Text");
    	selectButton.setToolTipText("Select Tool");
    	rotateButton.setToolTipText("Rotate Tool");
    	scaleButton.setToolTipText("Scale Tool");
    	shearButton.setToolTipText("Shear Tool");
    	
    	
    	//paintPatternChooser
    	paintPatternChooserButtonGroup.add(lineOnlyButton);
    	paintPatternChooserButtonGroup.add(fillOnlyButton);
    	paintPatternChooserButtonGroup.add(lineAndFillButton);
    	paintPatternChooser.setLayout(new GridLayout(2,1));
    	paintPatternChooser.add(paintPatternChooserPanel1);
    	paintPatternChooser.add(paintPatternChooserPanel2);
    	paintPatternChooserPanel1.add(lineOnlyButton);
    	paintPatternChooserPanel1.add(fillOnlyButton);
    	paintPatternChooserPanel1.add(lineAndFillButton);
    	paintPatternChooserPanel2.add(gradientFillCheck);
    	paintPatternChooserPanel2.add(gradientFillButton);
    	lineOnlyButton.setToolTipText("Border Line Only");
    	fillOnlyButton.setToolTipText("Fill Only");
    	lineAndFillButton.setToolTipText("Border Line and Fill");
    	gradientFillCheck.setEnabled(false);
    	gradientFillButton.setEnabled(false);
    	
    	//colorChooser
    	colorChooser.setLayout(new GridLayout(3,1));
    	colorChooser.add(lineColorChooser);
    	colorChooser.add(fillColorChooser);
    	colorChooser.add(fillColorChooser2);
    	lineColorIndicator=new JTextField("   ");
    	lineColorIndicator.setBackground(currentPaintProperty.currentLineColor);
    	fillColorIndicator=new JTextField("   ");
    	fillColorIndicator.setBackground(currentPaintProperty.currentFillColor);
    	fillColorIndicator2=new JTextField("   ");
    	fillColorIndicator2.setBackground(currentPaintProperty.currentGradientFillColor.color2);
    	lineColorChooser.add(linelabel);
    	lineColorChooser.add(lineColorIndicator);
    	lineColorChooser.add(changeLineColorButton);
    	fillColorChooser.add(filllabel);
    	fillColorChooser.add(fillColorIndicator);
    	fillColorChooser.add(changeFillColorButton);
    	fillColorChooser2.add(filllabel2);
    	fillColorChooser2.add(fillColorIndicator2);
    	fillColorChooser2.add(changeFillColorButton2);
    	
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
    	previewCanvas=new MyLittleCanvas(currentPaintProperty,this);
    	
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
    	
    	//chatPanel
    	chatRecordScroll.setPreferredSize(new Dimension(332,100));
    	chatPanel.add(chatRecordScroll);
    	chatPanel.add(privateWords);
    	chatPanel.add(sendButton);
    	chatPanel.add(faceButton);
    	faceButton.setToolTipText("Insert Face");
    	
    	
    	//TabbedPane
    	toolBoxTab.add("Toolbox",toolBox);
    	paintPatternChooserTab.setPreferredSize(new Dimension(350,40));
    	paintPatternChooserTab.add("Paint Pattern",paintPatternChooser);
    	paintPatternChooserTab.add("Color Chooser",colorChooser);
    	paintPatternChooserTab.add("Line Style Chooser",lineChooser);
    	lineChooserTab.add("Line Style",lineChooser);
    	lineChooserTab.add("RoundRectangle Arc",arcWidthHeightChooser);
    	previewTab.add("Preview",previewCanvas);
    	chatTab.add("Chat",chatPanel);
    	
    	    	
    	//propertyPanel
    	propertyPanel.setPreferredSize(new Dimension(350,0));
    	propertyPanel.setLayout(new GridLayout(4,1));
    	propertyPanel.add(paintPatternChooserTab);
    	propertyPanel.add(lineChooserTab);
    	propertyPanel.add(previewTab);
    	propertyPanel.add(chatTab);
    	    	
    	//mainPanel
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(toolBar,"North");
    	mainPanel.add(propertyPanel,"East");
    	mainPanel.add(toolBoxTab,"West");
    	mainPanel.add(canvas,"Center");

    	    	
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
    	createServer.addActionListener(new MenuHandler.CreateServer(this));
    	connectToServer.addActionListener(new MenuHandler.ConnectToServer(this));
    	delete.addActionListener(new MenuHandler.Delete(this));
    	cut.addActionListener(new MenuHandler.Cut(this));
    	copy.addActionListener(new MenuHandler.Copy(this));
    	paste.addActionListener(new MenuHandler.Paste(this));
    	toJPEG.addActionListener(new MenuHandler.ToJPEG(this));
    	toPNG.addActionListener(new MenuHandler.ToPNG(this));
    	toBMP.addActionListener(new MenuHandler.ToBMP(this));
    	about.addActionListener(new MenuHandler.About(this));
    	undo.addActionListener(new MenuHandler.Undo(this));
    	redo.addActionListener(new MenuHandler.Redo(this));
    	
    	
    	//add tool bar handler
    	saveButton.addActionListener(new MenuHandler.Save(this));
    	openButton.addActionListener(new MenuHandler.Open(this));
    	newButton.addActionListener(new MenuHandler.Create(this));
    	cutButton.addActionListener(new MenuHandler.Cut(this));
    	copyButton.addActionListener(new MenuHandler.Copy(this));
    	pasteButton.addActionListener(new MenuHandler.Paste(this));
    	undoButton.addActionListener(new MenuHandler.Undo(this));
    	redoButton.addActionListener(new MenuHandler.Redo(this));
    	saveButton.setToolTipText("Save");
    	newButton.setToolTipText("New");
    	openButton.setToolTipText("Open");
    	cutButton.setToolTipText("Cut");
    	copyButton.setToolTipText("Copy");
    	pasteButton.setToolTipText("Paste");
    	undoButton.setToolTipText("Undo");
    	redoButton.setToolTipText("Redo");
    	
    	
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
    	changeFillColorButton2.addActionListener(new ColorChooserHandler.FillColorChooser2(this));
    	
    	
    	//add lineChooser handler
    	changeLineStyleButton.addActionListener(new LineChooserHandler.LineStyleChooser(this));
    	lineWidthChooserSlider.addChangeListener(new LineChooserHandler.LineWidthChooser(this));
    	lineWidthChooserSlider.addMouseListener(new LineChooserHandler.LineWidthChooser2(this));
    	
    	//add paintPatternChooser handler
    	lineOnlyButton.addActionListener(new PaintPatternChooserHandler.LineOnlyButton(this));
    	fillOnlyButton.addActionListener(new PaintPatternChooserHandler.FillOnlyButton(this));
    	lineAndFillButton.addActionListener(new PaintPatternChooserHandler.LineAndFillButton(this));
    	gradientFillButton.addActionListener(new PaintPatternChooserHandler.GradientFillButton(this));
    	gradientFillCheck.addActionListener(new PaintPatternChooserHandler.GradientFillCheck(this));
    	
    	//add arcWidhtHeightChooser handler
    	arcWidthSlider.addChangeListener(new ArcWidthHeightChooserHandler.ArcWidthChooser(this));
    	arcWidthSlider.addMouseListener(new ArcWidthHeightChooserHandler.ArcWidthChooser2(this));
    	arcHeightSlider.addChangeListener(new ArcWidthHeightChooserHandler.ArcHeightChooser(this));
    	arcHeightSlider.addMouseListener(new ArcWidthHeightChooserHandler.ArcHeightChooser2(this));
    	
    	//add chatSendButton handler
    	sendButton.addActionListener(new ChatSendButtonHandler(this));
    	//add faceChooseButton handler
    	faceButton.addActionListener(new faceChooseButtonHandler(this));
    	
    	//set accelerator
    	cut.setAccelerator(key_cut);
    	copy.setAccelerator(key_copy);
    	paste.setAccelerator(key_paste);
    	delete.setAccelerator(key_delete);
    	open.setAccelerator(key_open);
    	create.setAccelerator(key_new);
    	save.setAccelerator(key_save);
    	undo.setAccelerator(key_undo);
    	redo.setAccelerator(key_redo);
    	
    }
    public void run(){
    	setVisible(true);
    }
}
