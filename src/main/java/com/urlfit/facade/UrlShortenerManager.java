package com.urlfit.facade;

import com.google.common.hash.Hashing;
import com.urlfit.model.Link;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class UrlShortenerManager {
    private static ConcurrentHashMap<String, Link> repo = new ConcurrentHashMap<>();


    /**
     * Responsible for simplify and store the url
     * @param link
     * @return
     */
    public static String simplifyUrl(Link link){
        if(existsShortedValue(link)) {
            return repo.get(link.getFullUrl()).getFullUrl();
        }
        return manageUrls(link);
    }

    private static boolean existsShortedValue(Link link) {
        Link auxLink = repo.get(link.getFullUrl());
        if(auxLink != null) {
            int count = auxLink.getAccess();
            auxLink.setAccess(++count);
            return true;
        } else {
            return false;
        }
    }

    public static String manageUrls(Link link){

        String shortUrl = Hashing.murmur3_32().hashString(link.getFullUrl(), StandardCharsets.UTF_8).toString();
        Link auxLink = repo.get(shortUrl);
        if( auxLink == null ){
            link.setUrl(shortUrl);
            repo.put(link.getUrl(), link);
            return link.getUrl();

        } else {
            int numberAccess = auxLink.getAccess();
            auxLink.setAccess(++numberAccess);
            return auxLink.getUrl();
        }
    }

    /**
     * Return all Links wthi their informations abount the use of this service
     * @return
     */
    public static Collection<Link> getInfo() {
        return repo.values();
    }
}
