package strategies;

import reviews.Review;

import java.util.Iterator;

public interface IStrategy
{
    int getScore(int year, Iterator<Review> reviews);
}
