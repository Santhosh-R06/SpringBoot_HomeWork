package com.CodeShuttle.module1.CakeBaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CakeBaker implements CommandLineRunner {

    //    @Autowired
    final Frosting chocolateFrostingObj;
    //    @Autowired
    final Frosting strawberryFrostingObj;
    //    @Autowired
    final Syrup chocolateSyrupObj;
    //    @Autowired
    final Syrup strawberrySyrupObj;

    public CakeBaker(@Qualifier("FrostingChocolate") Frosting chocolateFrostingObj,
                     @Qualifier("FrostingStrawberry") Frosting strawberryFrostingObj,
                     @Qualifier("SyrupChocolate") Syrup chocolateSyrupObj,
                     @Qualifier("SyrupStrawberry") Syrup strawberrySyrupObj) {
        this.chocolateFrostingObj = chocolateFrostingObj;
        this.strawberryFrostingObj = strawberryFrostingObj;
        this.chocolateSyrupObj = chocolateSyrupObj;
        this.strawberrySyrupObj = strawberrySyrupObj;
    }

    /*
    void bakeCake(int cakeType){

        System.out.println("Baking started...");

        Frosting selectedFrosting = null;
        Syrup selectedSyrup = null;

        switch (cakeType){
            case 1:
                selectedFrosting = this.chocolateFrostingObj;
                selectedSyrup = this.chocolateSyrupObj;
                break;

            case 2:
                selectedFrosting = this.chocolateFrostingObj;
                selectedSyrup = this.strawberrySyrupObj;
                break;

            case 3:
                selectedFrosting = this.strawberryFrostingObj;
                selectedSyrup = this.chocolateSyrupObj;
                break;

            case 4:
                selectedFrosting = this.strawberryFrostingObj;
                selectedSyrup = this.strawberrySyrupObj;
                break;
        }

        selectedFrosting.getFrostingType();
        selectedSyrup.getSyrupType();
        System.out.println("Your Cake is ready!!");
        */
    void bakeCake1(){
        chocolateFrostingObj.getFrostingType();
        strawberrySyrupObj.getSyrupType();
    }

    void bakeCake2(){
        strawberryFrostingObj.getFrostingType();
        chocolateSyrupObj.getSyrupType();
    }

    public static void main(String[] args) {
        SpringApplication.run(CakeBaker.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the type of Cake");
//        System.out.println("1 for Chocolate Frosting with Chocolate Syrup");
//        System.out.println("2 for Chocolate Frosting with Strawberry Syrup");
//        System.out.println("3 for Strawberry Frosting with Chocolate Syrup");
//        System.out.println("4 for Strawberry Frosting with Strawberry Syrup");
//        int CakeType = scanner.nextInt();
//        bakeCake(CakeType);
        System.out.println("1st Cake Baking started...");
        bakeCake1();
        System.out.println("Your 1st Cake is ready!!");
        System.out.println();
        System.out.println("2nd Cake Baking started...");
        bakeCake2();
        System.out.println("Your 2nd Cake is ready!!");
    }
}
