package Gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
	
	/**
	 * Read data from a xml document and store it on a array of String
	 * @return array of String that contain data from xml document
	 */
	public String[] read() {
		String [] arrayConf = new String[14];
		Frame.log.Escritura("START: Read configuration file xml ");		
		try {
			DocumentBuilderFactory myDbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder myDocumentBulider = myDbf.newDocumentBuilder(); 
			Document doc = myDocumentBulider.parse(myFile);//genera el documento a partir del xml para tratarlo con Java
			doc.getDocumentElement().normalize();   //con esta acción se mejora el rendimiento
			
			//extract data about database
			NodeList lista = doc.getElementsByTagName("database");  //se obtiene una lista con tudos los nodos que coincidan.
																	//En nuestro caos sólo uno. De otr forma se utilizaría un bucle
			Node nNode1 = lista.item(0);
			Element myElement = (Element)nNode1;
			arrayConf[0] = myElement.getElementsByTagName("motor").item(0).getTextContent();
			arrayConf[1] = myElement.getElementsByTagName("name").item(0).getTextContent();
			arrayConf[2] = myElement.getElementsByTagName("host").item(0).getTextContent();
			arrayConf[3] = myElement.getElementsByTagName("port").item(0).getTextContent();
			arrayConf[4] = myElement.getElementsByTagName("user").item(0).getTextContent();
			arrayConf[5] = myElement.getElementsByTagName("pass").item(0).getTextContent();
			
			//extract data about zones
			lista = doc.getElementsByTagName("zones");
			nNode1 = lista.item(0);
			myElement = (Element)nNode1;
			arrayConf[6] = myElement.getElementsByTagName("BarZones").item(0).getTextContent();
			arrayConf[7] = myElement.getElementsByTagName("IntTables").item(0).getTextContent();
			arrayConf[8] = myElement.getElementsByTagName("OutTables").item(0).getTextContent();
			arrayConf[9] = myElement.getElementsByTagName("DeliveryZones").item(0).getTextContent();

			//extract data about company
			lista = doc.getElementsByTagName("company");
			nNode1 = lista.item(0);
			myElement = (Element)nNode1;
			arrayConf[10] = myElement.getElementsByTagName("name").item(0).getTextContent();
			arrayConf[11] = myElement.getElementsByTagName("address").item(0).getTextContent();
			arrayConf[12] = myElement.getElementsByTagName("discount").item(0).getTextContent();
			arrayConf[13] = myElement.getElementsByTagName("taxes").item(0).getTextContent();
			}catch(ParserConfigurationException | SAXException | IOException ex) {
				System.out.println("Lectura xml " + ex.getMessage() + ex.getStackTrace().toString());
				Frame.log.Escritura("Lectura xml " + ex.getMessage() + ex.getStackTrace());
		};
		return arrayConf;
	}
	
	public void store(String[] arrayConf) {
		//String [] arrayConf = new String[14];
		Frame.log.Escritura("START: Store configuration into xml file");		
		try {
			DocumentBuilderFactory myDbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder myDocumentBulider = myDbf.newDocumentBuilder();
			Document doc = myDocumentBulider.parse(myFile); //genera el documento a partir del xml para tratarlo con Java
			doc.getDocumentElement().normalize();   //con esta acción se mejora el rendimiento
			
			arrayConf[1]= "bdRestaurant";
		
			//store data about database
			NodeList lista = doc.getElementsByTagName("database");
			Node nNode1 = lista.item(0);
			Element elemento1 = (Element)nNode1;
			elemento1.getElementsByTagName("motor").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfDatabaseMotor().getText());
			elemento1.getElementsByTagName("name").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfDatabaseName().getText());
			elemento1.getElementsByTagName("host").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfDatabasePort().getText());
			elemento1.getElementsByTagName("port").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfDatabasePort().getText());
			elemento1.getElementsByTagName("user").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfDatabaseUser().getText());
			elemento1.getElementsByTagName("pass").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfDatabasePass().getText());
			
			//store data about zones
			lista = doc.getElementsByTagName("zones");
			nNode1 = lista.item(0);
			elemento1 = (Element)nNode1;
			elemento1.getElementsByTagName("BarZones").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfZonesBar().getText());
			elemento1.getElementsByTagName("IntTables").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfZonesIntTables().getText());
			elemento1.getElementsByTagName("OutTables").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfZonesExtTables().getText());
			elemento1.getElementsByTagName("DeliveryZones").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfZonesDelivery().getText());

			//store data about company
			lista = doc.getElementsByTagName("company");
			nNode1 = lista.item(0);
			elemento1 = (Element)nNode1;
			elemento1.getElementsByTagName("name").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfCompanyName().getText());
			elemento1.getElementsByTagName("address").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfCompanyAddress().getText());
			elemento1.getElementsByTagName("discount").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfCompanyDiscount().getText());
			elemento1.getElementsByTagName("taxes").item(0).setTextContent(Frame.InstanceFRestaurant.panelConfig.getTfCompanyTaxes().getText());
			
			//store data in XML documnent
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(myFile);
			Source input = new DOMSource(doc);
			transformer.transform(input, output);
			
			JOptionPane.showMessageDialog(null, "Data Stored sucessfully", "Information", JOptionPane.INFORMATION_MESSAGE);
			Frame.log.Escritura("Configuration file config.xml stored sucessfully");
		}catch(ParserConfigurationException | SAXException | IOException | TransformerException | TransformerFactoryConfigurationError ex) {
				System.out.println("Lectura xml " + ex.getMessage() + ex.getStackTrace().toString());
				Frame.log.Escritura("Lectura xml " + ex.getMessage() + ex.getStackTrace());
		};
		
	}
}
