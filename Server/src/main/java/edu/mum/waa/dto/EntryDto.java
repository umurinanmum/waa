package edu.mum.waa.dto;

import edu.mum.waa.entity.Entry;

public class EntryDto extends BaseDto<EntryDto, Entry> {

    public EntryDto(){
        dtoClass =EntryDto.class;
        entityClass=Entry.class;
    }

    private long id;
    private String name;
}
