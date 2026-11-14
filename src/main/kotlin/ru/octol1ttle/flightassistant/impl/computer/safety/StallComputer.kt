package ru.octol1ttle.flightassistant.impl.computer.safety

import ru.octol1ttle.flightassistant.api.util.extensions.*
import net.minecraft.resources.ResourceLocation
import ru.octol1ttle.flightassistant.FlightAssistant
import ru.octol1ttle.flightassistant.api.computer.Computer
import ru.octol1ttle.flightassistant.api.computer.ComputerBus

class StallComputer(computers: ComputerBus) : Computer(computers) {
    var status: Status = Status.SAFE
        private set
    private var maximumSafePitch: Float = 90.0f

    override fun tick() {
        val angleOfAttack: Float = computers.data.pitch - computers.data.flightPitch
        status =
            if (computers.data.flying && !computers.data.fallDistanceSafe && angleOfAttack > 90.0f)
                if (status == Status.FULL_STALL || computers.data.velocityPerSecond.y <= -10) Status.FULL_STALL else Status.APPROACHING_STALL
            else Status.SAFE

        maximumSafePitch = (computers.data.flightPitch + 90.0).coerceAtMost(computers.data.forwardVelocityPerSecond.length() * 3.0 + 45.0).toFloat()
    }

    override fun reset() {
        status = Status.SAFE
        maximumSafePitch = 90.0f
    }

    enum class Status {
        FULL_STALL,
        APPROACHING_STALL,
        SAFE
    }

    companion object {
        val ID: ResourceLocation = FlightAssistant.id("stall")
    }
}
