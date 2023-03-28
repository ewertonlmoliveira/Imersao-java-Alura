import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{

        // fazer uma conexão HTTPe buscar os top250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();


        // pegar só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme: listaDeFilmes
             ) {
            System.out.printf("\u001B[1m Título: \u001B[m%s%n", filme.get("title"));
            System.out.println(filme.get("image"));
            double roundedRating = Double.parseDouble(filme.get("imDbRating"));
            String starUnicode = "\u2B50";
            for (int i = 1; i < roundedRating; i++) {
                System.out.print(starUnicode);
            }
            System.out.println("["+roundedRating+"]");
            System.out.println();
        }
    }
}