package uz.pdp.appstudycenters.payload;

import lombok.Data;
import uz.pdp.appstudycenters.entity.User;

import java.sql.Date;
import java.util.List;

@Data
public class ActiveCourseDTO {

    private Integer maxStudent;
    private Integer courseId;
    private Date date;
    private List<User> user;
}
