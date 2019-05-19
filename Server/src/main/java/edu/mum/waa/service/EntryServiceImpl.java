package edu.mum.waa.service;

import edu.mum.waa.dto.EntryDto;
import edu.mum.waa.entity.Entry;
import edu.mum.waa.repository.EntryRepo;
import edu.mum.waa.service.interfaces.EntryService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class EntryServiceImpl implements EntryService {

    private EntryRepo entryRepo;

    @Autowired
    public EntryServiceImpl(EntryRepo entryRepo) {
        this.entryRepo = entryRepo;
    }


    @Override
    public boolean save(EntryDto entryDto) {
        Entry entry = entryDto.convertToEntity(entryDto);
        entryRepo.save(entry);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        entryRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean update(EntryDto entryDto) {
        Entry entry = entryDto.convertToEntity(entryDto);
        entryRepo.save(entry);
        return true;
    }

    @Override
    public ArrayList<EntryDto> findAll() {
        var res = entryRepo.findAll();
        ArrayList<EntryDto> result = new ArrayList<>();
        EntryDto temp = new EntryDto();
        for (Entry entry : res) {
            result.add(temp.convertToDto(entry));
        }
        return result;
    }

    @Override
    public EntryDto findById(Long id) {
        var res = entryRepo.findById(id);
        EntryDto temp = new EntryDto();
        return temp.convertToDto(res.get());
    }
}