package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "t_discount")

public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_percent", nullable = false)
    private BigDecimal discountPercent;

    @Column(name = "active", nullable = false)
    private boolean active;

}

