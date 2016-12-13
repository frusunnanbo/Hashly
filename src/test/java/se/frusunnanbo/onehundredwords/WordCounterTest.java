package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;

/**
 * Created by piolin on 12/12/16.
 */
public class WordCounterTest
{
    @Test
    public void counts_no_words() {
        // given
        Collection<String> text = ImmutableList.of("");

        // then
        assertThat(WordCounter.countWordsIn(text), emptyCollectionOf(WordCount.class));
    }

    @Test
    public void counts_no_empty_words() {
        // given
        Collection<String> text = ImmutableList.of("", "", "");

        // then
        assertThat(WordCounter.countWordsIn(text), emptyCollectionOf(WordCount.class));
    }

    @Test
    public void counts_a_single_word() {
        // given
        Collection<String> text = ImmutableList.of("apan");

        // then
        assertThat(WordCounter.countWordsIn(text), containsInAnyOrder(new WordCount("apan", 1)));
    }

    @Test
    public void counts_two_different_words() {
        // given
        Collection<String> text = ImmutableList.of("apan", "bepan");

        // then
        assertThat(WordCounter.countWordsIn(text), containsInAnyOrder(new WordCount("apan", 1), new WordCount("bepan", 1)));
    }

    @Test
    public void counts_two_space_separated_words() {
        // given
        Collection<String> text = ImmutableList.of("apan bepan");

        // then
        assertThat(WordCounter.countWordsIn(text), containsInAnyOrder(new WordCount("apan", 1), new WordCount("bepan", 1)));
    }

    @Test
    public void counts_a_duplicate_word() {
        // given
        Collection<String> text = ImmutableList.of("apan", "apan");

        // then
        assertThat(WordCounter.countWordsIn(text), containsInAnyOrder(new WordCount("apan", 2)));
    }

    @Test
    public void counts_two_space_separated_pairs() {
        // given
        Collection<String> text = ImmutableList.of("apan bepan", "bepan apan");

        // then
        assertThat(WordCounter.countWordsIn(text), containsInAnyOrder(new WordCount("apan", 2), new WordCount("bepan", 2)));
    }
}
