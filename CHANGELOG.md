Please report bugs on [GitHub](https://github.com/Resident007/FlightAssistant-HUD-Edition/issues). This is an unofficial fork, so please don't report its problems to the original FlightAssistant projects.

## Changes in 3.2.0

First release of FlightAssistant HUD Edition, a read-only fork for servers that do not allow flight automation.

### Removed

- Autopilot, auto-thrust, flight directors and FMS/flight plans
- Automatic flight protections, terrain and void damage avoidance, automatic elytra deploy
- Firework locking, automatic firework use and firework response alerts
- Thrust and roll control sources, plus Do a Barrel Roll compatibility
- Automation key binds, displays, screens and config options

### Kept

- HUD displays: attitude, heading, speed, altitude, radar altitude, vertical speed, flight path vector, coordinates, elytra durability
- Alerts: stall, GPWS (Pull Up, Sink Rate, Terrain Ahead), void and chunk-loading warnings, HUD/computer faults, explosive fireworks
- Setup and config screens, alert display and hiding controls

### Other changes

- Mod ID changed to `flightassistant_hud`, so the fork can coexist with the original mod
- Minecraft 26.3 only; 26.2 support dropped
- Config files moved to `config/flightassistant_hud/`
