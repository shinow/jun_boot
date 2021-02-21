package com.ruoyi.app.modular.member.service.mapper;



import com.ruoyi.app.common.persistence.model.StoreMemberAddress;
import com.ruoyi.app.modular.mapper.EntityMapper;
import com.ruoyi.app.modular.member.service.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-06-05
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends EntityMapper<AddressDTO, StoreMemberAddress> {

}