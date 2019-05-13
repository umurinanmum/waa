package edu.mum.waa.controller;

import edu.mum.waa.dto.BlockDto;
import edu.mum.waa.service.interfaces.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/blocks")
public class BlockController {

    private BlockService blockService;

    @Autowired
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @PostMapping
    public boolean save(@RequestBody BlockDto blockDto) {
        return blockService.save(blockDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return blockService.delete(id);
    }

    @PutMapping
    public boolean update(@RequestBody BlockDto blockDto) {
        return blockService.update(blockDto);
    }

    @GetMapping("/{id}")
    public BlockDto findById(@PathVariable Long id) {
        return blockService.findById(id);
    }

    @GetMapping
    public List<BlockDto> findAll(){
        return blockService.findAll();
    }

}
