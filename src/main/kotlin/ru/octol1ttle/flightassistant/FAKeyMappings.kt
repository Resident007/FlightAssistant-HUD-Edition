package ru.octol1ttle.flightassistant

import com.mojang.blaze3d.platform.InputConstants
import ru.octol1ttle.flightassistant.api.util.extensions.*
import net.minecraft.client.KeyMapping
import ru.octol1ttle.flightassistant.FlightAssistant.mc
import ru.octol1ttle.flightassistant.api.computer.ComputerBus
import ru.octol1ttle.flightassistant.config.FAConfig
import ru.octol1ttle.flightassistant.screen.FlightAssistantSetupScreen

object FAKeyMappings {
//? if >=1.21.9 {
    private val DEFAULT_CATEGORY = KeyMapping.Category.register(FlightAssistant.id("main"))
//?} else {
    /*private const val DEFAULT_CATEGORY = "key.category.flightassistant.main"
*///?}

//? if >=26.3 {
    // 26.3 moved input handling to SDL, so default keys are SDL scancodes
    private val KEYBOARD_INPUT = InputConstants.Type.KEYBOARD
    private const val KEY_KP_ENTER = org.lwjgl.sdl.SDLScancode.SDL_SCANCODE_KP_ENTER
    private const val KEY_KP_0 = org.lwjgl.sdl.SDLScancode.SDL_SCANCODE_KP_0
    private const val KEY_KP_DECIMAL = org.lwjgl.sdl.SDLScancode.SDL_SCANCODE_KP_PERIOD
//?} else {
    /*private val KEYBOARD_INPUT = InputConstants.Type.KEYSYM
    private const val KEY_KP_ENTER = org.lwjgl.glfw.GLFW.GLFW_KEY_KP_ENTER
    private const val KEY_KP_0 = org.lwjgl.glfw.GLFW.GLFW_KEY_KP_0
    private const val KEY_KP_DECIMAL = org.lwjgl.glfw.GLFW.GLFW_KEY_KP_DECIMAL
*///?}

    internal val keyMappings: MutableList<KeyMapping> = ArrayList()

    lateinit var toggleEnabled: KeyMapping

    lateinit var openFlightAssistantSetup: KeyMapping

    lateinit var hideCurrentAlert: KeyMapping
    lateinit var showHiddenAlert: KeyMapping

    fun setup() {
        toggleEnabled = addKeyMapping("toggle_enabled", InputConstants.UNKNOWN.value)
        openFlightAssistantSetup = addKeyMapping("open_flightassistant_setup", KEY_KP_ENTER)

        hideCurrentAlert = addKeyMapping("hide_current_alert", KEY_KP_0)
        showHiddenAlert = addKeyMapping("show_hidden_alert", KEY_KP_DECIMAL)
    }

//? if >=1.21.9 {
    private fun addKeyMapping(translationKey: String, code: Int, category: KeyMapping.Category = DEFAULT_CATEGORY): KeyMapping {
        val keyBinding = KeyMapping("key.category.flightassistant.${category.id.path}.${translationKey}", KEYBOARD_INPUT, code, category)
//?} else {
    /*private fun addKeyMapping(translationKey: String, code: Int, category: String = DEFAULT_CATEGORY): KeyMapping {
        val keyBinding = KeyMapping("${category}.${translationKey}", KEYBOARD_INPUT, code, category)
*///?}
        keyMappings.add(keyBinding)
        return keyBinding
    }

    fun checkPressed(computers: ComputerBus) {
        while (toggleEnabled.consumeClick()) {
            FAConfig.global.modEnabled = !FAConfig.global.modEnabled
        }

        while (openFlightAssistantSetup.consumeClick()) {
            mc.execute {
                mc.setScreenSafe(FlightAssistantSetupScreen())
            }
        }

        while (hideCurrentAlert.consumeClick()) {
            computers.alert.hideCurrentAlert()
        }
        while (showHiddenAlert.consumeClick()) {
            computers.alert.showHiddenAlert()
        }
    }
}
