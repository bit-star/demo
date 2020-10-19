package com.lazulite.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MpHotspot.
 */
@Entity
@Table(name = "mp_hotspot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "mphotspot")
public class MpHotspot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "path_url")
    private String pathUrl;

    @Column(name = "add_time")
    private Instant addTime;

    @Column(name = "order_num")
    private Long orderNum;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JsonIgnoreProperties(value = "mpHotspots", allowSetters = true)
    private FmpSubCompany fmpSubCompany;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MpHotspot name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MpHotspot imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public MpHotspot pathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
        return this;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public Instant getAddTime() {
        return addTime;
    }

    public MpHotspot addTime(Instant addTime) {
        this.addTime = addTime;
        return this;
    }

    public void setAddTime(Instant addTime) {
        this.addTime = addTime;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public MpHotspot orderNum(Long orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getNote() {
        return note;
    }

    public MpHotspot note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public FmpSubCompany getFmpSubCompany() {
        return fmpSubCompany;
    }

    public MpHotspot fmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompany = fmpSubCompany;
        return this;
    }

    public void setFmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompany = fmpSubCompany;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MpHotspot)) {
            return false;
        }
        return id != null && id.equals(((MpHotspot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MpHotspot{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", pathUrl='" + getPathUrl() + "'" +
            ", addTime='" + getAddTime() + "'" +
            ", orderNum=" + getOrderNum() +
            ", note='" + getNote() + "'" +
            "}";
    }
}
