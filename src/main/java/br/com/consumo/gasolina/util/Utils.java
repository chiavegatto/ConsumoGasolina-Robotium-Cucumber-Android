package br.com.consumo.gasolina.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;

public class Utils {

	@SuppressLint("SimpleDateFormat")
	public static Calendar converteStringEmCalendar(String data)
			throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(formato.parse(data));
		return cal;
	}

	public static void capturaRelatoriosNoDevice(String packageAplicacao,
			String caminhoRelatorioCaputado) {
		try {
			// packageAplicacao = PomUtil.capturarPackageDoPomDaAplicacao();
			// caminhoRelatorioCaputado =
			// System.getProperty("user.dir")+"\\target";
			String command = "adb pull /data/data/" + packageAplicacao + "/ "
					+ caminhoRelatorioCaputado;
			Runtime.getRuntime().exec(command);
			System.out.println("Captura do relatório executado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		capturaRelatoriosNoDevice("br.com.consumogasolina","D:\\backup\\desempenhotest\\target");
	}
}
