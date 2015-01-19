package handler;
import frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;
/**
 * 处理菜单选项事件
 * @author 侯杰
 *
 */
public abstract class MenuHandler {
	/**处理退出事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Exit implements ActionListener{
		MainFrame frame;
		public Exit(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	/**处理外观切换为Mac OS事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Mac implements ActionListener{
		MainFrame frame;
		public Mac(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	/**处理外观切换为Mental事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Metal implements ActionListener{
		MainFrame frame;
		public Metal(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	/**处理外观切换为Motif事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Motif implements ActionListener{
		MainFrame frame;
		public Motif(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	/**处理外观切换为Windows事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Windows implements ActionListener{
		MainFrame frame;
		public Windows(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	/**处理保存文件事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Save implements ActionListener{
		MainFrame frame;
		public Save(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File newFile=fc.getSelectedFile();
				newFile=new File(newFile.toString()+".ap");
				//通过对象序列化方式输出至文件
				try{
				FileOutputStream fo=new FileOutputStream(newFile);
				ObjectOutputStream oo=new ObjectOutputStream(fo);
				oo.writeObject(frame.shapeProArray);
				}catch(Exception ex){System.out.print(ex);}
			}
			
		}
	}
	/**处理打开文件事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Open implements ActionListener{
		MainFrame frame;
		public Open(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JFileChooser fc=new JFileChooser(".");
			if(fc.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
				File newFile=fc.getSelectedFile();
				//通过序列化方式从文件读入
				try{
				FileInputStream fi=new FileInputStream(newFile);
				ObjectInputStream oi=new ObjectInputStream(fi);
				System.out.println(newFile);
				ArrayList<ShapePro> newShapeProArray=(ArrayList<ShapePro>)(oi.readObject());
				System.out.println(frame.shapeProArray.size());
				frame.shapeProArray.clear();
				frame.shapeProArray.ensureCapacity(newShapeProArray.size());
				for(int i=0;i<newShapeProArray.size();i++){
					frame.shapeProArray.add(newShapeProArray.get(i));
					System.out.println("outputint");
				}
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				}catch(Exception ex){System.out.println(ex);}
			}
			
		}
	}
	/**处理新建文件事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Create implements ActionListener{
		MainFrame frame;
		public Create(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.shapeProArray.clear();
			frame.backShapeProArrayArray.clear();
			frame.frontShapeProArrayArray.clear();
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
		}
	}
	/**处理建立服务器事件
	 * 
	 */
	public static class CreateServer implements ActionListener{
		MainFrame frame;
		public CreateServer(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			CreateServerDialog createServerDialog=new CreateServerDialog(frame);
		}
	}
	/**处理连接至服务器事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class ConnectToServer implements ActionListener{
		MainFrame frame;
		public ConnectToServer(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			ConnectToServerDialog connectToServerDialog=new ConnectToServerDialog(frame);
		}
	}
	/**处理剪切事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Cut implements ActionListener{
		MainFrame frame;
		public Cut(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				//剪切应先复制，再删除
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				for(int i=0;i<frame.shapeProArray.size();i++){
					if(frame.shapeProArray.get(i).equals(DrawHandler.currentSelectedShapePro)){
						frame.clipboardShapePro=CopyShapePro.copy1(DrawHandler.currentSelectedShapePro);
						DrawHandler.currentSelectedShapePro.selected=false;				
						frame.shapeProArray.remove(i);
						frame.canvas.repaint();
						frame.repaintFlip.setFlip();
						break;
					}
				}
			}
		}
	}
	/**处理复制事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Copy implements ActionListener{
		MainFrame frame;
		public Copy(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//复制仅需简单复制一下即可
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.clipboardShapePro=CopyShapePro.copy1(DrawHandler.currentSelectedShapePro);
				DrawHandler.currentSelectedShapePro.selected=false;
			}
		}
	}
	/**处理粘贴事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Paste implements ActionListener{
		MainFrame frame;
		public Paste(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//粘贴时新增一个图形
			frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
			ShapePro a=CopyShapePro.copy1(frame.clipboardShapePro);
			frame.shapeProArray.add(a);
			DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			System.out.println(frame.shapeProArray.size());
		}
	}
	/**处理删除事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Delete implements ActionListener{
		MainFrame frame;
		public Delete(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				for(int i=0;i<frame.shapeProArray.size();i++){
					if(frame.shapeProArray.get(i).equals(DrawHandler.currentSelectedShapePro)){
						DrawHandler.currentSelectedShapePro.selected=false;
						frame.shapeProArray.remove(i);
						frame.canvas.repaint();
						frame.repaintFlip.setFlip();
						break;
					}
				}
			}
		}
	}
	/**处理导出成JPEG图像事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class ToJPEG implements ActionListener{
		MainFrame frame;
		public ToJPEG(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//新建一个BufferedImage对象，在其上画图形
			Rectangle canvasBound=new Rectangle(0,0,frame.canvas.getWidth(),frame.canvas.getHeight());
			BufferedImage image_export=new BufferedImage(canvasBound.width,canvasBound.height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2_export=image_export.createGraphics();
			Shape transformedShape;
			RenderingHints rh = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2_export.setRenderingHints(rh);
			//画白底
			g2_export.setColor(Color.WHITE);
			g2_export.fill(canvasBound);
			
			for(int i=0;i<frame.shapeProArray.size();i++){
				ShapePro s=frame.shapeProArray.get(i);
				//如果是文字对象，先要生成shape
				if(s.shapeType==ShapeType.TEXT){
					    FontRenderContext context=g2_export.getFontRenderContext();
						TextShapePro ts=(TextShapePro)(s);//ts和s指向同一对象，只不过这两个引用的类型不同
						TextLayout layout=new TextLayout(ts.text,ts.font,context);
						AffineTransform transform=new AffineTransform();
						transform.setToTranslation(ts.position.x, ts.position.y);
						Shape outline=layout.getOutline(transform);
						ts.shape=outline;
						Rectangle2D boundsRect=ts.shape.getBounds2D();
						ts.textHeight=boundsRect.getHeight();
						ts.textWidth=boundsRect.getWidth();
				}
				//初始化transformedShape
				transformedShape=s.shape;
				//初始化图形角上的控制点
			    ShapePro.p1=s.getLeftTop();
				ShapePro.p2=s.getRightTop();
				ShapePro.p3=s.getLeftBottom();
				ShapePro.p4=s.getRightBottom();
				//设置基本绘图属性
				g2_export.setStroke(new BasicStroke(s.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,s.stroke.pattern,0));
				g2_export.setColor(s.lineColor);
				
				//判断是否发生缩放，若缩放则正变换
				if(s.scaled==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					t.setToScale(s.scaledFactorX,s.scaledFactorY);
					t2.concatenate(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				//判断是否发生剪切，若剪切则将shape正变换
				if(s.sheared==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					//
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					t.setToShear(s.shearedFactorX,s.shearedFactorY);
					t2.concatenate(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				//判断是否发生旋转，若旋转则将shape正变换
				if(s.rotated==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
				    t.setToRotation(s.rotatedAngle, s.getCenterPoint().x, s.getCenterPoint().y);
				    t2.concatenate(t);
				    transformedShape=t2.createTransformedShape(transformedShape);
				    t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				
				
				//若有边框则开始绘边框
				if(s.lined==true)
					g2_export.draw(transformedShape);
				//开始填充
				if(s.fillType==FillType.NONE){
					//Don't fill
				}
				if(s.fillType==FillType.SOLID){
				    g2_export.setColor(s.fillColor);
				    g2_export.fill(transformedShape);
				}
				else if(s.fillType==FillType.GRADIENT){
					g2_export.setPaint(new GradientPaint(s.gradientFillColor.point1,s.gradientFillColor.color1,s.gradientFillColor.point2,s.gradientFillColor.color2,true));
					g2_export.fill(transformedShape);
				}
			}
			//然后将BufferedImage里面的内容写到文件中
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File file=fc.getSelectedFile();
				file=new File(file.toString()+".jpg");
				try{
					FileOutputStream os=new FileOutputStream(file);
					ImageIO.write(image_export, "JPEG", os);
					}catch(Exception ex){
						System.out.print(ex);
					}
			}
			
		}
	}
	/**处理导出成PNG图像事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class ToPNG implements ActionListener{
		MainFrame frame;
		public ToPNG(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//新建一个BufferedImage对象，在其上画图形
			Rectangle canvasBound=new Rectangle(0,0,frame.canvas.getWidth(),frame.canvas.getHeight());
			BufferedImage image_export=new BufferedImage(canvasBound.width,canvasBound.height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2_export=image_export.createGraphics();
			Shape transformedShape;
			RenderingHints rh = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2_export.setRenderingHints(rh);
			//画白底
			g2_export.setColor(Color.WHITE);
			g2_export.fill(canvasBound);
			
			for(int i=0;i<frame.shapeProArray.size();i++){
				ShapePro s=frame.shapeProArray.get(i);
				//如果是文字对象，先要生成shape
				if(s.shapeType==ShapeType.TEXT){
					    FontRenderContext context=g2_export.getFontRenderContext();
						TextShapePro ts=(TextShapePro)(s);//ts和s指向同一对象，只不过这两个引用的类型不同
						TextLayout layout=new TextLayout(ts.text,ts.font,context);
						AffineTransform transform=new AffineTransform();
						transform.setToTranslation(ts.position.x, ts.position.y);
						Shape outline=layout.getOutline(transform);
						ts.shape=outline;
						Rectangle2D boundsRect=ts.shape.getBounds2D();
						ts.textHeight=boundsRect.getHeight();
						ts.textWidth=boundsRect.getWidth();
				}
				//初始化transformedShape
				transformedShape=s.shape;
				//初始化图形角上的控制点
			    ShapePro.p1=s.getLeftTop();
				ShapePro.p2=s.getRightTop();
				ShapePro.p3=s.getLeftBottom();
				ShapePro.p4=s.getRightBottom();
				//设置基本绘图属性
				g2_export.setStroke(new BasicStroke(s.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,s.stroke.pattern,0));
				g2_export.setColor(s.lineColor);
				
			//判断是否发生缩放，若缩放则正变换
				if(s.scaled==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					t.setToScale(s.scaledFactorX,s.scaledFactorY);
					t2.concatenate(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				//判断是否发生剪切，若剪切则将shape正变换
				if(s.sheared==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					t.setToShear(s.shearedFactorX,s.shearedFactorY);
					t2.concatenate(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				//判断是否发生旋转，若旋转则将shape正变换
				if(s.rotated==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
				  	t.setToRotation(s.rotatedAngle, s.getCenterPoint().x, s.getCenterPoint().y);
				    t2.concatenate(t);
				    transformedShape=t2.createTransformedShape(transformedShape);
				    t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				
				
				//若有边框则开始绘边框
				if(s.lined==true)
					g2_export.draw(transformedShape);
				//开始填充
				if(s.fillType==FillType.NONE){
					//Don't fill
				}
				if(s.fillType==FillType.SOLID){
				    g2_export.setColor(s.fillColor);
				    g2_export.fill(transformedShape);
				}
				else if(s.fillType==FillType.GRADIENT){
					g2_export.setPaint(new GradientPaint(s.gradientFillColor.point1,s.gradientFillColor.color1,s.gradientFillColor.point2,s.gradientFillColor.color2,true));
					g2_export.fill(transformedShape);
				}
			}
			//然后将BufferedImage里面的内容写到文件中
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File file=fc.getSelectedFile();
				file=new File(file.toString()+".png");
				try{
					FileOutputStream os=new FileOutputStream(file);
					ImageIO.write(image_export, "PNG", os);
					}catch(Exception ex){
						System.out.print(ex);
					}
			}
			
		}
	}
	/**处理导出成BMP图像事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class ToBMP implements ActionListener{
		MainFrame frame;
		public ToBMP(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			//新建一个BufferedImage对象，在其上画图形
			Rectangle canvasBound=new Rectangle(0,0,frame.canvas.getWidth(),frame.canvas.getHeight());
			BufferedImage image_export=new BufferedImage(canvasBound.width,canvasBound.height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2_export=image_export.createGraphics();
			Shape transformedShape;
			RenderingHints rh = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2_export.setRenderingHints(rh);
			//画白底
			g2_export.setColor(Color.WHITE);
			g2_export.fill(canvasBound);
			
			for(int i=0;i<frame.shapeProArray.size();i++){
				ShapePro s=frame.shapeProArray.get(i);
				//如果是文字对象，先要生成shape
				if(s.shapeType==ShapeType.TEXT){
					    FontRenderContext context=g2_export.getFontRenderContext();
						TextShapePro ts=(TextShapePro)(s);//ts和s指向同一对象，只不过这两个引用的类型不同
						TextLayout layout=new TextLayout(ts.text,ts.font,context);
						AffineTransform transform=new AffineTransform();
						transform.setToTranslation(ts.position.x, ts.position.y);
						Shape outline=layout.getOutline(transform);
						ts.shape=outline;
						Rectangle2D boundsRect=ts.shape.getBounds2D();
						ts.textHeight=boundsRect.getHeight();
						ts.textWidth=boundsRect.getWidth();
				}
				//初始化transformedShape
				transformedShape=s.shape;
				//初始化图形角上的控制点
			    ShapePro.p1=s.getLeftTop();
				ShapePro.p2=s.getRightTop();
				ShapePro.p3=s.getLeftBottom();
				ShapePro.p4=s.getRightBottom();
				//设置基本绘图属性
				g2_export.setStroke(new BasicStroke(s.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,s.stroke.pattern,0));
				g2_export.setColor(s.lineColor);
			
				//判断是否发生缩放，若缩放则正变换
				if(s.scaled==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					t.setToScale(s.scaledFactorX,s.scaledFactorY);
					t2.concatenate(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				//判断是否发生剪切，若剪切则将shape正变换
				if(s.sheared==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					//
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					t.setToShear(s.shearedFactorX,s.shearedFactorY);
					t2.concatenate(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				//判断是否发生旋转，若旋转则将shape正变换
				if(s.rotated==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
				    //t.setToRotation(s.rotatedVecX,s.rotatedVecY,s.getCenterPoint().x,s.getCenterPoint().y);
					t.setToRotation(s.rotatedAngle, s.getCenterPoint().x, s.getCenterPoint().y);
				    t2.concatenate(t);
				    transformedShape=t2.createTransformedShape(transformedShape);
				    t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
				
				
				//若有边框则开始绘边框
				if(s.lined==true)
					g2_export.draw(transformedShape);
				//开始填充
				if(s.fillType==FillType.NONE){
					//Don't fill
				}
				if(s.fillType==FillType.SOLID){
				    g2_export.setColor(s.fillColor);
				    g2_export.fill(transformedShape);
				}
				else if(s.fillType==FillType.GRADIENT){
					g2_export.setPaint(new GradientPaint(s.gradientFillColor.point1,s.gradientFillColor.color1,s.gradientFillColor.point2,s.gradientFillColor.color2,true));
					g2_export.fill(transformedShape);
				}
			}
			//然后将BufferedImage里面的内容写到文件中
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File file=fc.getSelectedFile();
				file=new File(file.toString()+".bmp");
				try{
					FileOutputStream os=new FileOutputStream(file);
					ImageIO.write(image_export, "BMP", os);
					}catch(Exception ex){
						System.out.print(ex);
					}
			}
			
		}
	}
	/**
	 * 处理打开“关于”对话框事件
	 * @author 侯杰
	 *
	 */
	public static class About implements ActionListener{
		MainFrame frame;
		public About(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			AboutDialog about=new AboutDialog();
		}
	}
	/**处理撤销事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Undo implements ActionListener{
		MainFrame frame;
		public Undo(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(frame.backShapeProArrayArray.size()!=0){//可以撤销
				ShapePro s;
				//shapeProArray拷一份给frontShapeProArrayArray,以便redo
				frame.frontShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				//当前shapeProArray改成旧值(从backShapeProArrayArray中取得旧值)
				frame.shapeProArray.clear();
				ArrayList<ShapePro> a=frame.backShapeProArrayArray.get(frame.backShapeProArrayArray.size()-1);
				for(int i=0;i<a.size();i++){
					s=a.get(i);
					if(s.selected==true)
						DrawHandler.currentSelectedShapePro=s;
					frame.shapeProArray.add(s);
				}
				//更新右侧属性面板
				if(DrawHandler.currentSelectedShapePro!=null){
					frame.lineColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.lineColor);
					frame.fillColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.fillColor);
					frame.fillColorIndicator2.setBackground(DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					if(DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE){
						frame.gradientFillCheck.setEnabled(true);
						if(DrawHandler.currentSelectedShapePro.fillType==FillType.GRADIENT){
							frame.gradientFillCheck.setSelected(true);
							frame.gradientFillButton.setEnabled(true);
						}
						else{
							frame.gradientFillCheck.setSelected(false);
							frame.gradientFillButton.setEnabled(false);
						}
					}
					else{
						frame.gradientFillCheck.setSelected(false);
						frame.gradientFillCheck.setEnabled(false);
						frame.gradientFillButton.setEnabled(false);
					}
					frame.lineWidthIndicator2.setText(new Integer(DrawHandler.currentSelectedShapePro.stroke.width).toString());
					frame.lineStyleIndicator2.setText(DrawHandler.currentSelectedShapePro.stroke.patternName);
					frame.lineWidthChooserSlider.setValue(DrawHandler.currentSelectedShapePro.stroke.width);
					if(DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
						frame.arcWidthIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth).toString());
						frame.arcWidthSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth);
						frame.arcHeightIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight).toString());
						frame.arcHeightSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight);
					}
					if(DrawHandler.currentSelectedShapePro.lined==true&&DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE)
						frame.lineAndFillButton.setSelected(true);
					else if(DrawHandler.currentSelectedShapePro.lined==true)
						frame.lineOnlyButton.setSelected(true);
					else
						frame.fillOnlyButton.setSelected(true);
					frame.previewCanvas.repaint();
				}
				//backShapeProArrayArray中存的最近一次的shapeProArray的旧值已经恢复给shapeProArray
				//可以将其删掉，露出更前一次的shapeProArray旧值
				frame.backShapeProArrayArray.remove(frame.backShapeProArrayArray.size()-1);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
			}
		}
	}
	/**处理重做事件
	 * 
	 * @author 侯杰
	 *
	 */
	public static class Redo implements ActionListener{
		MainFrame frame;
		public Redo(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(frame.frontShapeProArrayArray.size()!=0){//可以重做
				ShapePro s;
				//shapeProArray拷一份给backShapeProArrayArray,以便undo
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				//当前shapeProArray改成新值(从frontShapeProArrayArray中取得新值)
				frame.shapeProArray.clear();
				ArrayList<ShapePro> a=frame.frontShapeProArrayArray.get(frame.frontShapeProArrayArray.size()-1);
				for(int i=0;i<a.size();i++){
					s=a.get(i);
					if(s.selected==true)
						DrawHandler.currentSelectedShapePro=s;
					frame.shapeProArray.add(s);
				}
				//更新右侧属性面板
				if(DrawHandler.currentSelectedShapePro!=null){
					frame.lineColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.lineColor);
					frame.fillColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.fillColor);
					frame.fillColorIndicator2.setBackground(DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					if(DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE){
						frame.gradientFillCheck.setEnabled(true);
						if(DrawHandler.currentSelectedShapePro.fillType==FillType.GRADIENT){
							frame.gradientFillCheck.setSelected(true);
							frame.gradientFillButton.setEnabled(true);
						}
						else{
							frame.gradientFillCheck.setSelected(false);
							frame.gradientFillButton.setEnabled(false);
						}
					}
					else{
						frame.gradientFillCheck.setSelected(false);
						frame.gradientFillCheck.setEnabled(false);
						frame.gradientFillButton.setEnabled(false);
					}
					frame.lineWidthIndicator2.setText(new Integer(DrawHandler.currentSelectedShapePro.stroke.width).toString());
					frame.lineStyleIndicator2.setText(DrawHandler.currentSelectedShapePro.stroke.patternName);
					frame.lineWidthChooserSlider.setValue(DrawHandler.currentSelectedShapePro.stroke.width);
					if(DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
						frame.arcWidthIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth).toString());
						frame.arcWidthSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth);
						frame.arcHeightIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight).toString());
						frame.arcHeightSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight);
					}
					if(DrawHandler.currentSelectedShapePro.lined==true&&DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE)
						frame.lineAndFillButton.setSelected(true);
					else if(DrawHandler.currentSelectedShapePro.lined==true)
						frame.lineOnlyButton.setSelected(true);
					else
						frame.fillOnlyButton.setSelected(true);
					frame.previewCanvas.repaint();
				}
				//frontShapeProArrayArray中存的最近一次的shapeProArray的新值已经恢复给shapeProArray
				//可以将其删掉，露出更后一次的shapeProArray新值
				frame.frontShapeProArrayArray.remove(frame.frontShapeProArrayArray.size()-1);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
			}
		}
	}
}
