package trep.exampleagobaddon;

import net.fabricmc.api.ModInitializer;

public final class ExampleAgobAddonMod implements ModInitializer {
	@Override
	public void onInitialize() {
		ExampleItems.initialize();
	}
}
