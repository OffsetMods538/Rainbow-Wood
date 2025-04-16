package top.offsetmonkey538.rainbowwood.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;

// array index is block position (relative to chunk so 0 to 15 for x,z and -2032 to 2032 for y) and value is just the rgb value
public record ModBlockTintAttachedData(int[] tints) {
    public static final ModBlockTintAttachedData DEFAULT = new ModBlockTintAttachedData(new int[] {});

    public static Codec<ModBlockTintAttachedData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.listOf().fieldOf("tints").forGetter(modBlockTintAttachedData -> Arrays.stream(modBlockTintAttachedData.tints).boxed().toList())
    ).apply(instance, integers -> new ModBlockTintAttachedData(integers.stream().mapToInt(Integer::intValue).toArray())));
    public static PacketCodec<ByteBuf, ModBlockTintAttachedData> PACKET_CODEC = PacketCodecs.codec(CODEC);

    public ModBlockTintAttachedData addBlockTint(final BlockPos pos, final int value) {
        return addBlockTint(pos.getX(), pos.getY(), pos.getZ(), value);
    }
    public ModBlockTintAttachedData addBlockTint(final int x, final int y, final int z, final int value) {
        final int encodedPos = encodePos(x, y, z);
        final int[] newTints = new int[Math.max(encodedPos + 1, tints.length)];

        System.arraycopy(tints, 0, newTints, 0, tints.length);
        newTints[encodedPos] = value;

        return new ModBlockTintAttachedData(newTints);
    }

    public int getTint(final BlockPos pos) {
        return getTint(pos.getX(), pos.getY(), pos.getZ());
    }
    public int getTint(final int x, final int y, final int z) {
        final int encodedPos = encodePos(x, y, z);
        if (tints.length < encodedPos) return 0;
        return tints[encodedPos];
    }

    // No method for removing stuff but I guess that's fine?

    public static int encodePos(final BlockPos pos) {
        return encodePos(pos.getX(), pos.getY(), pos.getZ());
    }
    public static int encodePos(final int x, final int y, final int z) {
        // x and z are both 4 bits and y is 12 bits. All fits in 32bit int with 12 bits technically left over, but given to y.
        //  Repeated in those 12 bits is the sign for y
        //  000000000000yyyyyyyyyyyyzzzzxxxx

        final int xEncoded = x;
        final int zEncoded = z << 4;
        final int yEncoded = y << 8;
        return (xEncoded | zEncoded | yEncoded) & 0b00000000000011111111111111111111;
    }

    public static BlockPos decodePos(final int posEncoded) {
        final int x = posEncoded & 15;
        final int z = (posEncoded >> 4) & 15;
        final int y = posEncoded >> 8;
        return new BlockPos(x, y, z);
    }
 }
