package org.d3if3118.minikasir.model

data class PurchaseResult(
    val totalBelanja: Float,
    val uangKembali: Float,
    val bonusType: BonusType,
    val keterangan: String
) {
    fun getTotalBelanjaText(): String {
        return "Total belanja: Rp. $totalBelanja"

    }
}
