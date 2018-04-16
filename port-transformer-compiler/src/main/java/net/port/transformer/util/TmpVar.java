package net.port.transformer.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyongsheng on 2018/4/16.
 */
public class TmpVar {

    private Map<String, Integer> tmpVarIndices = new HashMap<>();

    public String getTmpVar(String prefix) {
        if (!prefix.startsWith("_"))
            throw new IllegalArgumentException("tmp var prefix should start with _");
        if (prefix.startsWith("__"))
            throw new IllegalArgumentException("tmp var prefix cannot use with __");
        Integer index = tmpVarIndices.get(prefix);
        if (index == null) index = 0;
        String result = buildTmpVar(prefix, index);
        tmpVarIndices.put(prefix, index + 1);
        return result;
    }

    private String buildTmpVar(String prefix, int index) {
        return prefix + (index == 0 ? "" : "_" + index);

    }
}
