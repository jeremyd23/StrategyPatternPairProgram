package reviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RankedReviews
{
	private static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		//prompt the user for a strategy...

		//create a manager to access employee names and reviews
		ReviewsManager manager = new ReviewsManager();

		//create a ranked list of employee reviews
		List<RankedReview> rankedList = new ArrayList<>();

		/*
		   for each user generate a score for 2017 using the given
		   strategy and then add a ranked review (see below) to the
		   list above...
		*/

		//sort our results
		RankedReview[] rankedReviews = rankedList.toArray(
				new RankedReview[rankedList.size()]);
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
