package tfar.proxies.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityProxyItem extends BlockItem {
    public EntityProxyItem(Block block, Properties properties) {
        super(block,properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.hasTag() && stack.getTag().contains("proxy")) {
            int[] xyz = stack.getTag().getIntArray("proxy");
            tooltip.add(new StringTextComponent("X: "+xyz[0]));
            tooltip.add(new StringTextComponent("Y: "+xyz[1]));
            tooltip.add(new StringTextComponent("Z: "+xyz[2]));
        }
    }
}
