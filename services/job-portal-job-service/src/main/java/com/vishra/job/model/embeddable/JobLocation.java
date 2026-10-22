package com.vishra.job.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JobLocation {
    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

}
