package com.example.monic.cvgenerator.Classes;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Used to parse the html source of an url.
 */
public class CVTipsWorker extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        if (params == null || params.length == 0 || params[0].equals("")) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream inputStream = http.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String htmlString = stringBuilder.toString();
            return parseHtml(htmlString);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseHtml(String htmlString) {
        StringBuilder tips = new StringBuilder();
        Document document = Jsoup.parse(htmlString);

        Elements contents = document.getElementsByClass("content__article-body");
        Element content=contents.first();
        Elements paragraphs = content.getElementsByTag("p");
        for(int i=0;i<paragraphs.size()-3;i++)
            tips.append(paragraphs.get(i).text()+"\n\n");
        
        return tips.toString();
    }
}
