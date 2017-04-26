package com.bunge.lms.service.imp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.bunge.lms.domain.Assessment;
import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;
import com.bunge.lms.service.ExcelSheetParserService;
import com.bunge.lms.util.ExcelSheetHelper;

@Service
public class ExcelSheetParserServiceImp implements ExcelSheetParserService {

	@Override
	public  Map<Assessment, Map<QuestionBlock,List<Question>>> readQuestionFromExcel(FileInputStream fileInputStream, String fileType) throws Exception {
		Workbook workbook = ExcelSheetHelper.getWorkbook(fileInputStream, fileType);
		return processWorkbook(workbook);
	}

	@Override
	public  Map<Assessment, Map<QuestionBlock,List<Question>>> readQuestionsFromExcel(String filePath) throws Exception {
		Workbook workbook = ExcelSheetHelper.getWorkbook(filePath);
		return processWorkbook(workbook);
	}

	private Map<Assessment, Map<QuestionBlock,List<Question>>> processWorkbook(Workbook workbook) throws Exception {
		Map<Assessment, Map<QuestionBlock, List<Question>>> returnAssessment = new HashMap<Assessment, Map<QuestionBlock, List<Question>>>();
	
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);

			Iterator<Row> rowItr = sheet.iterator();
			while (rowItr.hasNext()) {
				Assessment asm = new Assessment();
				QuestionBlock questionBlock = new QuestionBlock();
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
						//Assessment Title...
						asm.setTitle(getString(getCellValue(cell)));
						asm.setStatus(true);
						break;
					case 1:
						// question.setqId(((Double)getCellValue(cell)).longValue());
						questionBlock.setTitle(getString(getCellValue(cell)));
						questionBlock.setStatus(true);
						
						break;
					case 2:
						question.setqTitle(getString(getCellValue(cell)));
						break;
					case 3:
						question.setqSubTitle(getString(getCellValue(cell)));
						break;
					case 4:
						question.setqTitleType(getString(getCellValue(cell)));
						break;
					case 5:
						question.setqComment(getString(getCellValue(cell)));
						break;
					case 6:
						question.setqDesc(getString(getCellValue(cell)));
						break;
					case 7:
						question.setqFlag(getString(getCellValue(cell)) == "1");
						break;
					case 8:
						break;
					case 9:
						question.setqExplanation(getString(getCellValue(cell)));
						break;
					case 10:
						String answerValA = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValA)) {
							Answer answerA = new Answer();
							answerA.setQcTitle(answerValA);
							answerA.setQuestion(question);
							answerCol.put("A", answerA);
						}
						break;
					case 11:
						String answerValB = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValB)) {
							Answer answerB = new Answer();
							answerB.setQcTitle(answerValB);
							answerB.setQuestion(question);
							answerCol.put("B", answerB);
						}
						break;
					case 12:
						String answerValC = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValC)) {
							Answer answerC = new Answer();
							answerC.setQcTitle(answerValC);
							answerC.setQuestion(question);
							answerCol.put("C", answerC);
						}
						break;
					case 13:
						String answerValD = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValD)) {
							Answer answerD = new Answer();
							answerD.setQcTitle(answerValD);
							answerD.setQuestion(question);
							answerCol.put("D", answerD);
						}
						break;
					case 14:
						String answerValE = getString(getCellValue(cell));
						if (!StringUtils.isEmpty(answerValE)) {
							Answer answerE = new Answer();
							answerE.setQcTitle(answerValE);
							answerE.setQuestion(question);
							answerCol.put("E", answerE);
						}
						break;
					case 15:
						String correctStringVals = getString(getCellValue(cell));
						String[] correctAnswers = correctStringVals.split(",");
						for (String answer : correctAnswers) {
							Answer correctAnswer = answerCol.get(answer.toUpperCase());
							correctAnswer.setCorrectFlag(true);
						}
						break;
					}
				}
				
				//adding answers to question..
				question.setAnswers(new ArrayList<Answer>(answerCol.values()));
				
				Map<QuestionBlock,List<Question>> qBlockCol = null;
				if(returnAssessment.containsKey(asm)){
					qBlockCol = returnAssessment.get(asm);
				}else{
					qBlockCol = new HashMap<QuestionBlock,List<Question>>();
				}
				
				List<Question> questionCol = null;
				if(qBlockCol.containsKey(questionBlock)){
					questionCol = qBlockCol.get(questionBlock);
				}else{
					questionCol = new ArrayList<Question>();
				}
				questionCol.add(question);
				qBlockCol.put(questionBlock, questionCol);
				
				returnAssessment.put(asm, qBlockCol);
				
			}
		}
		
		return returnAssessment;
	}
	
	/*private Map<Assessment, List<QuestionBlock>> normalizedAssessment(Map<Assessment,List<QuestionBlock>> asmCol, Map<QuestionBlock, List<Question>> qBlockCol){
		Map<Assessment, List<QuestionBlock>> rtnObj = new HashMap<Assessment, List<QuestionBlock>>();
		
		Set<Assessment> asmSetObj = asmCol.keySet();
		Iterator<Assessment> asmItr = asmSetObj.iterator();
		while(asmItr.hasNext()){
			Assessment asmKey = asmItr.next();
			List<QuestionBlock> qBlockList = asmCol.get(asmKey);
			Iterator<QuestionBlock> qBlockItr = qBlockList.iterator();
			while(qBlockItr.hasNext()){
				QuestionBlock qBlockKey = qBlockItr.next();
				if(qBlockCol.containsKey(qBlockKey)){
					qBlockKey.setQuestions(new HashSet<Question>(qBlockCol.get(qBlockKey)));
				}
			}
			rtnObj.put(asmKey, qBlockList);
		}
		return rtnObj;
	}*/

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
