package org.garvit.IronHide.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.garvit.IronHide.models.AuditDTO;
import org.garvit.IronHide.models.AuditPublishDTO;
import org.garvit.IronHide.services.AuditServicePort;
import org.garvit.IronHide.utilities.ValidationUtils;
import org.hibernate.validator.cfg.defs.UUIDDef;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author garvit-joshi on 13/11/23
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/audits")
public class AuditController {

  private final AuditServicePort auditServicePort;

  @PostMapping("/save")
  public ResponseEntity<String> saveAudit(@RequestBody AuditPublishDTO auditPublishDTO) {
    log.info("Request for saving audit for uuid: {}", auditPublishDTO.getUuid());
    return ResponseEntity.ok(auditServicePort.saveAudit(auditPublishDTO));
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<AuditDTO> getAuditByUUID(@PathVariable String uuid) {
    ValidationUtils.checkPrecondition(StringUtils.isEmpty(uuid), "UUID cannot be null");
    return ResponseEntity.ok(null);
  }
}
