package jacobg5.japi;

import com.mojang.brigadier.Command;
import eu.midnightdust.lib.config.MidnightConfig;
import jacobg5.japi.compat.apoli.JApoli;
import jacobg5.japi.component.JComponents;
import jacobg5.japi.networking.s2c.FloatingItemPacket;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.Block;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JAPI extends JMod {
	public static final String MOD_ID = "japi";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID.toUpperCase());
    protected static boolean started = false;

    public static final Supplier<ItemStack> DefaultGroupIcon = () -> {
        ItemStack stack = new ItemStack(Items.EMERALD_BLOCK);
        stack.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true).build());
        return stack;
    };

    @Override
    public String ModID() {
        return MOD_ID;
    }

    @Override
    public Logger Logger() {
        return LOGGER;
    }

    public static void debugLog(Consumer<Logger> action) {
        if (Config.logDebug) action.accept(LOGGER);
    }

    @Override
	public void init() {
		LOGGER.info("Loading Jacob's API");
        addModule("components", JComponents::new);
        addModule("apoli_compat", JApoli::new, JLoaderData.APOLI);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
                CommandManager.literal("japi-test")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ctx -> {
                    if (ctx.getSource().isExecutedByPlayer()) {
                        ServerPlayerEntity player = ctx.getSource().getPlayer();
                    }
                    return Command.SINGLE_SUCCESS;
                })));

        PayloadTypeRegistry.playS2C().register(FloatingItemPacket.PACKET_ID, FloatingItemPacket.PACKET_CODEC);
	}

    protected static void first() {
        if (started) return;
        started = true;
        MidnightConfig.init("japi", Config.class);
    }

    /**
     * {@return creates a new identifier using japi as the namespace}
     *
     * @param path path used for the identifier
     */
    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static class Config extends MidnightConfig {
        public static final String DEVELOPER = "developer";

        @Entry(category = DEVELOPER) public static boolean logDebug = false;
        @Entry(category = DEVELOPER) public static boolean createModTabs = false;
    }

    public static Optional<TagKey<Block>> getLegacyMiningLevel(int miningLevel) {
        return switch (miningLevel) {
            case 1 -> Optional.of(BlockTags.NEEDS_STONE_TOOL);
            case 2 -> Optional.of(BlockTags.NEEDS_IRON_TOOL);
            case 3 -> Optional.of(BlockTags.NEEDS_DIAMOND_TOOL);
            case 4 -> Optional.of(TagKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla("needs_netherite_tool")));
            default -> Optional.empty();
        };
    }
}