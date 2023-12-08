package org.garvit.IronHide.repositories;

import java.util.Optional;
import org.garvit.IronHide.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {

  Optional<AuditEntity> findByUuid(String uuid);
}
