import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {


            //Url da API do Chuck Norris
            String apiURL = "https://api.chucknorris.io/jokes/random";
            //fazendo a Requisição GET para a API
            HttpURLConnection conexao = (HttpURLConnection) new URL(apiURL).openConnection();
            conexao.setRequestMethod("GET");

            //Lendo a resposta
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null){
                resposta.append(linha);

            }

            leitor.close();
            String piada = obterPiadaChuckNorris(resposta.toString());
            System.out.println("Piada"+ piada);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * *Método para extrair a piada do JSON retornardo pela API.
     * @param resposta Resposta da APi no formato String.
     * @return A piada extraída da resposta da API.
     */
    private static String obterPiadaChuckNorris(String resposta)  {
        //Extraindo a piada do JSON
        int inicioDoIndice = resposta.indexOf("\"value\":") + ("\"value\":".length());
        int fimDoIndice = resposta.lastIndexOf("\"");
        return resposta.substring(inicioDoIndice,fimDoIndice);
    }
}