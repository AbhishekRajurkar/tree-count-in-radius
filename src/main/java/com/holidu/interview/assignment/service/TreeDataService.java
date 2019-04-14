package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.domain.TreeData;
import com.holidu.interview.assignment.repository.TreeCensusDataRepository;
import com.holidu.interview.assignment.repository.TreeCensusDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreeDataService {

    private final TreeCensusDataRepository<TreeData> treeCensusDataStore;

    @Autowired
    public TreeDataService (TreeCensusDataRepository treeCensusDataStore) {
        this.treeCensusDataStore =  treeCensusDataStore;
    }

    private List<TreeData> getTreesInRadius (double xCoordinate, double yCoordinate, double radius) {
        BigDecimal xCo = BigDecimal.valueOf(xCoordinate);
        BigDecimal yCo = BigDecimal.valueOf(yCoordinate);
        BigDecimal red = BigDecimal.valueOf(radius);

        BigDecimal from  = xCo.multiply(xCo).add(yCo.multiply(yCo));

        BigDecimal to = red.multiply(red);
        return treeCensusDataStore.getTreeDataWithinRadius(from,to);

    }

    public Map<String, Long> getSpeciesCountInRedius(double xCoordinate, double yCoordinate, double radius) {
        List<TreeData> trees = getTreesInRadius(xCoordinate,yCoordinate,radius);
        return trees.stream().filter(tree -> tree.getCommonSpc() != null).collect(Collectors.groupingBy(TreeData::getCommonSpc ,Collectors.counting()));
    }
}
