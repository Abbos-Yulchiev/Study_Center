package uz.pdp.appstudycenters.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private String phoneNumber;
    private String email;
    private String webPage;
    private String telegramAddress;
}
