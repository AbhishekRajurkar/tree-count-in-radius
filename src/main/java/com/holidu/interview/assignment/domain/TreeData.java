package com.holidu.interview.assignment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TreeData {

    @JsonProperty("tree_id")
    private Integer treeId;
    @JsonProperty("spc_common")
    private String commonSpc;
    @JsonProperty("spc_latin")
    private String latinSpc;
    @JsonProperty("x_sp")
    private Double xCoordinate;
    @JsonProperty("y_sp")
    private Double yCoordinate;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public String getCommonSpc() {
        return commonSpc;
    }

    public void setCommonSpc(String commonSpc) {
        this.commonSpc = commonSpc;
    }

    public void setLatinSpc(String latinSpc) {
        this.latinSpc = latinSpc;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
