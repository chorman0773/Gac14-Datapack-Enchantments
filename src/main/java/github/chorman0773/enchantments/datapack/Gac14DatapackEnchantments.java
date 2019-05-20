package github.chorman0773.enchantments.datapack;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("gac14-datapack-enchantments")
public class Gac14DatapackEnchantments
{
    public Gac14DatapackEnchantments() {
    	MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
    }
    
    public void serverStarting(FMLServerStartingEvent event) {
    	
    }
}
