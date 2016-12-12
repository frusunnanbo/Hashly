package se.frusunnanbo.onehundredwords;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by piolin on 12/12/16.
 */
public class WordCounter
{
    public static List<WordCount> countWordsIn(Collection<String> text)
    {
        return text.stream()
                .flatMap(input -> Stream.of(input.split(" ")))
                .distinct()
                .map(word -> new WordCount())
                .collect(Collectors.toList());
    }
}
