//package Quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    //This method returns an integer that is the number of points in Shape s
    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for(Point currPt:s.getPoints()){
            count = count + 1;
        }
        return count;
    }

    /*This method returns a number of type double that is the 
     *calculated average of all the sides’ lengths in the Shape S
     */
    public double getAverageLength(Shape s) {
        // Put code here
        double perim = getPerimeter(s);
        int sides = getNumPoints(s);
        double avgLen = perim/sides;
        return avgLen;
    }

    /*This method returns a number of type double 
     * that is the longest side in the Shape S
     */
    public double getLargestSide(Shape s) {
        double lSide = 0.0;
        
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(lSide < currDist){
                lSide = currDist;
            }
        }
        return lSide;
    }

    /*
     * This method returns a number of type double that is the 
     * largest x value over all the points in the Shape s
     */
    public double getLargestX(Shape s) {
        // Put code here        
        Point prevPt = s.getLastPoint();
        //System.out.println(prevPt);
        
        double largeX = prevPt.getX();        
        
        for (Point currPt : s.getPoints()) {
            int currX = currPt.getX();
            if(largeX < currX){
                largeX = currX;
            }
            
        }
        return largeX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double perim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            //For each File f, it converts the file into a FileResource 
            FileResource fr = new FileResource(f);
            //creates a Shape from the FileResource
            Shape shape = new Shape(fr);
            //perimeter of the shape
            double currPerim = getPerimeter(shape);
            // return the the largest perimeter over the shape in the file
            if(perim < currPerim){
                perim = currPerim;
            }
            //System.out.println(f);
        }
        return perim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double perim = 0.0;        
        File temp = null;  
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            //For each File f, it converts the file into a FileResource 
            FileResource fr = new FileResource(f);
            //creates a Shape from the FileResource
            Shape shape = new Shape(fr);
            //perimeter of the shape
            double currPerim = getPerimeter(shape);
            // return the the largest perimeter over the shape in the file
            if(perim < currPerim){
                perim = currPerim;
                temp = f;
            }
        }  
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int noOfPts = getNumPoints(s); 
        double avgLen = getAverageLength(s);
        double longestSide = getLargestSide(s);
        double largeX = getLargestX(s);
        System.out.println("Number of points = " + noOfPts);
        System.out.println("Average Length = " + avgLen);
        System.out.println("Longest Side = " + longestSide);
        System.out.println("Longest X = " + largeX);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter from mul files = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileL = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + fileL);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
