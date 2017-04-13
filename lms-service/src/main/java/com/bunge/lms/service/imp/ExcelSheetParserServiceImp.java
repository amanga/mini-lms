package com.bunge.lms.service.imp;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bunge.lms.domain.Answer;
import com.bunge.lms.domain.Question;
import com.bunge.lms.service.ExcelSheetParserService;
import com.bunge.lms.service.QuestionService;
import com.bunge.lms.util.ExcelSheetHelper;

@Service
public class ExcelSheetParserServiceImp implements ExcelSheetParserService {

	@Autowired
	private QuestionService questionService;
	
	@Override
	public List<Question> readQuestionsFromExcel(String filePath) throws Exception {
		List<Question> returnQuestions = new ArrayList<Question>();
		
		Workbook workbook = ExcelSheetHelper.getWorkbook(filePath);

		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);

			Iterator<Row> rowItr = sheet.iterator();
			while (rowItr.hasNext()) {
				Question question = new Question();
				Map<String, Answer> answerCol = new HashMap<String, Answer>();
				
				Row nextRow = rowItr.next();
				
				if(nextRow.getRowNum() == 0){
					System.out.println("Skipping the row # 0");
					continue;					
				}
				Iterator<Cell> cellItr = nextRow.cellIterator();
				while (cellItr.hasNext()) {
					Cell cell = cellItr.next();
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					
					case 0:
//						question.setqId(((Double)getCellValue(cell)).longValue());
						break;
					case 1:
						question.setqTitle((String)getCellValue(cell));
						break;
					case 2:
						question.setqSubTitle((String)getCellValue(cell));
						break;
					case 3:
						question.setqTitleType((String)getCellValue(cell));
						break;
					case 4:
						question.setqComment((String)getCellValue(cell));
						break;
					case 5:
						question.setqDesc((String)getCellValue(cell));
						break;
					case 6:
						question.setqFlag(((Double)getCellValue(cell)).byteValue()==1);
						break;
					case 7:
						break;
					case 8:
						question.setqExplanation((String)getCellValue(cell));
						break;
					case 9:
						String answerValA = (String)getCellValue(cell);
						if(!StringUtils.isEmpty(answerValA)){
							Answer answerA = new Answer();
							answerA.setQcTitle(answerValA);
							answerA.setQuestion(question);
							answerCol.put("A", answerA);
						}
						break;
					case 10:
						String answerValB = (String)getCellValue(cell);
						if(!StringUtils.isEmpty(answerValB)){
							Answer answerB = new Answer();
							answerB.setQcTitle(answerValB);
							answerB.setQuestion(question);
							answerCol.put("B", answerB);
						}
						break;
					case 11:
						String answerValC = (String)getCellValue(cell);
						if(!StringUtils.isEmpty(answerValC)){
							Answer answerC = new Answer();
							answerC.setQcTitle(answerValC);
							answerC.setQuestion(question);
							answerCol.put("C", answerC);
						}
						break;
					case 12:
						String answerValD = (String)getCellValue(cell);
						if(!StringUtils.isEmpty(answerValD)){
							Answer answerD = new Answer();
							answerD.setQcTitle(answerValD);
							answerD.setQuestion(question);
							answerCol.put("D", answerD);
						}
						break;
					case 13:
						String answerValE = (String)getCellValue(cell);
						if(!StringUtils.isEmpty(answerValE)){
							Answer answerE = new Answer();
							answerE.setQcTitle(answerValE);
							answerE.setQuestion(question);
							answerCol.put("E", answerE);
						}
						break;
					case 14:
						String correctStringVals = (String)getCellValue(cell);
						String[] correctAnswers = correctStringVals.split(",");
						for(String answer : correctAnswers){
							Answer correctAnswer = answerCol.get(answer.toUpperCase());
							correctAnswer.setCorrectFlag(true);
						}
						break;
					
					}
				}
				question.setAnswers(new HashSet<Answer>(answerCol.values()));
//				if(question.getqId()!=null){
				questionService.save(question);
//				}
				
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
}
