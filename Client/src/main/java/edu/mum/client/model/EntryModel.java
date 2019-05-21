package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@Getter
@Setter
public class EntryModel {

    private long id;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.name}")
    private String name;
}
