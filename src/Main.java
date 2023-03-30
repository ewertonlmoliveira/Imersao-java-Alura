import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        var diretorio = new File("saida2/");
        diretorio.mkdir();

        List<Conteudo> conteudos = extrator.extraiConteudos(json);


        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream InputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida2/" + conteudo.titulo().replace(":", "-") + ".png";

            geradora.cria(InputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();

            //System.out.println(conteudo.get("image"));
            //double roundedRating = Double.parseDouble(conteudo.get("imDbRating"));
            //String starUnicode = "\u2B50";
            //for (int a = 1; a < roundedRating; a++) {
            //System.out.print(starUnicode);
            //}
            //System.out.println("[" + roundedRating + "]");
            //System.out.println();
        }
    }
}