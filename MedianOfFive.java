/*

	Created by: Austin Bryant

	- The goal of this program is to determine the median of five numbers using the smallest number of comparisons possible.

	*Initial thoughts*:

	I will initially start with a very straightforward, bruteforce solution, but I will then attempt to optimize it into a much more
	elegant and efficient one.

	*Conclusion*:

	Through some research, I was able to discover an algorithm created by H.B. Demuth, who managed to prove that the minimum number
	of comparisons needed to find the median of a set of five numbers is seven.

	I have recreated this algorithm below in the method "superConfusingMedianAlgorithm", which I named due to the complexity that comes 
	with optimizing this deceptively simple problem.

*/


import java.util.*;
import java.io.*;


public class MedianOfFive
{

	public static void main(String [] args)	
	{

		//The correct answer is: 13
		int [] array = new int[] { 5, 10, 14, 20, 13};
		//The correct answer is: 7
		int [] edge = new int[] {5, 5, 7, 8, 8};
		//The correct answer is: 9
		int [] edge2 = new int[] {18, 14, 3, 1, 9};
		//The correct answer is: 14
		int [] samesies = new int[] {14, 14, 14, 14, 14};

		System.out.println(superConfusingMedianAlgorithm(edge));
		System.out.println(superConfusingMedianAlgorithm(edge2));
		System.out.println(superConfusingMedianAlgorithm(array));
		System.out.println(superConfusingMedianAlgorithm(samesies));
	}

	//This is an incredibly convulted, yet intersting algorithm, that H.B. Demuth discovered in 1956.
	//This algorithm demonstrates that it is possible to find the median of a set of five numbers by only using
	//at most, 7 comparisons. 
	public static int superConfusingMedianAlgorithm(int [] array)
	{
		//first, we need to sort the first two pairs in the array
		int temp;
		int [] sorted = new int[5];

		//for my sanity, I decided to create a new array for processing our solution.
		for(int i = 0; i < 5; i++)
			sorted[i] = array[i];

		//sorts the first pair
		if(array[0] > array[1])
		{
			temp = array[1];
			array[1] = array[0];
			array[0] = temp;
		}
		//sorts the second pair
		if(array[2] > array[3])
		{
			temp = array[3];
			array[3] = array[2];
			array[2] = temp;
		}

		//sorts the first two pairs w.r.t. their largest respective element
		if(array[1] > array[3])
		{
			sorted[0] = array[2];
                        sorted[1] = array[3];
                        sorted[2] = array[0];
                        sorted[3] = array[1];
		}
		else
		{
			sorted[0] = array[0];
                        sorted[1] = array[1];
                        sorted[2] = array[2];
                        sorted[3] = array[3];
		}
		//for the sake of simplicity, a = 0, b = 1, c = 2, d = 3.
		//By this notion, we know for certain that a < b < d and also that c < d. However, we cannot be certain
		//that a and b are < c.
		int c = sorted[2];
		int e = array[4];
		
		//If E is greater than b
		if(e > sorted[1])
		{
			//is E less than D? If not, then it is currently in the correct place.
			if(e < sorted[3])
				sorted[2] = e;
		}
		//Otherwise, we should check to see if E is greater than A.
		else
		{
			//If E is greater than A.
			if(e > array[0])
			{
				sorted[2] = sorted[1];
				sorted[1] = e;
			}
		}

		//Now we need to insert C back into the array.
		//We know that C is certainly less than sorted[3].
		//So, if C is greater than sorted[2], then sorted[2] is our median.
		if(c > sorted[2])
			return sorted[2];
		//Otherwise, if C is greater than sorted[1], that means the C is now the middle element of our set.
		else if(c > sorted[1])
			return c;
		//Otherwise, if C is smaller in value than sorted[1], then sorted[1] will become the median of our data set.
		else
			return sorted[1];
	}
}
