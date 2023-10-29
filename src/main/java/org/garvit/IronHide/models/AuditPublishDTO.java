package org.garvit.IronHide.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditPublishDTO implements Serializable {

  @Serial private static final long serialVersionUID = -417463532907899400L;

  protected String application;
  protected String caller;
  protected AuditContext context;
  protected long time;
  protected String auditor;
  protected String blob;
  protected String additionalMetadata;
}
