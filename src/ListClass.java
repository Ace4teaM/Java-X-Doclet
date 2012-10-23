
import org.jdom2.*;

import com.sun.javadoc.*;


public class ListClass {
/*
    public static boolean start(RootDoc root) {
    	Element  xdocNode = new Element("xdoc");
    	Document document = new Document(xdocNode);
    	
        Element classNode = new Element("class");
        xdocNode.addContent(classNode);

        System.out.println(document);
        return true;
    }
*/

    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        System.out.println("<xdoc type=\"java\">");
        for (int i = 0; i < classes.length; ++i) {
        	exportClass(classes[i]);
        }
        System.out.println("</xdoc>");
        return true;
    }

    /** Exporte une methode */
    public static void exportClass(ClassDoc doc) {
        System.out.println("\t<class>");
        System.out.println("\t\t<name>"+doc.name()+"</name>");
        //exporte les methodes
		for (int j=0; j < doc.methods().length; j++) {
			exportMethod(doc.methods()[j]);
		}
        //exporte les membres
		//...
        System.out.println("\t</class>");
    }
    
    /** Exporte une methode */
    public static void exportMethod(MethodDoc doc) {
        System.out.println("\t\t<method>");
        System.out.println("\t\t\t<name>"+doc.name()+"</name>");
        System.out.println("\t\t</method>");
    }
}

