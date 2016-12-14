package se.frusunnanbo.onehundredwords;

import java.util.Collection;

public interface SearchClient
{
    Collection<String> getSearchResult(String query);
}
