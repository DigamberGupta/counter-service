package com.digambergupta.counter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.digambergupta.counter.api.controllers.KubernetesCounterApiDelegate;
import com.digambergupta.counter.api.model.ApiKubernetesCounterResponse;
import com.digambergupta.counter.api.model.ApiSubmitKubernetesCounterDecrementRequest;
import com.digambergupta.counter.api.model.ApiSubmitKubernetesCounterIncrementRequest;
import com.digambergupta.counter.exception.ResourceNotFoundException;
import com.digambergupta.counter.service.CounterService;

@Component
public class KubernetesCounterApiDelegateImpl implements KubernetesCounterApiDelegate {

	private final CounterService counterService;

	@Autowired
	public KubernetesCounterApiDelegateImpl(final CounterService counterService) {
		this.counterService = counterService;
	}

	@Override
	public ResponseEntity<ApiKubernetesCounterResponse> createOrIncrementKubeCounter(final ApiSubmitKubernetesCounterIncrementRequest incrementRequest) {

		return ResponseEntity.ok(counterService.createOrIncrementKubeCounter(incrementRequest));
	}

	@Override
	public ResponseEntity<ApiKubernetesCounterResponse> decrementKubeCount(final ApiSubmitKubernetesCounterDecrementRequest decrementRequest) {
		return ResponseEntity.ok(counterService.decrementKubeCount(decrementRequest));
	}

	@Override
	public ResponseEntity<ApiKubernetesCounterResponse> getKubernetesCounter(final String kind, final String metadataName) {
		final ApiKubernetesCounterResponse response = counterService.getKubeCounter(kind, metadataName);

		if (response == null) {
			throw new ResourceNotFoundException("Kubernetes Resource NotFound", Map.of("kind", kind, "metadataName", metadataName));
		}

		return ResponseEntity.ok(response);
	}

}