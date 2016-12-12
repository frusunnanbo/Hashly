package se.frusunnanbo.onehundredwords;


import com.google.gson.Gson;
import spark.Request;
import spark.Spark;

/**
 * Created by piolin on 12/12/16.
 */
public class OneHundredWords
{
    public static void main(String argv[])
    {
        final Gson gson = new Gson();
        final SearchService searchService = new SearchService();
        Spark.get("/v1/commonwords", (req, res) -> searchService.countWordsForQuery(getQuery(req)), gson::toJson);
    }

    private static String getQuery(Request request)
    {
        return request.queryParams("q");
    }
}
