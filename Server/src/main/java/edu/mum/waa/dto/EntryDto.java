package edu.mum.waa.dto;

import edu.mum.waa.entity.Entry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EntryDto extends BaseDto<EntryDto, Entry> {

    public EntryDto(){
        super.dtoClass =EntryDto.class;
        super.entityClass=Entry.class;
    }

    private long id;
    private String name;
}
