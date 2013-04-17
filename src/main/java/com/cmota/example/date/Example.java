package com.cmota.example.date;

import java.util.Calendar;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Calendar.getInstance().add(Calendar.DAY_OF_MONTH, -10);
		
		System.out.println(Calendar.getInstance().getTime());
		
		
		Bean myBean = new Bean();
		myBean.setDueDate(Calendar.getInstance().getTime());
		myBean.setGracePeriod(10);
		
		KnowledgeBase kbase = getKnowledgeBase();
		
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		
		ksession.execute(myBean);
		
		
	}
	
	private static KnowledgeBase getKnowledgeBase(){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(
				"dateRule.drl", Example.class), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		return kbase;
	}

}
