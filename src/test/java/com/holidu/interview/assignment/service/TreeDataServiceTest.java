package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.domain.TreeData;
import com.holidu.interview.assignment.repository.TreeCensusDataStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TreeDataServiceTest {

    @Mock
    TreeCensusDataStore treeCensusDataStore;

    private TreeDataService treeDataService;

    @Before
    public void init() {
        treeDataService = new TreeDataService(treeCensusDataStore);
    }

    @Test
    public void shouldReturnAllSpeciesCountWithinRadius() {
        double xCoord = 109567.3418;
        double yCoord = 108456.3412;
        double radius = 107567.1984;
        BigDecimal x = BigDecimal.valueOf(xCoord);
        BigDecimal y = BigDecimal.valueOf(yCoord);
        BigDecimal rad = BigDecimal.valueOf(radius);
        BigDecimal from  = getSqureOfNumber(x).add(getSqureOfNumber(y));
        BigDecimal to = getSqureOfNumber(rad);

        List<TreeData> trees = getMockTreeDataList();

        when(treeCensusDataStore.getTreeDataWithinRadius(from,to)).thenReturn(trees);

        Map<String, Long> results = treeDataService.getSpeciesCountInRedius(xCoord,yCoord,radius);
        assertEquals(3, (long)results.get("red mapple"));
        assertEquals(2, (long)results.get("American linden"));

    }

    private BigDecimal getSqureOfNumber(BigDecimal number) {
        return number.multiply(number);
    }

    private List<TreeData> getMockTreeDataList() {
        TreeData treeDataOne = new TreeData();
        treeDataOne.setCommonSpc("red mapple");
        treeDataOne.setxCoordinate(108345.472);
        treeDataOne.setyCoordinate(106345.472);
        TreeData treeDataTwo = new TreeData();
        treeDataTwo.setCommonSpc("red mapple");
        treeDataTwo.setxCoordinate(108245.472);
        treeDataTwo.setyCoordinate(107345.472);
        TreeData treeDataThree = new TreeData();
        treeDataThree.setCommonSpc("red mapple");
        treeDataThree.setxCoordinate(108285.472);
        treeDataThree.setyCoordinate(117345.472);

        TreeData treeDataFour = new TreeData();
        treeDataFour.setCommonSpc("American linden");
        treeDataFour.setxCoordinate(128285.472);
        treeDataFour.setyCoordinate(117345.472);
        TreeData treeDataFive = new TreeData();
        treeDataFive.setCommonSpc("American linden");
        treeDataFive.setxCoordinate(128285.472);
        treeDataFive.setyCoordinate(117345.472);

        List<TreeData> trees = new ArrayList<>();
        trees.add(treeDataOne);
        trees.add(treeDataTwo);
        trees.add(treeDataThree);
        trees.add(treeDataFour);
        trees.add(treeDataFive);
        return trees;
    }
}
