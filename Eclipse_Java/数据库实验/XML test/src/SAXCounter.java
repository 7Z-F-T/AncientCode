import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;

import org.xml.sax.*;

import org.xml.sax.helpers.*;

import java.util.*;

import java.io.*;

public class SAXCounter extends DefaultHandler {

	// �����ĵ�ǰ�Ĺ���

	public void startDocument() throws SAXException {


	}

	//��ÿһ����ʼԪ�����д���

	public void startElement(String namespaceURI, String localName,

			String rawName, Attributes atts)

	throws SAXException

	{

		//String key = rawName;
		//System.out.println(key);

	}

	//������ɺ��ͳ�ƹ���

	public void endDocument() throws SAXException {



	}
}
