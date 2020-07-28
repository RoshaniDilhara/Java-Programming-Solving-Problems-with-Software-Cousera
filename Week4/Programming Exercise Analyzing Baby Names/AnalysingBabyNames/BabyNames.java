
/**
 * Programming Exercise: Analyzing Baby Names
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * @author Roshani Dilhara
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class BabyNames {
    
    public void printNames(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        
        for(CSVRecord record : parser){
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + record.get(0) +
				" Gender " + record.get(1) +
				" Num Born " + record.get(2));
           }
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int numBirths = 0;
        int boyBirths = 0;
        int girlBirths = 0;
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            numBirths = Integer.parseInt(rec.get(2));
            totalBirths +=  numBirths;
            
            if(rec.get(1).equals("M")){
                boyBirths += numBirths;
            }else{
                girlBirths += numBirths;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("boy births = " + boyBirths);
        System.out.println("girl births = " + girlBirths);
    }

    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    
    public int getRank(int year, String name, String gender){
        String fName = "us_babynames_by_year/yob"+year+".csv";
        //System.out.println(fName);
        FileResource fr = new FileResource(fName);
        int rankCount = 0;
        int maleCount = 0;        
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            rankCount++;            
                                    
            if(rec.get(1).equals("M")){                
                //System.out.println("maleC is " + maleCount);
                maleCount++;
                rankCount = maleCount;
                //System.out.println("maleC is " + maleCount);
                //System.out.println("rankC is " + rankCount);
            }
            
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                return rankCount;
            }
        }
    
    return -1;
    }
    
    public void testRank(){
        int rank = getRank(1971,"Frank","M");
        System.out.println("rank is " + rank);
    }
    
    public String getName(int year, int rank, String gender){
        String fName = "us_babynames_by_year/yob"+year+".csv";
        //System.out.println(fName);
        FileResource fr = new FileResource(fName);
        int currentRank = 0;
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            //int currentRank = getRank(year,rec.get(0),rec.get(1));
            
            if (rec.get(1).equals(gender)){
                currentRank++;
                if(currentRank == rank && rec.get(1).equals(gender)){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void testgetName(){
        String name = getName(1982,450,"M");
        System.out.println(name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int currYearRank = getRank(year, name, gender);
        String newName = getName(newYear, currYearRank, gender);        
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        
        DirectoryResource dr = new DirectoryResource();
        int year = Integer.MIN_VALUE;
        int rank = Integer.MAX_VALUE;
        
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            
            if (currentRank != -1 && currentRank < rank) {
                rank = currentRank;
                year = currentYear;
            }
            
        } 
        
        if (year == Integer.MIN_VALUE) {
            return -1;
        } else {
            return year;
        }
    }
    
    public void testYearOfHighestRank(){
     int year = yearOfHighestRank("Genevieve","F");
     System.out.println("Year of highest rank is " + year);
    }
    
    public double getAverageRank(String name, String gender){
    
        double totalRank = 0.0;
        int recCount = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            
            if (currRank != -1) {
                totalRank = totalRank + currRank;
                recCount++;
            }
        } 
        
        double avgRank = totalRank / recCount; 
        if (recCount == 0) {
            return -1;
        } else {
            return avgRank;
        }        
       // return -1;
    }
    
    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        double avgRank = getAverageRank(name, gender);
        System.out.println("Average rank for " + name + " is " + avgRank);
              
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String fName = "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fName);
        int rank = getRank(year, name, gender);
        int totalBirths = 0;
        int currentRank = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            String currentGender = record.get(1);
            
            if (currentGender.equals(gender)) {
                currentRank++;
                
                if (currentRank < rank) {
                    int currentBirths = Integer.parseInt(record.get(2));
                    totalBirths += currentBirths;
                } else {
                    break;
                }
            }
        }
        
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int totalBirths = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births higher than " + name + " is " + totalBirths);
    }
    
    
    
    
}
