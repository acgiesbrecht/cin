package com.chortitzer.cin.ui.validators;

import com.chortitzer.cin.utils.Utils;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRules;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.beans.value.ObservableValue;

public class RucValidator extends ObservableRuleBasedValidator {

    public RucValidator(ObservableValue<String> source) {
        addRule(ObservableRules.notEmpty(source), ValidationMessage.error("RUC es mandatorio."));
        addRule(ObservableRules.fromPredicate(source, new Utils().rucPredicate),
                ValidationMessage.warning("RUC invalido."));
    }

}
