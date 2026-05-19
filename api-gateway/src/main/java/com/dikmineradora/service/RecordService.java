package com.dikmineradora.service;

import com.dikmineradora.dto.RecordsDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface RecordService {

    ByteArrayInputStream generateCSVrecord();

    List<RecordsDto> generateRecordsData();

}
