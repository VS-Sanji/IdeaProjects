package com.bjpowernode.entity;

import java.util.Objects;

/*
    城市类
 */
public class City {
    private Integer id;
    private String name;
    private String provinceId;

    public City() {
    }

    public City(Integer id, String name, String provinceId) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(provinceId, city.provinceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, provinceId);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provinceId='" + provinceId + '\'' +
                '}';
    }
}
