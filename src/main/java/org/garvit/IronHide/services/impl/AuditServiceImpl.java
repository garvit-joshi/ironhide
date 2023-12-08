package org.garvit.IronHide.services.impl;

import lombok.RequiredArgsConstructor;
import org.garvit.IronHide.entity.AuditEntity;
import org.garvit.IronHide.mappers.ServiceMappers;
import org.garvit.IronHide.models.AuditDTO;
import org.garvit.IronHide.models.AuditPublishDTO;
import org.garvit.IronHide.repositories.AuditRepository;
import org.garvit.IronHide.services.AuditServicePort;
import org.garvit.IronHide.utilities.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/** Created by garvit-joshi on 29/11/23. */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditServicePort {

  private final AuditRepository auditRepository;

  @Override
  public String saveAudit(AuditPublishDTO auditDTO) {
    ValidationUtils.validateObject(auditDTO, true);
    return auditRepository.save(ServiceMappers.INSTANCE.auditDTOToEntity(auditDTO)).getUuid();
  }

  @Override
  public AuditDTO getAudit(String uuid) {
    Optional<AuditEntity> auditEntity = auditRepository.findByUuid(uuid);
    return auditEntity.map(ServiceMappers.INSTANCE::auditEntityToDTO).orElse(null);
  }
}
