package com.dell.service.dao;

public class AppDAO {
    private String service;
    private String facilityName;
    private String location;

    private AppDAO(Builder builder) {
        this.service = builder.service;
        this.facilityName = builder.facilityName;
        this.location = builder.location;
    }

    public String getService() {
        return service;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getLocation() {
        return location;
    }

    public static class Builder {
        private String service;
        private String facilityName;
        private String location;

        public Builder service(String service) {
            this.service = service;
            return this;
        }

        public Builder facilityName(String facilityName) {
            this.facilityName = facilityName;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public AppDAO build() {
            return new AppDAO(this);
        }
    }
}