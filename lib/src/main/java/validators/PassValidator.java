package validators;

import java.util.List;

public interface PassValidator {

	public enum Reasons {
		MUST_CONTAINT_NUMBER, MUST_CONTAINT_UPPERCASE, MUST_CONTAINT_SYMBOL, MUST_CONTAINT_LOWERCASE, MINIMAL_LENGTH
	}

	public List<Reasons> validate(String s);

	public void disable(Reasons mustContaintSymbol);

}
