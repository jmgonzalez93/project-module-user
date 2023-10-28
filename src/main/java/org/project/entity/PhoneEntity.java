package org.project.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHONE")
public class PhoneEntity {

    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "city_code")
    private String citycode;

    @Column(name = "country_code")
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDataEntity user;

}
