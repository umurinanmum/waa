package edu.mum.waa.service;

import edu.mum.waa.dto.BlockDto;
import edu.mum.waa.repository.BlockRepo;
import edu.mum.waa.service.interfaces.BlockService;
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
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(BlockDto blockDto) {
        return false;
    }

    @Override
    public ArrayList<BlockDto> findAll() {
        return null;
    }

    @Override
    public BlockDto findById(Long id) {
        return null;
    }
}
