package com.alexgilleran.goget.dao;

import java.util.List;

import com.alexgilleran.goget.model.Pod;

public interface GoGetDao {
    List<Pod> getPods();

    Pod getPod(long id);
}
