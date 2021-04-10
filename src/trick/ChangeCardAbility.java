package trick;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeCardAbility extends Trick {
	private char activateArea;
	private char activateCondition;
	private char conditionType;
	private int conditionCost;
	private int conditionAttackDamage;
	private int conditionAttackRange;
	private int conditionHeart;
	private int conditionSpeed;
	private int cost;
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;
	
	public ChangeCardAbility(String trickparameter) {
		super(trickparameter);
		activateArea = trickParameter.get(1);
	}
}
