package com.tesda8.region8.certification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredCertificateDetails {

    private String trainerName;
    private Long id;
    private Boolean expiredNC = false;
    private Boolean expiredNTTC = false;
    private Boolean expiredTMC = false;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExpiredCertificateDetails other = (ExpiredCertificateDetails) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
