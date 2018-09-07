package trapeze.action;

import org.junit.Test;

import trapeze.entity.Person;
import trapeze.entity.Trapeze;

public class Motion {
	@Test
	public void Swing() {
		Person person = new Person();
		person.setWxId(String.valueOf(Math.random() * 1000000));
		Trapeze t = new Trapeze();
		t.add(person);
	}

	@Test
	public void leave() {
		Person person = new Person();
		person.setWxId(String.valueOf(Math.random() * 1000000));
		Trapeze t = new Trapeze();
		// t.add(person);
		t.remove(person);
	}

	public void stop() {

	}

}
