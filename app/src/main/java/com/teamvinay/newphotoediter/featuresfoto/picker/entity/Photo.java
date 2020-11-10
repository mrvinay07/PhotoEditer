package com.teamvinay.newphotoediter.featuresfoto.picker.entity;

public class Photo {

    /* renamed from: id */
    private int f622id;
    private String path;

    public Photo(int i, String str) {
        this.f622id = i;
        this.path = str;
    }

    public Photo() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Photo) && this.f622id == ((Photo) obj).f622id) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f622id;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public int getId() {
        return this.f622id;
    }

    public void setId(int i) {
        this.f622id = i;
    }
}
