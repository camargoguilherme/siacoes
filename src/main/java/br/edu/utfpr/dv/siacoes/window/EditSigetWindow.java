﻿package br.edu.utfpr.dv.siacoes.window;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import br.edu.utfpr.dv.siacoes.bo.SigetConfigBO;
import br.edu.utfpr.dv.siacoes.components.YearField;
import br.edu.utfpr.dv.siacoes.model.SigetConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;
import br.edu.utfpr.dv.siacoes.view.ListView;

public class EditSigetWindow extends EditWindow {
	
	private final SigetConfig config;
	
	private final TextField textMinimumScore;
	private final CheckBox checkRegisterProposal;
	private final CheckBox checkShowGradesToStudent;
	private final NativeSelect comboSupervisorFilter;
	private final NativeSelect comboCosupervisorFilter;
	private final YearField textSupervisorIndicator;
	private final YearField textMaxTutoredStage1;
	private final YearField textMaxTutoredStage2;
	private final CheckBox checkRequestFinalDocumentStage1;
	private final TextField textRepositoryLink;
	private final CheckBox checkSupervisorJuryRequest;
	private final CheckBox checkSupervisorAgreement;
	
	public EditSigetWindow(SigetConfig config, ListView parentView){
		super("Editar Configurações", parentView);
		
		this.config = config;
		
		this.textMinimumScore = new TextField("Nota mínima para aprovação");
		this.textMinimumScore.setWidth("100px");
		
		this.checkRegisterProposal = new CheckBox("O acadêmico deve registrar a proposta para TCC1");
		
		this.checkShowGradesToStudent = new CheckBox("Permitir que o acadêmico visualize as notas atribuídas pela banca");
		
		this.comboSupervisorFilter = new NativeSelect("Orientador deve pertencer ao");
		this.comboSupervisorFilter.setWidth("400px");
		this.comboSupervisorFilter.setNullSelectionAllowed(false);
		this.comboSupervisorFilter.addItem(SupervisorFilter.DEPARTMENT);
		this.comboSupervisorFilter.addItem(SupervisorFilter.CAMPUS);
		this.comboSupervisorFilter.addItem(SupervisorFilter.INSTITUTION);
		this.comboSupervisorFilter.addItem(SupervisorFilter.EVERYONE);
		
		this.comboCosupervisorFilter = new NativeSelect("Coorientador deve pertencer ao");
		this.comboCosupervisorFilter.setWidth("400px");
		this.comboCosupervisorFilter.setNullSelectionAllowed(false);
		this.comboCosupervisorFilter.addItem(SupervisorFilter.DEPARTMENT);
		this.comboCosupervisorFilter.addItem(SupervisorFilter.CAMPUS);
		this.comboCosupervisorFilter.addItem(SupervisorFilter.INSTITUTION);
		this.comboCosupervisorFilter.addItem(SupervisorFilter.EVERYONE);
		
		this.textSupervisorIndicator = new YearField();
		this.textSupervisorIndicator.setCaption("Número de indicações do orientador para Proposta de TCC 1");
		
		this.textMaxTutoredStage1 = new YearField();
		this.textMaxTutoredStage1.setCaption("Número máximo de orientados para TCC 1");
		
		this.textMaxTutoredStage2 = new YearField();
		this.textMaxTutoredStage2.setCaption("Número máximo de orientados para TCC 2");
		
		this.checkRequestFinalDocumentStage1 = new CheckBox("Exigir envio da versão final para TCC 1");
		
		this.textRepositoryLink = new TextField("Link para o repositório de TCCs");
		this.textRepositoryLink.setWidth("810px");
		this.textRepositoryLink.setMaxLength(255);
		
		this.checkSupervisorJuryRequest = new CheckBox("Permitir que o orientador monte a composição da banca");
		
		this.checkSupervisorAgreement = new CheckBox("Exigir termo de concordância de orientação");
		
		GridLayout g1 = new GridLayout(2, 2);
		g1.setSpacing(true);
		g1.addComponent(this.comboSupervisorFilter, 0, 0);
		g1.addComponent(this.comboCosupervisorFilter, 1, 0);
		g1.addComponent(this.textMaxTutoredStage1, 0, 1);
		g1.addComponent(this.textMaxTutoredStage2, 1, 1);
		VerticalLayout v4 = new VerticalLayout(g1);
		v4.setMargin(true);
		Panel panel1 = new Panel("Orientação");
		panel1.setContent(v4);
		this.addField(panel1);
		
		VerticalLayout v1 = new VerticalLayout(this.checkShowGradesToStudent, this.checkSupervisorJuryRequest);
		v1.setSpacing(true);
		HorizontalLayout h1 = new HorizontalLayout(this.textMinimumScore, v1);
		h1.setSpacing(true);
		h1.setMargin(true);
		Panel panel2 = new Panel("Banca");
		panel2.setContent(h1);
		this.addField(panel2);
		
		VerticalLayout v2 = new VerticalLayout(this.checkSupervisorAgreement, this.checkRegisterProposal, this.checkRequestFinalDocumentStage1);
		v2.setSpacing(true);
		HorizontalLayout h2 = new HorizontalLayout(v2, this.textSupervisorIndicator);
		h2.setSpacing(true);
		h2.setMargin(true);
		Panel panel3 = new Panel("TCC 1");
		panel3.setContent(h2);
		this.addField(panel3);
		
		VerticalLayout v3 = new VerticalLayout(this.textRepositoryLink);
		v3.setSpacing(true);
		v3.setMargin(true);
		Panel panel4 = new Panel("Biblioteca");
		panel4.setContent(v3);
		this.addField(panel4);
		
		this.loadConfigurations();
	}
	
