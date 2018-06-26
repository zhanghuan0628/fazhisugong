package com.ffxl.hi.util.factory;

import com.ffxl.hi.util.factory.design.SuGongInfos;

public class SuGongFactory implements UserProviderInterface {

    @Override
    public UserInterface produce() {
        return new SuGongInfos();
    }
    
}
