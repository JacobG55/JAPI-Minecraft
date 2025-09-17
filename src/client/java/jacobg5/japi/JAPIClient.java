package jacobg5.japi;

import jacobg5.japi.item.JItem;
import jacobg5.japi.networking.s2c.FloatingItemPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.FoliageColors;

public class JAPIClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		JAPI.LOGGER.info("Hello Client world!");
		ClientPlayNetworking.registerGlobalReceiver(FloatingItemPacket.PACKET_ID, (payload, context) -> MinecraftClient.getInstance().gameRenderer.showFloatingItem(payload.stack()));
	}

	private void spawnEggColorProviders() {
		for (SpawnEggItem spawnEggItem : SpawnEggItem.getAll()) {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(spawnEggItem.getColor(tintIndex)), spawnEggItem);
		}
	}

    public static int jItemColorProvider(ItemStack stack, int tintIndex) {
        return ColorHelper.Argb.fullAlpha(JItem.getColor(stack.getItem(), tintIndex));
    }

    public static int foliageColorProvider(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex) {
        return world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor();
    }
}