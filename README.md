# OneHundredWords
Lists the 100 most common words for a given Twitter hashtag.

## Starting

`$ ./gradlew run`

## Using

Go to 

http://localhost:4567/v1/commonwords?q=%23<hashtag>

e.g.

http://localhost:4567/v1/commonwords?q=%23bolibompa

## Output

A json formatted list of maximum one hundred common words used on Twitter for the given hashtag.

Each word has the following format:

```
{
    "word": "<word>",
    "count": <number of occurences>
}
```

The words are sorted with the most common word first. 

Please note that
* The Twitter search is limited to 100 tweets and the latest month
* Links are excluded from the output
* All words are treated as lowercase
* Hashtags and mentions are stripped of their @/# and counted as words
