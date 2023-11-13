package org.garvit.IronHide.controllers;

import lombok.extern.slf4j.Slf4j;
import org.garvit.IronHide.models.AuditDTO;
import org.garvit.IronHide.models.AuditPublishDTO;
import org.garvit.IronHide.utilities.ValidationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author garvit-joshi on 13/11/23
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/audits")
public class AuditController {

  @PostMapping("/save")
  public ResponseEntity<UUID> saveAudit(@RequestBody AuditPublishDTO auditPublishDTO) {
    log.info("Request for saving audit for uuid: {}", auditPublishDTO.getUuid());
    ValidationUtils.validateObject(auditPublishDTO, true);
    // Add Service layer here
    return ResponseEntity.ok(UUID.randomUUID());
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<AuditDTO> getAuditByUUID(@PathVariable String uuid) {
    // Add Service layer here
    return ResponseEntity.ok(null);
  }
}
