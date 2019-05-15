package edu.mum.waa.dto;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseDto<TDto,TEntity> {

    @Autowired
    private ModelMapper modelMapper ;

    protected Class<TDto> dtoClass;
    protected Class<TEntity> entityClass;

    public TDto convertToDto(TEntity source) {
        TDto dto = modelMapper.map(source, dtoClass);
        return dto;
    }

    public TEntity convertToEntity(TDto source) {
        TEntity entity = modelMapper.map(source, entityClass);
        return entity;
    }



}
