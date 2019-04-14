package com.holidu.interview.assignment.repository;

import com.holidu.interview.assignment.domain.TreeData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TreeCensusDataStore implements TreeCensusDataRepository{

    private final RestTemplate restTemplate;
    private SortedMap<BigDecimal, TreeData> treeDataMap = new TreeMap<>();

    public TreeCensusDataStore(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @PostConstruct
    public void fetchAndSortTreeCensusData() {
        ResponseEntity<List<TreeData>> response  = restTemplate.exchange(
                "https://data.cityofnewyork.us/resource/uvpi-gqnh.json?$limit=100&$offset=0",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TreeData>>(){});
        List<TreeData> treeDataList = response.getBody();
        treeDataMap.putAll(treeDataList.stream().collect(Collectors.toMap(this::addUpXCoordiToYCoordi, tree ->tree)));
    }

    private BigDecimal addUpXCoordiToYCoordi(TreeData treeData){
        BigDecimal x = BigDecimal.valueOf(treeData.getxCoordinate());
        BigDecimal y = BigDecimal.valueOf(treeData.getyCoordinate());
        x = x.multiply(x);
        y = y.multiply(y);
        return x.add(y);

    }

    @Override
    public List<TreeData> getTreeDataWithinRadius(BigDecimal from, BigDecimal to) {
        if (from.compareTo(to) == 1) {
            throw new IllegalArgumentException("Invalid arguments received - from value is greater than to" );
        }
        return new ArrayList<>(treeDataMap.subMap(from,to).values());
    }

}
