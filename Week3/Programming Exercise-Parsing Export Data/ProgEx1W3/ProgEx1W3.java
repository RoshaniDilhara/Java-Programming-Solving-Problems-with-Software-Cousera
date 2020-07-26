
/**
 * Write a description of listExporters here.
 * 
 * @author Roshani Dilhara
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ProgEx1W3 {

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String countryIf  = countryInfo(parser,"Nauru");
        //System.out.println(countryIf);
        parser = fr.getCSVParser();
        //listExportersTwoProducts(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser,"sugar"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser,String country){
        
	for (CSVRecord record : parser) {
		String export = record.get("Country");
		if (export.contains(country)) {
		    String s = country + ": " +record.get("Exports")+ ": " +record.get("Value (dollars)");
		    return s;
		}
	}
            
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        
        String s = null;
        for (CSVRecord record : parser) {
		String export = record.get("Exports");
		if (export.contains(exportItem1) && export.contains(exportItem2)) {
		    s = record.get("Country");
		    System.out.println(s);
		}
	}        
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem){
        
        int count = 0;
        for (CSVRecord record : parser) {
		String export = record.get("Exports");
		if (export.contains(exportItem)) {
		    count++;
		}
	} 
	return count;
    }
    
    public void bigExporters(CSVParser parser,String amount){
        
        int count = 0;
        String s = null;
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length()>amount.length()) {
		String export = record.get("Exports");
		s = record.get("Country") + " "+ record.get("Value (dollars)");
		System.out.println(s);
            }
	} 
    }
    
}
