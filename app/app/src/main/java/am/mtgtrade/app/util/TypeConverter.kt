package am.mtgtrade.app.util

import am.mtgtrade.app.ui.models.CardInfo
import io.magicthegathering.kotlinsdk.model.card.MtgCard

object TypeConverter {

    fun MtgCardToCardInfo(mtgCard: MtgCard): CardInfo {
        return CardInfo(mtgCard.multiverseid!!, mtgCard.name, mtgCard.rarity, mtgCard.setName)
    }
}