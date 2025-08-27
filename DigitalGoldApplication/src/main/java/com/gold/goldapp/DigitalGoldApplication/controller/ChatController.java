package com.gold.goldapp.DigitalGoldApplication.controller;


import com.gold.goldapp.DigitalGoldApplication.dto.PurchaseRequest;
import com.gold.goldapp.DigitalGoldApplication.entity.GoldPurchase;
import com.gold.goldapp.DigitalGoldApplication.repository.GoldPurchaseRepository;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OpenAiService service;
    private final GoldPurchaseRepository purchaseRepo;

    public ChatController(@Autowired(required = false) OpenAiService service, GoldPurchaseRepository purchaseRepo) {
        this.service = service;
        this.purchaseRepo = purchaseRepo;
    }

    @PostMapping
    public Map<String,Object> chat(@RequestBody Map<String,String> request) {
        String message = request.get("question");
        Map<String,Object> response = new HashMap<>();

        boolean aboutGold = message.toLowerCase().contains("gold");
        boolean aboutDigital = message.toLowerCase().contains("digital gold");
        boolean wantsBuy = message.toLowerCase().matches(".*(buy|invest|purchase|yes).*");

        String reply;
        try {
            if (service != null) {
                ChatCompletionRequest req = ChatCompletionRequest.builder()
                        .model("gpt-4")
                        .messages(List.of(
                                new ChatMessage("system", "You are an assistant who identifies messages about gold and digital gold. Provide facts and nudge the user to invest."),
                                new ChatMessage("user", message)))
                        .maxTokens(400)
                        .build();

                reply = service.createChatCompletion(req)
                        .getChoices().get(0)
                        .getMessage()
                        .getContent();

            } else {
                // Fallback heuristic
                if (aboutDigital) {
                    reply = "Digital gold tracks real gold prices, stored in insured vaults. Would you like to purchase?";
                } else if (aboutGold) {
                    reply = "Gold is a stable investment. You can also buy digital gold online.";
                } else {
                    reply = "I can provide information about digital gold investments.";
                }
            }
        } catch (Exception e) {
            reply = "Currently unable to contact GPT-4. Digital gold is a stable investment. Would you like to purchase?";
        }

        response.put("reply", reply);
        response.put("aboutGold", aboutGold);
        response.put("aboutDigitalGold", aboutDigital);
        response.put("userWantsToBuy", wantsBuy);

        // ======= Auto-trigger purchase if user wants to buy =======
        if (aboutDigital && wantsBuy) {
            // For demo: fixed grams and price, in real app fetch live price
            PurchaseRequest purchaseRequest = new PurchaseRequest();
            purchaseRequest.setExternalUserId("user"); // could come from session
            purchaseRequest.setGrams(2.5);
            purchaseRequest.setPricePerGram(5400.0);

            GoldPurchase purchase = new GoldPurchase();
            purchase.setExternalUserId(purchaseRequest.getExternalUserId());
            purchase.setGrams(purchaseRequest.getGrams());
            purchase.setPricePerGram(purchaseRequest.getPricePerGram());
            purchase.setTimestamp(LocalDateTime.now());
            purchase.setStatus("SUCCESS");

            purchaseRepo.save(purchase);
            response.put("purchaseCompleted", true);
            response.put("purchaseDetails", purchase);
        }

        return response;
    }
}

