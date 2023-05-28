package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(targetEntity = ProductEntity.class)
    private long productId;

}
