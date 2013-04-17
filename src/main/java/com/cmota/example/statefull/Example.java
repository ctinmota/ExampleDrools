package com.cmota.example.statefull;

import java.util.HashMap;
import java.util.Map;

import org.drools.FactHandle;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.cmota.example.statefull.Fire;
import com.cmota.example.statefull.Room;
import com.cmota.example.statefull.Sprinkler;

public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		KnowledgeBase kbase = getKnowledgeBase();

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

//		noFire(ksession);

		fireExists(ksession);
	}

	private static void noFire(StatefulKnowledgeSession ksession) {
		String[] names = new String[] { "kitchen", "bedroom", "office",
				"livingroom" };

		Map<String, Room> name2room = new HashMap<String, Room>();

		for (String name : names) {
			Room room = new Room(name);
			name2room.put(name, room);
			ksession.insert(room);
			Sprinkler sprinkler = new Sprinkler(room);
			ksession.insert(sprinkler);
		}

		ksession.fireAllRules();
	}

	private static void fireExists(StatefulKnowledgeSession ksession) {

		String[] names = new String[] { "kitchen", "bedroom", "office",
				"livingroom" };

		Map<String, Room> name2room = new HashMap<String, Room>();

		for (String name : names) {
			Room room = new Room(name);
			name2room.put(name, room);
			ksession.insert(room);
			Sprinkler sprinkler = new Sprinkler(room);
			ksession.insert(sprinkler);
		}

		Fire kitchenFire = new Fire(name2room.get("kitchen"));
		Fire officeFire = new Fire(name2room.get("office"));
		
		FactHandle kitchenFireHandle = (FactHandle) ksession.insert(kitchenFire);
		FactHandle officeFireHandle = (FactHandle) ksession.insert(officeFire);

		ksession.fireAllRules();
		
		System.out.println("**************");
		
		ksession.retract( kitchenFireHandle );
		ksession.retract( officeFireHandle );
		
		ksession.fireAllRules();
		
	}

	private static KnowledgeBase getKnowledgeBase() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("fireAlarm.drl",
				Example.class), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		return kbase;
	}

}
