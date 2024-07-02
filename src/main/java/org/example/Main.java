package org.example;
import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        CrptApi.JsonSerializer jsonSerializer = new CrptApi.GsonJsonSerializer();
        CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 10, httpClient, jsonSerializer);

        try {
            crptApi.authenticate("https://auth.url/endpoint", "your_username", "your_password");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        CrptApi.Document document = new CrptApi.Document();
        CrptApi.Description description = new CrptApi.Description();
        description.setParticipantInn("1234567890");
        document.setDescription(description);
        document.setDoc_id("12345");
        document.setDoc_status("NEW");
        document.setDoc_type("LP_INTRODUCE_GOODS");
        document.setImportRequest(true);
        document.setOwner_inn("0987654321");
        document.setParticipant_inn("1234567890");
        document.setProducer_inn("5678901234");
        document.setProduction_date("2024-07-01");
        document.setProduction_type("type");

        CrptApi.Product product = new CrptApi.Product();
        product.setCertificate_document("cert_doc");
        product.setCertificate_document_date("2024-07-01");
        product.setCertificate_document_number("cert_num");
        product.setOwner_inn("0987654321");
        product.setProducer_inn("5678901234");
        product.setProduction_date("2024-07-01");
        product.setTnved_code("tnved_code");
        product.setUit_code("uit_code");
        product.setUitu_code("uitu_code");

        document.setProducts(new CrptApi.Product[]{product});
        document.setReg_date("2024-07-01");
        document.setReg_number("reg_num");

        try {
            crptApi.createDocument(document, "signature");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
