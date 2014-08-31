package com.hack.server;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * Created by inderbir.singh on 31/08/14.
 */
public class AddPropertyToRelationShip {

    public static void addPropertyToRelation( String relationshipUri,
                                              String propertyName,
                                              String propertyValue ){

        String output = null;

        try{
            String relPropUrl = relationshipUri + "/properties";
            HttpClient client = new HttpClient();
            PutMethod mPut = new PutMethod(relPropUrl);

            /**
             * set headers
             */
            Header mtHeader = new Header();
            mtHeader.setName("content-type");
            mtHeader.setValue("application/json");
            mtHeader.setName("accept");
            mtHeader.setValue("application/json");
            mPut.addRequestHeader(mtHeader);

            /**
             * set json payload
             */
            String jsonString = toJsonNameValuePairCollection(propertyName,propertyValue );
            StringRequestEntity requestEntity = new StringRequestEntity(jsonString,
                    "application/json",
                    "UTF-8");
            mPut.setRequestEntity(requestEntity);
            int satus = client.executeMethod(mPut);
            output = mPut.getResponseBodyAsString( );

            mPut.releaseConnection( );
            System.out.println("satus : " + satus);
            System.out.println("output : " + output);
        }catch(Exception e){
            System.out.println("Exception in creating node in neo4j : " + e);
        }

    }

    private static String toJsonNameValuePairCollection(String name, String value) {
        return String.format("{ \"%s\" : \"%s\" }", name, value);
    }
}
