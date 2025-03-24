package com.sentiment.app;

import com.sentiment.app.SentimentChartGenerator;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.io.*;
import java.util.*;


public class SentimentAnalyzer {
    public static void main(String[] args) throws IOException {
        // קריאה של קובץ הביקורות
        File file = new File("texts/reviews.txt");
        List<String> reviews = new ArrayList<>();

        int positiveCount = 0;
        int negativeCount = 0;
        int neutralCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reviews.add(line);
            }
        }

        // יצירת pipeline של CoreNLP
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // פתיחת כותב לקובץ CSV
        PrintWriter writer = new PrintWriter(new FileWriter("results.csv"));
        writer.println("Review,Sentiment"); // שורת כותרת

        // עיבוד וניתוח סנטימנט לכל ביקורת
        for (String review : reviews) {
            Annotation annotation = new Annotation(review);
            pipeline.annotate(annotation);

            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

                switch (sentiment.toLowerCase()) {
                    case "positive":
                        positiveCount++;
                        break;
                    case "negative":
                        negativeCount++;
                        break;
                    default:
                        neutralCount++;
                }


                System.out.println("Review: " + sentence);
                System.out.println("Sentiment: " + sentiment);
                System.out.println("----------");

                // כתיבה לקובץ CSV
                writer.println("\"" + review.replace("\"", "\"\"") + "\"," + "\"" + sentiment + "\"");
            }
        }

        writer.close();
        Files.createDirectories(Paths.get("charts"));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        String timestamp = now.format(formatter);
        String pieFilename = "charts/sentiment_" + timestamp + ".png";
        String barChartFilename = "charts/sentiment_bar_" + timestamp + ".png";

        SentimentChartGenerator.createPieChart(
                positiveCount,
                negativeCount,
                neutralCount,
                pieFilename
        );

        SentimentChartGenerator.createBarChart(
                positiveCount,
                negativeCount,
                neutralCount,
                barChartFilename
        );

        System.out.println("Pie chart saved as: " + pieFilename);
        System.out.println("Bar chart saved as: " + barChartFilename);
        System.out.println("Results exported to results.csv");
    }
}
