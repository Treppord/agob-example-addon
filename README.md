# Example AGOB Addon

This repository is a complete Fabric addon example for `A Game of Blocks` using the documented AGOB addon API. It is intentionally small and copyable: one addon entrypoint, one standard Fabric mod entrypoint for addon-owned items, one troop pack, one quest template, one custom religion, one custom Essos kingdom, and three kingdom character options with one primary option.

## What It Demonstrates

- a custom Fabric `agob-addon` entrypoint
- a standard Fabric `main` entrypoint for addon-owned item and armor registration
- `trep.got.api.AgobAddonEntrypoint` registration flow
- troop JSON loaded from `data/exampleagobaddon/agob/troops`
- a quest template loaded from `data/exampleagobaddon/agob/quests`
- a custom religion and a custom Essos kingdom registered through the public AGOB API
- one `ReligionSelectionOption`
- one primary `CharacterSelectionOption`
- one addon blacksmith kingdom tab registered through the public AGOB API
- one addon blacksmith group and four addon blacksmith recipes
- one addon armor material and four addon-owned items: `plate_dothraki`, chestplate, leggings, and boots
- placeholder textures whose resource ids match the registered content

## Project Layout

- `src/main/java/trep/exampleagobaddon/ExampleAgobAddon.java`
- `src/main/java/trep/exampleagobaddon/ExampleAgobAddonMod.java`
- `src/main/java/trep/exampleagobaddon/ExampleArmorMaterials.java`
- `src/main/java/trep/exampleagobaddon/ExampleItems.java`
- `src/main/resources/fabric.mod.json`
- `src/main/resources/data/exampleagobaddon/agob/troops/dothraki_riders.json`
- `src/main/resources/data/exampleagobaddon/agob/quests/khalasar_supplies.json`
- `src/main/resources/data/exampleagobaddon/blacksmith_groups/dothraki/plates.json`
- `src/main/resources/data/exampleagobaddon/recipe/dothraki_plate.json`
- `src/main/resources/data/exampleagobaddon/recipe/dothraki_chestplate.json`
- `src/main/resources/data/exampleagobaddon/recipe/dothraki_leggings.json`
- `src/main/resources/data/exampleagobaddon/recipe/dothraki_boots.json`
- `src/main/resources/assets/exampleagobaddon/...`

## Local Setup

This sample is pinned to AGOB `1.23.6-beta` from Modrinth and targets the same Minecraft and Fabric stack AGOB uses:

- Minecraft `1.21.1`
- Fabric Loader `0.18.5`
- Fabric API `0.116.9+1.21.1`
- Yarn mappings `1.21.1+build.3`
- Loom `1.15-SNAPSHOT`
- AGOB Modrinth dependency `maven.modrinth:a-game-of-blocks:1.23.6-beta`

The build uses Modrinth Maven, so developers copying this repository do not need a neighboring `got-mod` checkout:

```bash
./gradlew build
```

If you want to target a different public AGOB release later, update `agob_version` in `gradle.properties`.

## How The Addon Uses The AGOB API

The addon is discovered through the Fabric entrypoint key:

```json
{
  "entrypoints": {
    "agob-addon": [
      "trep.exampleagobaddon.ExampleAgobAddon"
    ]
  }
}
```

The addon also uses a normal Fabric `main` entrypoint to register its own armor material and item instances. AGOB addon registration handles kingdoms, religions, and content hooks; standard Fabric registration still handles addon-owned items.

The entrypoint registers content in this order:

1. troop resources with `registerTroopResourceDirectory(...)`
2. one quest template with `registerQuestTemplateResource(...)`
3. one religion with `registerReligion(...)`
4. one custom Essos kingdom with `registerKingdom(...)`
5. one addon blacksmith kingdom tab with `registerBlacksmithKingdomTab(...)`
6. one `ReligionSelectionOption`
7. one primary `CharacterSelectionOption`

The example kingdom uses the custom `Great Shepherd` religion and `Culture.ESSOS`, so it represents an Essos-side faction addon while staying inside the supported addon surface and avoiding AGOB internals, mixins, or reflection hooks.

