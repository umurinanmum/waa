package edu.mum.waa.service;

import edu.mum.waa.dto.LocationDto;
import edu.mum.waa.entity.Location;
import edu.mum.waa.entity.Student;
import edu.mum.waa.repository.LocationRepo;
import edu.mum.waa.repository.StudentRepo;
import edu.mum.waa.service.interfaces.LocationService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepo locationRepo;

    @Autowired
    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public LocationDto findByLocationCode(String locationName) {
        LocationDto locationDto = new LocationDto();
        var location = locationRepo.findByLocationCode(locationName);
        return locationDto.convertToDto(location.get());
    }

    @Override
    public boolean save(LocationDto locationDto) {
        Location location = locationDto.convertToEntity(locationDto);
        locationRepo.save(location);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        locationRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean update(LocationDto locationDto) {
        Location location = locationDto.convertToEntity(locationDto);
        locationRepo.save(location);
        return true;
    }

    @Override
    public ArrayList<LocationDto> findAll() {
        var res = locationRepo.findAll();
        ArrayList<LocationDto> result = new ArrayList<>();
        LocationDto temp = new LocationDto();
        for (Location location : res) {
            result.add(temp.convertToDto(location));
        }
        return result;
    }

    @Override
    public LocationDto findById(Long id) {
        var res = locationRepo.findById(id);
        LocationDto temp = new LocationDto();
        return temp.convertToDto(res.get());
    }



}
