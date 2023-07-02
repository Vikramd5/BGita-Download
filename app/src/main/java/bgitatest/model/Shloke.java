package bgitatest.model;

import lombok.Data;
import lombok.ToString;

@Data
public class Shloke {
    String url;
    String lang,chapterID,shlokeID;
    String audioURL,shlokeText,traslatedShloke;
    int index;

    public Shloke(String url, String lang, String chapterID, String shlokeID, String audioURL, String shlokeText,
            String traslatedShloke) {
        this.url = url;
        this.lang = lang;
        this.chapterID = chapterID;
        this.shlokeID = shlokeID;
        this.audioURL = audioURL;
        this.shlokeText = shlokeText;
        this.traslatedShloke = traslatedShloke;
    }
}
