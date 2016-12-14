package se.frusunnanbo.onehundredwords;

import com.google.common.base.MoreObjects;

import java.util.Objects;


public class WordCount implements Comparable<WordCount>
{
    private final String word;
    private final long count;

    public WordCount(String word, long count)
    {
        this.word = word;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WordCount) {
            WordCount that = (WordCount) obj;
            return Objects.equals(this.word, that.word) && Objects.equals(this.count, that.count);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(word, count);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("word", word)
                .add("count", count)
                .toString();
    }

    @Override
    public int compareTo(WordCount other)
    {
        return Long.valueOf(other.count).compareTo(Long.valueOf(this.count));
    }
}
