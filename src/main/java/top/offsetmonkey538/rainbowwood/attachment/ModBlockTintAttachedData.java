package top.offsetmonkey538.rainbowwood.attachment;

import net.minecraft.util.math.BlockPos;

// array index is block position (relative to chunk so 0 to 15 for x,z and -2032 to 2032 for y) and value is just the rgb value
public record ModBlockTintAttachedData(int[] tints) {
    //public static Codec<ModBlockTintAttachedData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    //        Codec.INT.listOf().fieldOf("tints").forGetter(modBlockTintAttachedData -> Arrays.stream(modBlockTintAttachedData.tints).boxed().toList())
    //).apply(instance, integers -> new ModBlockTintAttachedData((int[]) (Object) integers.toArray())));
    //public static PacketCodec<ByteBuf, ModBlockTintAttachedData> PACKET_CODEC = PacketCodecs.codec(CODEC);

    //public ModBlockTintAttachedData addBlockTint(final BlockPos pos, final int value) {

    //}

    //public ModBlockTintAttachedData removeBlockTint(final BlockPos pos) {

    //}

    public static int encodePos(final BlockPos pos) {
        // x and z are both 4 bits and y is 12 bits. All fits in 32bit int with 12 bits technically left over, but given to y.
        //  Somewhere in those 12 bits is the sign for y
        //  000000000000yyyyyyyyyyyyzzzzxxxx

        final int xEncoded = pos.getX();
        final int zEncoded = pos.getZ() << 4;
        final int yEncoded = pos.getY() << 8;
        return xEncoded | zEncoded | yEncoded;
    }

    public static BlockPos decodePos(final int posEncoded) {
        final int x = posEncoded & 15;
        final int z = (posEncoded >> 4) & 15;
        final int y = posEncoded >> 8;
        return new BlockPos(x, y, z);
    }
 }
