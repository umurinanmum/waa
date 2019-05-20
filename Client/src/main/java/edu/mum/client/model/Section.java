package edu.mum.client.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {


    private long id;

    private BlockModel block;

    private Course course;

    private Faculty faculty;


}
