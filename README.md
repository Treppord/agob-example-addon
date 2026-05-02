# Example AGOB Addon

This repository demonstrates how to build a Fabric addon mod against the AGOB addon API.

It shows four supported extension patterns:

- adding troop packs from JSON resources
- adding quest templates that merge with AGOB quests
- registering a custom religion and kingdom
- registering a matching character option for that kingdom

## What This Example Contains

- an `agob-addon` Fabric entrypoint
- one addon troop pack under `data/<modid>/agob/troops`
- one addon quest template under `data/<modid>/agob/quests`
- one registered religion
- one registered kingdom
- one registered character option
- one texture set referenced by the addon content

## AGOB API Entry Point

AGOB discovers addons through the Fabric entrypoint key:

```json
{
  "entrypoints": {
    "agob-addon": ["com.example.agobaddon.ExampleAgobAddon"]
  }
}
```

The entrypoint must implement `trep.got.api.AgobAddonEntrypoint`.

## Troop Pack Registration

Register a troop resource directory:

```java
context.registerTroopResourceDirectory("data/exampleagobaddon/agob/troops");
```

AGOB will load every `.json` file directly inside that folder.

## Quest Registration

Register each quest template file explicitly:

```java
context.registerQuestTemplateResource("data/exampleagobaddon/agob/quests/bronze_delivery.json");
```

## Faction Registration

The example also shows how to register:

- a `Religion`
- a `Kingdom`
- a `ReligionSelectionOption`
- a `CharacterSelectionOption`

That is the current supported path for creating a full custom faction addon without depending on AGOB internals.

## Important Limits

This example intentionally avoids private AGOB systems.

Do not depend on:

- AGOB networking payloads
- castle generation internals
- village internals
- internal registries outside the published API path

If you want to build on additional systems later, wait until AGOB explicitly documents them as public API.

## Development Notes

- Keep all ids namespaced to your addon mod id.
- Ensure troop ids and option ids are globally unique.
- Register kingdoms before character options that reference them.
- Register religions before religion options or kingdoms that reference them.
- If a kingdom exposes character options, provide exactly one primary option.
