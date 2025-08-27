package com.gold.goldapp.DigitalGoldApplication.repository;

import com.gold.goldapp.DigitalGoldApplication.entity.GoldPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GoldPurchaseRepository extends JpaRepository<GoldPurchase , Long> {


}
