package bgitatest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShlokePage {
    String prevPageURL,pageURL,nextPageURL;
    Shloke shloke;
}
