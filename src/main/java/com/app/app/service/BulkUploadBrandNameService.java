package com.app.app.service;


import com.app.app.entity.cars.Brand;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {

    public List<Brand> readExcel(String filepath) throws IOException{

        List<Brand> entities=new ArrayList<>();

        FileInputStream fis =new FileInputStream(new File(filepath));
        Workbook workbook=new XSSFWorkbook(fis);

        Sheet sheet=workbook.getSheetAt(0);
        Iterator<Row> rowIterator= sheet.iterator();

        if(rowIterator.hasNext()){
            rowIterator.next();
        }

        while(rowIterator.hasNext()){
            Row row=rowIterator.next();
            Brand entity=new Brand();

            entity.setId((long)row.getCell(0).getNumericCellValue());
            entity.setName(row.getCell(1).getStringCellValue());

            entities.add(entity);

        }
        workbook.close();
        fis.close();
        return entities;

    }
}
