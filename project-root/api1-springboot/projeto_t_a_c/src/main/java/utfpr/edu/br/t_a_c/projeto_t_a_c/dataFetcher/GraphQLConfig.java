package utfpr.edu.br.t_a_c.projeto_t_a_c.dataFetcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLSchema graphQLSchema(PessoaDataFetcher pessoaDataFetcher) {
        try {

            return SchemaParser.newParser()
                    .files("graphql/schema.graphqls")
                    .resolvers(pessoaDataFetcher)
                    .build()
                    .makeExecutableSchema();
        } catch (Exception e) {
            System.err.println("Erro ao criar o schema GraphQL: " + e.getMessage());
            throw new RuntimeException("Erro ao criar o schema GraphQL", e);
        }
    }

}
