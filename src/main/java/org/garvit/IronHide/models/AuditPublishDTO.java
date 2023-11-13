package org.garvit.IronHide.models;

import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.garvit.IronHide.constants.SecurityConstants;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditPublishDTO implements Serializable {

  @Serial private static final long serialVersionUID = -417463532907899400L;

  @NotBlank(message = SecurityConstants.UUID_BLANK_MESSAGE)
  @UUID(message = SecurityConstants.UUID_NOT_VALID_MESSAGE)
  protected String uuid;

  @NotBlank(message = SecurityConstants.APPLICATION_NAME_BLANK_MESSAGE)
  protected String application;

  @NotBlank(message = SecurityConstants.CALLER_BLANK_MESSAGE)
  protected String caller;

  @NotNull(message = SecurityConstants.CONTEXT_BLANK_MESSAGE)
  protected AuditContext context;

  protected long timeMillis;

  @NotBlank(message = SecurityConstants.AUDITOR_BLANK_MESSAGE)
  protected String auditor;

  @NotBlank(message = SecurityConstants.BLOB_BLANK_MESSAGE)
  protected String blob;

  protected String additionalMetadata;
}
