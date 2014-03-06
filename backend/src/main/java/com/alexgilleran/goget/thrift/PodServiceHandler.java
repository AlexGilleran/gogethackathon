package com.alexgilleran.goget.thrift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexgilleran.goget.dao.GoGetDao;
import com.alexgilleran.goget.model.Pod;
import com.alexgilleran.goget.rest.adapters.PodWrapper;

@Component
public class PodServiceHandler implements PodService.Iface {

    @Autowired
    private GoGetDao dao;

    @Override
    public List<ThriftPod> getPods(int maxTrip, int from, int to, Sort sort) throws TException {
	List<Pod> pods = new ArrayList<Pod>(dao.getPods());
	Collections.sort(pods, getComparator(maxTrip, sort));

	List<ThriftPod> restPods = new ArrayList<ThriftPod>();
	for (int i = from; i < to; i++) {
	    restPods.add(buildThriftPod(new PodWrapper(i + 1, pods.get(i), maxTrip)));
	}

	return restPods;
    }

    @Override
    public ThriftPod getPod(int id, int maxTrip) throws TException {
	Pod pod = dao.getPod(id);
	PodWrapper wrapper = new PodWrapper(1, pod, maxTrip);

	return buildThriftPod(wrapper);
    }

    private ThriftPod buildThriftPod(PodWrapper podWrapper) {
	ThriftPod pod = new ThriftPod();

	pod.setId(podWrapper.getId());
	pod.setLatitude(podWrapper.getLatitude());
	pod.setLongitude(podWrapper.getLongitude());
	pod.setName(podWrapper.getName());
	pod.setPlaceInOrder(podWrapper.getPlaceInOrder());
	pod.setSaveableCo2(podWrapper.getSavableCo2());
	pod.setSaveableTripCount(podWrapper.getSaveableTripCount());
	pod.setSaveableTripFraction(podWrapper.getSavableTripFraction());
	pod.setSaveableFuel(podWrapper.getSavableCityFuel());
	pod.setTripCount(podWrapper.getTripCount());

	return pod;
    }

    private Comparator<Pod> getComparator(float maxTrip, Sort sort) {
	switch (sort) {
	case CO2:
	    return new Pod.CO2SavedComparator(maxTrip);
	case FUEL:
	    return new Pod.FuelSavedComparator(maxTrip);
	case PERCENTAGE:
	    return new Pod.PercentageComparator(maxTrip);
	}
	return null;
    }

}
