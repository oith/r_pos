package org.reflection.service.etc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reflection.model.sample.ZxEmp;
import org.reflection.service.ZxEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service("excelFileUploadService")
public class ExcelFileUploadServiceImpl implements ExcelFileUploadService {

    @Autowired
    private ZxEmpService zxEmpService;

    @Override
    public void upload(InputStream inputStream) {
        List<Map<String, Object>> datam = getData(inputStream);

        for (Map<String, Object> entry : datam) {

            String code = (String) entry.get("CODE");
            String fullName = (String) entry.get("FULL_NAME");
            Double salary = (Double) entry.get("SALARY");
            String email = (String) entry.get("EMAIL");
            Boolean isActive = (Boolean) entry.get("IS_ACTIVE");
            String remarks = (String) entry.get("REMARKS");

            ZxEmp c = new ZxEmp(code, fullName, salary, email, isActive, remarks);

            zxEmpService.create(c);

        }

    }

    @Override
    public List<Map<String, Object>> getData(InputStream inputStream) {
        try {

            Workbook workbook;
//            if (file.toString().toLowerCase().endsWith(".xls")) {
//                workbook = new HSSFWorkbook(inputStream);
//            } else {
            workbook = new XSSFWorkbook(inputStream);
//            }

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            Map<Integer, String> mapColHead = new LinkedHashMap();
            List<Map<String, Object>> listData = new ArrayList();

            int i = 0;
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                Map<String, Object> mapData = new LinkedHashMap();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();

                    Object val = null;
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            val = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            val = cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            val = cell.getNumericCellValue();
                            break;
                    }

                    if (i == 0) {
                        mapColHead.put(columnIndex, val.toString());
                    } else {
                        String lgColNm = mapColHead.get(columnIndex);
                        mapData.put(lgColNm, val);
                    }
                }

                if (i == 0) {
                    System.out.println("Head: " + mapColHead);
                } else {
                    listData.add(mapData);
                }

                i++;
            }

            System.out.println("Data Map: " + listData);

            workbook.close();

            return listData;
        } catch (Exception e) {
            System.out.println("err vi parlam na: " + e);
            return null;
        }

    }
}
