package br.com.cesarMontaldi.service;

import br.com.cesarMontaldi.domain.SocialMetaTag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SocialMetaTagService {

    private static Logger log = LoggerFactory.getLogger(SocialMetaTagService.class);

    public SocialMetaTag getSocialMetaTagByUrl(String url) {

        SocialMetaTag openGraph = getOpenGraphByUrl(url);
        if (!isEmpty(openGraph)) {
            return openGraph;
        }

        SocialMetaTag twitter = getTwitterCardByUrl(url);
        if (!isEmpty(twitter)) {
            return twitter;
        }
        return null;
    }

    private SocialMetaTag getOpenGraphByUrl(String url) {
        SocialMetaTag tag = new SocialMetaTag();

        try {
            Document doc = Jsoup.connect(url).get();
            tag.setTitle(doc.head().select("meta[property=og:title]").attr("content"));
            tag.setSite(doc.head().select("meta[property=og:site_name]").attr("content"));
            if (tag.getSite().equals("Magazine Luiza")) {
                tag.setImage(doc.head().select("link[as=image]").attr("href"));
            } else {
                tag.setImage(doc.head().select("meta[property=og:image]").attr("content"));
            }
            if (tag.getSite().equals("casasbahia.com")) {
                tag.setUrl(doc.head().select("link[rel]").attr("href"));
            } else {
                tag.setUrl(doc.head().select("meta[property=og:url]").attr("content"));
            }
            tag.setDescricao(doc.head().select("meta[property=og:description]").attr("content"));
        } catch (IOException e) {
            log.error(e.getMessage(), e.getCause());
        }
        return tag;
    }

    private SocialMetaTag getTwitterCardByUrl(String url) {
        SocialMetaTag tag = new SocialMetaTag();

        try {
            Document doc = Jsoup.connect(url).get();
            tag.setTitle(doc.head().select("meta[name=twiter:title]").attr("content"));
            tag.setSite(doc.head().select("meta[name=twiter:site]").attr("content"));
            tag.setImage(doc.head().select("meta[name=twiter:image]").attr("content"));
            tag.setUrl(doc.head().select("meta[name=twiter:url]").attr("content"));

        } catch (IOException e) {
            log.error(e.getMessage(), e.getCause());
        }
        return tag;
    }

    private boolean isEmpty(SocialMetaTag tag) {
        if (tag.getSite().isEmpty()) return true;
        if (tag.getImage().isEmpty()) return true;
        if (tag.getTitle().isEmpty()) return true;
        if (tag.getUrl().isEmpty()) return true;
        if (tag.getDescricao().isEmpty()) return true;
        return false;
    }

}
