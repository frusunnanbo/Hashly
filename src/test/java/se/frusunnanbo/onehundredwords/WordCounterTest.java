package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by piolin on 12/12/16.
 */
public class WordCounterTest
{
    @Test
    public void returns_one_object_per_unique_word() {
        // given
        Collection<String> text = ImmutableList.of("apan", "bepan", "apan");

        // then
        assertThat(WordCounter.countWordsIn(text), hasSize(2));
    }

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
        assertThat(WordCounter.countWordsIn(text), containsInAnyOrder(new WordCount("apan")));
    }

    @Test
    public void handles_text_with_more_than_one_word() {
        // given
        Collection<String> text = ImmutableList.of("apan", "apan bepan", "apan");

        // then
        List<WordCount> result = WordCounter.countWordsIn(text);
        assertThat(result, containsInAnyOrder(new WordCount("apan"), new WordCount("bepan")));
    }
}
