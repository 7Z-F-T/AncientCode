import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;

import org.xml.sax.*;

import org.xml.sax.helpers.*;

import java.util.*;

import java.io.*; 

public class ParseTest {
	//程序入口，用来完成解析工作

	static public void main(String[] args) {

		String filename = null;

		boolean validation = false;

		filename="300k.xml";

		SAXParserFactory spf = SAXParserFactory.newInstance();

		SAXParser saxParser=null;

		try {

			// 创建一个解析器SAXParser对象

			saxParser = spf.newSAXParser();


		} catch (Exception ex) {

			System.err.println(ex);

			System.exit(1);

		}

		try {

			//使用指定的ContentHandler，解析给XML文件，这儿要注意的是，为了

			//程序的简单起见，这儿将主程序和ContentHandler放在了一起。实际上

			//main方法中所作的所有事情，都与ContentHandler无关。

			saxParser.parse(new File(filename), new SAXCounter());

		} catch (SAXException se) {

			System.err.println(se.getMessage());

			System.exit(1);

		} catch (IOException ioe) {

			System.err.println(ioe);

			System.exit(1);

		}

	}

} 


