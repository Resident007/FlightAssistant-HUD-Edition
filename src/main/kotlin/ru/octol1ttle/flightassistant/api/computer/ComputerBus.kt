package ru.octol1ttle.flightassistant.api.computer

import ru.octol1ttle.flightassistant.api.ModuleView
import ru.octol1ttle.flightassistant.impl.computer.data.AirDataComputer
import ru.octol1ttle.flightassistant.impl.computer.data.HudDisplayDataComputer
import ru.octol1ttle.flightassistant.impl.computer.safety.*

interface ComputerBus : ModuleView<Computer> {
    @Deprecated("Will be private")
    fun <C, T> guardedCall(computer: C, call: (C) -> T): T?
    fun <Event : ComputerEvent> dispatchEvent(event: Event)
    fun <Response> dispatchQuery(query: ComputerQuery<Response>): Collection<Response>

    val data: AirDataComputer
        get() = get(AirDataComputer.ID) as AirDataComputer

    val hudData: HudDisplayDataComputer
        get() = get(HudDisplayDataComputer.ID) as HudDisplayDataComputer

    val alert: AlertComputer
        get() = get(AlertComputer.ID) as AlertComputer

    val chunk: ChunkStatusComputer
        get() = get(ChunkStatusComputer.ID) as ChunkStatusComputer

    val elytra: ElytraStatusComputer
        get() = get(ElytraStatusComputer.ID) as ElytraStatusComputer

    val gpws: GroundProximityComputer
        get() = get(GroundProximityComputer.ID) as GroundProximityComputer

    val stall: StallComputer
        get() = get(StallComputer.ID) as StallComputer

    val voidProximity: VoidProximityComputer
        get() = get(VoidProximityComputer.ID) as VoidProximityComputer
}
