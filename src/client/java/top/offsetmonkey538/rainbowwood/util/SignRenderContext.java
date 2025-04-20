package top.offsetmonkey538.rainbowwood.util;

public final class SignRenderContext {
    private SignRenderContext() {

    }

    public static ThreadLocal<Integer> color = new ThreadLocal<>();

    public static void contextualize(Runnable renderCall, Integer color) {
        SignRenderContext.color.set(color);
        renderCall.run();
        SignRenderContext.color.remove();
    }
}
