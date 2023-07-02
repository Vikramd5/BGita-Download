package bgitatest.processors;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bgitatest.model.Shloke;
import bgitatest.model.ShlokePage;

public class PageDetailsFetcher {
    

    public static ShlokePage fetchShlokePage(String pageUrl) throws IOException
    {
        System.out.println("Fetching page: "+pageUrl);
        Connection con = Jsoup.connect(pageUrl);
        Document doc = con.get();
        Element elm = doc.selectFirst("#edit-language");
        String lang=elm.selectFirst("[selected]").text();
        // System.out.println(lang);

        elm = doc.selectFirst("#edit-field-chapter-value");
        String chapterID=elm.selectFirst("[selected]").text();
        // System.out.println(chapterID);

        elm = doc.selectFirst("#edit-field-nsutra-value");
        String shlokeID=elm.selectFirst("[selected]").text();
        // System.out.println(shlokeID);

        elm = doc.selectFirst("#tejAudio>source");
        String audioURL=elm.absUrl("src");
        // System.out.println(audioURL);

        elm = doc.selectFirst("#block-system-main > div > div > div.view-content > div > div.custom_display_even > div > div > p[align='center'] > font");
        String shloke=elm.text();
        // System.out.println(shloke);

        elm = doc.selectFirst("#block-system-main > div > div > div.view-content > div > div.custom_display_odd > div > div > p[align='justify'] > font");
        String traslatedShloke=elm.text();
        // System.out.println(traslatedShloke);

        Shloke s=new Shloke(pageUrl, lang, chapterID, shlokeID, audioURL, shloke, traslatedShloke);

        elm = doc.selectFirst("#edit-navigation > p > a:nth-child(2)");
        String prevPageURL=elm.absUrl("href");
        // System.out.println(prevPageURL);

        elm = doc.selectFirst("#edit-navigation > p > a:nth-child(3)");
        String nextPageURL=elm.absUrl("href");
        // System.out.println(nextPageURL);
        
        ShlokePage page=new ShlokePage(prevPageURL, pageUrl, nextPageURL, s);
        // System.out.println(page);
        return page;
    }

    
}
