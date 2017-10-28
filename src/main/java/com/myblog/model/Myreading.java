package com.myblog.model;

import java.io.Serializable;

/**
 * @author
 */
public class Myreading implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String author;
    private String bookindex;
    private String rentdate;
    private String returndate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookindex() {
        return bookindex;
    }

    public void setBookindex(String bookindex) {
        this.bookindex = bookindex;
    }

    public String getRentdate() {
        return rentdate;
    }

    public void setRentdate(String rentdate) {
        this.rentdate = rentdate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null != obj && obj instanceof Myreading) {
            Myreading myreading = (Myreading) obj;
            if (title.equals(myreading.title)) {
                return true;
            }
        }
        return false;
    }
}