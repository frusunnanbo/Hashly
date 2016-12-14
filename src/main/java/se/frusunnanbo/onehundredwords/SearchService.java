package se.frusunnanbo.onehundredwords;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SearchService
{
    private static final int MAX_CONCURRENT_REQUESTS = 3;
    private static final int MAX_NUMBER_OF_WORDS = 100;

    private final SearchClient searchClient;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENT_REQUESTS);

    public SearchService(SearchClient searchClient)
    {
        this.searchClient = searchClient;
    }

    public List<WordCount> countWordsForQuery(String query) {

        return countWords(getSearchResult(query));
    }

    private static List<WordCount> countWords(Collection<String> searchResult)
    {
        return WordProcessor.mostCommonWordsIn(searchResult, MAX_NUMBER_OF_WORDS);
    }

    private Collection<String> getSearchResult(String query)
    {
        Future<Collection<String>> future = executorService.submit(() -> searchClient.getSearchResult(query));
        try
        {
            return future.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            System.out.println("Ouch, caught exception when getting twitter results!" + e);
            return Collections.emptyList();
        }
    }
}
