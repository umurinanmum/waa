package edu.mum.waa.service.interfaces;

import java.util.ArrayList;

public interface BaseService<TDto> {

    boolean save(TDto dto);

    boolean delete(Long id);

    boolean update(TDto dto);

    ArrayList<TDto> findAll();

    TDto findById(Long id);

}
