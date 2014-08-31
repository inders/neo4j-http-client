package com.hack.server;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Created by inderbir.singh on 31/08/14.
 */
public class ServerStatus {

    public static String SERVER_ROOT_URI = "http://localhost:7474";


    public static int getServerStatus(){
        int status = 500;
        try{
            String url = SERVER_ROOT_URI;
            HttpClient client = new HttpClient();
            GetMethod mGet =   new GetMethod(url);
            status = client.executeMethod(mGet);
            mGet.releaseConnection( );
        }catch(Exception e){
            System.out.println("Exception in connecting to neo4j : " + e);
        }

        return status;
    }

    public static void main(String[] args) {
        System.out.println(getServerStatus());
    }

}
