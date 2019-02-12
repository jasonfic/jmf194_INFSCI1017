package connecttest;

public class Tester {
	public static void main(String[] args) {
		Genre g = new Genre(2);
		g.setGenreName("punk rock");
		g.setDescription("super fast guitar licks");
		
		Genre g1 = new Genre("classic rock", "your dad loves it");
		
		Genre g2 = new Genre(3);
		System.out.println(g2.getGenreName());
		System.out.println(g2.getDescription());
	}
}
