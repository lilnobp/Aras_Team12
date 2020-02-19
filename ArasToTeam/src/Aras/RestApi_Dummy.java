package Aras;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestApi_Dummy {
    private static HttpURLConnection con;
    private static String getArasAcessToken;
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		var url = "http://localhost/InnovatorServer/oauthserver/connect/token";
        var urlParameters = "grant_type=password&scope=Innovator&client_id=IOMApp&username=admin&password=607920B64FE136F9AB2389E371852AF2&database=InnovatorSolutions";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String fullresult = content.toString(); 
            
            
            String[] arrOfStr = fullresult.split("[,:]");
            for (int i=0;i<3;i++) {
            	if(i==1) {
            		getArasAcessToken=arrOfStr[i];
            	}
            	
            }
            System.out.println(getArasAcessToken);
            

        } finally {

            con.disconnect();
        }
    }
	}
	
