package jacobg5.japi;

import net.fabricmc.loader.api.FabricLoader;

public class JLoaderData {
    // Common APIs
    /**
     * @see <a href="https://modrinth.com/mod/trinkets">Trinkets</a>
     */
    public static final boolean TRINKETS = contains("trinkets");

    /**
     * @see <a href="https://github.com/apace100/apoli">Apoli</a>
     */
    public static final boolean APOLI = contains("apoli");

    /**
     * @see <a href="https://modrinth.com/mod/pehkui">Pehkui</a>
     */
    public static final boolean PEHKUI = contains("pehkui");

    // Jacob Mods
    /**
     * @see <a href="https://modrinth.com/mod/commandit">Command It</a>
     */
    public static final boolean COMMANDIT = contains("commandit");

    /**
     * @see <a href="https://modrinth.com/mod/jacobs-weapons">Jacob's Weapons</a>
     */
    public static final boolean JWEAPONS = contains("jweapons");

    /**
     * @see <a href="https://modrinth.com/mod/vanilla-variants">Vanilla Variants</a>
     */
    public static final boolean VANILLA_VARIANTS = contains("vanillavariants");

    // Common Mods
    /**
     * @see <a href="https://modrinth.com/mod/supplementaries">Supplementaries</a>
     */
    public static final boolean SUPPLEMENTARIES = contains("supplementaries");

    /**
     * {@return Checks if another mod is loaded in the current instance}
     *
     * @param id identifier as defined in a mod's fabric.mod.json file
     */
    public static boolean contains(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }
}
