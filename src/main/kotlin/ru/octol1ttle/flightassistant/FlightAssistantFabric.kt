package ru.octol1ttle.flightassistant

//? if fabric {
import dev.architectury.registry.client.keymappings.KeyMappingRegistry
import net.fabricmc.api.ClientModInitializer

object FlightAssistantFabric : ClientModInitializer {
    override fun onInitializeClient() {
        FlightAssistant.init()
        FAKeyMappings.keyMappings.forEach(KeyMappingRegistry::register)
    }
}
//?}
