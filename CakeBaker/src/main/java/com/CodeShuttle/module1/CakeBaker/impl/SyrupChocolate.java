package com.CodeShuttle.module1.CakeBaker.impl;

import com.CodeShuttle.module1.CakeBaker.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SyrupChocolate")
public class SyrupChocolate implements Syrup {

    @Override
    public void getSyrupType() {
        System.out.println("Adding Chocolate Syrup...");
    }
}
