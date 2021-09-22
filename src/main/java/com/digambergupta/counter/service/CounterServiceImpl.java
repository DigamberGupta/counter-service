package com.digambergupta.counter.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digambergupta.counter.api.model.ApiKubernetesCounterResponse;
import com.digambergupta.counter.api.model.ApiSubmitKubernetesCounterDecrementRequest;
import com.digambergupta.counter.api.model.ApiSubmitKubernetesCounterIncrementRequest;
import com.digambergupta.counter.persistence.KubeResourceInfoRepository;
import com.digambergupta.counter.persistence.entity.KubeResourceId;
import com.digambergupta.counter.persistence.entity.KubeResourceInfo;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {

	private final KubeResourceInfoRepository repository;

	@Autowired
	public CounterServiceImpl(KubeResourceInfoRepository repository) {
		this.repository = repository;
	}

	@Override
	public ApiKubernetesCounterResponse createOrIncrementKubeCounter(ApiSubmitKubernetesCounterIncrementRequest incrementRequest) {

		final KubeResourceId kubeResourceId = new KubeResourceId(StringUtils.capitalize(incrementRequest.getKind().toLowerCase()),
				incrementRequest.getMetadataName());

		final Optional<KubeResourceInfo> currentInfo = repository.findById(kubeResourceId);

		if (currentInfo.isPresent()) {
			currentInfo.get().setCount(currentInfo.get().getCount() + incrementRequest.getIncrementCountBy());
			KubeResourceInfo info = repository.save(currentInfo.get());
			return mapToCounterResponse(info);
		}

		final KubeResourceInfo newResourceInfo = mapToResourceInfo(incrementRequest);

		return mapToCounterResponse(repository.save(newResourceInfo));
	}

	@Override
	public ApiKubernetesCounterResponse decrementKubeCount(ApiSubmitKubernetesCounterDecrementRequest decrementRequest) {
		final KubeResourceId kubeResourceId = new KubeResourceId(decrementRequest.getKind(), decrementRequest.getMetadataName());

		Optional<KubeResourceInfo> currentInfo = repository.findById(kubeResourceId);

		if (currentInfo.isPresent() && currentInfo.get().getCount() > 0) {
			int countResult = currentInfo.get().getCount() - decrementRequest.getDecrementCountBy();

			if (countResult > 0) {
				currentInfo.get().setCount(countResult);
				KubeResourceInfo info = repository.save(currentInfo.get());
				return mapToCounterResponse(info);
			}
		}

		final KubeResourceInfo newResourceInfo = mapToResourceInfo(decrementRequest);

		return mapToCounterResponse(repository.saveAndFlush(newResourceInfo));
	}

	@Override
	public ApiKubernetesCounterResponse getKubeCounter(String kind, String metadataName) {
		final KubeResourceId kubeResourceId = new KubeResourceId(StringUtils.capitalize(kind.toLowerCase()), metadataName);

		final Optional<KubeResourceInfo> currentInfo = repository.findById(kubeResourceId);

		return currentInfo.map(this::mapToCounterResponse).orElse(null);
	}

	private KubeResourceInfo mapToResourceInfo(ApiSubmitKubernetesCounterDecrementRequest decrementRequest) {
		final KubeResourceInfo newInfo = new KubeResourceInfo();
		newInfo.setKubeResourceId(new KubeResourceId(decrementRequest.getKind(), decrementRequest.getMetadataName()));
		newInfo.setCount(0);

		return newInfo;
	}

	private KubeResourceInfo mapToResourceInfo(ApiSubmitKubernetesCounterIncrementRequest incrementRequest) {
		final KubeResourceInfo newInfo = new KubeResourceInfo();
		newInfo.setKubeResourceId(new KubeResourceId(StringUtils.capitalize(incrementRequest.getKind().toLowerCase()), incrementRequest.getMetadataName()));
		newInfo.setCount(incrementRequest.getIncrementCountBy() > 0 ? incrementRequest.getIncrementCountBy() : 1);

		return newInfo;
	}

	private ApiKubernetesCounterResponse mapToCounterResponse(KubeResourceInfo info) {
		final ApiKubernetesCounterResponse response = new ApiKubernetesCounterResponse();
		response.setKind(info.getKubeResourceId().getKind());
		response.setMetadataName(info.getKubeResourceId().getMetadataName());
		response.setCount(info.getCount());

		return response;
	}
}
