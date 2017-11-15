package reviews;

import strategies.IStrategy;
import strategies.ImprovementStrategy;
import strategies.TotalStrategy;
import strategies.WeightedTotalStrategy;

import java.util.*;

public class RankedReviews
{
	private static IStrategy strategy;
	private static final int YEAR = 2017;
	private static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		//prompt the user for a strategy...
		System.out.println("Employee Rankings Application");
		System.out.println("*****************************");

		System.out.println("Enter a strategy (Improvement, total, weighted)");
		String response = console.next();

		if(response.equalsIgnoreCase("improvement"))
		{
			strategy = new ImprovementStrategy();
		}
		else if(response.equalsIgnoreCase("total"))
		{
			strategy = new TotalStrategy();
		}
		else if(response.equalsIgnoreCase("weighted"))
		{
			strategy = new WeightedTotalStrategy();
		}

		//create a manager to access employee names and reviews
		ReviewsManager manager = new ReviewsManager();

		//create a ranked list of employee reviews
		List<RankedReview> rankedList = new ArrayList<>();

		/*
		   for each user generate a score for 2017 using the given
		   strategy and then add a ranked review (see below) to the
		   list above...
		*/
		Iterator<String> employees = manager.getNames();

		while(employees.hasNext())
		{
			String name = employees.next();
			Iterator<Review> reviews = manager.getReviews(name);
			int score = strategy.getScore(YEAR, reviews);
			rankedList.add(new RankedReview(name, score));
		}

		//sort our results
		RankedReview[] rankedReviews = rankedList.toArray(new RankedReview[rankedList.size()]);
		Arrays.sort(rankedReviews);

		//print out the ranked list
		for (int i = 0; i < rankedReviews.length; i++)
		{
			System.out.println(rankedReviews[i].name + ": " + rankedReviews[i].score);
		}
	}

	//This class represents an employee and review score.
	public static class RankedReview implements Comparable<RankedReview>
	{
		private String name;
		private int score;
		
		public RankedReview(String name, int score)
		{
			this.name = name;
			this.score = score;
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getScore()
		{
			return score;
		}

		//ranked reviews are sorted descending by score
		public int compareTo(RankedReview other)
		{
			return other.score - this.score;
		}
	}
}
