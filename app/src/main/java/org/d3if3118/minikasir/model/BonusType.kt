package org.d3if3118.minikasir.model

enum class BonusType(val minimumPurchase: Int, val description: String) {
    NONE(0, "Tidak ada bonus"),
    ADDITIONAL_ITEM(50000, "Bonus 1 barang");

    companion object {
        fun getBonusTypeByPurchase(purchaseAmount: Int): BonusType {
            return if (purchaseAmount >= ADDITIONAL_ITEM.minimumPurchase) {
                ADDITIONAL_ITEM
            } else {
                NONE
            }
        }
    }
}
