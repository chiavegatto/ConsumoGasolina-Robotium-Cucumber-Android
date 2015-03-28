package br.com.consumo.gasolina.util;

import java.util.Calendar;

import com.robotium.solo.Solo;

public class UtilsRobotium {
	public static void preencherDatePickerCriadoEmTempoExecucao(Calendar cal, Solo solo){
		solo.setDatePicker(0, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	    solo.clickOnText("OK");
	}
}
