package org.garvit.IronHide.mappers;

import org.garvit.IronHide.entity.AuditEntity;
import org.garvit.IronHide.models.AuditDTO;
import org.garvit.IronHide.models.AuditPublishDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/** Created by garvit-joshi on 09/12/23. */
@Mapper
public interface ServiceMappers {

  ServiceMappers INSTANCE = Mappers.getMapper(ServiceMappers.class);

  @Mapping(target = "uuid", source = "auditDTO.uuid")
  @Mapping(target = "application", source = "auditDTO.application")
  @Mapping(target = "caller", source = "auditDTO.caller")
  @Mapping(target = "context", source = "auditDTO.context")
  @Mapping(target = "time", source = "auditDTO.timeMillis", qualifiedByName = "millisToDate")
  @Mapping(target = "auditor", source = "auditDTO.auditor")
  @Mapping(target = "blob", source = "auditDTO.blob")
  @Mapping(target = "metadata", source = "auditDTO.additionalMetadata")
  AuditEntity auditDTOToEntity(AuditPublishDTO auditDTO);

  @Mapping(target = "uuid", source = "auditEntity.uuid")
  @Mapping(target = "id", source = "auditEntity.id")
  @Mapping(target = "application", source = "auditEntity.application")
  @Mapping(target = "caller", source = "auditEntity.caller")
  @Mapping(target = "context", source = "auditEntity.context")
  @Mapping(target = "timeMillis", source = "auditEntity.time", qualifiedByName = "dateToMillis")
  @Mapping(target = "auditor", source = "auditEntity.auditor")
  @Mapping(target = "blob", source = "auditEntity.blob")
  @Mapping(target = "additionalMetadata", source = "auditEntity.metadata")
  AuditDTO auditEntityToDTO(AuditEntity auditEntity);

  @Named("millisToDate")
  default Date millisToDate(long millis) {
    return new Date(millis);
  }

  @Named("dateToMillis")
  default long dateToMillis(Date date) {
    return date.getTime();
  }
}
