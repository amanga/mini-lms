package com.bunge.lms.service.imp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bunge.lms.domain.Answer;
import com.bunge.lms.domain.Question;
import com.bunge.lms.service.ExcelSheetParserService;
import com.bunge.lms.util.ExcelSheetHelper;

@Service
public class ExcelSheetParserServiceImp implements ExcelSheetParserService {

	@Override
	public List<Question> readQuestionFromExcel(FileInputStream fileInputStream, String fileType) throws Exception {
		Workbook workbook = ExcelSheetHelper.getWorkbook(fileInputStream, fileType);
		return processWorkbook(workbook);
	}

	@Override
	public List<Question> readQuestionsFromExcel(String filePath) throws Exception {
		Workbook workbook = ExcelSheetHelper.getWorkbook(filePath);
		return processWorkbook(workbook);
	}

	private List<Question> processWorkbook(Workbook workbook) throws Exception {
		List<Question> returnQuestions = new ArrayList<Question>();

		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);

			Iterator<Row> rowItr = sheet.iterator();
			while (rowItr.hasNext()) {
				Question question = new Question();
				Map<String, Answer> answerCol = new HashMap<String, Answer>();

				Row nextRow = rowItr.next();

				if (nextRow.getRowNum() == 0) {
					System.out.println("Skipping the row # 0");
					continue;
				}
				Iterator<Cell> cellItr = nextRow.cellIterator();
				while (cellItr.hasNext()) {
					Cell cell = cellItr.next();
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {

					case 0:
						// question.setqId(((Double)getCellValue(cell)).longValue());
						break;
					case 1:
						question.setqTitle(getString(getCellValue(cell)));
						break;
					case 2:
						question.setqSubTitle(getString(getCellValue(cell)));
						break;
					case 3:
						question.setqTitleType(getString(getCellValue(cell)));
						break;
					case 4:
						question.setqComment(getString(getCellValue(cell)));
						break;
					case 5:
						question.setqDesc(getString(getCellValue(cell)));
						break;
					case 6:
						question.setqFlag(getString(getCellValue(cell)) == "1");
						break;
					case 7:
						break;
					case 8:
						question.setqExplanation(getString(getCellValue(cell)));
						break;
					case 9:
						String answerValA = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValA)) {
							Answer answerA = new Answer();
							answerA.setQcTitle(answerValA);
							answerA.setQuestion(question);
							answerCol.put("A", answerA);
						}
						break;
					case 10:
						String answerValB = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValB)) {
							Answer answerB = new Answer();
							answerB.setQcTitle(answerValB);
							answerB.setQuestion(question);
							answerCol.put("B", answerB);
						}
						break;
					case 11:
						String answerValC = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValC)) {
							Answer answerC = new Answer();
							answerC.setQcTitle(answerValC);
							answerC.setQuestion(question);
							answerCol.put("C", answerC);
						}
						break;
					case 12:
						String answerValD = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValD)) {
							Answer answerD = new Answer();
							answerD.setQcTitle(answerValD);
							answerD.setQuestion(question);
							answerCol.put("D", answerD);
						}
						break;
					case 13:
						String answerValE = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValE)) {
							Answer answerE = new Answer();
							answerE.setQcTitle(answerValE);
							answerE.setQuestion(question);
							answerCol.put("E", answerE);
						}
						break;
					case 14:
						String correctStringVals = getString(getCellValue(cell));
						String[] correctAnswers = correctStringVals.split(",");
						for (String answer : correctAnswers) {
							Answer correctAnswer = answerCol.get(answer.toUpperCase());
							correctAnswer.setCorrectFlag(true);
						}
						break;

					}
				}
				question.setAnswers(new HashSet<Answer>(answerCol.values()));
				returnQuestions.add(question);
			}
		}
		return returnQuestions;
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return (cell.getStringCellValue());

		case Cell.CELL_TYPE_BOOLEAN:
			return (cell.getBooleanCellValue());

		case Cell.CELL_TYPE_NUMERIC:
			return (cell.getNumericCellValue());
		}
		return null;
	}

	private String getString(Object object) {
		String rtnValue = null;
		if (object instanceof String)
			rtnValue = (String) object;

		if (object instanceof Double)
			rtnValue = "" + ((Double) object).longValue();

		if (object instanceof Boolean)
			rtnValue = ((Boolean) object) ? "1" : "0";

		return rtnValue;
	}
}
