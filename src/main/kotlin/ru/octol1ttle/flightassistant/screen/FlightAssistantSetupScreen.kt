package ru.octol1ttle.flightassistant.screen

import ru.octol1ttle.flightassistant.api.util.extensions.*
import net.minecraft.client.gui.components.Button
import net.minecraft.network.chat.CommonComponents
import net.minecraft.network.chat.Component
import ru.octol1ttle.flightassistant.config.FAConfigScreen
import ru.octol1ttle.flightassistant.impl.computer.ComputerHost
import ru.octol1ttle.flightassistant.impl.display.HudDisplayHost
import ru.octol1ttle.flightassistant.screen.components.SmartStringWidget
import ru.octol1ttle.flightassistant.screen.system.SystemManagementScreen

class FlightAssistantSetupScreen : FABaseScreen(null, Component.translatable("menu.flightassistant")) {
    override fun init() {
        super.init()

        this.addRenderableWidget(SmartStringWidget(this.centerX, this.centerY - 80, Component.translatable("menu.flightassistant.system")).middleAligned())
        this.addRenderableWidget(Button.builder(Component.translatable("menu.flightassistant.system.manage_displays")) {
            this.minecraft!!.setScreenSafe(
                SystemManagementScreen(
                    this,
                Component.translatable("menu.flightassistant.system.manage_displays"), "menu.flightassistant.system.name.hud", HudDisplayHost)
            )
        }.pos(this.centerX - 105, this.centerY - 65).width(100).build())
        this.addRenderableWidget(Button.builder(Component.translatable("menu.flightassistant.system.manage_computers")) {
            this.minecraft!!.setScreenSafe(
                SystemManagementScreen(
                    this,
                Component.translatable("menu.flightassistant.system.manage_computers"), "menu.flightassistant.system.name.computer", ComputerHost)
            )
        }.pos(this.centerX + 5, this.centerY - 65).width(100).build())

        this.addRenderableWidget(Button.builder(Component.translatable("menu.flightassistant.config")) {
            this.minecraft!!.setScreenSafe(FAConfigScreen.generate(this))
        }.pos(10, this.height - 30).width(120).build())

        this.addRenderableWidget(Button.builder(Component.translatable("menu.flightassistant.wiki")) {
//? if >=26.3 {
            com.mojang.blaze3d.Blaze3D.openUri(java.net.URI.create("https://github.com/Octol1ttle/FlightAssistant/wiki"))
//?} else {
            /*net.minecraft.Util.getPlatform().openUri("https://github.com/Octol1ttle/FlightAssistant/wiki")
*///?}
        }.pos(140, this.height - 30).width(80).build())

        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE) { _: Button? ->
            this.onClose()
        }.pos(this.width - 90, this.height - 30).width(80).build())
    }
}
