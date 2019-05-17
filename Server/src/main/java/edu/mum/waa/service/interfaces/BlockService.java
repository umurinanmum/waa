package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.BlockDto;

import java.time.LocalDate;
import java.util.List;

public interface BlockService extends BaseService<BlockDto> {

    BlockDto findBlockByStartDateBeforeAndEndDateAfter(LocalDate date);

    BlockDto findByName(String name);

    //List<String> blocks



}
