package com.elleined.philippinelocationapi.populator;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.service.baranggay.BaranggayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Qualifier("baranggayPopulator")
@Transactional

public class BaranggayPopulator extends Populator {
    private final BaranggayService baranggayService;

    protected BaranggayPopulator(ObjectMapper objectMapper, BaranggayService baranggayService) {
        super(objectMapper);
        this.baranggayService = baranggayService;
    }

    @Override
    public void populate(final String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(List.class, BaranggayDTO.class);

        List<BaranggayDTO> baranggays = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        baranggayService.saveAll(baranggays);
    }
}
