package Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Solution2b {

private static NormalDistribution d;
double transmat[][] = {{0,0,0.5,0.25,0.25},{0.714,0,0,0.14,0.14},{0.166,0.166,0,0.33,0.33},{0.25,0.5,0.125,0,0.125},{0.33,0,0.166,0.5,0}};

static double[] albanyData = {60.44, 18.82, -200.04, 142.08, -195.59, 185.67, 200.59, 30.4, -336.63, 395.82, 202.59, 182.25, 159.14, 188.31, -270.73};
static double[] bostonData = {346.4, 79.09, 620.13, 62.12, 496.2, 95.53, 292.77, -297.47, 369.64};
static double[] washingtonData = {159.67, 537.1, 284.93, 152.77, 472.77, 346.42, -101.1, 61.39};
static double[] philadelphiaData = {231.84, 177.49, 180.69, 213.88, 93.81, 214.96, 97.44, 154.91, 46.59, 75.75};
static double[] newYorkData = {109.82, 100.25, 36.17, 3.76, 119.03, -22.4, 41.39};

public static void main(String[] args) {
	Solution2b sol=new Solution2b();
	sol.findLargestProbabiltySequence();
}
public void findLargestProbabiltySequence() {
	Map pi = new HashMap();
	pi.put("A",0.5);
	pi.put("B",0.3);
	pi.put("W",0.0);
	pi.put("P",0.1);
	pi.put("N",0.1);
	ArrayList piArray = new ArrayList();
	piArray.add("A");
	piArray.add("B");
	piArray.add("W");
	piArray.add("P");
	piArray.add("N");
	int r= 625;
	int c= 5;
	ArrayList sequence = new ArrayList();
	double largestProbability=0.0;

	for (int i=0;i<5;i++){
		for(int j = 0;j<5;j++){
			for(int k=0;k<5;k++){
				for(int l=0;l<5;l++){
					String state1 =  (String) piArray.get(i);
					String state2 =  (String) piArray.get(j);
					String state3 =  (String) piArray.get(k);
					String state4 =  (String) piArray.get(l);
					 double y0 =  transmat[2][i];
					 double y1 =  transmat[i][j];
					 double y2 =  transmat[j][k];
					 double y3 =  transmat[k][l];
					 
					 double cumulativeProbi=calCumulativeProb(i,355);
					 double cumulativeProbj=calCumulativeProb(j,339);
					 double cumulativeProbk=calCumulativeProb(k,148);
					 double cumulativeProbl=calCumulativeProb(l,50);

					double currentLargestProbability= y0*(double)cumulativeProbi*y1*(double)cumulativeProbj*y2*(double)cumulativeProbk*y3*(double)cumulativeProbl;
					if(currentLargestProbability > largestProbability){
						sequence.clear();
						sequence.add(state1);
						sequence.add(state2);
						sequence.add(state3);
						sequence.add(state4);
						largestProbability = currentLargestProbability;
					}
				}
			}
		}
	}
	System.out.println("Largest Probability: "+ largestProbability);
	System.out.println("Sequence of next four cities, given that current city is Washington D.C: "+ sequence.toString());
}

private double calCumulativeProb(int a, int i) {
	double cumulativeProbability=0.0;
	ArrayList totalData= new ArrayList();
	totalData.add(albanyData);
	totalData.add(bostonData);
	totalData.add(washingtonData);
	totalData.add(philadelphiaData);
	totalData.add(newYorkData);
	double mean = calMean( (double[]) totalData.get(a));
	double standardDev= standardDeviation( (double[]) totalData.get(a), mean);
	d = new NormalDistribution(mean, standardDev);
    cumulativeProbability = d.cumulativeProbability(i);
    return cumulativeProbability;
}

public static double calMean(double[] values){
		double sum = 0.0;
		for(int i=0; i<values.length; i++){
		sum = sum + values[i];
		}
		double mean = sum/values.length;
		return mean;
	}
	 public static double standardDeviation(double[] values, double mean){
		 double standardDev= 0.0;
		 double calstandardDev = 0.0;
		 for(int i=0; i<values.length; i++){
			double difference= ((double)values[i] - mean);
			 calstandardDev= calstandardDev + Math.pow(difference, 2);
		 }
		 double total=calstandardDev/values.length;
		 standardDev = Math.sqrt(total);
		 return standardDev;
	 }
}
