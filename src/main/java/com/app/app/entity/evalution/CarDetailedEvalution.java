package com.app.app.entity.evalution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "car_detailed_evalution")
public class CarDetailedEvalution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kms", nullable = false)
    private Long kms;

    @Column(name = "year_of_manufacturing", nullable = false)
    private Long yearOfManufacturing;

    @OneToMany(mappedBy = "carDetailedEvalution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEvalutionPhotos> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKms() {
        return kms;
    }

    public void setKms(Long kms) {
        this.kms = kms;
    }

    public Long getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(Long yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public List<CarEvalutionPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<CarEvalutionPhotos> photos) {
        this.photos = photos;
    }
}
