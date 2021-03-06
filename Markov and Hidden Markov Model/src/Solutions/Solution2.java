package Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution1b {

double transmat[][] = {{0,0,0.5,0.25,0.25},{0.714,0,0,0.14,0.14},{0.166,0.166,0,0.33,0.33},{0.25,0.5,0.125,0,0.125},{0.33,0,0.166,0.5,0}};

public static void main(String[] args) {
	Solution1b sol=new Solution1b();
	sol.findLargestSequence();
	
}
private void findLargestSequence() {
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
					
					double currentLargestProbability= y0*y1*y2*y3;
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
}
