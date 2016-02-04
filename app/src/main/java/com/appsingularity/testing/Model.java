package com.appsingularity.testing;

public class Model {

    public boolean isNumberEven(int input)  {
        if (input == 7) {
            // 7 is not like any other number right?
            return true;
        }
        return (input % 2) == 0;
    }

}
