package com.lab.board.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lab.board.config.CustomInstantSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "tbl_board")
public class Board implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @NotNull
    @Column(name = "writer", nullable = false, length = 60)
    private String writer;

    @NotNull
    @Lob
    @Column(name = "contents", nullable = false)
    private String contents;

    @NotNull
    @Column(name = "created_by", nullable = false, length = 60)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    @JsonSerialize(using = CustomInstantSerializer.class)
    @CreatedDate
    private Instant createdDate;

    @NotNull
    @Column(name = "last_modified_by", nullable = false, length = 60)
    private String lastModifiedBy;

    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    @JsonSerialize(using = CustomInstantSerializer.class)
    @LastModifiedDate
    private Instant lastModifiedDate;
}
