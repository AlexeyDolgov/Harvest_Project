package harvest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import harvest.dto.ImportVarietyFields;

@Service
public class ExcelReader {
	Logger logger = LoggerFactory.getLogger(ExcelReader.class);
	
	@Value("${excelreader.file.path}")
	private String path;
	
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not an Excel file!");
		}

		return workbook;
	}

	private Object getCellValue(Cell cell) {

		switch (cell.getCellType()) {

		case NUMERIC:
			return cell.getNumericCellValue();

		case STRING:
			return cell.getStringCellValue();

		case FORMULA:
			return cell.getCellFormula();

		case BLANK:
			return "";

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case ERROR:
			return cell.getErrorCellValue();

		case _NONE:
			break;

		default:
			break;
		}

		return null;
	}

	public List<Map<Integer, String>> readFromCertainRangeInExcelFile(ImportVarietyFields importVarietyFields) throws IOException {
		logger.trace("Reading information from certain range in Excel file...");
		
		List<Map<Integer, String>> list = new ArrayList<>();

		ArrayList<Integer> arrayOfColumnNumbers = new ArrayList<Integer>(importVarietyFields.getColumnFields().values());
		Collections.sort(arrayOfColumnNumbers);
		int startCell = arrayOfColumnNumbers.get(0);
		int endCell = arrayOfColumnNumbers.get(arrayOfColumnNumbers.size() - 1);

		FileInputStream inputStream = new FileInputStream(new File(path + importVarietyFields.getFilename()));
		Workbook workbook = getWorkbook(inputStream, path + importVarietyFields.getFilename());
		Sheet firstSheet = workbook.getSheetAt(0);

		for (int r = importVarietyFields.getStartRow() - 1; r < importVarietyFields.getEndRow(); r++) {
			Row nextRow = firstSheet.getRow(r);

			if (nextRow == null)
				continue;

			HashMap<Integer, String> map = new HashMap<Integer, String>();

			for (int c = startCell - 1; c < endCell; c++) {
				if (!arrayOfColumnNumbers.contains(c + 1))
					continue;

				Cell nextCell = nextRow.getCell(c);

				if (nextCell == null)
					continue;

				map.put(nextCell.getColumnIndex() + 1, (String) getCellValue(nextCell).toString());
			}

			list.add(map);
		}

		workbook.close();
		inputStream.close();

		logger.trace("Returning list of read information from Excel file...");
		return list;
	}
}