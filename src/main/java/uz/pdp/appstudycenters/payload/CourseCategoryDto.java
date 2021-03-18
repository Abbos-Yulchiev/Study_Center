package uz.pdp.appstudycenters.payload;

import lombok.Data;

@Data
public class CourseCategoryDto {
    private String name;

    private String description;

    private Integer courseCategoryId;

    private Integer courseId;
}
