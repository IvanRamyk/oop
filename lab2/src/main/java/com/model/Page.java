package com.model;

import java.util.Objects;

public class Page
{
    private int id;
    private String title;
    private PageType pageType;
    private boolean requiredAuth;
    private PageChars chars;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public PageType getPageType() {
        return pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public boolean isRequiredAuth() {
        return requiredAuth;
    }

    public void setRequiredAuth(boolean requiredAuth) {
        this.requiredAuth = requiredAuth;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pageType=" + pageType +
                ", requiredAuth=" + requiredAuth +
                ", chars=" + chars +
                '}';
    }

    public PageChars getChars() {
        return chars;
    }

    public void setChars(PageChars chars) {
        this.chars = chars;
    }

    public int getId() {
        return id;
    }

    public Page(int id, String name, PageType pageType, boolean requiredAuth, PageChars chars) {
        this.id = id;
        this.title = name;
        this.pageType = pageType;
        this.requiredAuth = requiredAuth;
        this.chars = chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return id == page.id &&
                requiredAuth == page.requiredAuth &&
                Objects.equals(title, page.title) &&
                pageType == page.pageType &&
                Objects.equals(chars, page.chars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, pageType, requiredAuth, chars);
    }
}
