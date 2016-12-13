package se.frusunnanbo.onehundredwords;

import twitter4j.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by piolin on 12/12/16.
 */
public class TwitterSearchClient
{
    private static final int MAX_TWEETS = 100;

    Collection<String> getSearchResult(String query)
    {
        Twitter twitter = TwitterFactory.getSingleton();
       try
        {
            QueryResult result = twitter.search(new Query(query).count(MAX_TWEETS));
            return result.getTweets().stream().map(tweet -> tweet.getText()).collect(Collectors.toList());
        }
        catch (TwitterException e)
        {
            return Collections.emptyList();
        }
    }
}
