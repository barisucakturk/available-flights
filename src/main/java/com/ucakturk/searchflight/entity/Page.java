package com.ucakturk.searchflight.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class Page<T> {

    private int count;

    private int pageNumber;

    private int totalCount;

    private List<T> data;

    public Page(int totalCount, int pageNumber, List<T> data) {
        this.count = data.size();
        this.pageNumber = pageNumber;
        this.data = data;
        this.totalCount = totalCount;
    }
}
