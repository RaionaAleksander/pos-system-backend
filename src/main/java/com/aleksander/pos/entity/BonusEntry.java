package com.aleksander.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bonus_entries", indexes = {
        @Index(name = "idx_bonus_entry_card", columnList = "loyalty_card_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonusEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loyalty_card_id", nullable = false)
    private LoyaltyCard loyaltyCard;

    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "period_end", nullable = false)
    private LocalDate periodEnd;

    @Column(name = "total_earned", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalEarned;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal remaining;

    @Column(name = "available_from", nullable = false)
    private LocalDate availableFrom;

    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.remaining == null) {
            this.remaining = this.totalEarned;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}