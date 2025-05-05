package top.offsetmonkey538.rainbowwood.util;

public final class TintedRenderContext {
    private TintedRenderContext() {

    }

    public static final ThreadLocal<Integer> color = new ThreadLocal<>();

    public static void contextualize(Runnable renderCall, Integer color) {
        TintedRenderContext.color.set(color);
        renderCall.run();
        TintedRenderContext.color.remove();
    }
}
