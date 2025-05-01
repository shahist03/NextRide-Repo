package com.app.app.entity.evalution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car_evalution_photos")
public class CarEvalutionPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "car_detailed_evalution_id", nullable = false)
    private CarDetailedEvalution carDetailedEvalution;

    // ✅ Correct Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public CarDetailedEvalution getCarDetailedEvalution() {
        return carDetailedEvalution;
    }

    public void setCarDetailedEvalution(CarDetailedEvalution carDetailedEvalution) {
        this.carDetailedEvalution = carDetailedEvalution;
    }
}
