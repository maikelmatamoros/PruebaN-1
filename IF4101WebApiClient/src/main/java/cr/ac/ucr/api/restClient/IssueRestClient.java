package cr.ac.ucr.api.restClient;

import cr.ac.ucr.api.model.Issue;
import cr.ac.ucr.api.model.IssueDTO;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class IssueRestClient {

    private String endpoint = "https://localhost:44317/api/Issues/";

    RestTemplate restTemplate = this.getRestTemplate();

    public void callPostIssueAPI(Issue issue){

        IssueDTO issueDTO = new IssueDTO(
                issue.getReport_Number(),
                issue.getStatus(),
                issue.getRegister_Timestamp(),
                issue.getService_Id(),
                issue.isState(),
                issue.getCreation_Date(),
                issue.getModify_Date(),
                issue.getCreated_By(),
                issue.getModified_By()
        );

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<IssueDTO> issueResponse = restTemplate.postForEntity(endpoint, issueDTO, IssueDTO.class);
        System.out.print(issueResponse.getBody());
    }

    public RestTemplate getRestTemplate(){
        RestTemplate template = null;
        SSLConnectionSocketFactory socketFactory = null;
        try {
            socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());
            HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            clientHttpRequestFactory.setHttpClient(httpClient);
            template = new RestTemplate(clientHttpRequestFactory);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return template;
    }

}
