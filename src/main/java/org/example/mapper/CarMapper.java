package org.example.mapper;

import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealershipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "carModelId", target = "carModel.id"),
            @Mapping(source = "dealershipName", target = "dealership.name"),
            @Mapping(target = "dealership.cars", ignore = true)
    })
    CarDTO carEntityToCarDTO(CarEntity carEntity);

    @Mappings({
            @Mapping(source = "carModel.id", target = "carModelId"),
            @Mapping(source = "dealership.name", target = "dealershipName")
    })
    CarEntity carDTOToCarEntity(CarDTO carDTO);

    CarModelDTO carModelEntityToCarModelDTO(CarModelEntity carModelEntity);

    CarModelEntity carModelDTOToCarModelEntity(CarModelDTO carModelDTO);

    DealershipDTO dealershipEntityToDealershipDTO(DealershipEntity dealershipEntity);

    DealershipEntity dealershipDTOToDealershipEntity(DealershipDTO dealershipDTO);
}
