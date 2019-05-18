package edu.mum.waa.controller;

import edu.mum.waa.dto.EntryDto;
import edu.mum.waa.service.interfaces.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entries")
public class EntryController {

    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public boolean save(@RequestBody EntryDto blockDto) {
        return entryService.save(blockDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return entryService.delete(id);
    }

    @PutMapping
    public boolean update(@RequestBody EntryDto blockDto) {
        return entryService.update(blockDto);
    }

    @GetMapping("/{id}")
    public EntryDto findById(@PathVariable Long id) {
        return entryService.findById(id);
    }

    @GetMapping
    public List<EntryDto> findAll(){
        return entryService.findAll();
    }

}
