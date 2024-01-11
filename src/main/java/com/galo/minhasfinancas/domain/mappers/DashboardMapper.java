package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.dashboard.DashboardDTO;
import com.galo.minhasfinancas.domain.entity.Dashboard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DashboardMapper extends MAPPER<DashboardDTO, Dashboard> {
    DashboardDTO toDto(Dashboard obj);
    Dashboard toObj(DashboardDTO dto);
}

