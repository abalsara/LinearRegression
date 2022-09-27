/*
Name: Azita Balsara 
Date: 9/27/22

Read Me: 
This program performs a Linear Regression on a data set. It can be used 
to model the relationship between 1 independent and 1 dependent variable.
Given a set of randomized data points, this program finds and graphs a
line of best fit. 

- My Linear Regression class handles the main code and mathmetics of this 
program. 
- My Graphing class handles the visual graphing aspect of the program,
using DrawingPanel
- My Point class keeps track of a single Point, composed of x and y coordinates.
- The DrawingPanel classes, written by my previous professor Stuart Reges, form
a simple java drawing window program
*/

/* 
This class iteratively adjusts a predicted slope, mPredict, and 
y-intercept, cPredict, to better fit the given data. 

In each iteration, my program uses a parabolic error function to calculate the
accuracy of the current line. My program then adjusts the line to minimize
such error, and therfore improve accuracy of the line. The program stops adjusting
the line after 1000 iterations. 
*/

import java.awt.*;
import java.util.concurrent.TimeUnit;
public class LinearRegression{
   // array of data points
   private Point[] data;
   // number of data points
   private int n;
   //predicted values for slope and y-intercept of the line
   private double mPredict = 0.0;
   private double cPredict = 0.0;
   // Learning Rates to adjust the slope, mPredict, and y-intercept, cPredict
   private static final double mLearningRate = 0.000004;
   private static final double cLearningRate = 0.004;
   
   public LinearRegression(Point[] data){
      this.data = data;
      this.n = data.length;
   }
   
   // returns the predicted slope
   public double getM(){
      return mPredict;
   }
   
   // returns the predicted y-intercept
   public double getC(){
      return cPredict;
   }
   
   // adjusts the slope and y-intercept 1000 times to reduce error
   public void trainModel(){
      // do 1000 iterations
      for(int i = 0; i < 1000; i++){
         try{
            TimeUnit.MILLISECONDS.sleep(5);
         } catch(InterruptedException e) {
         
         }
         if(i%5 == 0){
            Graphing.resetPanel(data, mPredict, cPredict);
         }
         doSingleTrainingStep();
      }
   }
   
   // adjusts the predicted slope and y-intercept one time to reduce error
   public void doSingleTrainingStep(){
      double DEbyDMsum = 0;
      double DEbyDCsum = 0;
      
      for(Point p: data){
         double x = p.x();
         double y = p.y();
         double yPredict = mPredict*x + cPredict; 
         double difference = yPredict - y;
         DEbyDMsum += difference*x;
         DEbyDCsum += difference;
      }
      
     double DEbyDM = DEbyDMsum/n;
     double DEbyDC = DEbyDCsum/n;
     
     mPredict = mPredict - DEbyDM*mLearningRate;
     cPredict = cPredict - DEbyDC*cLearningRate;
   }
}