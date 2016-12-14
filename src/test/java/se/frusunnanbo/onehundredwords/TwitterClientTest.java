package se.frusunnanbo.onehundredwords;

import org.junit.Ignore;
import org.junit.Test;
import twitter4j.TwitterException;

import java.util.Collection;

public class TwitterClientTest
{
    @Ignore("This is not a test, just a convenient sanity check for manual use.")
    @Test
    public void can_search_for_hashtag() throws TwitterException
    {
        Collection<String> texts = new TwitterSearchClient().getSearchResult("#bolibompa");
        texts.forEach(text -> System.out.println(text));
        System.out.println("Found total: " + texts.size());
    }
}
