package net.port.transformer.compiler.data;

import java.util.List;

/**
 * Created by zhongyongsheng on 2018/4/19.
 */
public class PortPairData {
    public List<PairData> pairDataList;

    public PortPairData(List<PairData> pairDataList) {
        this.pairDataList = pairDataList;
    }

    public static class PairData {
        public String key;
        public String value;

        public PairData(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
