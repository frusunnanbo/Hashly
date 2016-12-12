package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

/**
 * Created by piolin on 12/12/16.
 */
public class SearchService
{
    private final TwitterSearchClient searchClient;

    public SearchService(TwitterSearchClient searchClient)
    {
        this.searchClient = searchClient;
    }

    public List<WordCount> countWordsForQuery(String query) {

        return countWords(searchClient.getSearchResult(query));
    }

    private static List<WordCount> countWords(Collection<String> searchResult)
    {
        return ImmutableList.of(new WordCount());
    }
}
