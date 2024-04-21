package com.fleming99.MarketplaceOnline.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartDetails cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "item_quantity")
    private int itemQuantity;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "subtotal")
    private double subtotal;

    public CartItem() {
    }

    public CartItem(int cartItemId, CartDetails cart, Product product, int itemQuantity, double unitPrice, double subtotal) {
        this.cartItemId = cartItemId;
        this.cart = cart;
        this.product = product;
        this.itemQuantity = itemQuantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }
}
