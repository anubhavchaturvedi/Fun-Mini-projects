import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Analyzer  {

	ArrayList<Record> recordList;
	int randomNumbers[];
	final int primes[] = { 2 , 3 , 5 ,  7 ,  11 ,  13 ,  17 ,  19 ,  23 ,  29 ,  31 ,  37 ,  41 ,  43 ,  47 ,  53 ,  59 ,  61 ,  67 ,  71 ,  73 ,  79 ,  83 ,  89 ,  97 ,  101 ,  103 ,  107 ,  109 ,  113 ,  127 ,  131 ,  137 ,  139 ,  149 ,  151 ,  157 ,  163 ,  167 ,  173 ,  179 ,  181 ,  191 ,  193 ,  197 ,  199 ,  211 ,  223 ,  227 ,  229 ,  233 ,  239 ,  241 ,  251 ,  257 ,  263 ,  269 ,  271 ,  277 ,  281 ,  283 ,  293 ,  307 ,  311 ,  313 ,  317 ,  331 ,  337 ,  347 ,  349 ,  353 ,  359 ,  367 ,  373 ,  379 ,  383 ,  389 ,  397 ,  401 ,  409 ,  419 ,  421 ,  431 ,  433 ,  439 ,  443 ,  449 ,  457 ,  461 ,  463 ,  467 ,  479 ,  487 ,  491 ,  499 ,  503 ,  509 ,  521 ,  523 ,  541 ,  547 ,  557 ,  563 ,  569 ,  571 ,  577 ,  587 ,  593 ,  599 ,  601 ,  607 ,  613 ,  617 ,  619 ,  631 ,  641 ,  643 ,  647 ,  653 ,  659 ,  661 ,  673 ,  677 ,  683 ,  691 ,  701 ,  709 ,  719 ,  727 ,  733 ,  739 ,  743 ,  751 ,  757 ,  761 ,  769 ,  773 ,  787 ,  797 ,  809 ,  811 ,  821 ,  823 ,  827 ,  829 ,  839 ,  853 ,  857 ,  859 ,  863 ,  877 ,  881 ,  883 ,  887 ,  907 ,  911 ,  919 ,  929 ,  937 ,  941 ,  947 ,  953 ,  967 ,  971 ,  977 ,  983 ,  991 ,  997 };
	final int fibonacci[] = { 1 , 1 , 2 , 3 , 5 , 8 , 13 , 21 , 34 , 55 , 89 , 144 , 233 , 377 , 610 , 987};
	final int perfectCubes[]={1 , 8 , 27 , 64 , 125 , 216 , 343 , 512 , 729};
	final int perfectSquares[]={1 , 4 , 9 , 16 , 25 , 36 , 49 , 64 , 81 , 100 , 121 , 144 , 169 , 196 , 225 , 256 , 289 , 324 , 361 , 400 , 441 , 484 , 529 , 576 , 625 , 676 , 729 , 784 , 841 , 900 , 961};
	
	public Analyzer(ArrayList<Record> list)
	{
		recordList = list;
		
		Random random = new Random(325229268);
		randomNumbers = new int[list.size() * 3];
		for(int i=0;i< randomNumbers.length;i++)
		{
			randomNumbers[i]=random.nextInt(1000);
			while(randomNumbers[i] < 100 || randomNumbers[i] > 1000)
				randomNumbers[i]=random.nextInt(999);
		}
	}
	
	public void evenOddCount(DefaultCategoryDataset dataset)
	{
		System.out.println("Even vs Odd count of Numbers Selected:");
		
		int countEven_human = 0;
		int countOdd_human = 0;
		for(Record record : recordList)
		{
			int number[]=record.getNumbers();
			for( int num : number)
			{
				if(num%2 == 0)
					countEven_human++;
				else
					countOdd_human++;
			}
		}
		
		System.out.println("Humans \t: \tEven : "+ countEven_human + "\t Odd : "+countOdd_human);
		
		int countEven_rng = 0;
		int countOdd_rng = 0;
		
		for( int num : randomNumbers)
		{
			if(num%2 == 0)
				countEven_rng++;
			else
				countOdd_rng++;
		}
		System.out.println("RNG  \t: \tEven : "+ countEven_rng + "\t Odd : "+countOdd_rng);
		System.out.println();
		
		dataset.addValue(countOdd_human, "Human", "Odd Number");
		dataset.addValue(countOdd_rng, "RNG", "Odd Number");
		
		dataset.addValue(countEven_human, "Human", "Even Number");
		dataset.addValue(countEven_rng, "RNG", "Even Number");
	}
	
	public void primeCount(DefaultCategoryDataset dataset)
	{
		System.out.println("Prime number count of Numbers Selected:");
		
		int countPrime_human=0;
		for(Record record : recordList)
		{
			int number[]=record.getNumbers();
			for( int num : number)
			{
				if(Arrays.binarySearch(primes, num)>=0)
					countPrime_human++;
			}
		}
		
		int countPrime_rng=0;
		for( int num : randomNumbers)
		{
			if(Arrays.binarySearch(primes, num)>=0)
				countPrime_rng++;
		}
		System.out.println("Humans \t: "+ countPrime_human + "\t RNG : "+ countPrime_rng );
		System.out.println();
		
		dataset.addValue(countPrime_human, "Human", "Prime Numbers");
		dataset.addValue(countPrime_rng, "RNG", "Prime Numbers");
	}
	
	public void countPerfectSquares(DefaultCategoryDataset dataset)
	{
		System.out.println("Count of perfect squares:");
		
		int count_human=0;
		for(Record record : recordList)
		{
			for(int num : record.getNumbers())
			{
				if(Arrays.binarySearch(perfectSquares, num) >= 0 )
					count_human++;
			}
		}

		int count_rng=0;
		for(int num : randomNumbers)
		{
			if(Arrays.binarySearch(perfectSquares, num) >= 0 )
				count_rng++;
		}
		
		System.out.println("Humans \t: "+ count_human + "\t RNG : "+ count_rng );
		System.out.println();
		
		dataset.addValue(count_human, "Human", "Perfect Squares");
		dataset.addValue(count_rng, "RNG", "Perfect Squares");
		
	}
	
	public void countPerfectCubes(DefaultCategoryDataset dataset)
	{
		System.out.println("Count of perfect cubes :");
		
		int count_human=0;
		for(Record record : recordList)
		{
			for(int num : record.getNumbers())
			{
				if(Arrays.binarySearch(perfectCubes, num) >= 0 )
					count_human++;
			}
		}
		
		int count_rng=0;
		for(int num : randomNumbers)
		{
			if(Arrays.binarySearch(perfectCubes, num) >= 0 )
				count_rng++;
		}
		
		System.out.println("Humans \t: "+ count_human + "\t RNG : "+ count_rng );
		System.out.println();
		
		dataset.addValue(count_human, "Human", "Perfect Cubes");
		dataset.addValue(count_rng, "RNG", "Perfect Cubes");
	}
	
	public void countFibonacci(DefaultCategoryDataset dataset)
	{
		System.out.println("Count of Fibonacci Numbers :");
		
		int count_human=0;
		for(Record record : recordList)
		{
			for(int num : record.getNumbers())
			{
				if(Arrays.binarySearch(fibonacci, num) >= 0 )
					count_human++;
			}
		}
		
		int count_rng=0;
		for(int num : randomNumbers)
		{
			if(Arrays.binarySearch(fibonacci, num) >= 0 )
				count_rng++;
		}
		
		System.out.println("Humans \t: "+ count_human + "\t RNG : "+ count_rng );
		System.out.println();
		
		dataset.addValue(count_human, "Human", "Fibonacci Numbers");
		dataset.addValue(count_rng, "RNG", "Fibonacci Numbers");
	}
	
	public void frequencyDistribution()
	{
		System.out.println("Frequency distribution : ");
		int arr_human[]=new int[900];
		int arr_random[]=new int[900];
		Arrays.fill(arr_human, 0);
		Arrays.fill(arr_random, 0);
		
		for(Record record : recordList)
		{
			for(int num : record.getNumbers())
			{
				arr_human[num-100]++;
			}
		}
		
		
		for(int num : randomNumbers)
		{
			arr_random[num-100]++;
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		System.out.println("Value\tHuman\tRNG");
		for(int i=0;i<arr_human.length;i++)
		{
			dataset.addValue(arr_human[i], "Human", Integer.toString(i+100));
			dataset.addValue(arr_random[i], "RNG", Integer.toString(i+100));
			System.out.println((i+100)+"\t"+arr_human[i]+"\t"+arr_random[i]);
		}
		
		JFreeChart chart = ChartFactory.createBarChart("Frequency Distribution", "Number selected", "frequency", dataset,PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart,false);
		
		JFrame frame = new JFrame("Frequency Distribution");
		frame.getContentPane().add(chartPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void sequenceOrderCount(DefaultCategoryDataset dataset)
	{
		System.out.println("Order in selection:");
		
		int index=0;
		
		int countIncreasing_human=0;
		int countDecreasing_human=0;
		int countOther_human=0;
		
		int countIncreasing_rng=0;
		int countDecreasing_rng=0;
		int countOther_rng=0;
		
		for(Record record : recordList)
		{
			int order_rng = sequenceorder(randomNumbers[index],	randomNumbers[index+1], randomNumbers[index+2]);
			
			int humanSelection[] = record.getNumbers();
			int order_human = sequenceorder(humanSelection[0], humanSelection[1], humanSelection[2]);
			
			if(order_human==0)
				countOther_human++;
			else if(order_human==1)
				countIncreasing_human++;
			else if(order_human==2)
				countDecreasing_human++;
			
			if(order_rng==0)
				countOther_rng++;
			else if(order_rng==1)
				countIncreasing_rng++;
			else if(order_rng==2)
				countDecreasing_rng++;
			
			index+=3;
		}
		
		System.out.println("Human : \tIncreasing : "+countIncreasing_human
				+"\tDecreasing : "+countDecreasing_human
				+"\tOther : "+countOther_human);
		System.out.println("RNG   : \tIncreasing : "+countIncreasing_rng
				+"\tDecreasing : "+countDecreasing_rng
				+"\tOther : "+countOther_rng);
		
		dataset.addValue(countIncreasing_human, "Human", "Increasing Order");
		dataset.addValue(countIncreasing_rng, "RNG", "Increasing Order");
		
		dataset.addValue(countDecreasing_human, "Human", "Decreasing Order");
		dataset.addValue(countDecreasing_rng, "RNG", "Decreasing Order");
		
		dataset.addValue(countOther_human, "Human", "Other Order");
		dataset.addValue(countOther_rng, "RNG", "Other Order");
	}
	
	/*
	 * Tells if a,b,c are in increasing, decreasing or no order
	 */
	int sequenceorder(int a , int b, int c)
	{
		if(a<b && b<c)
			return 1; // increasing
		else if( a>b && b>c)
			return 2; // decreasing
		else
			return 0;
	}
	
	public void process()
	{
		System.out.println("********* PROCESSING DATA ************");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		evenOddCount(dataset);
		primeCount(dataset);
		countPerfectSquares(dataset);
		countPerfectCubes(dataset);
		countFibonacci(dataset);
		sequenceOrderCount(dataset);
		
		
		JFreeChart chart = ChartFactory.createBarChart("Basic comaprisions", "Category", "Count", dataset,PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart,false);
		
		JFrame frame = new JFrame("Basic Analysis");
		frame.getContentPane().add(chartPanel);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println();
	}

	
	public static void main(String args[]) throws FileNotFoundException
	{
		DataReader reader = new DataReader(new File("data.csv"));
		Analyzer obj = new Analyzer(reader.process());
		
		obj.process();
		obj.frequencyDistribution();
	}
}
