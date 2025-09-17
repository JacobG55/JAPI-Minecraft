package jacobg5.japi;

import net.minecraft.util.Identifier;
import java.util.function.Supplier;

/**
 * Custom Module for a JAPI Mod
 * Use this as a container for registering a type of content to your mod to keep your workspace clean.
 * To add a module to your mod see {@link JMod#addModule(String, Supplier)}
 */
public abstract class JModModule {
    public Identifier identifier;
    public abstract void init(JMod mod);
}
