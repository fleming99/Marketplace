package com.fleming99.MarketplaceOnline.core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_details")
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Customer buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Company seller;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "cart_amount")
    private double cartAmount;
}
