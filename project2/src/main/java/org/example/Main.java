package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
//        Scanner scanner = new Scanner(System.in);
        String code = args[0];

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(args[1]);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(date);

        String result = getCurrencyInfo(dateString, code);
        System.out.println(result);
    }

    public static String getTreeInfo(String dateString) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format("https://www.cbr.ru/scripts/XML_daily.asp?date_req=%s", dateString))).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String getCurrencyInfo(String dateString, String code) throws IOException, InterruptedException {
        String body = getTreeInfo(dateString);
        Parser parser = Parser.getInstance();
        parser.setBody(body);
        int curPos = parser.getIdByKey(ParseTags.CHARCODE, code);
        Elements elements = parser.getElements(ParseTags.VALUTE);
        Element element = elements.get(curPos);

        String name = parser.getValueById(element, ParseTags.NAME).text();
        String charCode = parser.getValueById(element, ParseTags.CHARCODE).text();
        String value = parser.getValueById(element, ParseTags.VALUE).text();

        return charCode + " (" + name + "): " + value;
    }
}