package net.port.transformer;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class PortTransformerBuilder<T> {
    private static final String IMPL_SUFFIX = "_Impl";
    private final Class<T> mTransformerClass;
    private final Context mContext;

    public PortTransformerBuilder(Context context, Class<T> cls) {
        mContext = context;
        mTransformerClass = cls;
    }

    public static <T> PortTransformerBuilder<T> newBuilder(
            @NonNull Context context, @NonNull Class<T> cls) {
        return new PortTransformerBuilder<>(context, cls);
    }

    public T build() {
        return getGeneratedImplementation(mTransformerClass, IMPL_SUFFIX);
    }

    @NonNull
    static <T, C> T getGeneratedImplementation(Class<C> cls, String suffix) {
        final String fullPackage = cls.getPackage().getName();
        String name = cls.getCanonicalName();
        final String postPackageName = fullPackage.isEmpty()
                ? name
                : (name.substring(fullPackage.length() + 1));
        final String implName = postPackageName.replace('.', '_') + suffix;
        try {

            @SuppressWarnings("unchecked")
            final Class<T> aClass = (Class<T>) Class.forName(
                    fullPackage.isEmpty() ? implName : fullPackage + "." + implName);
            return aClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find implementation for "
                    + cls.getCanonicalName() + ". " + implName + " does not exist");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access the constructor"
                    + cls.getCanonicalName());
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to create an instance of "
                    + cls.getCanonicalName());
        }
    }
}
