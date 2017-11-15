package strategies;

import reviews.Review;

import java.util.Iterator;

public class ImprovementStrategy implements IStrategy
{
    @Override
    public int getScore(int year, Iterator<Review> reviews)
    {
        while (reviews.hasNext())
        {
            Review review = reviews.next();

            if (review.getYear() == year)
            {
                if (reviews.hasNext())
                {
                    Review previousReview = reviews.next();

                    if (previousReview.getYear() == (year - 1))
                    {
                        int score = 0;

                        for (int i = 0; i < review.getKpis().length; i++)
                        {
                            score += (review.getKpis()[i] - previousReview.getKpis()[i]);
                        }
                        return score;
                    }
                    return 0;
                }
            }
            return 0;
        }
        return 0;
    }

}
