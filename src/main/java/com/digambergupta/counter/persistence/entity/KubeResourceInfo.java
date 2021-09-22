package com.digambergupta.counter.persistence.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "kube_resource_info")
public class KubeResourceInfo {

	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private KubeResourceId kubeResourceId;

	@Column(name = "count")
	private Integer count;

	public KubeResourceInfo() {
	}

	public KubeResourceInfo(KubeResourceId kubeResourceId, Integer count) {
		this.kubeResourceId = kubeResourceId;
		this.count = count;
	}

	public KubeResourceId getKubeResourceId() {
		return kubeResourceId;
	}

	public void setKubeResourceId(KubeResourceId kubeResourceId) {
		this.kubeResourceId = kubeResourceId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		KubeResourceInfo info = (KubeResourceInfo) o;
		return Objects.equals(kubeResourceId, info.kubeResourceId) && Objects.equals(count, info.count);
	}

	@Override
	public int hashCode() {
		return Objects.hash(kubeResourceId, count);
	}
}
