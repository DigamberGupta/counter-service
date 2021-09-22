package com.digambergupta.counter.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class KubeResourceId implements Serializable {

	@Column(name = "kind")
	private String kind;

	@Column(name = "metadata_name")
	private String metadataName;

	public KubeResourceId() {
	}

	public KubeResourceId(String kind, String metadataName) {
		this.kind = kind;
		this.metadataName = metadataName;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMetadataName() {
		return metadataName;
	}

	public void setMetadataName(String metadataName) {
		this.metadataName = metadataName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		KubeResourceId that = (KubeResourceId) o;
		return Objects.equals(kind, that.kind) && Objects.equals(metadataName, that.metadataName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(kind, metadataName);
	}
}
