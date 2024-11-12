package com.lab.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CommonCodeKey implements Serializable {

    @Serial
    private static final long serialVersionUID = -8613432757337775933L;

    @Size(max = 120)
    @NotNull
    @Column(name = "common_code", nullable = false, length = 120)
    private String commonCode;

    @Size(max = 120)
    @NotNull
    @Column(name = "common_group_code", nullable = false, length = 120)
    private String commonGroupCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommonCodeKey entity = (CommonCodeKey) o;
        return Objects.equals(this.commonCode, entity.commonCode) &&
                Objects.equals(this.commonGroupCode, entity.commonGroupCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commonCode, commonGroupCode);
    }

}