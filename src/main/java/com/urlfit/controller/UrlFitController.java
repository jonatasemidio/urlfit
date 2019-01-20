package com.urlfit.controller;

import com.urlfit.facade.UrlShortenerManager;
import com.urlfit.model.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UrlFitController {

    @RequestMapping("/")
    public String index() {
        return "UrlFit is a rest service for simplifying your hard to memorise url in something easy to catch. \n Just add /<your url> and you will get your simple link";
    }

    @RequestMapping("/{url}")
    public String shorten(@PathVariable("url") String url) {
        return UrlShortenerManager.simplifyUrl(new Link(url));
    }

    @RequestMapping("/info")
    public Collection<Link> info(){
        return UrlShortenerManager.getInfo();
    }
}
