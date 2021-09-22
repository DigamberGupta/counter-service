package com.digambergupta.counter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digambergupta.counter.persistence.entity.KubeResourceId;
import com.digambergupta.counter.persistence.entity.KubeResourceInfo;

public interface KubeResourceInfoRepository extends JpaRepository<KubeResourceInfo, KubeResourceId> {

	@Query()
	KubeResourceInfo findByKindAndMetadataName(String kind, String metadataName);
}
