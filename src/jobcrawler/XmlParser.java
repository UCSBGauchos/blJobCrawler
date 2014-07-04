package jobcrawler;

import java.util.ArrayList;
import java.util.Iterator;  
import org.dom4j.Document;  
import org.dom4j.Element;  
import org.dom4j.io.SAXReader;  

public class XmlParser {
	public ArrayList<People> peoplelist = new ArrayList<People>();
	public void runXmpParser() throws Exception {  
        //create file obj   
        java.io.File file=new java.io.File("/Users/yangbo/Desktop/people.xml");     
        //create a xml read obj     
        SAXReader reader=new SAXReader();     
        //create a doc file    
        Document document=reader.read(file);     
        //get root node of file     
        Element element=document.getRootElement();  
        for(Iterator i=element.elementIterator("person");i.hasNext();){  
            //get node     
            element=(Element)i.next();  
            //String name=element.attributeValue("name");  
            String firstName=element.elementText("first-name");//取disk子元素capacity的内容   
            String lastName=element.elementText("last-name");     
            //System.out.println("People Info:");    
            //System.out.println("firstName:"+firstName);   
            //System.out.println("lastName:"+lastName);      
            //System.out.println("-----------------------------------"); 
            People newPeople = new People(firstName, lastName);
            peoplelist.add(newPeople);
        }  
//        for(People people: peoplelist){
//        	System.out.print(people.firstName+" "+people.lastName);
//        	System.out.println();
//        }
    }     
}
