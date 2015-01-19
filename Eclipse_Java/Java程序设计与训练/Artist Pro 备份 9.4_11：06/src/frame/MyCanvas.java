package frame;
import handler.*;
import java.awt.*;
import java.awt.font.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class MyCanvas extends JPanel{
	ArrayList<ShapePro> shapeProArray;
	PaintProperty currentPaintProperty;
	float[] dashPattern={5,5,5,5,5,5,5};
	BufferedImage texture=new BufferedImage(4,4,BufferedImage.TYPE_INT_ARGB);
	
	public MyCanvas(ArrayList<ShapePro> array,PaintProperty property){
		shapeProArray=array;
		currentPaintProperty=property;
		this.setBackground(Color.GREEN);
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		Shape transformedShape;
		//AffineTransform oldTransform=g2.getTransform();
		//渲染质量设置
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		//开始输出所有图形
		Iterator it=shapeProArray.iterator();
		while(it.hasNext()){
			ShapePro s=(ShapePro)it.next();
			//如果是文字对象，先要生成shape
			if(s.shapeType==ShapeType.TEXT){
				FontRenderContext context=g2.getFontRenderContext();
				TextShapePro ts=(TextShapePro)(s);
				TextLayout layout=new TextLayout(ts.text,ts.font,context);
				AffineTransform transform=AffineTransform.getTranslateInstance(ts.position.x, ts.position.y);
				Shape outline=layout.getOutline(transform);
				s.shape=outline;
			}
			//初始化transformedShape
			transformedShape=s.shape;
			//初始化图形角上的控制点
			ShapePro.p1=s.getLeftTop();
			ShapePro.p2=s.getRightTop();
			ShapePro.p3=s.getLeftBottom();
			ShapePro.p4=s.getRightBottom();
			//设置基本绘图属性
			g2.setStroke(s.stroke);
			g2.setColor(s.lineColor);
			//oldTransform=g2.getTransform();
			
			
			//*****************************************************************
			//变换说明
			//对于drag和scale操作，可根据鼠标操作，直接修改图形的坐标变量值即可（起始点、宽高值）
			//对于rotate和shear操作，无法通过修改坐标变量实现变换。所以需根据鼠标操作，采用
			//仿射变换出的新的Shape对象(transformedShape)，并利用这个新的对象进行绘图输出。
			//但是这个新的Shape对象并不存储，内存中存的还是仿射变化前的Shape。
			//而进行鼠标点击测试时，用户需要的判断是鼠标是否在新的Shape区域中。由于这个新的Shape
			//并未存储，所以进行鼠标点击判断时需要将鼠标位置进行反变换后，与内存中的变换前的
			//Shape对象的区域关系进行判断。
			//注意顺序！我的变换顺序如下
			//正变换：shear,rotate
			//逆变换：rotate,shear
			//注意到顺序必须相反，这样才能使一个值经正变换再经逆变换后，能回到原来的状态
			//*****************************************************************
			
			
			//此函数内完成原始Shape的正变换
			
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
			/*//判断是否发生缩放，若缩放则正变换
			if(s.scaled==true){
				AffineTransform t=new AffineTransform();
				AffineTransform t2=new AffineTransform();
				t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
				t2.concatenate(t);
				//g2.transform(t);
				t.setToScale(s.scaledFactorX,s.scaledFactorY);
				t2.concatenate(t);
				//transformedShape=t.createTransformedShape(transformedShape);
				//g2.transform(t);
				t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
				t2.concatenate(t);
				transformedShape=t2.createTransformedShape(transformedShape);
				//g2.transform(t);
			}*/
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
			    //g2.transform(t);
			}
			
			
			//若有边框则开始绘边框
			if(s.lined==true)
				g2.draw(transformedShape);
			//开始填充
			if(s.fillType==FillType.NONE){
				//Don't fill
			}
			if(s.fillType==FillType.SOLID){
			    g2.setColor(s.fillColor);
			    g2.fill(transformedShape);
			}
			else if(s.fillType==FillType.GRADIENT){
				g2.setPaint(s.gradientFillColor);
				g2.fill(transformedShape);
			}
			//判断是否被指向，如果被指向就填充纹理
			if(s.pointed==true){
				Graphics2D g2_texture=texture.createGraphics();
				Rectangle2D anchorRectangle=new Rectangle2D.Double(0.0,0.0,2.0,2.0);
				switch(s.shapeType){
				case ShapeType.RECTANGLE:
					g2_texture.setColor(Color.WHITE);
					g2_texture.fillRect(0,0,1,1);
					g2.setPaint(new TexturePaint(texture,anchorRectangle));
					g2.fill(transformedShape);
					break;
				case ShapeType.ELLIPSE:
					g2_texture.setColor(Color.WHITE);
					g2_texture.fillRect(0,0,1,1);
					g2.setPaint(new TexturePaint(texture,anchorRectangle));
					g2.fill(transformedShape);
					break;
				case ShapeType.LINE:
					g2_texture.setColor(Color.WHITE);
					g2_texture.fillRect(0,0,1,1);
					g2.setPaint(new TexturePaint(texture,anchorRectangle));
					g2.draw(transformedShape);
					
				}
			}
			//判断是否被选中，若选中则画上边框
			if(s.selected==true){
				switch(s.shapeType){
				case ShapeType.RECTANGLE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					g2.draw(transformedShape);
					break;
				case ShapeType.ELLIPSE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					//g2.draw(transformedShape);
					g2.draw(new Line2D.Double(ShapePro.p2.x,ShapePro.p2.y,ShapePro.p1.x,ShapePro.p1.y));
					g2.draw(new Line2D.Double(ShapePro.p3.x,ShapePro.p3.y,ShapePro.p1.x,ShapePro.p1.y));
					g2.draw(new Line2D.Double(ShapePro.p2.x,ShapePro.p2.y,ShapePro.p4.x,ShapePro.p4.y));
					g2.draw(new Line2D.Double(ShapePro.p3.x,ShapePro.p3.y,ShapePro.p4.x,ShapePro.p4.y));
					break;
				case ShapeType.LINE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					g2.draw(transformedShape);
					break;
				}
				//若当前功能为rotate,shear,scale,则还需在选定的图形角上画上小圆圈
				if(currentPaintProperty.transformable==true){
						ShapePro.c1.x=ShapePro.p1.x-6.0;
						ShapePro.c1.y=ShapePro.p1.y-6.0;
						ShapePro.c2.x=ShapePro.p2.x-6.0;
						ShapePro.c2.y=ShapePro.p2.y-6.0;
						ShapePro.c3.x=ShapePro.p3.x-6.0;
						ShapePro.c3.y=ShapePro.p3.y-6.0;
						ShapePro.c4.x=ShapePro.p4.x-6.0;
						ShapePro.c4.y=ShapePro.p4.y-6.0;
						ShapePro.c1.height=ShapePro.c1.width=12;
						ShapePro.c2.height=ShapePro.c2.width=12;
						ShapePro.c3.height=ShapePro.c3.width=12;
						ShapePro.c4.height=ShapePro.c4.width=12;
						g2.setStroke(new BasicStroke(3.0f));
						g2.setColor(Color.BLACK);
						g2.draw(ShapePro.c1);
						g2.draw(ShapePro.c2);
						g2.draw(ShapePro.c3);
						g2.draw(ShapePro.c4);
						g2.setColor(Color.GREEN);
						g2.fill(ShapePro.c1);
						g2.fill(ShapePro.c2);
						g2.fill(ShapePro.c3);
						g2.fill(ShapePro.c4);	
				}
				
			}
			//恢复默认绘图属性
			//g2.setTransform(oldTransform);
			
			
			
		}
			
	}
}
