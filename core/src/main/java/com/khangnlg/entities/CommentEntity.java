package com.khangnlg.entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String comment;
    private int star;
    private long date;

    @ManyToOne(targetEntity = ProductEntity.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    private ProductEntity productId;

}
