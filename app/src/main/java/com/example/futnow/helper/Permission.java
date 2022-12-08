package com.example.futnow.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    public static boolean validatePermissions(String[] necessaryPermissions, Activity activity, int requestCode){
        if (Build.VERSION.SDK_INT > 23) {
            List<String> listpermission = new ArrayList<>();

            // percorre as permissões passadas verificando uma a uma se já tem a permissao liberada
            for ( String permission : necessaryPermissions ){
                Boolean hasPermission = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if ( !hasPermission ) listpermission.add(permission);
            }

            if ( listpermission.isEmpty() ) return true;

            String[] newPermissions = new String[ listpermission.size() ];
            listpermission.toArray( newPermissions );

            // solicita permissão
            ActivityCompat.requestPermissions(activity, newPermissions, requestCode );
        }

        return true;
    }
}