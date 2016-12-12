package se.frusunnanbo.onehundredwords;

import org.junit.Test;
import twitter4j.*;

/**
 * Created by piolin on 12/12/16.
 */
public class TwitterClientTest
{
    @Test
    public void can_search_for_hashtag() throws TwitterException
    {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("#brexit");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }
}
