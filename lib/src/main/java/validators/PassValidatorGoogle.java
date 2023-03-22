package validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class PassValidatorGoogle implements PassValidator {

	static final int MINIMAL_LEN = 8;

	Set<Reasons> disabledReasons = new HashSet<>();

	Pattern patternUpperCase = Pattern.compile("[A-Z]");
	Pattern patternNumber = Pattern.compile("[0-9]");
	Pattern patternLowercase = Pattern.compile("[a-z]");
	Pattern patternSymbol = Pattern.compile("[@#$%&/(){}=?!\\.-]");

	@Override
	public List<Reasons> validate(String s) {
		s = s.trim();
		List<Reasons> reasons = new ArrayList<>();
		reasons.addAll(Arrays.asList(Reasons.values()));

		if (patternUpperCase.matcher(s).find())
			reasons.removeIf(r -> r == Reasons.MUST_CONTAINT_UPPERCASE);

		if (patternLowercase.matcher(s).find())
			reasons.removeIf(r -> r == Reasons.MUST_CONTAINT_LOWERCASE);

		if (patternNumber.matcher(s).find())
			reasons.removeIf(r -> r == Reasons.MUST_CONTAINT_NUMBER);

		if (disabledReasons.contains(Reasons.MUST_CONTAINT_SYMBOL))
			reasons.removeIf(r -> r == Reasons.MUST_CONTAINT_SYMBOL);
		else if (patternSymbol.matcher(s).find())
			reasons.removeIf(r -> r == Reasons.MUST_CONTAINT_SYMBOL);

		if (s.length() >= MINIMAL_LEN)
			reasons.removeIf(r -> r == Reasons.MINIMAL_LENGTH);

		return reasons;
	}

	@Override
	public void disable(Reasons reason) {
		this.disabledReasons.add(reason);
	}

}
