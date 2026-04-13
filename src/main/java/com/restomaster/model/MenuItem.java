package com.restomaster.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;

    @ManyToMany
    @JoinTable(
            name = "item_suggestions",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "suggestion_id")
    )
    private Set<MenuItem> suggestedItems;
}