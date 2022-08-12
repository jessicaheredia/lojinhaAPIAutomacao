package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto.")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        // Confirgurando os dados da API Rest da lojinha;
        baseURI = "http://165.227.93.41";
        //port = 8080;
        basePath = "/lojinha-bugada";


        // Obter o token do usuario admin
        this.token = given()// dado que
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
            .when() //quando
                .post("/v2/login")
            .then() // então
                .extract()
                    .path("data.token");
    }


    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 nao e permitido.")
    public void testValidarLimitesZeradoProibidosValorProduto(){

        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        //status code retornado foi 422

         given()// dado que
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
                .when() //quando
                    .post("/v2/produtos")
                .then() // então
                    .assertThat()  //valide que
                        .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                              .statusCode(422);


    }
        @Test
        @DisplayName("Validar os limites proibidos do valor do produto maior que 7000,01 nao é permitido.")
        public void testValidarLimitesMaioresSeteMilProibidosValorProduto(){
        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        //status code retornado foi 422

            given()// dado que
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
                .when() //quando
                     .post("/v2/produtos")
                .then() // então
                        .assertThat()  //valide que
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }
}