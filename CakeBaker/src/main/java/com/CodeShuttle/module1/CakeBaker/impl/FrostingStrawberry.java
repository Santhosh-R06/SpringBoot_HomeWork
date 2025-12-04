package com.CodeShuttle.module1.CakeBaker.impl;

import com.CodeShuttle.module1.CakeBaker.Frosting;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
@Qualifier("FrostingStrawberry")
public class FrostingStrawberry implements Frosting {

    @Override
    public void getFrostingType() {
        System.out.println("Adding Strawberry Frosting...");
    }
}
