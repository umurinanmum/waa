package edu.mum.waa.service;

import edu.mum.waa.dto.BaseDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public abstract class ServiceImplXXXX<TEntitiy , TDto extends BaseDto,TRepo extends CrudRepository> {
/*
    @Autowired
    private TRepo tRepo;




    public boolean save(TDto tDto) {
        TEntitiy tEntitiy =(TEntitiy) tDto.convertToEntity(tDto);
        tRepo.save(tEntitiy);
        return true;
    }

    public boolean delete(Long id) {
        tRepo.deleteById(id);
        return true;
    }

    public boolean update(TDto tDto) {
        TEntitiy tEntitiy = (TEntitiy) tDto.convertToEntity(tDto);
        tRepo.save(tEntitiy);
        return true;
    }

    public ArrayList<TDto> findAll() {
        var res = tRepo.findAll();
        ArrayList<TDto> result = new ArrayList<>();
        TDto temp = new TDto();
        for (TEntitiy tEntitiy : res) {
            result.add((TDto) temp.convertToDto(tEntitiy));
        }
        return result;
    }

    public TDto findById(Long id) {
        var res = tRepo.findById(id);
        TDto temp = new TDto();
        return (TDto) temp.convertToDto(res.get());
    }*/
}
