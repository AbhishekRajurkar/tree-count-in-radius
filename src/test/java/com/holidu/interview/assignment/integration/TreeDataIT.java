package com.holidu.interview.assignment.integration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TreeDataIT extends BaseIT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testRetrieveTreesWithinRadius() {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(createURLWithPort("/v1/treeData")).
                queryParam("xCoordinate",1027431.148)
                .queryParam("yCoordinate",202756.7687)
                .queryParam("radius",1104687.1234).build();

        ResponseEntity<Map<String, Long>> response  = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Long>>(){});
        Map<String, Long> treeData = response.getBody();

        //can assert json as well.
        assertEquals(2, (long)treeData.get("pin oak"));
        assertEquals(1, (long)treeData.get("red maple"));
        assertEquals(6, (long)treeData.get("London planetree"));
        assertEquals(1, (long)treeData.get("Amur maple"));
    }
}
