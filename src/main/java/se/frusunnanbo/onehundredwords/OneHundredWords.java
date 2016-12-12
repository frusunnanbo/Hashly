package se.frusunnanbo.onehundredwords;


import spark.Spark;

/**
 * Created by piolin on 12/12/16.
 */
public class OneHundredWords
{
    public static void main(String argv[])
    {
        Spark.get("/v1/commonwords", (req, res) -> "Hej!");
    }
}
