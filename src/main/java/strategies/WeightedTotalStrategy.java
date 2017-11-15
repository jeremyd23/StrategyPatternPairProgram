package strategies;

import reviews.Review;

import java.util.Iterator;

public class WeightedTotalStrategy implements IStrategy
{
    @Override
    public int getScore(int year, Iterator<Review> reviews)
    {
        while(reviews.hasNext())
        {
            Review review = reviews.next();

            if(review.getYear() == year)
            {
                int score = 0;

                for(int i  = 0; i < review.getKpis().length; i++)
                {
                    if(i == 1)
                    {
                       score += (review.getKpis()[i] * 2);
                    }
                    else if(i == 3)
                    {
                        score += (review.getKpis()[i] * 3);
                    }
                    else
                    {
                        score += review.getKpis()[i];
                    }
                }

                return score;
            }
        }

        return 0;
    }
}
