package com.gold.goldapp.DigitalGoldApplication.controller;
import com.gold.goldapp.DigitalGoldApplication.dto.PurchaseRequest;
import com.gold.goldapp.DigitalGoldApplication.entity.GoldPurchase;
import com.gold.goldapp.DigitalGoldApplication.repository.GoldPurchaseRepository;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final GoldPurchaseRepository repo;

    public PurchaseController(GoldPurchaseRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public GoldPurchase purchase(@Valid @RequestBody PurchaseRequest request) {
        GoldPurchase gp = new GoldPurchase();
        gp.setExternalUserId(request.getExternalUserId());
        gp.setGrams(request.getGrams());
        gp.setPricePerGram(request.getPricePerGram());
        gp.setTimestamp(LocalDateTime.now());
        gp.setStatus("SUCCESS");
        return repo.save(gp);
    }

    @GetMapping("/all")
    public List<GoldPurchase> allPurchases() {
        return repo.findAll();
    }
}

