package helpers;

public class Constants {

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Enemies{
		public static final int OCTOROK = 0;
		public static final int BAT = 1;
		public static final int SKELETON = 2;
		public static final int KNIGHT = 3;
		public static final int ALLMIGHTYKNIGHT = 4;

		public static float GetSpeed(int enemyType) {
			switch(enemyType) {
				case OCTOROK:
					return 0.75f;
				case BAT:
					return 1.0f;
				case KNIGHT:
					return 0.55f;
				case SKELETON:
					return 1.10f;
				case ALLMIGHTYKNIGHT:
					return 0.50f;
			}

			return 0;
		}
	}
	
	public static class Tiles{
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}

	public static class Towers {
		public static final int ARCHERY_1 = 0;
		public static final int ARCHERY_2 = 1;
		public static final int ARCHERY_3 = 2;
		public static final int CANNON_1 = 3;
		public static final int CANNON_2 = 4;
		public static final int CANNON_3 = 5;
		public static final int MAGIC_1 = 6;
		public static final int MAGIC_2 = 7;
		public static final int MAGIC_3 = 8;

		public static String GetName(int towerType) {
			switch(towerType) {
				case ARCHERY_1:
					return "Tour d'archers niveau 1";
				case ARCHERY_2:
					return "Tour d'archers niveau 2";
				case ARCHERY_3:
					return "Tour d'archers niveau 3";
				case CANNON_1:
					return "Canon niveau 1";
				case CANNON_2:
					return "Canon niveau 2";
				case CANNON_3:
					return "Canon niveau 3";
				case MAGIC_1:
					return "Tour d'arcane niveau 1";
				case MAGIC_2:
					return "Tour d'arcane niveau 2";
				case MAGIC_3:
					return "Tour d'arcane niveau 3";
			}
			return "";
		}
	}

	public static class Projectile {
		public static final int ARROW = 0;
		public static final int BOMB = 1;
		public static final int FIREBALL = 2;

		public static float GetSpeed(int type) {
			switch(type) {
				case ARROW:
					return 8.0f;
				case BOMB:
					return 6.5f;
				case FIREBALL:
					return 7.5f;
			}
			return 0f;
		}
	}

}
