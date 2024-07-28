package com.example.railwayapp.service.impl;

import com.example.railwayapp.Model.Dto.RailwayLineAddDto;
import com.example.railwayapp.Model.Dto.RailwayLineViewDto;
import com.example.railwayapp.service.PictureService;
import com.example.railwayapp.service.RailwayLineService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RailwayLineServiceImpl implements RailwayLineService {

    private final PictureService pictureService;
    private final RestClient restClient;

    public RailwayLineServiceImpl( PictureService pictureService, RestClient restClient) {

        this.pictureService = pictureService;
        this.restClient = restClient;
    }


    @Override
    public RailwayLineViewDto findLineById(long id) {
        return restClient
                .get()
                .uri("http://localhost:8081/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(RailwayLineViewDto.class);
    }

    @Override
    public List<RailwayLineViewDto> getAllRailwayLines() {
        return restClient
                .get()
                .uri("http://localhost:8081/lines")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});

    }

    @Override
    public void addLine(RailwayLineAddDto lineData) {
          restClient.post().uri("http://localhost:8081/add").body(lineData).retrieve();
    }

    @Override
    public void deleteLineById(long id) {

                restClient.delete().uri("http://localhost:8081/{id}", id).retrieve();


    }
}
