package edu.mum.waa.service;

import edu.mum.waa.dto.BlockDto;
import edu.mum.waa.entity.Block;
import edu.mum.waa.repository.BlockRepo;
import edu.mum.waa.service.interfaces.BlockService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BlockServiceImpl implements BlockService {

    private BlockRepo blockRepo;

    @Autowired
    public BlockServiceImpl(BlockRepo blockRepo) {
        this.blockRepo = blockRepo;
    }

    @Override
    public boolean save(BlockDto blockDto) {
        Block block = blockDto.convertToEntity(blockDto);
        blockRepo.save(block);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        blockRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean update(BlockDto blockDto) {
        Block block = blockDto.convertToEntity(blockDto);
        blockRepo.save(block);
        return true;
    }

    @Override
    public ArrayList<BlockDto> findAll() {
        var res = blockRepo.findAll();
        ArrayList<BlockDto> result = new ArrayList<>();
        BlockDto temp = new BlockDto();
        for (Block block : res) {
            result.add(temp.convertToDto(block));
        }
        return result;
    }

    @Override
    public BlockDto findById(Long id) {
        var res = blockRepo.findById(id);
        BlockDto temp = new BlockDto();
        return temp.convertToDto(res.get());
    }
}
