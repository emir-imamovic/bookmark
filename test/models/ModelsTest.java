package models;

import java.util.List;

import org.junit.*;

import play.test.WithApplication;
import static org.junit.Assert.*;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
	@Before
	public void setUp() {
		fakeApplication(inMemoryDatabase());
	}

	@Test
	public void testCreate() {
		Bookmark.create("test", "www.bitcamp.ba");
		Bookmark b = Bookmark.find(1);

		assertNotNull(b);
		assertEquals(b.username, "test");
		assertEquals(b.url, "www.bitcamp.ba");
	}

	@Test
	public void testFindNonExisting() {
		Bookmark b = Bookmark.find(100);

		assertNull(b);
	}

	@Test
	public void testDelete() {
		Bookmark.create("test", "www.bitcamp.ba");
		Bookmark.delete(1);
		Bookmark b = Bookmark.find(1);
		assertNull(b);
	}

	@Test
	public void testAll() {
		Bookmark[] all = { new Bookmark("test", "www.bitcamp.ba"),
				new Bookmark("test", "www.pik.ba"),
				new Bookmark("test", "www.ekupon.ba"),
				new Bookmark("nijeTest", "www.tanjir.ba") };
		for (Bookmark b : all) {
			b.save();
		}
		
		assertEquals(3, Bookmark.all("test").size());
		List<Bookmark> base = Bookmark.all("test");
		assertTrue(base.contains(all[0]));
		assertFalse(base.contains(all[3]));
	}
	
}
