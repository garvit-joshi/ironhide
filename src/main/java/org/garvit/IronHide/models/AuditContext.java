package org.garvit.IronHide.models;

import lombok.Getter;

@Getter
public enum AuditContext {
  GENERAL("GENERAL"),
  CREATED("CREATED"),
  UPDATED("UPDATED"),
  DELETED("DELETED"),
  LOGIN("LOGIN"),
  LOGOUT("LOGOUT");

  private final String auditContext;

  AuditContext(String auditContext) {
    this.auditContext = auditContext;
  }

  @Override
  public String toString() {
    return auditContext;
  }
}
