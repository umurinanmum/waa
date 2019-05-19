package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EntryModel {

    private long id;
    private String name;
}
