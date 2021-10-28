/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Cart {
    private Map<String,Books> cart;

    public Cart() {
    }

    public Cart(Map<String, Books> cart) {
        this.cart = cart;
    }

    public Map<String, Books> getCart() {
        return cart;
    }

    public void setCart(Map<String, Books> cart) {
        this.cart = cart;
    }
    
    public void add(Books book){
        if(this.cart==null){
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(book.getBookId())){
            int currentQuantity = this.cart.get(book.getBookId()).getQuantity();
            book.setQuantity(currentQuantity+book.getQuantity());
        }
        cart.put(book.getBookId(), book);
    }
    
    
    public void delete(String id){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    
    public void update (String id , Books newBook){
        if(this.cart==null){
            return;
        }else{
            this.cart.replace(id, newBook);
        }
    }
}
