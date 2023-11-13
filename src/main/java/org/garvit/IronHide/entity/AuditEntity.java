package org.garvit.IronHide.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.garvit.IronHide.models.AuditContext;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audit")
public class AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_id_generator")
  @SequenceGenerator(name = "audit_id_generator", sequenceName = "audit_id_seq", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "uuid", nullable = false, length = 128)
  private String uuid;

  @Column(name = "application", nullable = false, length = 32)
  private String application;

  @Column(name = "caller", nullable = false, length = 32)
  private String caller;

  @Column(name = "audit_context", nullable = false, length = 8)
  @Enumerated(EnumType.STRING)
  private AuditContext context;

  @Column(name = "time", nullable = false)
  private Date time;

  @Column(name = "auditor", nullable = false, length = 64)
  private String auditor;

  @Column(name = "blob")
  @Type(value = JsonBinaryType.class)
  private String blob;

  @Column(name = "metadata")
  private String metadata;
}
