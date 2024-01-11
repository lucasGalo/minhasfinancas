package com.galo.minhasfinancas.domain.mappers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.entity.EntityBase;

public interface MAPPER<R extends DTO,E extends EntityBase>{
     @JsonIgnore
     R toDto(E e);
    @JsonIgnore
     E toObj(R r);
}
