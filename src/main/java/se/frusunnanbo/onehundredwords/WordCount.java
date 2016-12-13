package se.frusunnanbo.onehundredwords;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Created by piolin on 12/12/16.
 */
public class WordCount
{
    private final String word;

    public WordCount(String word)
    {
        this.word = word;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WordCount) {
            WordCount that = (WordCount) obj;
            return Objects.equals(this.word, that.word);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(word);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("word", word).toString();
    }
}
