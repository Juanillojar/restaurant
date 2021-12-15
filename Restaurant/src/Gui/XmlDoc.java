package Gui;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDoc {
	String documentName="config.xml";
	String path;
	File myFile;
	
	public XmlDoc(String documentName, String path) {
		this.documentName = documentName;
		this.path = path;
		this.myFile = new File(path + documentName);
	}
	
	public String[] load() {
		String [] arrayConf = new String[10];
		try {
			DocumentBuilderFactory myDbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder myDocumentBulider = myDbf.newDocumentBuilder();
			Document doc = myDocumentBulider.parse(myFile);
			doc.getDocumentElement().normalize();
			System.out.println("raiz " + doc.getDocumentElement().getNodeName());
			NodeList lista = doc.getElementsByTagName("database");
			Node nNode1 = lista.item(0);
			Element elemento1 = (Element)nNode1;
			arrayConf[0] = elemento1.getElementsByTagName("motor").item(0).getTextContent();
			arrayConf[1] = elemento1.getElementsByTagName("name").item(0).getTextContent();
			arrayConf[2] = elemento1.getElementsByTagName("host").item(0).getTextContent();
			arrayConf[3] = elemento1.getElementsByTagName("port").item(0).getTextContent();
			arrayConf[4] = elemento1.getElementsByTagName("user").item(0).getTextContent();
			arrayConf[5] = elemento1.getElementsByTagName("pass").item(0).getTextContent();
			
			//extract data about zones
			lista = doc.getElementsByTagName("zones");
			nNode1 = lista.item(0);
			elemento1 = (Element)nNode1;
			arrayConf[6] = elemento1.getElementsByTagName("BarZones").item(0).getTextContent();
			arrayConf[7] = elemento1.getElementsByTagName("IntTables").item(0).getTextContent();
			arrayConf[8] = elemento1.getElementsByTagName("OutTables").item(0).getTextContent();
			arrayConf[9] = elemento1.getElementsByTagName("DeliveryZones").item(0).getTextContent();

			//	for(int j = 0;j < lista.getLength();j++) {
		//		Node nNode = lista.item(j);
		//		System.out.println("Elemento: " + nNode.getNodeName());
		//		if(nNode.getNodeType() == Node.ELEMENT_NODE){
		//			Element elemento = (Element)nNode;
		//			System.out.println(elemento.getElementsByTagName("name").item(0).getTextContent());
		//			System.out.println(elemento.getElementsByTagName("host").item(0).getTextContent());
		//			System.out.println(elemento.getElementsByTagName("port").item(0).getTextContent());
		//			System.out.println(elemento.getElementsByTagName("user").item(0).getTextContent());
		//			System.out.println(elemento.getElementsByTagName("pass").item(0).getTextContent());
		//		};
		//	};
				}catch(ParserConfigurationException | SAXException | IOException ex) {
			System.out.println("Lectura xm l" + ex.getMessage() + ex.getStackTrace().toString());
			Frame.log.Escritura("Lectura xml " + ex.getMessage() + ex.getStackTrace());
		};
		return arrayConf;
	}
}
