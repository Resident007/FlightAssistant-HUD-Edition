# FlightAssistant HUD Edition

> [!IMPORTANT]
> **Unofficial fork — HUD-only edition, without any protections or automations**  
> This is an unofficial fork of [FlightAssistant by Octol1ttle](https://github.com/Octol1ttle/FlightAssistant), based on [FlightAssistant Continued](https://github.com/Henner4746/FlightAssistant).  
> It is **not affiliated with and not endorsed by** the original authors. Please report problems with this fork [here](https://github.com/Resident007/FlightAssistant-HUD-Edition/issues), not to the original projects.  
> Every feature that creates or modifies player control inputs has been removed, so the mod can be used on servers that do not allow flight automation.

**FlightAssistant HUD Edition** is a client-side avionics HUD for Minecraft elytra flight. It shows flight data and raises warnings and cautions, and it never touches your controls.

## What this fork removes

All control-taking functionality is removed, including its settings screens and key binds:

- Autopilot, auto-thrust, flight directors and FMS/flight plans
- Automatic flight protections (terrain and void damage avoidance, automatic elytra deploy)
- Firework locking and automatic firework use
- Thrust and roll control sources, including Do a Barrel Roll compatibility

There is no code path left that moves the player, changes pitch/yaw, or consumes items on your behalf. Everything the mod does is read-only: it observes the world and draws HUD to your screen above it.

## What remains

- Fully customizable flight HUD: attitude, heading, speed, altitude, radar altitude, vertical speed, flight path vector, coordinates and elytra durability
- Flight safety alerts: stall, ground proximity (Pull Up, Sink Rate, Terrain Ahead), void and chunk-loading warnings, HUD/flight computer faults, and explosive-firework caution
- Config and setup screens for displays, computers and alert behaviour
- Key binds to hide the current alert and restore the last hidden one

## Supported versions

| Minecraft | Loader |
|-----------|--------|
| 26.3      | Fabric |

Minecraft 26.2 support has been dropped. For older Minecraft versions, Forge and NeoForge, or the full feature set, use the [original FlightAssistant](https://modrinth.com/mod/flightassistant) or [FlightAssistant Continued](https://modrinth.com/mod/flightassistant-continued).

## Installation

Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 26.3 and put these mods into your `mods` folder:

- [Fabric API](https://modrinth.com/mod/fabric-api)
- [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)
- [YetAnotherConfigLib](https://modrinth.com/mod/yacl)
- [Architectury API](https://modrinth.com/mod/architectury-api)
- [Mod Menu](https://modrinth.com/mod/modmenu) (optional, for the config button)

The fork uses the mod ID `flightassistant_hud`, so it can be installed next to the original mod. Make sure the original is not also active as it will most likely result in conflicts between the two.

## Default controls

| Action | Key |
|--------|-----|
| Open the setup screen | Numpad Enter |
| Hide current alert / show last hidden alert | Numpad 0 / Numpad . |
| Toggle the mod on or off | Unbound by default |

All keys can be changed in Options → Controls → Key Binds, under the FlightAssistant category.

## Configuration

Settings are stored in `config/flightassistant_hud/`:

- `global.json5` — toggles and general behaviour
- `displays.json5` — HUD layout and display options
- `safety.json5` — alert thresholds and alert methods

## Building

You need JDK 27 (the project compiles for Java 25). The project uses [Stonecutter](https://stonecutter.kikugie.dev/):

```
./gradlew :26.3-fabric:build
```

The jar ends up in `versions/26.3-fabric/build/libs/`.

## Modrinth

This fork is planned to be published on Modrinth as a separate, unofficial project soon. It is not a replacement for the original mod, and its project page is independent of the original authors' pages.

## Credits and license

FlightAssistant was created by [Octol1ttle](https://github.com/Octol1ttle) with contributions from mctaylors and others. The 26.x port and the continued maintenance come from [Henner4746](https://github.com/Henner4746). This read-only fork is maintained by [Resident007](https://github.com/Resident007). This fork is AI-assisted.

Licensed under the [GNU Lesser General Public License v3.0](LICENSE), like the original.
