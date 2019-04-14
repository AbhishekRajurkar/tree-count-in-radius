package com.holidu.interview.assignment.repository;

import com.holidu.interview.assignment.domain.TreeData;

import java.math.BigDecimal;
import java.util.List;

public interface TreeCensusDataRepository<TreeData> {

    void fetchAndSortTreeCensusData();
    public List<TreeData> getTreeDataWithinRadius(BigDecimal from, BigDecimal to);
}
