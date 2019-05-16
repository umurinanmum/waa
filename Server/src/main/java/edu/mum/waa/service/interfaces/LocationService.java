package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.LocationDto;

public interface LocationService extends BaseService<LocationDto> {


    LocationDto findByLocationCode(String locationName);


}
