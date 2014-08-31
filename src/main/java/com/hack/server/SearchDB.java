package com.hack.server;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * Created by inderbir.singh on 31/08/14.
 */
public class SearchDB {

    public static String searchDatabase(String nodeURI, String relationShip){
        String output = null;

        try{

            TraversalDescription t = new TraversalDescription();
            t.setOrder( TraversalDescription.DEPTH_FIRST );
            t.setUniqueness( TraversalDescription.NODE );
            t.setMaxDepth( 10 );
            t.setReturnFilter( TraversalDescription.ALL );
            t.setRelationships( new Relationship( relationShip, Relationship.OUT ) );

            System.out.println(t.toString());
            HttpClient client = new HttpClient();
            PostMethod mPost = new PostMethod(nodeURI+"/traverse/node");

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
            StringRequestEntity requestEntity = new StringRequestEntity(t.toJson(),
                    "application/json",
                    "UTF-8");
            mPost.setRequestEntity(requestEntity);
            int satus = client.executeMethod(mPost);
            output = mPost.getResponseBodyAsString( );
            mPost.releaseConnection( );
            System.out.println("satus : " + satus);
            System.out.println("output : " + output);
        }catch(Exception e){
            System.out.println("Exception in creating node in neo4j : " + e);
        }

        return output;
    }


}
