package com.writer.aspiring.unittestingdemo;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by pkasaragod on 2/17/2017.
 */

public class UserCredentials {

    public final String un[] = {"Walter","Olivia","Lincoln","Astrid"};
    public final String pw[] = {"Bishop","Dunham","Lee","Farnsworth"};

    public boolean validCredentials(String useNam, String passWrd) {
        for (int i=0;i<un.length; i++){
            if (useNam.equals(un[i]))
                if (passWrd.equals(pw[i]))
                    return true;
        }

        return false;

    }

    }


