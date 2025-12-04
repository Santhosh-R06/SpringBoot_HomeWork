package com.CodeShuttle.module1.CakeBaker.impl;

import com.CodeShuttle.module1.CakeBaker.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
@Qualifier("SyrupStrawberry")
public class SyrupStrawberry implements Syrup {

    @Override
    public void getSyrupType() {
        System.out.println("Adding Strawberry Syrup...");
    }
}
