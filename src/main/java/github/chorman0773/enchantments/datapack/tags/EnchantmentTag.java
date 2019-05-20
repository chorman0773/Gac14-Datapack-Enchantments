package github.chorman0773.enchantments.datapack.tags;

import java.util.Collection;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class EnchantmentTag extends Tag<Enchantment> {

	public EnchantmentTag(ResourceLocation resourceLocationIn, Collection<ITagEntry<Enchantment>> entriesIn,
			boolean preserveOrder) {
		super(resourceLocationIn, entriesIn, preserveOrder);
		// TODO Auto-generated constructor stub
	}

	public EnchantmentTag(ResourceLocation resourceLocationIn) {
		super(resourceLocationIn);
		// TODO Auto-generated constructor stub
	}

}
