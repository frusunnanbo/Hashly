package se.frusunnanbo.onehundredwords;

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
        assertThat(mostCommonWordsIn(""), emptyCollectionOf(WordCount.class));
    }

    @Test
    public void counts_no_empty_words() {
        assertThat(mostCommonWordsIn("", "", ""), emptyCollectionOf(WordCount.class));
    }

    @Test
    public void counts_a_single_word() {
        assertThat(mostCommonWordsIn("apan"), containsInAnyOrder(wordCount("apan", 1)));
    }

    @Test
    public void counts_two_different_words() {
       assertThat(mostCommonWordsIn("apan", "bepan"), containsInAnyOrder(wordCount("apan", 1), wordCount("bepan", 1)));
    }

    @Test
    public void counts_two_space_separated_words() {
       assertThat(mostCommonWordsIn("apan bepan"), containsInAnyOrder(wordCount("apan", 1), wordCount("bepan", 1)));
    }

    @Test
    public void counts_a_duplicate_word() {
        assertThat(mostCommonWordsIn("apan", "apan"),
                containsInAnyOrder(wordCount("apan", 2)));
    }

    @Test
    public void counts_two_space_separated_pairs() {
         assertThat(mostCommonWordsIn("apan bepan", "bepan apan"),
                 containsInAnyOrder(wordCount("apan", 2), wordCount("bepan", 2)));
    }

    @Test
    public void sorts_in_descending_order() {
        assertThat(mostCommonWordsIn("apan bepan cepan", "bepan apan", "apan"),
                contains(wordCount("apan", 3), wordCount("bepan", 2), wordCount("cepan", 1)));
    }

    @Test
    public void limits_output() {
        assertThat(mostCommonWordsIn(2, asList("apan bepan cepan", "bepan apan", "apan")),
                contains(wordCount("apan", 3), wordCount("bepan", 2)));
    }

    @Test
    public void lowercases_all_words() {
        assertThat(mostCommonWordsIn("Fisk fisk FISK fISK fIsk"), contains(wordCount("fisk", 5)));
    }

    @Test
    public void filters_away_links() {
        assertThat(mostCommonWordsIn("http://apan.com", "giraffen", "https://gnun.se"),
                contains(wordCount("giraffen", 1)));
    }

    @Test
    public void handles_all_space_as_space() {
        assertThat(mostCommonWordsIn("torskblock\nananas\tsillsallad"),
                containsInAnyOrder(wordCount("torskblock", 1), wordCount("ananas", 1), wordCount("sillsallad", 1)));
    }

    @Test
    public void cleans_non_word_characters() {
        assertThat(mostCommonWordsIn("#lucia, #bolibompa. #gingerdragon @ Sveriges Television: https://t.co/sst65Se20j"),
                containsInAnyOrder(
                        wordCount("lucia", 1),
                        wordCount("bolibompa", 1),
                        wordCount("gingerdragon", 1),
                        wordCount("sveriges", 1),
                        wordCount("television", 1)));
    }

    @Test
    public void preserves_local_characters() {
        assertThat(mostCommonWordsIn("räksmörgås"),
                containsInAnyOrder(wordCount("räksmörgås", 1)));

    }

    private List<WordCount> mostCommonWordsIn(String... texts)
    {
        return mostCommonWordsIn(DEFAULT_MAX_WORDS, asList(texts));
    }

    private List<WordCount> mostCommonWordsIn(int maxNumberOfResults, Collection<String> texts)
    {
        return findMostCommonWords(texts, maxNumberOfResults);
    }

    private static WordCount wordCount(String word, int count)
    {
        return new WordCount(word, count);
    }

}
