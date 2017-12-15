package com.potatosaucevfx.SpringBootSandbox.model;

/**
 *
 * @author PotatoSauceVFX
 */
public class Book {

    private int bookID;
    private String bookNumber;
    private String name;
    private String author;
    private Integer publishYear;
    private String publisher;
    private String imagePath;
    private String userAdded;

    public Book() {

    }

    public Book(String bookNumber, String name, String author, int publishYear, String publisher, String imagePath, User user) {
        this.bookNumber = bookNumber;
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.imagePath = imagePath;
        this.userAdded = user.getUsername();
    }

    public String getUserAdded() {
        return userAdded;
    }

    public void setUserAdded(String userAdded) {
        this.userAdded = userAdded;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID + ", bookNumber=" + bookNumber + ", name=" + name + ", author=" + author + ", publishYear=" + publishYear + ", publisher=" + publisher + '}';
    }

}
