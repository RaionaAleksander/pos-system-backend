package com.aleksander.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aleksander.pos.entity.enums.TransactionType;

@Entity
@Table(name = "bonus_transactions", indexes = {
        @Index(name = "idx_bonus_tx_card", columnList = "loyalty_card_id"),
        @Index(name = "idx_bonus_tx_entry", columnList = "bonus_entry_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonusTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loyalty_card_id", nullable = false)
    private LoyaltyCard loyaltyCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bonus_entry_id")
    private BonusEntry bonusEntry;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}