package uz.pdp.appstudycenters.payload;

import lombok.Data;
import uz.pdp.appstudycenters.entity.Company;

import javax.persistence.ManyToOne;

@Data
public class CourseDto {

    private String name;

    private String description;

    private Integer companyId;
}
