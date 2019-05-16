package edu.mum.waa.dto;

import edu.mum.waa.entity.Faculty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FacultyDto extends BaseDto<FacultyDto, Faculty> {

    public FacultyDto(){
        super.dtoClass =FacultyDto.class;
        super.entityClass=Faculty.class;
    }


    private long id;

    private String firstName;

    private String lastName;
}
