package ru.octol1ttle.flightassistant.impl.computer.safety

import ru.octol1ttle.flightassistant.api.util.extensions.*
import net.minecraft.resources.ResourceLocation
import ru.octol1ttle.flightassistant.FlightAssistant
import ru.octol1ttle.flightassistant.api.computer.Computer
import ru.octol1ttle.flightassistant.api.computer.ComputerBus
import ru.octol1ttle.flightassistant.api.util.extensions.bottomY

class VoidProximityComputer(computers: ComputerBus) : Computer(computers) {
    var status: Status = Status.ABOVE_GROUND
        private set

    override fun tick() {
        status = if (computers.gpws.groundY != null) {
            Status.ABOVE_GROUND
        } else {
            val heightAboveDamageAltitude: Double = computers.data.altitude - computers.data.voidY
            if (heightAboveDamageAltitude > 16.0) {
                Status.CLEAR_OF_DAMAGE_ALTITUDE
            } else if (status != Status.REACHED_DAMAGE_ALTITUDE && heightAboveDamageAltitude > 1.0) {
                Status.APPROACHING_DAMAGE_ALTITUDE
            } else {
                Status.REACHED_DAMAGE_ALTITUDE
            }
        }
    }

    override fun reset() {
        status = Status.ABOVE_GROUND
    }

    enum class Status {
        REACHED_DAMAGE_ALTITUDE,
        APPROACHING_DAMAGE_ALTITUDE,
        CLEAR_OF_DAMAGE_ALTITUDE,
        ABOVE_GROUND
    }

    companion object {
        val ID: ResourceLocation = FlightAssistant.id("void_proximity")
    }
}