Because this example kingdom does not define its own AGOB castle set, its character options are configured to spawn specifically in the built-in `agob:dothraki_sea` and `agob:dothraki_sea_steppe` biomes in the `iceandfire` dimension. That keeps the faction usable in world creation without requiring custom worldgen first.

The example also opts the kingdom into the blacksmith furnace Addon section. In AGOB builds that include the addon blacksmith tab API, the furnace screen shows `Westeros | Essos | Addon`, and Dothraki recipes appear under the Addon section through the kingdom id registered in code plus data-driven group and recipe files.

## Registered Example Content

- Religion id: `exampleagobaddon:great_shepherd`
- Kingdom id: `exampleagobaddon:dothraki`
- Kingdom culture: `ESSOS`
- Kingdom religion: `Great Shepherd`
- Religion option id: `exampleagobaddon:great_shepherd`
- Primary character option id: `exampleagobaddon:house_drogo`
- Additional character option ids: `exampleagobaddon:house_jhaqo`, `exampleagobaddon:house_pono`
- Troop file: `dothraki_riders.json`
- Quest file: `khalasar_supplies.json`
- Blacksmith group file: `blacksmith_groups/dothraki/plates.json`
- Blacksmith recipe files: `recipe/dothraki_plate.json`, `recipe/dothraki_chestplate.json`, `recipe/dothraki_leggings.json`, `recipe/dothraki_boots.json`
- Addon item ids: `exampleagobaddon:plate_dothraki`, `exampleagobaddon:dothraki_chestplate`, `exampleagobaddon:dothraki_leggings`, `exampleagobaddon:dothraki_boots`

The troop JSON follows AGOB's dynamic troop schema and includes:

- a namespaced `kingdom_id`
- one `camp_site`
- one cavalry troop entry with equipment, stats, traits, and a matching skin texture id

The quest JSON follows AGOB's current quest template schema and includes:

- a namespaced quest template id
- one allowed NPC role
- one allowed kingdom id
- at least one reward entry

The character example includes three lore-inspired Dothraki options:

- `Drogo` as the primary option, representing Khal Drogo's khalasar
- `Jhaqo`
- `Pono`

The starting attributes are also written in the same style as AGOB's built-in kingdom options: every house defines all seven skill attributes with kingdom-scale values rather than sparse low placeholder numbers.

All three Dothraki character options now use the addon-owned Dothraki chestplate, leggings, and boots, with no helmet, plus a bow and an iron sword. The troop example mirrors that same setup.

The addon blacksmith integration mirrors that loadout by adding a `plates` group for the Dothraki kingdom plus a small item progression:

- `plate_dothraki` is forged as an intermediate plate item
- the Dothraki chestplate, leggings, and boots are then crafted from those plates

## Extension Notes

- Keep all ids under your own mod namespace.
- Register kingdoms before character options that point at them.
- Register addon blacksmith tabs in code before expecting those kingdoms to appear under the furnace Addon section.
- Register religions before kingdoms or religion selection options that use them.
- Keep troop ids globally unique across AGOB and other addons.
- Add more troop packs by placing more `.json` files directly inside the registered troop directory.
- Add more quest templates by creating more files under `data/<modid>/agob/quests` and registering each file explicitly.
- Add more blacksmith groups under `data/<modid>/blacksmith_groups/<kingdom path>/`.
- Add more blacksmith recipes under `data/<modid>/recipe/` using your addon kingdom id in the `kingdom` field.

## Release Note

The blacksmith Addon tab example depends on an AGOB build that includes the public `registerBlacksmithKingdomTab(...)` addon API and the matching furnace screen support in the main mod. If you are still targeting an older public Modrinth AGOB build, update to the first release that contains that API before expecting this example to compile and run standalone.

## Intentional Limits

This example does not use AGOB internal registries, networking classes, village systems, or castle-generation internals. It is meant to be a clean public-API reference that addon developers can duplicate safely.
