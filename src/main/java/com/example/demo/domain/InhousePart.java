package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 *
 *
 *
 *
 */
@Entity
@DiscriminatorValue("1")
@Table(name = "inhouse_parts")
public class InhousePart extends Part{
    @Min(value = 0, message = "Inventory cannot be less than 0")
    private Integer inv;

    @Min(value = 1, message = "Minimum inventory must be at least 1")
    private Integer minInv;

    @Min(value = 1, message = "Maximum inventory must be greater than minimum inventory")
    private Integer maxInv;

    private Integer partId;

    public Integer getInv() {
        return inv;
    }

    public void setInv(Integer inv) {
        this.inv = inv;
    }

    public Integer getMinInv() {
        return minInv;
    }

    public void setMinInv(Integer minInv) {
        this.minInv = minInv;
    }

    public Integer getMaxInv() {
        return maxInv;
    }

    public void setMaxInv(Integer maxInv) {
        this.maxInv = maxInv;
    }

    public InhousePart() {
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }
}
