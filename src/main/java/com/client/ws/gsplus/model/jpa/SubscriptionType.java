package com.client.ws.gsplus.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "subscriptions_type")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptions_type_id")
    private Long id;

    @Column(name = "access_months")
    private Integer accessMonths;
    private String name;
    private BigDecimal price;
    @Column(name = "product_key")
    private String productKey;
}
