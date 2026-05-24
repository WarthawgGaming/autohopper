# AutoHopper Troubleshooting

## Plugin Does Not Load

Check:

- Minecraft server version compatibility
- Paper or Spigot installation
- Java version compatibility
- Console error messages

---

## Commands Do Not Work

Verify:

- The plugin loaded correctly
- The player has the correct permissions
- No plugin conflicts exist

---

## Hopper Is Not Working

Check:

- The hopper was registered using a stick
- The player is sneaking on the hopper
- Inventory contains transferable items

---

## Permissions Issues

Default permission:

```text
autohopper.use
```

Example using LuckPerms:

```text
/lp group default permission set autohopper.use true
```
