package br.com.cesarMontaldi.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SocialMetaTag implements Serializable {

    private String site;
    private String title;
    private String url;
    private String image;
    private String descricao;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "SocialMetaTag{" + "\n" +
                "site= " + site + "\n"+
                "title= " + title + "\n" +
                "url= " + url + "\n"+
                "image= " + image + "\n" +
                "descricao= " + descricao + "\n" +
                '}';
    }
}
