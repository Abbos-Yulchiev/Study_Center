package uz.pdp.appstudycenters.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String  streetName;
    private Integer districtId;
    private String  code;
}
