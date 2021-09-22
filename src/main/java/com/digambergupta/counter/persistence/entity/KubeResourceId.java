package com.digambergupta.counter.persistence.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class KubeResourceId implements Serializable {

	private String kind;

	private String metadataName;

	public KubeResourceId() {
	}

	public KubeResourceId(String kind, String metadataName) {
		this.kind = kind;
		this.metadataName = metadataName;
	}

	public String getKind() {
		if (StringUtils.isEmpty(kind)) {
			return kind;
		}

		return StringUtils.capitalize(kind.toLowerCase());
	}

	public String getMetadataName() {
		return metadataName;
	}
}
