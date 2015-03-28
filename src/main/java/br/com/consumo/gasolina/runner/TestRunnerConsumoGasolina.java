package br.com.consumo.gasolina.runner;


import cucumber.api.CucumberOptions;

@CucumberOptions(features = "features",
				 glue="br.com.consumo.gasolina.stepsdefs",
                 plugin = {"html:/data/data/br.com.consumogasolina/cucumber/",
	                       "junit:/data/data/br.com.consumogasolina/JUnitReport.xml", 
	                       "json:/data/data/br.com.consumogasolina/JSONReport.json"})
public class TestRunnerConsumoGasolina {

}
