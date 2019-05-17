package edu.mum.waa.dto;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class BaseDto<TDto,TEntity> {

    //@Autowired
    private ModelMapper modelMapper = new ModelMapper() ;

    protected Class<TDto> dtoClass;
    protected Class<TEntity> entityClass;

    public TDto convertToDto(TEntity source) {
        if(source == null){
            try {
                return dtoClass.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        TDto dto = modelMapper.map(source, dtoClass);
        return dto;
    }

    public TEntity convertToEntity(TDto source) {
        TEntity entity = modelMapper.map(source, entityClass);
        return entity;
    }



}
