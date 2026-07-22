package com.example.demo.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.FakeProductRequestDTO;
import com.example.demo.dto.FakeProductResponseDTO;

@Component
public class FakeDemoApiClient {
	
	private RestTemplate restTemplate;
	private String fakeStoreAPIURL;
	private String fakeProductAPI;
	
	public FakeDemoApiClient(RestTemplate restTemplate, @Value("${fakestore.api.url}") String fakeStoreAPIURL, @Value("${fakestore.api.path.product}") String fakeProductAPI) {
        this.restTemplate = restTemplate;
        this.fakeStoreAPIURL = fakeStoreAPIURL;
        this.fakeProductAPI = fakeProductAPI;
        
    }
	
	
	
	public List<FakeProductResponseDTO> getAllProducts() {
	String url = fakeStoreAPIURL + fakeProductAPI;
	ResponseEntity<FakeProductResponseDTO[]> response = restTemplate.getForEntity(url, FakeProductResponseDTO[].class);
	FakeProductResponseDTO[] resp =  response.getBody();
	return Arrays.stream(resp).toList();
	}
	
	public FakeProductResponseDTO getProduct(int id) {
		String url =  fakeStoreAPIURL+fakeProductAPI+id;
		ResponseEntity<FakeProductResponseDTO> response = restTemplate.getForEntity(url, FakeProductResponseDTO.class);
		FakeProductResponseDTO resp =  response.getBody();
		return resp;
	}
	
	public FakeProductResponseDTO createProduct(FakeProductRequestDTO fakeProductRequestDTO) {
		String url = fakeStoreAPIURL + fakeProductAPI;
		ResponseEntity<FakeProductResponseDTO> response = restTemplate.postForEntity(url, fakeProductRequestDTO ,FakeProductResponseDTO.class);
		FakeProductResponseDTO resp =  response.getBody();
		return resp;
	}
	
	public boolean deleteProduct(String id) {
		String url = fakeStoreAPIURL+fakeProductAPI+id;
		restTemplate.delete(url);
		return true;
	}
	
	public Boolean updateProduct(Long id,FakeProductRequestDTO fakeProductRequestDTO) {
		String url = fakeStoreAPIURL+fakeProductAPI+id;
		restTemplate.put(url, fakeProductRequestDTO ,FakeProductResponseDTO.class);
		return true;
	}

}
