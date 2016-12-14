package se.frusunnanbo.onehundredwords;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertTrue;

/**
 * Created by piolin on 13/12/16.
 */
public class SearchServiceTest
{
    private static final int MAX_CONCURRENCY_LEVEL = 3;
    private static final int NUMBER_OF_CONCURRENT_ATTEMPTS = 10;

    private SearchService searchService;
    private final ConcurrencyCountingClient concurrencyCountingClient = new ConcurrencyCountingClient();

    @Test
    public void limits_parallell_twitter_requests_to_three() throws InterruptedException
    {
        givenSearchServiceWithClient(concurrencyCountingClient);

        whenSearchRequestsAreIssued(searchService, NUMBER_OF_CONCURRENT_ATTEMPTS);

        assertThat(concurrencyCountingClient.maxConcurrencyLevelSeen(), is(lessThanOrEqualTo(MAX_CONCURRENCY_LEVEL)));
    }

    private void givenSearchServiceWithClient(SearchClient searchClient) {
        this.searchService = new SearchService(searchClient);
    }

    private void whenSearchRequestsAreIssued(SearchService searchService, int numberOfConcurrentAttempts) throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfConcurrentAttempts);
        IntStream.rangeClosed(1, numberOfConcurrentAttempts)
                .forEach(i -> executorService.submit(() -> searchService.countWordsForQuery("query" + i)));

        executorService.shutdown();
        assertTrue("Executor did not terminate all jobs", executorService.awaitTermination(15, TimeUnit.SECONDS));
    }

    private static class ConcurrencyCountingClient implements SearchClient
    {
        private final AtomicInteger concurrencyCounter = new AtomicInteger(0);
        private final AtomicInteger maxConcurrencyLevelSeen = new AtomicInteger(0);

        public int maxConcurrencyLevelSeen() {
            return maxConcurrencyLevelSeen.get();
        }

        @Override
        public Collection<String> getSearchResult(String query)
        {
            int concurrencyLevel = concurrencyCounter.incrementAndGet();
            maxConcurrencyLevelSeen
                    .getAndUpdate(currentMax -> (currentMax < concurrencyLevel) ? concurrencyLevel : currentMax);
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println("Interrupted!");
            }
            concurrencyCounter.decrementAndGet();
            return Arrays.asList("apan", "gnun", "krokodilen");
        }
    }
}
