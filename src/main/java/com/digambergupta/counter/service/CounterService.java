package com.digambergupta.counter.service;

import com.digambergupta.counter.api.model.ApiKubernetesCounterResponse;
import com.digambergupta.counter.api.model.ApiSubmitKubernetesCounterDecrementRequest;
import com.digambergupta.counter.api.model.ApiSubmitKubernetesCounterIncrementRequest;

public interface CounterService {
	ApiKubernetesCounterResponse createOrIncrementKubeCounter(ApiSubmitKubernetesCounterIncrementRequest request);

	ApiKubernetesCounterResponse decrementKubeCount(ApiSubmitKubernetesCounterDecrementRequest decrementRequest);

	ApiKubernetesCounterResponse getKubeCounter(String kind, String metadataName);
}
