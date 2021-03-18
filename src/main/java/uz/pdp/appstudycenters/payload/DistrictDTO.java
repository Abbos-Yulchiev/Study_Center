package uz.pdp.appstudycenters.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private String name;
    private String code;
    private Integer regionId;
}
