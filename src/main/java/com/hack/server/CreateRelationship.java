package com.hack.server;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * Created by inderbir.singh on 31/08/14.
 */
public class CreateRelationship {

    public static String addRelationship(String startNodeURI,
                                         String endNodeURI,
                                         String relationshipType,
                                         String jsonAttributes){
        String output = null;
        String location = null;
        try{
            String fromUrl = startNodeURI + "/relationships";
            System.out.println("from url : " + fromUrl);

            String relationshipJson = generateJsonRelationship( endNodeURI,
                    relationshipType,
                    jsonAttributes );

            System.out.println("relationshipJson : " + relationshipJson);

            HttpClient client = new HttpClient();
            PostMethod mPost = new PostMethod(fromUrl);

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
            StringRequestEntity requestEntity = new StringRequestEntity(relationshipJson,
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

    private static String generateJsonRelationship(String endNodeURL,
                                                   String relationshipType,
                                                   String ... jsonAttributes) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"to\" : \"");
        sb.append(endNodeURL);
        sb.append("\", ");

        sb.append("\"type\" : \"");
        sb.append(relationshipType);
        if(jsonAttributes == null || jsonAttributes.length < 1) {
            sb.append("\"");
        } else {
            sb.append("\", \"data\" : ");
            for(int i = 0; i < jsonAttributes.length; i++) {
                sb.append(jsonAttributes[i]);
                if(i < jsonAttributes.length -1) { // Miss off the final comma
                    sb.append(", ");
                }
            }
        }

        sb.append(" }");
        return sb.toString();
    }
}
