package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
    private static final Parser instance = new Parser();
    Document doc;
    private Parser(){
    }

    public static Parser getInstance() {
        return instance;
    }

    public void setBody(String body){
        instance.doc = Jsoup.parse(body);
    }

    public Document getBody(String body){
        return doc;
    }

    public Elements getElements(ParseTags tag){
        return doc.getElementsByTag(tag.descr());
    }

    public int getIdByValueFromElements(Elements elements, String code){
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).text().equals(code)){
                return i;
            }
        }
        return -1;
    }

    public Element getValueById(Element element, ParseTags tag){
        return element.getElementsByTag(tag.descr()).get(0);
    }

    public int getIdByKey(ParseTags tag, String code){

        Elements elements = getElements(tag);
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).text().equals(code)){
                return i;
            }
        }
        return -1;
    }

}
