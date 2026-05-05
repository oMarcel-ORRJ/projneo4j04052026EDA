package org.example;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class Neo4jExample implements AutoCloseable {

    private final Driver driver;

    public Neo4jExample(String uri, String user, String password) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void criarRelacoes() {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("""
                        CREATE (a:Pessoa {nome: 'André'})
                        CREATE (b:Pessoa {nome: 'Joao'})
                        CREATE (c:Pessoa {nome: 'Maria'})
                        CREATE (a)-[:AMIGO]->(b)
                        CREATE (a)-[:AMIGO]->(c)
                        """);
                return null;
            });
        }
    }

    public static void main(String[] args) {
        try(Neo4jExample app = new Neo4jExample(
                "bolt://localhost:7687",
                "neo4j",
                "senha123"
        )){
            app.criarRelacoes();
        }
    }
}
