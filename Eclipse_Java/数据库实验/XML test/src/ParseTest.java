import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;

import org.xml.sax.*;

import org.xml.sax.helpers.*;

import java.util.*;

import java.io.*; 

public class ParseTest {
	//������ڣ�������ɽ�������

	static public void main(String[] args) {

		String filename = null;

		boolean validation = false;

		filename="300k.xml";

		SAXParserFactory spf = SAXParserFactory.newInstance();

		SAXParser saxParser=null;

		try {

			// ����һ��������SAXParser����

			saxParser = spf.newSAXParser();


		} catch (Exception ex) {

			System.err.println(ex);

			System.exit(1);

		}

		try {

			//ʹ��ָ����ContentHandler��������XML�ļ������Ҫע����ǣ�Ϊ��

			//����ļ������������������ContentHandler������һ��ʵ����

			//main�������������������飬����ContentHandler�޹ء�

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


