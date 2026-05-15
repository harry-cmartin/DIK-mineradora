package com.dikmineradora.utils;

import com.dikmineradora.dto.RecordsDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVhelper {

    public static ByteArrayInputStream recordsToCsv (List<RecordsDto> RecordsDtos){

        final CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("ID proposta" , "Cliente" , "Preço por tonelada", "Melhor cotação de moeda");


        try (

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter =
                        new CSVPrinter(new PrintWriter(out), csvFormat);)

        {


            for (RecordsDto recs : RecordsDtos) {
                List<String> data = Arrays.asList(String.
                                valueOf(recs.getProposalId()),recs.getCostumer(), String.valueOf(recs.getPriceTonne()),
                        String.valueOf(recs.getLastDollarCotation()));

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Fail to import data to CSV file: "
                    +e.getMessage());
        }


    }

}
