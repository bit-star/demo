package com.lazulite.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FmpSubCompany.
 */
@Entity
@Table(name = "fmp_sub_company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "fmpsubcompany")
public class FmpSubCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "more_todo_url")
    private String moreTodoUrl;

    @OneToMany(mappedBy = "fmpSubCompany")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MpHotspot> mpHotspots = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoreTodoUrl() {
        return moreTodoUrl;
    }

    public FmpSubCompany moreTodoUrl(String moreTodoUrl) {
        this.moreTodoUrl = moreTodoUrl;
        return this;
    }

    public void setMoreTodoUrl(String moreTodoUrl) {
        this.moreTodoUrl = moreTodoUrl;
    }

    public Set<MpHotspot> getMpHotspots() {
        return mpHotspots;
    }

    public FmpSubCompany mpHotspots(Set<MpHotspot> mpHotspots) {
        this.mpHotspots = mpHotspots;
        return this;
    }

    public FmpSubCompany addMpHotspot(MpHotspot mpHotspot) {
        this.mpHotspots.add(mpHotspot);
        mpHotspot.setFmpSubCompany(this);
        return this;
    }

    public FmpSubCompany removeMpHotspot(MpHotspot mpHotspot) {
        this.mpHotspots.remove(mpHotspot);
        mpHotspot.setFmpSubCompany(null);
        return this;
    }

    public void setMpHotspots(Set<MpHotspot> mpHotspots) {
        this.mpHotspots = mpHotspots;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FmpSubCompany)) {
            return false;
        }
        return id != null && id.equals(((FmpSubCompany) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FmpSubCompany{" +
            "id=" + getId() +
            ", moreTodoUrl='" + getMoreTodoUrl() + "'" +
            "}";
    }
}
