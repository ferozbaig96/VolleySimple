package com.app.fbulou.volleysimple.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SamplePojo {

    @SerializedName("page")
    private String page;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<DataItem> data;

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "per_page = '" + perPage + '\'' +
                        ",total = '" + total + '\'' +
                        ",data = '" + data + '\'' +
                        ",page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        "}";
    }
}