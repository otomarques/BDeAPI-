package gws.api.aplication;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class test {

    @Test
    public void test(){
        double numero = 10000d;

        if (numero >= 1000d){
            System.out.println("ultrapassou");
        }
    }
    @Test
    public void test2(){

        int[] numeros = {10,1,5,4,3};
        for (int numero = 0; numero <= numeros.length ; numero++){

            System.out.println(numero+" = numero ");
        }
    }
}
