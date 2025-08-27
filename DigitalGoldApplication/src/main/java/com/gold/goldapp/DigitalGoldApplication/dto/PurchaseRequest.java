package com.gold.goldapp.DigitalGoldApplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PurchaseRequest {

    @NotNull
    private String externalUserId;

    @NotNull
    @Min(0)
    private Double grams;

    @NotNull
    @Min(0)
    private Double pricePerGram;

    // getters & setters
    public String getExternalUserId() { return externalUserId; }
    public void setExternalUserId(String externalUserId) { this.externalUserId = externalUserId; }
    public Double getGrams() { return grams; }
    public void setGrams(Double grams) { this.grams = grams; }
    public Double getPricePerGram() { return pricePerGram; }
    public void setPricePerGram(Double pricePerGram) { this.pricePerGram = pricePerGram; }
}

