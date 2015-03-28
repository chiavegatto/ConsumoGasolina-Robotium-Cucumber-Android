package br.com.consumo.gasolina.stepsdefs;

import java.util.Calendar;
import java.util.Map;

import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import br.com.consumo.gasolina.entidades.Abastecimento;
import br.com.consumo.gasolina.util.Utils;
import br.com.consumo.gasolina.util.UtilsRobotium;
import br.com.consumogasolina.MainActivity;
import br.com.consumogasolina.R;

import com.robotium.solo.Solo;
import com.robotium.solo.Solo.Config;
import com.robotium.solo.Solo.Config.ScreenshotFileType;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefCalDesempenho extends ActivityInstrumentationTestCase2<MainActivity> {

	private static Solo solo;

	public StepDefCalDesempenho() {
		super(MainActivity.class);
	}

	@Before
	public void inicia() throws Exception {
		Config config = new Config();
		config.screenshotFileType = ScreenshotFileType.PNG;
		config.screenshotSavePath = Environment.getDataDirectory() + "/Robotium/";
		config.shouldScroll = false;
		solo = new Solo(getInstrumentation(), config);
		getActivity();
	}

	@After
	public void finaliza(Scenario scenario) throws Exception {
		if(scenario.isFailed()){
			solo.takeScreenshot();
		}
		solo.finishOpenedActivities();
		
	}

	@Given("^Estou acessando a aplicação Desempenho Mensal$")
	public void estou_acessando_a_aplicação_Desempenho_Mensal()
			throws Throwable {
		solo.assertCurrentActivity("Não é a activity", MainActivity.class);
	}

	@When("^Clico no menu \"(.*?)\"$")
	public void clico_no_menu(String txOpcaoMenu) throws Throwable {
		switch (txOpcaoMenu) {
		case "Adicionar Abastecimentos":
			solo.clickOnActionBarItem(R.id.action_cadastrar_abastecimento);
			break;
		case "Ver Abastecimentos":
			solo.clickOnActionBarItem(R.id.action_listar_abastecimentos);
			break;
		}
	}

	@When("^Preencho o abastecimento com os seguintes valores:$")
	public void preencho_o_abastecimento_com_os_seguintes_valores(DataTable tableAbastecimento) throws Throwable {
		
		for (Map<String, String> dadosAbastecimento : tableAbastecimento.asMaps(String.class,String.class)) {
			Abastecimento abastecimento = new Abastecimento(dadosAbastecimento);
			abastecimento.preencherAbastecimento(abastecimento, solo);
		}
	}
	
	@When("^Preencho a data do abastecimento com a data \"([^\"]*)\"$")
	public void Preencho_a_data_do_abastecimento_com_a_data(String txDataAbastecimento) throws Throwable {
		Abastecimento.preencherDataDoAbastecimento(txDataAbastecimento, solo);
	}

	@When("^Clico no botão \"(.*?)\"$")
	public void clico_no_botão(String txBotao) throws Throwable {
		solo.clickOnText(txBotao);
	}

	@Then("^Deve ser exibido a mensagem \"(.*?)\"$")
	public void deve_ser_exibido_a_mensagem(String txMensagem) throws Throwable {
		assertTrue(solo.searchText(txMensagem, true));
	}
	
	@When("^Pressiono com um click no abastecimento no KM abastecido \"([^\"]*)\"$")
	public void Pressiono_com_um_click_no_abastecimento_no_KM_abastecido(String txKmAbastecimento) throws Throwable {
		solo.clickOnText(txKmAbastecimento);
		solo.waitForText("Alterar Abastecimento");
	}
	
	@Then("^Deve ser exibido as informações referente ao abastecimento:$")
	public void Deve_ser_exibido_as_informações_referente_ao_abastecimento(DataTable tableAbastecimento) throws Throwable {
		for (Map<String, String> dadosAbastecimento: tableAbastecimento.asMaps(String.class,String.class)){
			assertTrue(solo.searchText(dadosAbastecimento.get("KM Abastecimento")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Quantidade Litros")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Valor")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Data Abastecimento")));	
		}
	}
	
	@When("^Altero o abastecimento com as seguintes informações:$")
	public void Altero_o_abastecimento_com_as_seguintes_informações(DataTable tableAbastecimento) throws Throwable {
		
		for (Map<String, String> dadosAbastecimento : tableAbastecimento.asMaps(String.class,String.class)) {
			Abastecimento abastecimento = new Abastecimento(dadosAbastecimento);
			abastecimento.preencherAbastecimento(abastecimento, solo);
			Abastecimento.preencherDataDoAbastecimento(dadosAbastecimento.get("Data Abastecimento"), solo);
		}
	}

	@When("^Pressiono com um click longo no abastecimento no KM abastecido \"([^\"]*)\"$")
	public void Pressiono_com_um_click_longo_no_abastecimento_no_KM_abastecido(String txKMAbastecido) throws Throwable {
		solo.clickLongOnText(txKMAbastecido);
	}
	
	@Then("^Deve ser exibido um modal de confirmação da exclusão$")
	public void Deve_ser_exibido_um_modal_de_confirmação_da_exclusão() throws Throwable {
		solo.waitForDialogToOpen();
	}
	
	@Then("^Deve ser exibido as seguintes informações no desempenho de abastecimento:$")
	public void Deve_ser_exibido_as_seguintes_informações_no_desempenho_de_abastecimento(DataTable tableAbastecimento) throws Throwable {
		for (Map<String, String> dadosAbastecimento : tableAbastecimento.asMaps(String.class,String.class)) {
			assertTrue(solo.searchText(dadosAbastecimento.get("KM Abastecimento")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Quantidade Litros")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Valor")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Quantidade Abastecimentos")));
			assertTrue(solo.searchText(dadosAbastecimento.get("Quantidade KM")));
		}
	}
	
	@When("^Preencho a data início \"([^\"]*)\" e data final \"([^\"]*)\"$")
	public void Preencho_a_data_início_e_data_final(String dataInicio, String dataFinal) throws Throwable {
		EditText editDataInicio = (EditText) solo.getCurrentActivity().findViewById(R.id.editText_data_inicio);
		EditText editDataFinal = (EditText) solo.getCurrentActivity().findViewById(R.id.editText_data_final);
		
		Calendar calDataInicio = Utils.converteStringEmCalendar(dataInicio);
		solo.clickOnView(editDataInicio);
		UtilsRobotium.preencherDatePickerCriadoEmTempoExecucao(calDataInicio,solo);

		Calendar calDataFinal = Utils.converteStringEmCalendar(dataFinal);
		solo.clickOnView(editDataFinal);
		UtilsRobotium.preencherDatePickerCriadoEmTempoExecucao(calDataFinal,solo);
	}
	
	@When("^Seleciono a pesquisa por \"([^\"]*)\"$")
	public void Seleciono_a_pesquisa_por(String txOpcaoPesquisa) throws Throwable {
		solo.clickOnText(txOpcaoPesquisa);
	}
}
