package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;

import java.util.Collection;

/**
 * Created by piolin on 12/12/16.
 */
public class TwitterSearchClient
{
    Collection<String> getSearchResult(String query)
    {
        return ImmutableList.of("apan", "bepan", "cepan");
    }
}
