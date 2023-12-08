package org.garvit.IronHide.services;

import org.garvit.IronHide.models.AuditDTO;
import org.garvit.IronHide.models.AuditPublishDTO;

import java.util.UUID;

/** Created by garvit-joshi on 29/11/23. */
public interface AuditServicePort {

   String saveAudit(AuditPublishDTO auditDTO);

   AuditDTO getAudit(String uuid);
}
