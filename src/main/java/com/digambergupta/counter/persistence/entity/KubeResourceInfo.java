package com.digambergupta.counter.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "kube_resource_info")
@IdClass(KubeResourceId.class)
public class KubeResourceInfo {

	@Id
	@Column(name = "kind")
	private String kind;

	@Id
	@Column(name = "metadata_name")
	private String metadataName;

	@Column(name = "count")
	private Integer count;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		if (StringUtils.isEmpty(kind)) {
			this.kind = kind;
		}

		this.kind = StringUtils.capitalize(kind.toLowerCase());
	}

	public String getMetadataName() {
		return metadataName;
	}

	public void setMetadataName(String metadataName) {
		this.metadataName = metadataName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
