package com.digambergupta.counter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digambergupta.counter.persistence.entity.KubeResourceId;
import com.digambergupta.counter.persistence.entity.KubeResourceInfo;

@Repository
public interface KubeResourceInfoRepository extends JpaRepository<KubeResourceInfo, KubeResourceId> {

}
