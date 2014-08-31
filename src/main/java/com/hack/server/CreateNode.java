package com.hack.server;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * Created by inderbir.singh on 31/08/14.
 */
public class CreateNode {

    public static String createNode(){
        String output = null;
        String location = null;
        try{
            String nodePointUrl = ServerStatus.SERVER_ROOT_URI + "/db/data/node";
            HttpClient client = new HttpClient();
            PostMethod mPost = new PostMethod(nodePointUrl);

            /**
             * set headers
             */
            Header mtHeader = new Header();
            mtHeader.setName("content-type");
            mtHeader.setValue("application/json");
            mtHeader.setName("accept");
            mtHeader.setValue("application/json");
            mPost.addRequestHeader(mtHeader);

            /**
             * set json payload
             */
            StringRequestEntity requestEntity = new StringRequestEntity("{}",
                    "application/json",
                    "UTF-8");
            mPost.setRequestEntity(requestEntity);
            int satus = client.executeMethod(mPost);
            output = mPost.getResponseBodyAsString( );
            Header locationHeader =  mPost.getResponseHeader("location");
            location = locationHeader.getValue();
            mPost.releaseConnection( );
            System.out.println("satus : " + satus);
            System.out.println("location : " + location);
            System.out.println("output : " + output);
        }catch(Exception e){
            System.out.println("Exception in creating node in neo4j : " + e);
        }

        return location;
    }

    public static void main(String[] args) {
     //   System.out.println(createNode());
    }
}
