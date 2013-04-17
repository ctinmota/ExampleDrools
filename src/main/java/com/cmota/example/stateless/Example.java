package com.cmota.example.stateless;

import java.util.Arrays;
import java.util.Date;

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
		
		KnowledgeBase kbase = getKnowledgeBase();
		
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		
		Applicant applicant = new Applicant( "Mr John Smith", 23 );
		Application application = new Application(new Date());
		
		System.out.println(application.getDateApplied());
		
		System.out.println(application.isValid());
		
		ksession.execute( Arrays.asList( new Object[] { application, applicant } ) );
		
		System.out.println(application.isValid());
		
	}
	
	private static KnowledgeBase getKnowledgeBase(){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(
				"licenseApplication.drl", Example.class), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		return kbase;
	}

}
