/* 
This class handles the visual graphing aspect of the program.
I used Drawing Panel, a drawing window application to draw a graph, 
plot points, and draw a line of best fit. 
*/

import java.awt.*;
public class Graphing{
   public static Point[] data;
   public static Graphics g;

   // size of the drawing panel
   public static final int WIDTH = 1000;
   public static final int HEIGHT = 1000;
   
   // size of the margin
   public static final int MARGIN = 20;
   
   // DrawingPanel origin coordinates
   public static final int X_ORIGIN = WIDTH/2;
   public static final int Y_ORIGIN = HEIGHT/2;
   
   // Set Values for generating randomized data
   public static final double SLOPE = -0.5;
   public static final double YINT = 60;
   public static final double RANDOMNESS = 0.02;
   
   public static void main(String[] args){
      DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
      g = panel.getGraphics();
      drawGraph();
      data = drawData(SLOPE, YINT, RANDOMNESS);
      
      LinearRegression test = new LinearRegression(data);
      test.trainModel();
      double m = test.getM();
      double c = test.getC();
      
      System.out.println("Set Slope: " + SLOPE);
      System.out.println("Set Y-Intercept: " + YINT);
      
      System.out.println("Predicted Slope: " + m);
      System.out.println("Predicted Y-Intercept: " + c);
   }
   
   // Draws the axes of the graph
   public static void drawGraph(){
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, WIDTH, HEIGHT);
      g.setColor(Color.BLACK);
      g.drawLine(MARGIN, HEIGHT/2, WIDTH - MARGIN, HEIGHT/2); // x-axis
      g.drawString("x", WIDTH - MARGIN + 5, HEIGHT/2 + 10);
      g.drawLine(WIDTH/2, MARGIN, WIDTH/2, HEIGHT - MARGIN); // y-axis
      g.drawString("y", WIDTH/2 - 5, MARGIN - 5);
      
   }
   
   // Draws a single point
   public static void drawPoint(Point p){
      g.fillOval((int)(X_ORIGIN + p.x()), (int)(Y_ORIGIN - p.y()), 1, 1);
   }
   
   // Draws multiple points from a point array
   public static void drawMultiplePoints(Point[] points){
      for(Point p:points){
         drawPoint(p);
      }
   }
   
   // Draws randomized data points around a given slope and y-intercept
   // with given randomness
   // double m - slope of the given line
   // double c - y-intercept of the given line
   // double randomness - how scattered the data points are around the line
   public static Point[] drawData(double m, double c, double randomness){
      int start = -(X_ORIGIN - MARGIN);
      int end = X_ORIGIN - MARGIN;
      Point[] data = new Point[end-start];
      for(int i = start; i < end; i++){
         double x = (double)i;
         double y = (m*x + c) + (HEIGHT*randomness*(2*Math.random() - 1));       
         Point p = new Point(x, y);
         data[i-start] = p;
         g.setColor(Color.BLACK);
         drawPoint(p);
      }
      return data;
   }
   
   // Draws a line with given slope and y-intercept
   // double m - slope of the line
   // double c - y-intercept of the line
   public static void drawLine(double m, double c){
      int start = -(X_ORIGIN - MARGIN);
      int end = X_ORIGIN - MARGIN;
      Point[] data = new Point[end-start];
      for(int i = start; i < end; i++){
         double x = (double)i;
         double y = (m*x + c);       
         Point p = new Point(x, y);
         g.setColor(Color.RED);
         drawPoint(p);
      }
   }
   
   // resets the graph with an updated slope and y-intercept
   // double mPredict - new slope
   // double  cPredict - new y-intercept
   public static void resetPanel(Point[] p, double mPredict, double cPredict){
      drawGraph();
      drawMultiplePoints(p);
      drawLine(mPredict, cPredict);
   }

}