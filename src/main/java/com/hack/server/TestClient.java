package com.hack.server;

import com.google.gson.Gson;

/**
 * Created by inderbir.singh on 31/08/14.
 */
public class TestClient {

    static Gson gson = new Gson();

    public void test() {
        String actorNodeUrl = CreateNode.createNode();
        System.out.println("Actor Node URL :" + actorNodeUrl);
        System.out.println("Adding property to this node actor");
        AddPropertyToNode.addProperty(actorNodeUrl, "label", "actor");
        AddPropertyToNode.addProperty(actorNodeUrl, "name", "SRK");

        String movieNode = CreateNode.createNode();
        System.out.println("Movie node URL :" + movieNode);
        AddPropertyToNode.addProperty(movieNode, "label", "movie");
        AddPropertyToNode.addProperty(movieNode, "name", "DDLJ");


        Attributes attributes = new Attributes();
        attributes.setMoney("1000");
        String relationShipType = "ACTED_IN";
        String relationShipurl = CreateRelationship.addRelationship(actorNodeUrl, movieNode, relationShipType, gson.toJson(attributes));
        System.out.println("Relationship URL "+ relationShipurl);

       AddPropertyToRelationShip.addPropertyToRelation(relationShipurl, "rating", "5/5");

       String output = SearchDB.searchDatabase(actorNodeUrl, relationShipType);
        System.out.println("Search output = " + output);

    }

    public static void main(String[] args) {
        TestClient testClient = new TestClient();
        testClient.test();


    }

    public class Attributes {
        private String money;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