	private void loadConfigurations(){
		this.textMinimumScore.setValue(String.format("%.2f", this.config.getMinimumScore()));
		this.checkRegisterProposal.setValue(this.config.isRegisterProposal());
		this.checkShowGradesToStudent.setValue(this.config.isShowGradesToStudent());
		this.comboSupervisorFilter.setValue(this.config.getSupervisorFilter());
		this.comboCosupervisorFilter.setValue(this.config.getCosupervisorFilter());
		this.textSupervisorIndicator.setYear(this.config.getSupervisorIndication());
		this.textMaxTutoredStage1.setYear(this.config.getMaxTutoredStage1());
		this.textMaxTutoredStage2.setYear(this.config.getMaxTutoredStage2());
		this.checkRequestFinalDocumentStage1.setValue(this.config.isRequestFinalDocumentStage1());
		this.textRepositoryLink.setValue(this.config.getRepositoryLink());
		this.checkSupervisorJuryRequest.setValue(this.config.isSupervisorJuryRequest());
		this.checkSupervisorAgreement.setValue(this.config.isSupervisorAgreement());
	}

	@Override
	public void save() {
		try{
			SigetConfigBO bo = new SigetConfigBO();
			
			this.config.setMinimumScore(Double.parseDouble(this.textMinimumScore.getValue().replace(",", ".")));
			this.config.setRegisterProposal(this.checkRegisterProposal.getValue());
			this.config.setShowGradesToStudent(this.checkShowGradesToStudent.getValue());
			this.config.setSupervisorFilter((SupervisorFilter)this.comboSupervisorFilter.getValue());
			this.config.setCosupervisorFilter((SupervisorFilter)this.comboCosupervisorFilter.getValue());
			this.config.setSupervisorIndication(this.textSupervisorIndicator.getYear());
			this.config.setMaxTutoredStage1(this.textMaxTutoredStage1.getYear());
			this.config.setMaxTutoredStage2(this.textMaxTutoredStage2.getYear());
			this.config.setRequestFinalDocumentStage1(this.checkRequestFinalDocumentStage1.getValue());
			this.config.setRepositoryLink(this.textRepositoryLink.getValue());
			this.config.setSupervisorJuryRequest(this.checkSupervisorJuryRequest.getValue());
			this.config.setSupervisorAgreement(this.checkSupervisorAgreement.getValue());
			
			bo.save(this.config);
			
			Notification.show("Salvar Configurações", "Configurações salvas com sucesso.", Notification.Type.HUMANIZED_MESSAGE);
			
			this.parentViewRefreshGrid();
			this.close();
		}catch(Exception e){
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			Notification.show("Salvar Configurações", e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}

}
