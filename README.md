# OneHundredWords
Lists the 100 most common words for a given Twitter hashtag.

## Running

You need to provide Twitter authentication details in order to use their search api. To do so: add a file called `twitter4j.properties` to the project root, with the following contents:

`oauth.consumerKey=<your consumer key>`

`oauth.consumerSecret=<your consumer secret>`

`oauth.accessToken=<your access token>`

`oauth.accessTokenSecret=<your access token secret>`

Then run the application like so

`$ ./gradlew run`

## Using

Go to `http://localhost:4567/v1/commonwords?q=%23YourHashtagHere`

e.g.

[http://localhost:4567/v1/commonwords?q=%23bolibompa](http://localhost:4567/v1/commonwords?q=%23bolibompa)

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
