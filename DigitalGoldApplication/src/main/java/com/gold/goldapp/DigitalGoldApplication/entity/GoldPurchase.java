package com.gold.goldapp.DigitalGoldApplication.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gold_purchase")
public class GoldPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String externalUserId;

    @Column(nullable = false)
    private Double grams;

    @Column(nullable = false)
    private Double pricePerGram;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String status;

    // getters & setters
    public Long getId() { return id; }
    public String getExternalUserId() { return externalUserId; }
    public void setExternalUserId(String externalUserId) { this.externalUserId = externalUserId; }
    public Double getGrams() { return grams; }
    public void setGrams(Double grams) { this.grams = grams; }
    public Double getPricePerGram() { return pricePerGram; }
    public void setPricePerGram(Double pricePerGram) { this.pricePerGram = pricePerGram; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


