package github.chorman0773.enchantments.datapack.tags;

import java.nio.file.Path;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;

public class EnchantmentTagProvider extends TagsProvider<Enchantment> {
	private DataGenerator gen;
	
	@SuppressWarnings("deprecation")
	public EnchantmentTagProvider(DataGenerator gen) {
		super(gen, IRegistry.field_212628_q);
		this.gen = gen;
		gen.addProvider(this);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "enchantments_tags";
	}

	@Override
	protected void registerTags() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setCollection(TagCollection<Enchantment> colectionIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Path makePath(ResourceLocation id) {
		return gen.getOutputFolder().resolve(id.getNamespace()).resolve("tags/enchantments");
	}

}
