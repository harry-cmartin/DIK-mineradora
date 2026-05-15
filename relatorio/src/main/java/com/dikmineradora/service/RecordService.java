package com.dikmineradora.service;


import com.dikmineradora.dto.ProposalDto;
import com.dikmineradora.dto.QuotationDto;
import com.dikmineradora.dto.RecordsDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface RecordService {

    void buildRecords(ProposalDto proposalDto);

    void saveQuotation(QuotationDto quotationDto);

    List<RecordsDto> generateRecordsData();


}
