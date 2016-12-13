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
    public static List<WordCount> findMostCommonWords(Collection<String> texts, int maxNumberOfWords)
    {
        return texts.stream()
                .flatMap(input -> Stream.of(input.split("\\s")))
                .filter(word -> !isUrl(word))
                .map(word -> cleanNonWordCharacters(word))
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new WordCount(entry.getKey(), entry.getValue()))
                .sorted()
                .limit(maxNumberOfWords)
                .collect(Collectors.toList());
    }

    private static boolean isUrl(String input) {
        return input.startsWith("https://") || input.startsWith("http://");
    }

    private static String cleanNonWordCharacters(String input) {
        return input.replaceAll("^[@#.]+", "").replaceAll("[@#.,;,:?!]+$", "");
    }
}
