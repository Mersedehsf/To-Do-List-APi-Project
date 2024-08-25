package com.example.ToDoList.mapper;

import com.example.ToDoList.model.entity.AbstractEntity;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;


@MapperConfig(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface AbstractMapper<E extends AbstractEntity,D>{

    E dtoToEntity(D dto);

    D  entityToDto(E entity);

}
