package com.holidu.interview.assignment.controller;

import com.holidu.interview.assignment.service.TreeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TreeDataController {

    private final TreeDataService treeDataService;

    @Autowired
    public TreeDataController (TreeDataService treeDataService) {
        this.treeDataService = treeDataService;
    }

    @RequestMapping(value = "/v1/treeData", method = RequestMethod.GET)
    ResponseEntity<Map<String, Long>> getAllTreesInRadius(@RequestParam(name = "xCoordinate", required = true) double xCoordinate,
                                          @RequestParam(name = "yCoordinate", required = true) double yCoordinate,
                                          @RequestParam(name = "radius", required = true) double radius)
    {
        Map<String, Long> result = treeDataService.getSpeciesCountInRedius(xCoordinate, yCoordinate, radius);
        return new ResponseEntity<Map<String, Long>>(result,HttpStatus.OK);
    }
}
