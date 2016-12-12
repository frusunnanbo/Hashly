package se.frusunnanbo.onehundredwords;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
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
    public void handles_text_with_more_than_one_word() {
        // given
        Collection<String> text = ImmutableList.of("apan", "bepan cepan", "apan");

        // then
        assertThat(WordCounter.countWordsIn(text), hasSize(3));
    }
}
