package com.sentiment.app;

    import org.jfree.chart.ChartFactory;
    import org.jfree.chart.ChartUtils;
    import org.jfree.chart.JFreeChart;
    import org.jfree.data.general.DefaultPieDataset;
    import org.jfree.data.category.DefaultCategoryDataset;
    import org.jfree.chart.plot.CategoryPlot;
    import org.jfree.chart.renderer.category.BarRenderer;
    import org.jfree.chart.renderer.category.StandardBarPainter;
    import java.awt.Color;
    import org.jfree.chart.plot.PiePlot;
    import java.awt.Font;


    import java.io.File;
    import java.io.IOException;

    public class SentimentChartGenerator {

        public static void createPieChart(int positiveCount, int negativeCount, int neutralCount, String outputPath) {
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Positive", positiveCount);
            dataset.setValue("Negative", negativeCount);
            dataset.setValue("Neutral", neutralCount);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Sentiment Distribution",
                    dataset,
                    true,  // include legend
                    true,
                    false
            );

            // שדרוג עיצוב
            chart.setBackgroundPaint(Color.WHITE);
            chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));

// שינוי צבעים לפרוסות
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionPaint("Positive", new Color(102, 204, 0)); // ירוק
            plot.setSectionPaint("Negative", new Color(255, 51, 51)); // אדום
            plot.setSectionPaint("Neutral", Color.GRAY);              // אפור
            plot.setBackgroundPaint(Color.WHITE);
            plot.setOutlineVisible(false);

            try {
                File outFile = new File(outputPath);
                ChartUtils.saveChartAsPNG(outFile, chart, 800, 600);
                System.out.println(" Pie chart saved to: " + outFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println(" Failed to save pie chart: " + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void createBarChart(int positiveCount, int negativeCount, int neutralCount, String outputPath) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(positiveCount, "Sentiment", "Positive");
            dataset.addValue(negativeCount, "Sentiment", "Negative");
            dataset.addValue(neutralCount, "Sentiment", "Neutral");

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Sentiment Distribution",
                    "Sentiment",
                    "Count",
                    dataset
            );

            CategoryPlot plot = (CategoryPlot) barChart.getPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(76, 135, 185));
            renderer.setBarPainter(new StandardBarPainter());

            try {
                File outFile = new File(outputPath);
                ChartUtils.saveChartAsPNG(outFile, barChart, 800, 600);
                System.out.println(" Bar chart saved to: " + outFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println(" Failed to save bar chart: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }