package com.example.specialentityspawner;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class XPSpawner extends Item{
    public XPSpawner(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {

        if (!pContext.getLevel().isClientSide()) {

            // Get the world as an object
            Level world = pContext.getLevel();

            // identify the block clicked from the current context
            BlockPos posClicked = pContext.getClickedPos().above();

            // Get player for optional use
            Player player = pContext.getPlayer();

            // This can be anything at all
            EntityType<?> entityType = EntityType.EXPERIENCE_ORB;
            Entity entity = entityType.create(world);

            // ensure entity isn't null
            assert entity != null;
            entity.setPos(posClicked.getX(), posClicked.getY(), posClicked.getZ());

            // Add the entity to the world
            world.addFreshEntity(entity);

            //send optional message to player if they aren't null
            assert player != null;
            player.sendSystemMessage(Component.literal("XP Orb spawned at (" + posClicked.getX() + "," + posClicked.getY() + "," + posClicked.getZ() + ")."));
        }

        return InteractionResult.SUCCESS;
    }
}
