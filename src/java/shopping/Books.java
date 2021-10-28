/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

/**
 *
 * @author ASUS
 */
public class Books {

    private String bookId;
    private String bookName;
    private int quantity;
    private float price;
    private String description;
    private String image;

    public Books() {
        this.bookId = "";
        this.bookName = "";
        this.quantity = 0;
        this.price = 0;
        this.description = "";
        this.image = "";

    }

    public Books(String bookId, String bookName, int quantity, float price, String description, String image) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;

    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
