package ru.octol1ttle.flightassistant.impl.alert.firework

import ru.octol1ttle.flightassistant.api.util.extensions.*
import ru.octol1ttle.flightassistant.api.util.extensions.FAGuiGraphics
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import ru.octol1ttle.flightassistant.api.alert.Alert
import ru.octol1ttle.flightassistant.api.alert.AlertData
import ru.octol1ttle.flightassistant.api.alert.ECAMAlert
import ru.octol1ttle.flightassistant.api.computer.ComputerBus
import ru.octol1ttle.flightassistant.api.util.extensions.cautionColor
import ru.octol1ttle.flightassistant.api.util.extensions.drawString
import ru.octol1ttle.flightassistant.config.FAConfig

class FireworkExplosiveAlert(computers: ComputerBus, private val hand: InteractionHand) : Alert(computers), ECAMAlert {
    override val priorityOffset: Int = 5
    override val data: AlertData = AlertData.MASTER_CAUTION

    override fun shouldActivate(): Boolean {
        if (!FAConfig.safety.fireworkExplosiveAlert) {
            return false
        }

        val stack = computers.data.player.getItemInHand(hand)
        return stack.get(DataComponents.FIREWORKS)?.explosions?.isEmpty() == false
    }

    override fun render(guiGraphics: FAGuiGraphics, firstLineX: Int, otherLinesX: Int, firstLineY: Int): Int {
        return guiGraphics.drawString(Component.translatable("alert.flightassistant.firework.explosive.${hand.toString().lowercase()}"), firstLineX, firstLineY, cautionColor)
    }
}
