import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;

import org.xml.sax.*;

import org.xml.sax.helpers.*;

import java.util.*;

import java.io.*;

public class SAXCounter extends DefaultHandler {

	// 处理文档前的工作

	public void startDocument() throws SAXException {


	}

	//对每一个开始元属进行处理

	public void startElement(String namespaceURI, String localName,

			String rawName, Attributes atts)

	throws SAXException

	{

		//String key = rawName;
		//System.out.println(key);

	}

	//解析完成后的统计工作

	public void endDocument() throws SAXException {



	}
}
