package br.com.zup.file.generator.format.gateway.http;

import br.com.zup.file.generator.format.gateway.http.exception.RecordNotFoundException;
import br.com.zup.file.generator.format.model.CustodiaRequest;
import br.com.zup.file.generator.format.write.ExcelWrite;
import br.com.zup.file.generator.format.write.ExcelWriteStream;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/files")
@AllArgsConstructor
public class FileController {

    private final ExcelWrite excelWrite;

    private final ExcelWriteStream excelWriteStream;

    @GetMapping("/memory")
    public ResponseEntity<InputStreamResource> generateFile() {
        List<CustodiaRequest> custodies = new ArrayList<>();

        for (int i = 0; i < 50000; i++) {
            CustodiaRequest custody = new CustodiaRequest();
            custody.setId(1L);
            custody.setDataSolicitacao(LocalDate.now());
            custody.setDataEvento(LocalDate.now());
            custody.setNome("Nome Teste 01");
            custody.setTipo("abertura");
            custody.setCpf("000.000.000.00");
            custody.setStatus("pendente de análise");
            custody.setVizualisar(true);

            custodies.add(custody);
        }

        ByteArrayInputStream in = excelWrite.write(custodies);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=custodia.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/stream")
    public ResponseEntity<InputStreamResource> getFile() throws Exception {

        List<CustodiaRequest> custodies = new ArrayList<>();

        for (int i = 0; i < 50000; i++) {
            CustodiaRequest custody = new CustodiaRequest();
            custody.setId(1L);
            custody.setDataSolicitacao(LocalDate.now());
            custody.setDataEvento(LocalDate.now());
            custody.setNome("Nome Teste 01");
            custody.setTipo("abertura");
            custody.setCpf("000.000.000.00");
            custody.setStatus("pendente de análise");
            custody.setVizualisar(true);

            custodies.add(custody);
        }

        ByteArrayInputStream in = excelWriteStream.write(custodies);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=custodia.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
