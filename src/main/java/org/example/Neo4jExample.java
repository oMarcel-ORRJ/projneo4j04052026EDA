package org.example;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class Neo4jExample implements AutoCloseable {

    private final Driver driver;

    public Neo4jExample(String uri, String user, String password, Driver driver) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        // implementar
    }
}
