package top.offsetmonkey538.rainbowwood.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

import static top.offsetmonkey538.rainbowwood.RainbowWood.id;

public class ModAttachmentTypes {

    public static final AttachmentType<ModBlockTintAttachedData> BLOCK_TINT_TYPE = AttachmentRegistry.create(
            id("block_tint_type"),
            builder -> builder
                    .initializer(() -> ModBlockTintAttachedData.DEFAULT)
                    .persistent(ModBlockTintAttachedData.CODEC)
                    .syncWith(
                            ModBlockTintAttachedData.PACKET_CODEC,
                            AttachmentSyncPredicate.all()
                    )
    );
}
