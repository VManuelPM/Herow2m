package com.w2m.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_HEROES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    private int id;

    @Column(name = "heroe_name")
    @NotNull
    @NotBlank
    private String heroeName;

    public HeroeEntity(String heroeName) {
        this.heroeName = heroeName;
    }
}
