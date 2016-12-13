package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static se.frusunnanbo.onehundredwords.WordCounter.findMostCommonWords;

/**
 * Created by piolin on 12/12/16.
 */
public class WordCounterTest
{
    private static int DEFAULT_MAX_WORDS = 100;

    @Test
    public void counts_no_words() {
        // given
        Collection<String> text = ImmutableList.of("");

        // then
        assertThat(mostCommonWordsIn(text), emptyCollectionOf(WordCount.class));
    }

    @Test
    public void counts_no_empty_words() {
        // given
        Collection<String> text = ImmutableList.of("", "", "");

        // then
        assertThat(mostCommonWordsIn(text), emptyCollectionOf(WordCount.class));
    }

    @Test
    public void counts_a_single_word() {
        // given
        Collection<String> text = ImmutableList.of("apan");

        // then
        assertThat(mostCommonWordsIn(text), containsInAnyOrder(new WordCount("apan", 1)));
    }

    @Test
    public void counts_two_different_words() {
        // given
        Collection<String> text = ImmutableList.of("apan", "bepan");

        // then
        assertThat(mostCommonWordsIn(text), containsInAnyOrder(new WordCount("apan", 1), new WordCount("bepan", 1)));
    }

    @Test
    public void counts_two_space_separated_words() {
        // given
        Collection<String> text = ImmutableList.of("apan bepan");

        // then
        assertThat(mostCommonWordsIn(text), containsInAnyOrder(new WordCount("apan", 1), new WordCount("bepan", 1)));
    }

    @Test
    public void counts_a_duplicate_word() {
        // given
        Collection<String> text = ImmutableList.of("apan", "apan");

        // then
        assertThat(mostCommonWordsIn(text), containsInAnyOrder(new WordCount("apan", 2)));
    }

    @Test
    public void counts_two_space_separated_pairs() {
        // given
        Collection<String> text = ImmutableList.of("apan bepan", "bepan apan");

        // then
        assertThat(mostCommonWordsIn(text), containsInAnyOrder(new WordCount("apan", 2), new WordCount("bepan", 2)));
    }

    @Test
    public void sorts_in_descending_order() {
        assertThat(mostCommonWordsIn(texts("apan bepan cepan", "bepan apan", "apan")),
                contains(new WordCount("apan", 3), new WordCount("bepan", 2), new WordCount("cepan", 1)));
    }

    @Test
    public void limits_output() {
        assertThat(mostCommonWordsIn(texts("apan bepan cepan", "bepan apan", "apan"), 2),
                contains(new WordCount("apan", 3), new WordCount("bepan", 2)));
    }

    private List<WordCount> mostCommonWordsIn(Collection<String> texts)
    {
        return mostCommonWordsIn(texts, DEFAULT_MAX_WORDS);
    }

    private List<WordCount> mostCommonWordsIn(Collection<String> texts, int maxNumberOfResults)
    {
        return findMostCommonWords(texts, maxNumberOfResults);
    }

    private Collection<String> texts(String...texts)
    {
        return asList(texts);
    }

}
