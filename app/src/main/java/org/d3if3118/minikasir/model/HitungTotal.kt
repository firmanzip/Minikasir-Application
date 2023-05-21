package org.d3if3118.minikasir.model

import org.d3if3118.minikasir.db.MiniKasir

fun MiniKasir.hitungTotal(): PurchaseResult {
    val totalBelanja = jumlahBeli * harga
    val uangKembali = uangBayar - totalBelanja
    val bonusType = BonusType.getBonusTypeByPurchase(totalBelanja)
    val keterangan =
        if (uangKembali >= 0) "Terima kasih telah berbelanja." else "Uang anda tidak mencukupi."

    return PurchaseResult(totalBelanja, uangKembali, bonusType, keterangan)

}