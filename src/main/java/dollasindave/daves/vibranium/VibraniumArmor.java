package dollasindave.daves.vibranium;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class VibraniumArmor implements ArmorMaterial {
    private static final int[] BASE_DURABILITY =  new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {4, 6, 8, 4};

    //A is boots, B Leggings, C Chestplate, D helmet
    // For reference, Leather uses {1, 2, 3, 1}, and Diamond/Netherite {3, 6, 8, 3}

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 50;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
	public int getEnchantability() {
		return 15;
	}

    @Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
	}

    @Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(DavesVibranium.VIBRANIUM_INGOT);
	}

    @Override
	public String getName() {
		// Must be all lowercase
		return "vibranium";
	}

    @Override
	public float getToughness() {
		return 4F;
	}

    @Override
	public float getKnockbackResistance() {
		return .15F;
	}
}
