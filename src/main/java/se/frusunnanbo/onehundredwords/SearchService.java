package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by piolin on 12/12/16.
 */
public class SearchService
{
    public List<WordCount> countWordsForQuery(String query) {
        return ImmutableList.of(new WordCount());
    }
}
