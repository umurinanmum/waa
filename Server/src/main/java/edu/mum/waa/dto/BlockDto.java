package edu.mum.waa.dto;


import edu.mum.waa.entity.Block;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
public class BlockDto extends BaseDto<BlockDto, Block>{

    public BlockDto(){
        super.dtoClass =BlockDto.class;
        super.entityClass=Block.class;
    }

    private long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int canceledDays=0;


}
