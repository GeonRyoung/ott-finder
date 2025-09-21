package com.example.ott_finder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbOttProviderDto {

    @JsonProperty("provider_id")
    private Integer providerId;

    @JsonProperty("provider_name")
    private String providerName;

    public Integer getProviderId() {
        return providerId;
    }

    public String getProviderName() {
        return providerName;
    }
}
