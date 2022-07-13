package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", updatable = false, nullable = false)
    private Integer rating_id;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "product_id", nullable = false)
    private Product product_id;

    @Column
    private String rating_text;

    @Column
    private Double rating_stars; // int, 0 <= rating <= 5, maybe?

    // one product, many ratings
}
