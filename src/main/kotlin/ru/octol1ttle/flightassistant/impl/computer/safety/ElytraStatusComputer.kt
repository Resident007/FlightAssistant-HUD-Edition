package ru.octol1ttle.flightassistant.impl.computer.safety

import java.time.Duration
import kotlin.math.round
import kotlin.math.roundToInt
import ru.octol1ttle.flightassistant.api.util.extensions.*
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import ru.octol1ttle.flightassistant.FlightAssistant
import ru.octol1ttle.flightassistant.api.computer.Computer
import ru.octol1ttle.flightassistant.api.computer.ComputerBus
import ru.octol1ttle.flightassistant.api.util.extensions.formatRoot
import ru.octol1ttle.flightassistant.config.options.DisplayOptions
import ru.octol1ttle.flightassistant.impl.computer.data.AirDataComputer

class ElytraStatusComputer(computers: ComputerBus) : Computer(computers) {
    private var activeElytra: ItemStack? = null

    override fun tick() {
        val data: AirDataComputer = computers.data
        activeElytra = findActiveElytra(data.player)
    }

    private fun findActiveElytra(player: Player): ItemStack? {
//? if >=1.21.2 {
        for (equipmentSlot in net.minecraft.world.entity.EquipmentSlot.VALUES) {
            val stack: ItemStack = player.getItemBySlot(equipmentSlot)
            if (net.minecraft.world.entity.LivingEntity.canGlideUsing(stack, equipmentSlot)) {
                return stack
            }
        }
//?} else {
        /*for (stack: ItemStack in player.armorSlots) {
            if (stack.item is net.minecraft.world.item.ElytraItem) {
                return stack
            }
        }
*///?}

//? if >=1.21.5 {
        for (equipmentSlot in net.minecraft.world.entity.EquipmentSlot.VALUES) {
            val stack: ItemStack = player.getItemBySlot(equipmentSlot)
//?} else
        /*for (stack: ItemStack in player.handSlots) {*/
//? if >=1.21.2 {
            if (stack.has(net.minecraft.core.component.DataComponents.GLIDER)) {
//?} else
            /*if (stack.item is net.minecraft.world.item.ElytraItem) {*/
                return stack
            }
        }

        return null
    }

    fun formatDurability(units: DisplayOptions.DurabilityUnits, player: Player): Component? {
        val active: ItemStack = activeElytra ?: return null
        if (!active.isDamageableItem) {
            return Component.translatable("short.flightassistant.infinite")
        }

        val unbreakingLevel: Int = EnchantmentHelper.getItemEnchantmentLevel(
//? if >=1.21.2 {
            player.level().registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).get(Enchantments.UNBREAKING).orElseThrow()
//?} else if >=1.21 {
            /*player.level().registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolder(Enchantments.UNBREAKING).get()
*///?} else
            /*Enchantments.UNBREAKING*/
            , active
        )

        return when (units) {
            DisplayOptions.DurabilityUnits.RAW -> Component.literal((active.maxDamage - active.damageValue).toString())
            DisplayOptions.DurabilityUnits.PERCENTAGE -> Component.literal("${round((active.maxDamage - active.damageValue - 1) * 100 / active.maxDamage.toFloat()).roundToInt()}%")
            DisplayOptions.DurabilityUnits.TIME -> {
                val duration: Duration = Duration.ofSeconds(getRemainingFlightTime(player)!!.toLong())
                val seconds: Int = when (unbreakingLevel) {
                    0 -> duration.toSecondsPart()
                    1 -> Mth.quantize(duration.toSecondsPart().toDouble(), 5)
                    2 -> Mth.quantize(duration.toSecondsPart().toDouble(), 15)
                    else -> Mth.quantize(duration.toSecondsPart().toDouble(), 30)
                }
                Component.literal("${duration.toMinutesPart()}:${"%02d".formatRoot(seconds)}")
            }
        }
    }

    fun getRemainingFlightTime(@Suppress("UNUSED_PARAMETER", "KotlinRedundantDiagnosticSuppress") player: Player): Int? {
        val active: ItemStack = activeElytra ?: return null
        if (!active.isDamageableItem) {
            return Int.MAX_VALUE
        }

        val unbreakingLevel: Int = EnchantmentHelper.getItemEnchantmentLevel(
//? if >=1.21.2 {
            player.level().registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).get(Enchantments.UNBREAKING).orElseThrow()
//?} else if >=1.21 {
            /*player.level().registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolder(Enchantments.UNBREAKING).get()
*///?} else
            /*Enchantments.UNBREAKING*/
            , active
        )
        return (active.maxDamage - active.damageValue - 1) * (unbreakingLevel + 1)
    }

    override fun reset() {
        activeElytra = null
    }

    companion object {
        val ID: ResourceLocation = FlightAssistant.id("elytra_status")
    }
}
