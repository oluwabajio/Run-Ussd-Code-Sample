# Run-Ussd-Code-Sample
This code demonstrates how to create an app to run ussd codes.

1. Add ussd permission
 <uses-permission android:name="android.permission.CALL_PHONE"/>
 
 2.    
 
   //we want to remove the last # from the ussd code as we need to encode it. so *555# becomes *555
                    UssdCode = UssdCode.substring(0, UssdCode.length() - 1);

                    String UssdCodeNew = UssdCode + Uri.encode("#");

                    //request for permission
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                     ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                    } else {
                        //dial Ussd code
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + UssdCodeNew)));

                    }
