package com.cmota.example.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		fireTemplate();
	}

	private static void fireTemplate() throws Exception {

		Collection<ParamSet> cfl = new ArrayList<ParamSet>();
		cfl.add(new ParamSet("weight", 10, 99, EnumSet.of(ItemCode.LOCK,
				ItemCode.STOCK)));
		cfl.add(new ParamSet("price", 10, 50, EnumSet.of(ItemCode.BARREL)));
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		Expander ex = new Expander();
		
		InputStream dis = new FileInputStream(new File("src/main/java/com/cmota/example/template/template.drl"));
		
		ex.expand(kBase, dis, cfl);
		StatefulKnowledgeSession session = kBase.newStatefulKnowledgeSession();
		session.insert(new Item("A", 130, 42, ItemCode.LOCK));

		session.insert(new Item("B", 44, 100, ItemCode.STOCK));
		session.insert(new Item("C", 123, 180, ItemCode.BARREL));
		session.insert(new Item("D", 85, 9, ItemCode.LOCK));
		session.fireAllRules();
	}

}
