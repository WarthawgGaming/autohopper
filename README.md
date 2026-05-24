# AutoHopper

AutoHopper is a Minecraft Java Edition plugin designed to make inventory management faster and easier for survival servers.

Players can quickly dump items into a hopper without opening menus or manually moving stacks one at a time.

---

## Features

- Right-click a hopper with a stick to register it.
- Sneak (crouch) on the hopper to automatically empty inventory items into it.
- Keeps armor equipped.
- Keeps hotbar items untouched.
- Simple quality-of-life plugin for survival gameplay.
- Lightweight and easy to use.

---

## Quick Start

1. Install the plugin.
2. Restart the server.
3. Give players the `autohopper.use` permission.
4. Right-click a hopper with a stick.
5. Sneak on the hopper to transfer inventory items.

---

## How It Works

1. Hold a stick.
2. Right-click a hopper.
3. Sneak (crouch) on the hopper.
4. Inventory items are transferred into the hopper automatically.

Armor and hotbar items are ignored.

---

## Commands

| Command | Description | Permission |
|---|---|---|
| `/autohopper enable` | Enables AutoHopper for the player | `autohopper.use` |
| `/autohopper disable` | Disables AutoHopper for the player | `autohopper.use` |

---

## Permissions

| Permission | Description |
|---|---|
| `autohopper.use` | Allows the player to use the `/autohopper` command and enable or disable AutoHopper. |

### LuckPerms Example

```text
/lp group default permission set autohopper.use true
```

---

## Installation

1. Download the latest release from the Releases section.
2. Place the `.jar` file into your server `plugins` folder.
3. Restart your server.
4. Configure permissions if needed.

---

## Compatibility

| Platform | Supported Versions |
|---|---|
| Minecraft Java Edition | 1.17+ |
| Server Software | Paper / Spigot |
| API Version | 1.17 |

---

## Configuration

Configuration options and customization settings are planned for a future update.

---

## Roadmap

- [x] Basic hopper transfer system
- [x] Enable / disable commands
- [x] Permission support
- [ ] Configurable settings
- [ ] Hopper ownership limits
- [ ] Admin management commands
- [ ] Configurable item filters
- [ ] Expanded automation features
- [ ] Sound and particle feedback

---

## Releases

### v1.0.0

Initial release.

Features included:
- Hopper registration
- Automatic inventory transfer
- Enable / disable commands
- Permission support

---

## Support

For support, updates, and future projects:

- GitHub: https://github.com/WarthawgGaming
- Discord: https://discord.gg/SFKsPd8YjH
- Website: https://warthawggaming.github.io

---

## License

This project is licensed under the MIT License.

See the LICENSE file for more information.
