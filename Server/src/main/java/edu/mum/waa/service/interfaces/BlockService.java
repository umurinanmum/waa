package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.BlockDto;

import java.time.LocalDate;

public interface BlockService extends BaseService<BlockDto> {

    BlockDto findBlockByStartDateBeforeAndEndDateAfter(LocalDate date);


}
