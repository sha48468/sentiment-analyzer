#  Sentiment Analyzer

A Java-based NLP (Natural Language Processing) project that analyzes product review sentiments using **Stanford CoreNLP**, and visualizes the results with **JFreeChart** (Pie & Bar charts).

---

##  Features

-  Analyzes textual reviews (positive / negative / neutral)
-  Uses Stanford CoreNLP for sentiment analysis
-  Generates visual Pie & Bar charts of the results
-  Outputs results into a `.csv` file
-  Built with Java + Maven + IntelliJ IDEA

---

##  Sample Output

###  Pie Chart
![Pie Chart](sentiment_2025-03-24_18-36.png)

###  Bar Chart
![Bar Chart](sentiment_bar_2025-03-24_18-36.png)

> Charts are auto-saved to the `charts/` directory upon execution.

---

##  Project Structure

```
sentiment-analyzer/
├── charts/               # PNG charts (auto-generated)
├── src/                  # Java source files
│   └── main/
│       └── java/com/sentiment/app/
│           ├── SentimentAnalyzer.java
│           ├── SentimentChartGenerator.java
│           └── App.java
├── texts/
│   └── reviews.txt       # Input: one review per line
├── results.csv           # Output: sentiment results
├── pom.xml               # Maven config
└── README.md
```

---

##  How to Run

1. Add reviews to `texts/reviews.txt` (each line = one review)
2. Run the `SentimentAnalyzer.java` file
3. Output:
   -  `results.csv` with the sentiment for each review
   -  PNG charts saved under `charts/`

---

##  Dependencies

- [Stanford CoreNLP](https://stanfordnlp.github.io/CoreNLP/)
- [JFreeChart](http://www.jfree.org/jfreechart/)
- Java 11+
- Maven

---

##  What I Learned

- Working with NLP libraries (Stanford CoreNLP)
- Building Java projects with Maven
- Generating dynamic visualizations in Java
- Using Git & GitHub for version control

---

##  Contact

Feel free to reach out if you have questions or feedback!  
Shahar Moskovics  
shaharmos1@gmail.com  
http://www.linkedin.com/in/shahar-moskovics
