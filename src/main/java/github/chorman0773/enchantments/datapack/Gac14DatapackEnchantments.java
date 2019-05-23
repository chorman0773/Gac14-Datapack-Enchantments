package github.chorman0773.enchantments.datapack;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.tags.NetworkTagCollection;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("gac14-datapack-enchantments")
public class Gac14DatapackEnchantments
{
	
	@SuppressWarnings("deprecation")
	public static final TagCollection<Enchantment> EnchantmentTags = new NetworkTagCollection<Enchantment>(IRegistry.field_212628_q,"tags/enchantment","enchantment");
	
	
    public Gac14DatapackEnchantments() {
    	MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
    }
    
    public void serverStarting(FMLServerStartingEvent event) {
    	event.getServer().getResourceManager().addReloadListener(EnchantmentTags::reload);
    }
}
