# Example AGOB Addon

This repository is a complete Fabric addon example for `A Game of Blocks` using the documented AGOB addon API. It is intentionally small and copyable: one addon entrypoint, one troop pack, one quest template, one custom religion, one custom Essos kingdom, and three kingdom character options with one primary option.

## What It Demonstrates

- a custom Fabric `agob-addon` entrypoint
- `trep.got.api.AgobAddonEntrypoint` registration flow
- troop JSON loaded from `data/exampleagobaddon/agob/troops`
- a quest template loaded from `data/exampleagobaddon/agob/quests`
- a custom religion and a custom Essos kingdom registered through the public AGOB API
- one `ReligionSelectionOption`
- one primary `CharacterSelectionOption`
- placeholder textures whose resource ids match the registered content

## Project Layout

- `src/main/java/trep/exampleagobaddon/ExampleAgobAddon.java`
- `src/main/resources/fabric.mod.json`
- `src/main/resources/data/exampleagobaddon/agob/troops/dothraki_riders.json`
- `src/main/resources/data/exampleagobaddon/agob/quests/khalasar_supplies.json`
- `src/main/resources/assets/exampleagobaddon/...`

## Local Setup

This sample is pinned to AGOB `1.23.5-beta` from Modrinth and targets the same Minecraft and Fabric stack AGOB uses:

- Minecraft `1.21.1`
- Fabric Loader `0.18.5`
- Fabric API `0.116.9+1.21.1`
- Yarn mappings `1.21.1+build.3`
- Loom `1.15-SNAPSHOT`
- AGOB Modrinth dependency `maven.modrinth:a-game-of-blocks:1.23.5-beta`

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

The entrypoint registers content in this order:

1. troop resources with `registerTroopResourceDirectory(...)`
2. one quest template with `registerQuestTemplateResource(...)`
3. one religion with `registerReligion(...)`
4. one custom Essos kingdom with `registerKingdom(...)`
5. one `ReligionSelectionOption`
6. one primary `CharacterSelectionOption`

The example kingdom uses the custom `Great Shepherd` religion and `Culture.ESSOS`, so it represents an Essos-side faction addon while staying inside the supported addon surface and avoiding AGOB internals, mixins, or reflection hooks.

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

## Extension Notes

- Keep all ids under your own mod namespace.
- Register kingdoms before character options that point at them.
- Register religions before kingdoms or religion selection options that use them.
- Keep troop ids globally unique across AGOB and other addons.
- Add more troop packs by placing more `.json` files directly inside the registered troop directory.
- Add more quest templates by creating more files under `data/<modid>/agob/quests` and registering each file explicitly.

## Intentional Limits

This example does not use AGOB internal registries, networking classes, village systems, or castle-generation internals. It is meant to be a clean public-API reference that addon developers can duplicate safely.
