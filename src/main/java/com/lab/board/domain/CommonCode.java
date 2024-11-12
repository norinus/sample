package com.lab.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_common_code")
public class CommonCode {

    @EmbeddedId
    private CommonCodeKey id;

    @Size(max = 50)
    @NotNull
    @Column(name = "common_code_name", nullable = false, length = 50)
    private String commonCodeName;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = true;

    @Size(max = 120)
    @NotNull
    @Column(name = "created_by", nullable = false, length = 120)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Size(max = 120)
    @NotNull
    @Column(name = "last_modified_by", nullable = false, length = 120)
    private String lastModifiedBy;

    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

}