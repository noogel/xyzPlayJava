package noogel.xyz.grammar.config;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

public class ClassToInstanceMapDemo {

    private static ClassToInstanceMap<AbstractCfg> CFGS = MutableClassToInstanceMap.create();

    static {
        CFGS.putInstance(AbaCfg.class, new AbaCfg());
        CFGS.putInstance(AbbCfg.class, new AbbCfg());
        CFGS.putInstance(AbcCfg.class, new AbcCfg());
    }

    public static void main(String[] args) {
        System.out.println(CFGS);
    }
}
