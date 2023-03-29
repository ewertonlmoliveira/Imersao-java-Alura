import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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

        var diretorio = new File("saida/");
        diretorio.mkdir();

        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme: listaDeFilmes
             ) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            ///"\u001B[1m Título: \u001B[m%s%n",

            InputStream InputStream = new URL(urlImagem).openStream();
            //String nomeArquivo = "saida/" +  titulo + ".png";
            String nomeArquivo = "saida/" + titulo.replace(":", "-")  + ".png";

            geradora.cria(InputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();

            //System.out.println(filme.get("image"));
            //double roundedRating = Double.parseDouble(filme.get("imDbRating"));
            //String starUnicode = "\u2B50";
            //for (int i = 1; i < roundedRating; i++) {
                //System.out.print(starUnicode);
            //}
            //System.out.println("["+roundedRating+"]");
            //System.out.println();
        }
    }
}