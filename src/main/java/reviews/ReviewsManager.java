package reviews;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//provides access to a list of employee names and yearly reviews
public class ReviewsManager
{
	//all employee names (last names omitted)
	private Set<String> names;

	//a mapping of names to yearly reviews
	private Map<String, List<Review>> reviewData;
	
	public ReviewsManager()
	{
		names = new HashSet <>();
		reviewData = new HashMap<>();
		
		loadData();
	}

	//loads fictitious data from a text file
	private void loadData()
	{
		try(Scanner reader = new Scanner(new FileInputStream("src/files/reviews.txt")))
		{
			//for each review in the file
			while (reader.hasNextLine())
			{
				String line = reader.nextLine();
				
				//retrieve each part of the review
				String[] parts = line.split(", ");
				String name = parts[0];
				int year = Integer.parseInt(parts[1]);
				int[] kpis = new int[parts.length - 2];

				//parse out the key performance indicators
				for (int i = 0; i < kpis.length; i++)
				{
					kpis[i] = Integer.parseInt(parts[i + 2]);
				}

				//record the name encountered
				names.add(name);

				//add the review
				if (!reviewData.containsKey(name))
				{
					reviewData.put(name, new ArrayList<>());
				}
				reviewData.get(name).add(new Review(name, year, kpis));
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Write error: " + ex.getMessage());
		}
	}

	//returns an iterator for the reviews of an employee
	public Iterator<Review> getReviews(String name)
	{
		if (!reviewData.containsKey(name))
		{
			return null;
		}
		
		return reviewData.get(name).iterator();
	}

	//returns a list of employee names (with last names omitted)
	public Iterator<String> getNames()
	{
		return names.iterator();
	}
}
