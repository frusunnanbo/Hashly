package se.frusunnanbo.onehundredwords;

import java.util.Collection;

/**
 * Created by piolin on 13/12/16.
 */
public interface SearchClient
{
    Collection<String> getSearchResult(String query);
}
