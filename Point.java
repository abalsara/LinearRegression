/* 
This class keeps track of a coordinate pair, composed of double x and double y
*/

public class Point{
   // x and y coordinates
   private double x;
   private double y;
   
   // sets x and y to 0 if not explicitly initialized
   public Point(){
      this(0,0);
   }
   
   // initializes x and y to the given values
   public Point(double x, double y){
      this.x = x;
      this.y = y;
   }
   
   // returns the x value
   public double x(){
      return x;
   }
   
   // returns the y value
   public double y(){
      return y;
   }
}