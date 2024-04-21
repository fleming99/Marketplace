package com.fleming99.MarketplaceOnline.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "entity_address")
public class EntityAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_address_id")
    private int entityAddressId;

    @Column(name = "entity_address")
    private String entityAddress;

    @Column(name = "entity_address_number")
    private String entityAddressNumber;

    @Column(name = "entity_address_neighborhood")
    private String entityAddressNeighborhood;

    @Column(name = "entity_address_city")
    private String entityAddressCity;

    @Column(name = "entity_address_state")
    private String entityAddressState;

    @Column(name = "entity_address_country")
    private String entityAddressCountry;

    public EntityAddress() {
    }

    public EntityAddress(int entityAddressId, String entityAddress, String entityAddressNumber, String entityAddressNeighborhood, String entityAddressCity, String entityAddressState, String entityAddressCountry) {
        this.entityAddressId = entityAddressId;
        this.entityAddress = entityAddress;
        this.entityAddressNumber = entityAddressNumber;
        this.entityAddressNeighborhood = entityAddressNeighborhood;
        this.entityAddressCity = entityAddressCity;
        this.entityAddressState = entityAddressState;
        this.entityAddressCountry = entityAddressCountry;
    }
}
