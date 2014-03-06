package com.alexgilleran.goget.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexgilleran.goget.dao.GoGetDao;
import com.alexgilleran.goget.model.Pod;
import com.alexgilleran.goget.model.Pod.CO2SavedComparator;
import com.alexgilleran.goget.rest.adapters.PodWrapper;

@Component
@Path("")
public class EndPoint {
    @Autowired
    private GoGetDao dao;

    @PostConstruct
    public void init() {

    }

    @GET
    @Path("/pods")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPods(@DefaultValue("2147483647") @QueryParam("maxTrip") float maxTrip,
	    @QueryParam("from") @DefaultValue("0") int from, @QueryParam("to") @DefaultValue("10") int to,
	    @DefaultValue("CO2") @QueryParam("sort") Sort sort) {
	List<Pod> pods = new ArrayList<Pod>(dao.getPods());
	Collections.sort(pods, getComparator(maxTrip, sort));

	List<PodWrapper> restPods = new ArrayList<PodWrapper>();
	for (int i = from; i < to; i++) {
	    restPods.add(new PodWrapper(i + 1, pods.get(i), maxTrip));
	}

	return Response.status(Status.OK).entity(restPods).build();
    }

    @GET
    @Path("/pods/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPods(@PathParam("id") int id, @DefaultValue("2147483647") @QueryParam("maxTrip") float maxTrip) {
	Pod pod = dao.getPod(id);
	PodWrapper wrapper = new PodWrapper(1, pod, maxTrip);

	return Response.status(Status.OK).entity(wrapper).build();
    }

    private Comparator<Pod> getComparator(float maxTrip, Sort sort) {
	switch (sort) {
	case CO2:
	    return new CO2SavedComparator(maxTrip);
	case FUEL:
	    return new Pod.FuelSavedComparator(maxTrip);
	case PERCENTAGE:
	    return new Pod.PercentageComparator(maxTrip);
	}
	return null;
    }

    public enum Sort {
	CO2, FUEL, PERCENTAGE;
    }
}